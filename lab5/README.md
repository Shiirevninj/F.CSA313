cat > README.md <<'EOF'
# Лаборатори №5 — API System Testing (Postman & Newman)

**Оюутны нэр:** Шийрэвнинж  
**Оюутны код:** B232270049  
**Хичээл:** F.CSA313

## 1. Зорилго

API системийн бүртгэлийн үйл ажиллагааг Postman ашиглан тестлэх,
Newman ашиглан автоматжуулсан тест ажиллуулах, алдааг илрүүлэх
болон тестийн үр дүнг тайлагнах.

## 2. Ашигласан технологи

| Технологи | Хувилбар |
|---|---|
| Node.js | v20.19.2 |
| Newman | 6.2.2 |
| Postman | Desktop application |
| API | Node.js HTTP server |

## 3. API endpoint-үүд

| Method | Endpoint | Үүрэг |
|---|---|---|
| PUT | /students/:studentID | Оюутны мэдээлэл үүсгэх, шинэчлэх |
| PUT | /courses/:courseID | Хичээлийн мэдээлэл үүсгэх, шинэчлэх |
| POST | /registrations | Хичээлийн бүртгэлийн хүсэлт боловсруулах |

Base URL: `http://localhost:3000`

Postman collection-д `{{baseUrl}}` хувьсагч ашигласан.

## 4. Сонголт ба төлөөлөх утгын шинжилгээ

| Оролтын нөхцөл | Эквивалент бүлэг | Төлөөлөх утга | Хүлээгдэх үр дүн |
|---|---|---|---|
| Оюутан идэвхтэй, урьдчилсан нөхцөл хангагдсан | Зөв | active, CS201 судалсан | OK |
| Оюутан идэвхгүй | Буруу | inactive | ERROR_INACTIVE_STUDENT |
| Оюутан бүртгэлгүй | Буруу | B230000003 | ERROR_NO_STUDENT |
| Хичээл бүртгэлгүй | Буруу | CS999 | ERROR_NO_COURSE |
| Урьдчилсан хичээл судлаагүй | Буруу | coursesTaken: [] | ERROR_PREREQUISITES |
| Урьдчилсан нөхцөлгүй хичээл | Зөв | prerequisites: [] | OK |
| Заавал байх талбар дутуу | Буруу | studentID байхгүй | ERROR_BAD_REQUEST |
| Хоёр алдаа зэрэг үүссэн | Буруу | inactive + CS998 | ERROR_INACTIVE_STUDENT |

## 5. Тестийн спецификац

| № | Тестийн нэр | Оролт, урьдчилсан нөхцөл | HTTP status | Хүлээгдэх result |
|---|---|---|---|---|
| 1 | Happy Path Registration | active, CS201 судалсан, CS313 | 201 | OK |
| 2 | Inactive Student Registration | inactive, CS314 | 200 | ERROR_INACTIVE_STUDENT |
| 3 | No Student Registration | Бүртгэлгүй оюутан, CS315 | 200 | ERROR_NO_STUDENT |
| 4 | No Course Registration | active оюутан, CS999 | 200 | ERROR_NO_COURSE |
| 5 | Missing Prerequisite Registration | CS201 судлаагүй, CS316 | 200 | ERROR_PREREQUISITES |
| 6 | No Prerequisite Registration | active, CS317, prerequisites: [] | 201 | OK |
| 7 | Missing StudentID Registration | studentID талбар байхгүй | 400 | ERROR_BAD_REQUEST |
| 8 | Double Error Registration | inactive оюутан, CS998 | 200 | ERROR_INACTIVE_STUDENT |

Тест бүрд шаардлагатай оюутан болон хичээлийн өгөгдлийг
PUT хүсэлтээр бэлтгэж, POST /registrations хүсэлтээр шалгасан.

Нийт 20 request, 8 test script, 17 assertion ашигласан.

Assertion-уудаар HTTP status болон JSON response-ийн `result`
утгыг шалгасан. Урьдчилсан нөхцөлийн тестэд `missing` массив
`CS201` утгыг агуулж байгаа эсэхийг нэмэлтээр шалгасан.

Амжилттай бүртгэлийн `registrationID`-ийн тогтмол утгад
тулгуурласан assertion ашиглаагүй.

## 6. Newman PASS тест

API сервер ажиллаж байх үед:

```bash
newman run lab05-collection.json
```
## 7. Newman FAIL тест

`lab05-collection-fail.json` файлд Happy Path тестийн хүлээгдэх HTTP статусыг 201-ээс 400 болгон санаатай өөрчилсөн.

Ажиллуулах команд:

    newman run lab05-collection-fail.json

Бодит үр дүн:
- Requests: 20
- Assertions: 17
- Failed assertions: 1
- Exit code: 1

Илэрсэн алдаа:

`expected response to have status code 400 but got 201`

Энэ нь API-ийн алдаа биш, санаатай буруу тохируулсан oracle-ийг Newman илрүүлсэн үр дүн юм.

Дэлгэрэнгүй тайлан: `results/newman-fail.txt`

## 8. Newman DOWN тест

API серверийг зогсоосны дараа үндсэн collection-ийг ажиллуулсан.

Ажиллуулах команд:

    newman run lab05-collection.json

Бодит үр дүн:
- Exit code: 1
- Холболтын алдаа: `ECONNREFUSED 127.0.0.1:3000`
- HTTP response ирээгүй тул assertion болон JSON parsing алдаа үүссэн.

FAIL тестэд сервер хариу өгсөн боловч oracle-ийн хүлээгдэх утга буруу байсан. Харин DOWN тестэд сервертэй холбогдох боломжгүй байсан.

Дэлгэрэнгүй тайлан: `results/newman-down.txt`

## 9. Ажиллуулах заавар

Эхний Terminal дээр API серверийг асаана:

    node server.js

Хоёр дахь Terminal дээр PASS тест ажиллуулна:

    newman run lab05-collection.json

FAIL тест ажиллуулах:

    newman run lab05-collection-fail.json

DOWN тестийг шалгахдаа серверийг Ctrl+C ашиглан зогсоож, үндсэн collection-ийг дахин ажиллуулна.

## 10. Дүгнэлт

Энэхүү лабораторийн ажлаар API системийн тестийг Postman болон Newman ашиглан гүйцэтгэлээ.
Оюутны төлөв, хичээлийн бүртгэл болон урьдчилсан нөхцөлтэй холбоотой найман тестийн сценарий боловсруулсан.
Тестийн өгөгдлийг PUT хүсэлтээр бэлтгэж, POST хүсэлтээр бүртгэлийн үр дүнг шалгасан.
Postman collection-д нийт 20 request болон 17 assertion ашигласан.
Newman PASS туршилтаар 17 assertion бүгд амжилттай биелсэн.
FAIL туршилтаар хүлээгдэх HTTP статусыг санаатай өөрчилж, нэг assertion алдаатай болохыг баталсан.
DOWN туршилтаар серверийг зогсоож, ECONNREFUSED холболтын алдааг ажигласан.
Эдгээр туршилт нь бизнес логикийн алдаа болон холболтын алдааны ялгааг ойлгоход тусалсан.
Цаашид API тестүүдийг автоматжуулсан шалгалтын үйл явцад ашиглах боломжтой.