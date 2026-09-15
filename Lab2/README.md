# F.CSA313 Lab 2 — Performance Testing with Grafana k6

## 1. Лабораторийн ажлын зорилго

Энэхүү лабораторийн ажлаар Grafana k6 ашиглан веб системийн гүйцэтгэлийг ачааллын үед шалгасан. Туршилтын явцад latency, p90, p95, p99, throughput, error rate болон threshold үзүүлэлтүүдийг хэмжсэн. Мөн ачаалал нэмэгдэх үед системийн гүйцэтгэл хэрхэн өөрчлөгдөж байгааг харьцуулж үзсэн.

**Test target:** `https://test.k6.io`

Туршилтыг зөвхөн сургалтын зориулалттай test server дээр хийсэн.

## 2. Ашигласан хэрэгслүүд

* OS: Debian GNU/Linux 13
* Tool: Grafana k6
* k6 version: `v2.0.0-rc1`
* Test type: HTTP Load / Performance Testing
* Version control: Git / GitHub

## 3. Хэмжсэн үндсэн үзүүлэлтүүд

* **Latency** — HTTP хүсэлтэд хариу өгөх хугацаа
* **p90** — нийт хүсэлтийн 90% нь тухайн утгаас бага latency-тай байсан үзүүлэлт
* **p95** — нийт хүсэлтийн 95% нь тухайн утгаас бага latency-тай байсан үзүүлэлт
* **p99** — нийт хүсэлтийн 99% нь тухайн утгаас бага latency-тай байсан үзүүлэлт
* **Throughput** — секундэд боловсруулсан HTTP хүсэлтийн тоо
* **Error rate** — амжилтгүй болсон HTTP хүсэлтийн хувь
* **Threshold** — performance шаардлага хангаж байгаа эсэхийг автоматаар шалгах нөхцөл

## 4. Baseline Test

Эхний baseline тестийг 5 VU, 30 секундын хугацаатай ажиллуулсан.

| Үзүүлэлт   |     Үр дүн |
| ---------- | ---------: |
| VU         |          5 |
| Duration   |        30s |
| Average    |  216.44 ms |
| p90        |  328.66 ms |
| p95        |  403.10 ms |
| Max        |  451.36 ms |
| Throughput | 6.72 req/s |
| Error rate |         0% |
| Checks     |       100% |

Baseline тестийн бүтэн үр дүн:

`results/run-baseline.txt`

## 5. Fixed Load Test

Ачаалал нэмэгдэх үед системийн үзүүлэлтүүд хэрхэн өөрчлөгдөхийг харахын тулд 5, 30, 100 VU дээр тус бүр 1 минутын турш тест ажиллуулсан.

|  VU |       p90 |       p95 |   Throughput | Error rate |
| --: | --------: | --------: | -----------: | ---------: |
|   5 | 385.86 ms | 456.46 ms |   6.43 req/s |         0% |
|  30 | 348.47 ms | 464.72 ms |  40.22 req/s |         0% |
| 100 | 341.82 ms | 475.05 ms | 131.22 req/s |         0% |

Тестүүдийн бүтэн үр дүн:

* `results/run-05vu.txt`
* `results/run-30vu.txt`
* `results/run-100vu.txt`

Үр дүнгээс харахад VU нэмэгдэхэд throughput их хэмжээгээр өссөн. Харин p95 latency 456.46 ms-ээс 475.05 ms болж бага зэрэг нэмэгдсэн. Бүх fixed-load тестийн error rate 0% байсан. 100 VU үед maximum latency 5.3 секунд хүрсэн нь зарим хүсэлт удаан хариу авсныг харуулж байна.

## 6. Stages Test

Ачааллыг үе шаттайгаар нэмэгдүүлж, дараа нь бууруулахын тулд дараах stages тохиргоог ашигласан.

```text
30s → 5 VU
1m  → 30 VU
30s → 100 VU
30s → 0 VU
```

Нийт stages тестийн үр дүн:

| Үзүүлэлт    |      Үр дүн |
| ----------- | ----------: |
| Max VUs     |         100 |
| p90         |   577.27 ms |
| p95         |   739.16 ms |
| Max latency |      2.75 s |
| Throughput  | 38.19 req/s |
| Error rate  |          0% |
| Checks      |        100% |

