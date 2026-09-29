# Лаборатори №4: Нэгжийн тестийн эхлэл — JUnit 5

**Оюутны нэр:** Шийрэвнинж  
**Оюутны код:** B232270049

## Орчны мэдээлэл

### Java version

```text
openjdk version "21.0.11" 2026-04-21
OpenJDK Runtime Environment (build 21.0.11+10-1-deb13u2-Debian)
OpenJDK 64-Bit Server VM (build 21.0.11+10-1-deb13u2-Debian, mixed mode, sharing)
```

### Maven version

```text
Apache Maven 3.9.9
Maven home: /usr/share/maven
Java version: 21.0.11, vendor: Debian, runtime: /usr/lib/jvm/java-21-openjdk-amd64
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "6.12.85+deb13-amd64", arch: "amd64", family: "unix"
```

## Тестийн мэдээлэл

- Тестийн методын тоо: 14
- Tests run: 25
- Failures: 0
- Errors: 0
- Skipped: 0
- Үр дүн: BUILD SUCCESS

## Mutation test

`GradeCalculator` классын `letterGrade()` метод дахь:

`score >= 90`

нөхцөлийг зориуд:

`score > 90`

болгон өөрчилж mutation test хийсэн.

Mutation test-ийн үед `ninetyIsExactlyA` болон `letterGradeBoundaries` тестүүд амжилтгүй болсон.

Үр дүн:

- Tests run: 25
- Failures: 2
- Errors: 0
- Skipped: 0
- Үр дүн: BUILD FAILURE
- Алдаа: expected `<A>` but was `<B>`

Mutation test-ийн дараа нөхцөлийг `score >= 90` болгон буцааж зассан бөгөөд бүх тест дахин амжилттай ажилласан.

## Дүгнэлт

Энэ лабораторийн ажлаар JUnit 5 ашиглан Java класст нэгжийн тест бичиж ажиллууллаа. GradeCalculator классын letterGrade болон totalScore методуудын хэвийн болон буруу оролтуудыг тестээр шалгасан. Мөн 90, 89.99, 60, 59.99, 0, 100 гэсэн хязгаарын утгуудыг шалгаж, онооны зааг дээр гарч болох алдааг илрүүлэх тест бичсэн. letterGrade болон totalScore методуудад parameterized test болон CsvSource ашиглан олон төрлийн оролтыг шалгасан. Буруу оролтын үед IllegalArgumentException үүсэж байгаа эсэхийг assertThrows ашиглан шалгасан. Mutation test хийхдээ score >= 90 нөхцөлийг score > 90 болгон өөрчлөхөд 90 оноог шалгасан хоёр тест алдааг амжилттай илрүүлсэн. Энэ нь зөвхөн тест PASS болохоос гадна хязгаарын утгуудыг зөв сонгож тестлэх нь чухал гэдгийг харуулсан.