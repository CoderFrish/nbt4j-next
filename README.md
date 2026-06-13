# nbt4j-next

一个NBT读写库，前nbt4j的重置版本，致力于编写拥有更好的性能以及拓展的nbt库。

预计会实现的功能: 
1. [x] NBT文件的IO读写 (本体)
2. [x] String NBT的解析 (nbt4j-extra-snbt - 初步完成)
3. [ ] 针对Kotlin的语法优化 (nbt4j-language-kotlin)

## 如何引入

Maven
```xml
<dependency>
    <groupId>io.github.coderfrish</groupId>
    <artifactId>nbt4j-next</artifactId>
    <version>1.0.0</version>
</dependency>
```

Gradle
```groovy
implementation("io.github.coderfrish:nbt4j-next:1.0.0")
```

关于String NBT拓展的引入，需要在引入了核心库后才能使用

Maven
```xml
<dependency>
    <groupId>io.github.coderfrish</groupId>
    <artifactId>nbt4j-extra-snbt</artifactId>
    <version>1.0.0.beta-1</version>
</dependency>
```

Gradle
```groovy
implementation("io.github.coderfrish:nbt4j-extra-snbt:1.0.0.beta-1")
```
