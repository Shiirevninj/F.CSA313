# BUG-09 — API буруу өгөгдөлд HTTP 400 биш HTTP 200 буцааж байна

**Severity:** High  
**Tour:** Saboteur  
**Орчин:** Debian Linux, Firefox 140.10.1esr, Node.js v20.19.2

## Тайлбар

`/api/books` endpoint руу буруу форматтай POST хүсэлт илгээхэд сервер HTTP 400 Bad Request буцаах ёстой боловч HTTP 200 OK хариу өгч байна.

## Дахин гаргах алхам

1. Аппликейшнийг `node app.js` командаар шинээр ажиллуулна.
2. Terminal нээнэ.
3. `/api/books` endpoint руу буруу өгөгдөлтэй POST хүсэлт илгээнэ.
4. Серверээс буцсан HTTP статус болон response body-г шалгана.

## Хүлээгдэж буй үр дүн

Сервер буруу өгөгдлийг хүлээн авахаас татгалзаж, `HTTP/1.1 400 Bad Request` статус буцаах ёстой.

## Бодит үр дүн

Сервер дараах хариуг буцаасан:

```http
HTTP/1.1 200 OK
Content-Type: application/json
Date: Tue, 20 Oct 2026 12:58:05 GMT
Connection: keep-alive
Keep-Alive: timeout=5
Transfer-Encoding: chunked

{"result":"OK"}
```

## Нотолгоо

Бүрэн HTTP хариу:

[evidence/bug-09-curl.txt](../evidence/bug-09-curl.txt)

**Дүгнэлт:** Буруу өгөгдөлд HTTP 400 биш HTTP 200 буцааж байгаа нь API-ийн алдааны боловсруулалтын шаардлагыг зөрчиж байна.