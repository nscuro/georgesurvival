# George Survival
An extemely simple, minimalistic topdown-shooter I wrote back in 2011 while learning Java.
The game utilizes the [slick2d](http://slick.ninjacave.com/) game framework, which is based on [LWJGL](https://www.lwjgl.org/).

Besides the Maven integration and some slight refactoring (mainly changing package names), the original code hasn't been touched. It is not documented and doesn't follow any coding convention, 'cause who needs those anyway amirite.

# Building
```bash
git clone https://github.com/nscuro/georgesurvival.git
cd georgesurvival
mvn clean package
```
Maven will build 2 `jar` files in the `target` directory: `georgesurvival-1.0.jar` and `georgesurvival-1.0-with-dependencies.jar`. You want to use the last one, as the first doesn't contain any third party libraries and thus won't be executable.

# Running
```
mv target/georgesurvival-1.0-with-dependencies.jar ./georgesurvival.jar
java -jar georgesurvival.jar
```

If you acquired a [release](https://github.com/nscuro/georgesurvival/releases) copy of this awesome AAA title, there's already a ready-to-run jar. On Windows and Mac systems you may be able to just double-click the jar, otherwise just open a terminal, `cd` into the root folder and execute `java -jar georgesurvival.jar`.

# Screenshots
![First](https://i.imgur.com/0m4NHiug.png)
![Second](https://i.imgur.com/zZWk7RXg.png)
