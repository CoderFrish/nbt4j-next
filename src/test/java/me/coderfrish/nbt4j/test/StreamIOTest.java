package me.coderfrish.nbt4j.test;

import me.coderfrish.nbt4j.NBTStreamUtils;
import me.coderfrish.nbt4j.NBTagCompound;
import me.coderfrish.nbt4j.NBTagElement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class StreamIOTest {
    static void main() throws Exception {
        boolean readable = true;

        if (readable) {
            InputStream is = new FileInputStream("D:\\nbt4j-next\\src\\test\\resources\\archives.nbt");
            NBTagCompound compound = NBTStreamUtils.read(is);
            NBTagElement name = compound.get("name");
            NBTagElement age = compound.get("age");
            NBTagElement test0 = compound.get("test0");

            for (int i : test0.getAsIntArray()) {
                System.out.println(i);
            }

            System.out.println(name.getAsString() + " | " + age.getAsInt());
        }

        if (!readable) {
            NBTagCompound compound = new NBTagCompound();
            compound.addProperty("name", "Frish2021");
            compound.addProperty("age", 13);

            int[] test0 = new int[]{12, 60, 80};
            compound.addProperty("test0", test0);

            long[] test1 = new long[]{1256454545645578787L, 6057584547854356745L, 865454545454540L};
            compound.addProperty("test1", test1);

            byte[] test2 = "test".getBytes(StandardCharsets.UTF_8);
            compound.addProperty("test2", test2);

            OutputStream os = new FileOutputStream("D:\\nbt4j-next\\src\\test\\resources\\archives.nbt");
            NBTStreamUtils.write(os, "archives", compound);
        }
    }
}