Бүтэн үр дүн:

`results/run-stages.txt`

Stages тест нь бүх ачааллын үеийг нэг summary болгон гаргадаг. Тиймээс 5, 30, 100 VU тус бүрийн p95 утгыг fixed-load тестүүдээс авсан.

## 7. p99 Latency Test

Latency-ийн өндөр утгуудыг илүү сайн харахын тулд k6-ийн `p(99)` trend statistic-ийг ашиглаж туршилт хийсэн.

| Үзүүлэлт   |      Үр дүн |
| ---------- | ----------: |
| Average    |   189.04 ms |
| p90        |   284.97 ms |
| p95        |   300.40 ms |
| p99        |   358.47 ms |
| Max        |      1.32 s |
| Throughput | 43.63 req/s |
| Error rate |          0% |

Бүтэн үр дүн:

`results/run-p99.txt`

Энэ p99 үр дүн нь stages configuration ашигласан тусдаа туршилтын нэгтгэсэн summary бөгөөд 5, 30, 100 VU тус бүрийн p99 гэж үзэхгүй.

## 8. Threshold болон SLO Test

Threshold-ийн утгыг шууд таамгаар сонгохын оронд baseline тестийн үр дүнд үндэслэн тодорхойлсон.

Baseline тестийн p95:

```text
403.10 ms
```

Baseline p95-ийн 1.5 дахин утгыг SLO болгон авсан.

```text
403.10 ms × 1.5 = 604.65 ms
```

Иймээс latency-ийн SLO-г:

```text
p95 < 604.65 ms
```

гэж сонгосон. Энэ нь baseline-ийн хэмжилтээс 50%-ийн нэмэлт зөвшөөрөгдөх хугацаа өгч байгаа бөгөөд хэвийн хэлбэлзлийг тооцсон performance threshold болгон ашигласан.

Мөн HTTP request-ийн error rate:

```text
rate < 0.01
```

буюу 1%-иас бага байхаар тохируулсан.

### Threshold PASS

`thresholds.js` файлд дараах threshold-ийг ашигласан.

```javascript
http_req_duration: ['p(95)<604.65'],
http_req_failed: ['rate<0.01'],
```

Үр дүн:

| Үзүүлэлт   |    Үр дүн |
| ---------- | --------: |
| p95        | 381.00 ms |
| SLO        | 604.65 ms |
| Error rate |        0% |
| Threshold  |      PASS |

Бүтэн үр дүн:

`results/run-threshold-pass-baseline.txt`

p95 нь 381 ms гарсан бөгөөд 604.65 ms-ийн SLO-оос бага байсан тул threshold PASS болсон.

### Threshold FAIL

Threshold-ийн ажиллагааг шалгахын тулд зориудаар маш хатуу `p95 < 50 ms` threshold ашиглаж FAIL тест хийсэн.

Үр дүн:

| Үзүүлэлт             |    Үр дүн |
| -------------------- | --------: |
| p95                  | 455.16 ms |
| Threshold            |     50 ms |
| Error rate           |        0% |
| Latency threshold    |      FAIL |
| Error rate threshold |      PASS |

Бүтэн үр дүн:

`results/run-threshold-fail.txt`

p95 нь 455.16 ms буюу 50 ms-ээс их байсан тул k6 threshold зөрчигдөж тест FAIL болсон. Энэ нь threshold тохиргоо зөв ажиллаж байгааг шалгах зорилготой зориудын FAIL тест юм.

## 9. Screenshot

k6-ийн summary гаралтын screenshot-уудыг `screenshots/` хавтсанд оруулсан.

```text
screenshots/
├── baseline.png
├── fixed-load-5vu.png
├── fixed-load-30vu.png
├── fixed-load-100vu.png
├── stages.png
├── threshold-pass.png
└── threshold-fail.png
```

Screenshot-ууд дээр k6 тестийн p90, p95, throughput, error rate, VU болон бусад summary үзүүлэлтүүд харагдана.

## 10. k6 ба JMeter-ийн харьцуулалт

