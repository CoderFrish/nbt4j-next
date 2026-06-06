package me.coderfrish.nbt4j.test;

import me.coderfrish.nbt4j.NBTStreamUtils;
import me.coderfrish.nbt4j.NBTagCompound;
import me.coderfrish.nbt4j.NBTagElement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamIOTest {
    static void main() throws Exception {
        InputStream is = new FileInputStream("D:\\nbt4j-next\\src\\test\\resources\\archives.nbt");
        NBTagCompound compound0 = NBTStreamUtils.read(is);
        NBTagElement name = compound0.get("name");
        NBTagElement age = compound0.get("age");
        System.out.println(name.getAsString() + " | " + age.getAsInt());

        if (true) return;

        NBTagCompound compound1 = new NBTagCompound();
        compound1.addProperty("name", "Frish2021");
        compound1.addProperty("age", 13);

        OutputStream os = new FileOutputStream("D:\\nbt4j-next\\src\\test\\resources\\archives.nbt");
        NBTStreamUtils.write(os, "archives", compound1);
    }
}
