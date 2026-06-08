/**
 * @author CoderFrish
 * <p>
 * This is main package of nbt4j.
 * nbt4j-next is nbt4j rewrote,
 * OpenSource:
 * https://github.com/CoderFrish/nbt4j-next
 * Origin nbt4j Source:
 * https://github.com/FrishArchives/nbt4j
 * How to use:
 * <p>
 * Write File
 * <pre>
 *     <code>
 *         void main() {
 *             OutputStream os = new FileOutputStream("xxx");
 *             NBTagCompound compound = new NBTagCompound();
 *
 *             compound.addProperty("test", "Hello World!!");
 *             NBTStreamUtils.write(os, compound);
 *         }
 *     </code>
 * </pre>
 * Read File
 * <pre>
 *     <code>
 *         void main() {
 *             InputStream is = new FileInputStream("xxx");
 *             NBTagCompound c = NBTStreamUtils.read(is);
 *
 *             NBTagElement test_element = c.get("test");
 *             String test = test_element.getAsString();
 *             System.out.println(test);
 *         }
 *     </code>
 * </pre>
 */
package me.coderfrish.nbt4j;
