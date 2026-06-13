package me.coderfrish.nbt4j.snbt;

import me.coderfrish.nbt4j.NBTagCompound;

import java.util.regex.Pattern;

public class NBTStringReader extends BaseStringReader {
    private static final Pattern DOUBLE_PATTERN_NOSUFFIX = Pattern.compile("[-+]?(?:[0-9]+[.]|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?", Pattern.CASE_INSENSITIVE);
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?d", Pattern.CASE_INSENSITIVE);
    private static final Pattern FLOAT_PATTERN = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?f", Pattern.CASE_INSENSITIVE);
    private static final Pattern BYTE_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)b", Pattern.CASE_INSENSITIVE);
    private static final Pattern LONG_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)l", Pattern.CASE_INSENSITIVE);
    private static final Pattern SHORT_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)s", Pattern.CASE_INSENSITIVE);
    private static final Pattern INT_PATTERN = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)");

    private static final Pattern NUMBER_REGEX = Pattern.compile("[0-9-+.lsbdf]", Pattern.CASE_INSENSITIVE);
    private static final Pattern UNQUOTED_STRING_REGEX = Pattern.compile("[0-9a-z._+-]", Pattern.CASE_INSENSITIVE);
//    private static final Pattern UNQUOTED_START_REGEX = Pattern.compile("[a-z_]", Pattern.CASE_INSENSITIVE);
    private static final Pattern NUMBER_START_REGEX = Pattern.compile("[0-9-+.]");

    public NBTStringReader(String str) {
        super(str);
    }

    public NBTagCompound read() {
        return this.readCompound();
    }

    NBTagCompound readCompound() {
        NBTagCompound compound = new NBTagCompound();

        advance();
        while (canRead() && peek() != '}') {
            skipWhitespace();
            String key = readString();
            advance();
            skipWhitespace();
            this.encode(compound, key);
            advance();
            skipWhitespace();
        }
        advance();

        return compound;
    }

    void encode(NBTagCompound compound, String key) {
        if (peek() == '"') {
            compound.addProperty(key, readString());
        }

        if (peek() == '{') {
            compound.addProperty(key, readCompound());
        }

        if (match(NUMBER_START_REGEX)) {
            String number = readNumber();

            if (FLOAT_PATTERN.matcher(number).matches()) {
                compound.addProperty(key, Float.parseFloat(number.substring(0, number.length() - 1)));
            }

            if (BYTE_PATTERN.matcher(number).matches()) {
                compound.addProperty(key, Byte.parseByte(number.substring(0, number.length() - 1)));
            }

            if (LONG_PATTERN.matcher(number).matches()) {
                compound.addProperty(key, Long.parseLong(number.substring(0, number.length() - 1)));
            }

            if (SHORT_PATTERN.matcher(number).matches()) {
                compound.addProperty(key, Short.parseShort(number.substring(0, number.length() - 1)));
            }

            if (INT_PATTERN.matcher(number).matches()) {
                compound.addProperty(key, Integer.parseInt(number));
            }

            if (DOUBLE_PATTERN.matcher(number).matches()) {
                compound.addProperty(key, Double.parseDouble(number.substring(0, number.length() - 1)));
            }

            if (DOUBLE_PATTERN_NOSUFFIX.matcher(number).matches()) {
                compound.addProperty(key, Double.parseDouble(number));
            }

            if ("true".equalsIgnoreCase(number)) {
                compound.addProperty(key, (byte) 1);
            }

            if ("false".equalsIgnoreCase(number)) {
                compound.addProperty(key, (byte) 0);
            }
        }
    }

    String readString() {
        if (peek() == '"') {
            return readQuotedString();
        }

        return readUnquotedString();
    }

    String readUnquotedString() {
        StringBuilder builder = new StringBuilder();
        while (match(UNQUOTED_STRING_REGEX)) {
            builder.append(peek());
            advance();
        }
        return builder.toString();
    }

    String readQuotedString() {
        advance();
        StringBuilder builder = new StringBuilder();
        while (peek() != '"') {
            builder.append(peek());
            advance();
        }
        advance();
        return builder.toString();
    }

    String readNumber() {
        StringBuilder builder = new StringBuilder();
        while (match(NUMBER_REGEX)) {
            builder.append(peek());
            advance();
        }

        return builder.toString();
    }

    void skipWhitespace() {
        while (Character.isWhitespace(peek()) && canRead()) {
            advance();
        }
    }

    boolean match(Pattern regex) {
        return regex.matcher(String.valueOf(peek())).matches();
    }
}
