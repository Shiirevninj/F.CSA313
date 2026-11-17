
# Лаборатори №10 — Өгөгдлийн урсгалын тест

**Хичээл:** F.CSA313 
**Оюутан:** Шийрэвнинж  
**Оюутны код:** B232270049


## Ашигласан орчин

### Java хувилбар

```text
openjdk version "21.0.13" 2026-10-20
OpenJDK Runtime Environment (build 21.0.13+9-1-deb13u2-Debian)
OpenJDK 64-Bit Server VM (build 21.0.13+9-1-deb13u2-Debian, mixed mode, sharing)
```

### Maven хувилбар

```text
Apache Maven 3.9.9
Maven home: /usr/share/maven
Java version: 21.0.13, vendor: Debian, runtime: /usr/lib/jvm/java-21-openjdk-amd64
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "6.12.117+deb13-amd64", arch: "amd64", family: "unix"
```

## A. gcd() функцийн DU анализ

### A1. Definition ба Use

- Definition (def): Хувьсагчид утга оноох үйлдэл.
- c-use: Тооцоолол эсвэл return-д хувьсагч ашиглах.
- p-use: Нөхцөл шалгахад хувьсагч ашиглах.
- Def-clear path: Def-ээс use хүртэл тухайн хувьсагч дахин тодорхойлогдоогүй зам.

### A2. DU хосууд

| Хувьсагч | Def мөр | Use мөр | Төрөл | Def-clear зам | Тестийн ID | Хамрагдсан |
|---|---|---|---|---|---|---|
| x | 13 | 16 | c-use | 13→15(T)→16 | gcdTwoIterations | Тийм |
| x | 13 | 20 | c-use | 13→15(F)→20 | gcdZeroIterations | Тийм |
| x | 17 | 16 | c-use | 17→18→15(T)→16 | gcdTwoIterations | Тийм |
| x | 17 | 20 | c-use | 17→18→15(F)→20 | gcdOneIteration | Тийм |
| y | 13 | 15(T) | p-use | 13→15(T) | gcdOneIteration | Тийм |
| y | 13 | 15(F) | p-use | 13→15(F) | gcdZeroIterations | Тийм |
| y | 13 | 16 | c-use | 13→15(T)→16 | gcdTwoIterations | Тийм |
| y | 13 | 17 | c-use | 13→15(T)→16→17 | gcdTwoIterations | Тийм |
| y | 18 | 15(T) | p-use | 18→15(T) | gcdTwoIterations | Тийм |
| y | 18 | 15(F) | p-use | 18→15(F) | gcdOneIteration | Тийм |
| y | 18 | 16 | c-use | 18→15(T)→16 | gcdTwoIterations | Тийм |
| y | 18 | 17 | c-use | 18→15(T)→16→17 | gcdTwoIterations | Тийм |
| tmp | 16 | 18 | c-use | 16→17→18 | gcdOneIteration | Тийм |

### A3. JUnit тестүүд

| Тест | Оролт | Хүлээгдэх үр дүн |
|---|---|---|
| gcdZeroIterations | gcd(7,0) | 7 |
| gcdOneIteration | gcd(0,5) | 5 |
| gcdTwoIterations | gcd(12,8) | 4 |

`gcd(7,0)` нь давталтгүй замыг шалгана.

`gcd(12,8)` нь хоёр давталт гүйцэтгэж, өмнөх давталтад тодорхойлогдсон хувьсагч дараагийн давталтад ашиглагдах DU хосуудыг хамарна.

### A4. Тестийн үр дүн

- Tests run: 3
- Failures: 0
- Errors: 0
- Skipped: 0
- BUILD SUCCESS

JaCoCo-ийн одоогийн үр дүн:

- Branch covered: 2/16
- Line covered: 5/24

Эдгээр нь зөвхөн gcd() тестүүд ажилласан үеийн бүх классын хамралт юм. bonus() тестүүдийг нэмсний дараа өснө.
