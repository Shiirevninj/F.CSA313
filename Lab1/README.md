# Лаборатори №1 — UI автомат тест (Playwright)

## Оюутны мэдээлэл

* **Нэр:** [Х.Шийрэвнинж]
* **Оюутны код:** [B232270049]
* **Хичээл:** F.CSA313 — Программ хангамжийн чанарын баталгаа ба туршилт
* **Лабораторийн нэр:** Playwright

## 1. Лабораторийн зорилго

Энэ лабораторийн ажлаар Playwright ашиглаж веб дээр хийдэг үйлдлүүдийг автомат тест болгон бичиж үзсэн. Тест хийхдээ SauceDemo сайтыг сонгосон. Эхлээд Playwright төслөө тохируулж, дараа нь login хийх болон бүтээгдэхүүн сагслах үйлдлүүд дээр тест бичсэн. Мөн буруу password оруулсан үед систем зөв алдаа харуулж байгаа эсэхийг шалгасан. Үүнээс гадна Codegen ашиглаж код автоматаар үүсгэх, Trace Viewer ашиглаж тестийн алдааг шалгах үйлдлүүдийг туршиж үзсэн.
## 2. Ашигласан технологи

* Playwright
* TypeScript
* Node.js
* npm
* IntelliJ IDEA
* Git / GitHub
* SauceDemo

## 3. Хийсэн тестүүд

### Тест 1 — Амжилттай нэвтрэх

Зөв username болон password ашиглан SauceDemo сайтад амжилттай нэвтэрч байгаа эсэхийг шалгасан.

Ашигласан хэрэглэгч:

* Username: `standard_user`
* Password: `secret_sauce`

Login хийсний дараа Products хэсэг гарч ирсэн эсэх болон URL inventory.html болсон эсэхийг шалгасан. Тестийн төгсгөлд Logout хийсэн.

### Тест 2 — Буруу нууц үгээр нэвтрэх

Буруу password оруулсан үед систем нэвтрүүлэхгүй, алдааны message харуулж байгаа эсэхийг шалгасан.
* Username: `standard_user`
* Password: `wrong_password`

Дараах алдааны message гарсан эсэхийг шалгасан:
`Epic sadface: Username and password do not match any user in this service`

### Тест 3 — Бараа сагслах

Зөв мэдээллээр login хийсний дараа нэг бүтээгдэхүүнийг сагсанд нэмсэн. Сагсны тэмдэг дээр 1 гэсэн тоо гарч байгаа эсэхийг шалгасан. Тестийн төгсгөлд Logout хийсэн.

## 4. Locator ашигласан байдал

Тест бичихдээ Playwright-ийн ойлгомжтой locator-уудыг ашигласан.

Жишээ нь:
 
```ts
page.getByPlaceholder('Username')
page.getByPlaceholder('Password')
page.getByRole('button', { name: 'Login' })
page.getByRole('button', { name: 'Open Menu' })
page.getByRole('link', { name: 'Logout' })
page.getByText('Products', { exact: true })
page.getByTestId('shopping-cart-badge')
```

Мөн `getByTestId()` ашиглахын тулд `playwright.config.ts` файлд дараах тохиргоог хийсэн.

`testIdAttribute: 'data-test'`

XPath ашиглаагүй. Учир нь `getByRole()`, `getByText()`, `getByPlaceholder()` зэрэг locator-ууд нь илүү ойлгомжтой, уншихад хялбар байсан.

## 5. Codegen

Playwright Codegen ашиглан веб дээр хийсэн үйлдлүүдийг автоматаар код болгон үүсгэж үзсэн.
```
await page.locator('[data-test="username"]').fill('standard_user');
await page.locator('[data-test="password"]').fill('secret_sauce');
await page.locator('[data-test="login-button"]').click();
```
Codegen нь тестийн эхний кодыг хурдан үүсгэхэд хэрэгтэй байсан. Гэхдээ зарим шаардлагагүй үйлдлүүдийг мөн оруулдаг тул үүссэн кодыг шалгаж цэвэрлэх шаардлагатай байсан.

## 6. Trace Viewer
Тестийн алдааг шалгахын тулд Trace Viewer ашигласан.

`npx playwright test tests/mytest.spec.ts --project=chromium --trace on`

Нэг assertion-ийг зориудаар буруу болгож тестийг fail хийсэн. Дараа нь trace файлыг нээж, аль алхам дээр алдаа гарсныг шалгасан. Алдааг зассаны дараа тестээ дахин ажиллуулсан.

## 7. Test Isolation
Тестүүдийг бие биенээсээ хамааралгүй ажиллахаар бичсэн. Тест бүр шаардлагатай үедээ өөрөө login хийдэг. Мөн login хийсэн тестүүдийн төгсгөлд Logout хийсэн тул нэг тестийн session дараагийн тестэд нөлөөлөхгүй.

## 8. Selenium-тэй харьцуулалт
Playwright болон Selenium нь хоёулаа веб автомат тест хийхэд ашиглагддаг. Selenium нь олон жил ашиглагдсан бөгөөд олон programming language дэмждэг. Харин Playwright нь Chromium, Firefox, WebKit зэрэг browser-уудыг дэмждэг. Playwright-ийн `getByRole()`, `getByText()` зэрэг locator-уудыг ашиглахад код ойлгоход хялбар санагдсан. Мөн auto-waiting хийдэг тул ихэнх тохиолдолд гараар wait хийх шаардлагагүй байсан. Trace Viewer ашиглан тестийн алдааг шалгахад мөн хялбар байсан. Энэ лабораторийн ажлын хүрээнд Playwright ашиглах нь надад ойлгомжтой, хялбар санагдсан.

## 9. Дүгнэлт

Энэ лабораторийн ажлаар Playwright ашиглан SauceDemo сайт дээр UI автомат тест бичиж үзсэн. Login хийх, буруу password шалгах, бүтээгдэхүүн сагслах гэсэн гурван тест хийсэн.

Мөн Playwright-ийн locator-уудыг ашиглах, XPath хэрэглэхгүйгээр тест бичих, Codegen-ээр код үүсгэх, Trace Viewer ашиглан алдаа шалгах талаар туршлага авсан.

## 10. Тестийн үр дүн

Chromium, Firefox, WebKit browser дээр tests/mytest.spec.ts файлын 3 тестийг ажиллуулж шалгасан.
Тестийн үр дүн:
- Амжилттай нэвтрэх — PASSED
- Буруу нууц үгээр нэвтрэх — PASSED
- Нэвтэрсний дараа бараа сагслах — PASSED

Нийт: **3 passed**

## 11. Evidence файлууд

Лабораторийн ажлын нэмэлт evidence-үүдийг `docs` хавтсанд хадгалсан.

```text
docs/
├── add-to-cart-trace.zip
├── codegen.ts
├── failed-login-trace.zip
├── failed-trace.zip
└── successful-login-trace.zip

```

`codegen.ts` нь Playwright Codegen-ээр үүсгэсэн кодын жишээ бөгөөд `failed-trace.zip` нь зориудаар fail хийсэн тестийн Trace Viewer evidence юм.

## 12. Тест ажиллуулах

Dependencies суулгах:

`npm install`

Бүх тестийг ажиллуулах:

`npx playwright test`

Лабораторийн тестүүдийг Chromium дээр ажиллуулах:

`npx playwright test tests/mytest.spec.ts --project=chromium`

Trace-тэй ажиллуулах:

`npx playwright test tests/mytest.spec.ts --project=chromium --trace on`