| Үзүүлэлт         | k6                | JMeter                |
| ---------------- | ----------------- | --------------------- |
| Script           | JavaScript        | GUI + JMX             |
| Ажиллуулах       | CLI               | GUI / CLI             |
| Git / CI/CD      | Тохиромжтой       | Боломжтой             |
| Resource usage   | Харьцангуй хөнгөн | Харьцангуй их         |
| Protocol support | HTTP/API төвтэй   | Олон төрлийн protocol |

k6 нь JavaScript дээр тест бичдэг, CLI ашиглан ажиллуулахад хялбар тул Git болон CI/CD орчинд ашиглахад тохиромжтой санагдсан. Харин JMeter нь GUI орчинтой бөгөөд олон төрлийн protocol дэмждэгээрээ давуу талтай.

## 11. Дүгнэлт

Туршилтын үр дүнгээр VU-ийн тоо нэмэгдэхэд throughput 6.43 req/s-ээс 131.22 req/s хүртэл их хэмжээгээр өссөн. Fixed-load тестүүдийн p95 latency 456.46 ms-ээс 475.05 ms болж бага зэрэг нэмэгдсэн. Бүх fixed-load тестийн error rate 0% байсан тул туршилтын үед амжилтгүй хүсэлт гараагүй. 100 VU үед maximum latency 5.3 секунд хүрсэн нь зарим хүсэлтэд өндөр latency гарсныг харуулж байгаа. Stages тестээр нийт ачааллын үед p95 latency 739.16 ms болсон. Энэ нь ачаалал нэмэгдэх үед latency-ийн өндөр утгууд нэмэгдэх боломжтойг харуулсан. Baseline p95 болох 403.10 ms-ээс 1.5 дахин тооцож SLO-г 604.65 ms гэж сонгосон. Baseline-д суурилсан threshold тестээр p95 381 ms гарсан тул SLO шаардлага хангагдсан. Харин зориудын `p95 < 50 ms` threshold нь FAIL болсон бөгөөд энэ нь k6 threshold-ийн ажиллагааг шалгасан үр дүн болсон.

## 12. Result Files

Бүх туршилтын бүтэн output-уудыг `results/` хавтсанд хадгалсан.

```text
results/
├── run-baseline.txt
├── run-05vu.txt
├── run-30vu.txt
├── run-100vu.txt
├── run-stages.txt
├── run-p99.txt
├── run-threshold-pass.txt
├── run-threshold-pass-baseline.txt
└── run-threshold-fail.txt
```

## 13. Run Commands

### Baseline

Baseline тестийг эхний шатанд 5 VU, 30 секундын тохиргоотой ажиллуулсан. Тухайн үед `script.js` нь 5 VU, 30s fixed configuration-тэй байсан.

```bash
k6 run script.js
```

### Fixed Load

5, 30, 100 VU-ийн fixed-load тестүүдийг тус тусад нь дараах командуудаар ажиллуулсан.

```bash
k6 run --vus 5 --duration 1m script.js
k6 run --vus 30 --duration 1m script.js
k6 run --vus 100 --duration 1m script.js
```

> Fixed-load тестүүдийг ажиллуулсны дараа `script.js`-ийг stages configuration болгон өөрчилсөн. Одоогийн `script.js` нь stages тестийн тохиргоог агуулж байгаа.

### Stages

Одоогийн `script.js` ашиглан stages тестийг ажиллуулна.

```bash
k6 run script.js
```

### p99

```bash
k6 run --summary-trend-stats="avg,min,med,max,p(90),p(95),p(99)" script.js
```

### Threshold

```bash
k6 run thresholds.js
```

### Threshold FAIL

Зориудын threshold FAIL тестийн үед `p95 < 50 ms` нөхцөл ашигласан.

```bash
k6 run thresholds-fail.js
```

## 14. Анхаарах зүйл

`https://test.k6.io` нь external test server учраас өөр өөр үед тест ажиллуулахад latency болон throughput-ийн утга бага зэрэг өөрчлөгдөж болно. Энэхүү лабораторийн ажилд авсан хүснэгтүүд болон дүгнэлтүүд нь result файлд хадгалсан тухайн туршилтын үр дүнд үндэслэсэн.

## 15. Repository

GitHub repository:

`https://github.com/Shiirevninj/F.CSA313`
