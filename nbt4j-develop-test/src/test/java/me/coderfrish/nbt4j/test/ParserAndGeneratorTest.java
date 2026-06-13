package me.coderfrish.nbt4j.test;

import me.coderfrish.nbt4j.NBTagCompound;
import me.coderfrish.nbt4j.snbt.NBTStringReader;
import org.junit.jupiter.api.Test;

public class ParserAndGeneratorTest {
    @Test
    public void test() {
        String snbt = """
                {
                    test: "Hello World!!",
                    name: "CoderFrish",
                    fsdfds: "dsfdsfdsfdsf",
                    "545fdsfsdfdsf": "sdfsdfsdfsddd",
                    other: {
                        dfsf: "dsfsdfsd",
                        ffaa: "ssss"
                    },
                    age: 15,
                    id: 56476545645646L,
                    short: 45S,
                    byte: 12B,
                    f: 0.5F,
                    D, 6.8D
                }
                """;
        NBTStringReader reader = new NBTStringReader(snbt);
        NBTagCompound read = reader.read();

        System.out.println(read.get("age").getAsInt());
        System.out.println(read.get("id").getAsLong());
        System.out.println(read.get("short").getAsShort());
        System.out.println(read.get("byte").getAsByte());
        System.out.println(read.get("f").getAsFloat());
        System.out.println(read.get("D").getAsDouble());
    }
}
