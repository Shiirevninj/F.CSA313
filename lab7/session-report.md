# Session Report

Тестер: Шийрэвнинж
Огноо: 2026-10-20
Эхэлсэн цаг: 2026-10-20 20:28:02 +0800
Дууссан цаг: 2026-10-20 21:28:05 +0800
Mission: Уншлагын жагсаалт аппын чанарыг шинжлэх
Charter: charter.md

## Тэмдэглэл

20:28 - buh talbariig ni hooson uldeegeed nemeh darav

bodit ur dun - hooson nemegdej baina. bug-01

20:31 - garchig, zohiolch hooson harin on deer 2020 on gej oruulav.

ur dun - zuvhun on baigaa heseg deer 2020 on haragdaad hooson orhison heseg ni hooson heveeree nemegdsen. bug-01

20:33 - garchig test ,zohiolch Bold, on deer 999 on gej bicheed nom nemeh deer darav.

ur dun - Bold gesen zohiolchtoi test gesen nertei 999 on gesen nom nemegdsen baina. bug-02

20:35 - garchig test, zohiolch Bold, on deer 2201 on gej bicheed nom nemeh geed darav.

ur dun -Bold gesen zohiolchdo test gesen nertei 2201 on gesen nom nemegdsen baina. bug-02

20:37 - garchig test, zohiolch Bold, on deer butarhai buyu 1999.5 gesen on oruulav.

ur dun - Bold gesen zohiolchtoi test gesen nertai 1999.5 gesen butarhai ontoi nom nemegdsen baina. bug-02

20:39 - test book, test author, 2020 gesen ugugdluur 2 udaa nom nemj uzev.

ur dun - 2 udaa yg ijilhen nom nemegdej baina. bug-03 

20:43 - ui heseg deer ustgah tovchnii ungu ni ulaan bish nogoon ungutei bain. bug-04

20:44 - ui heseg deer oruulsan on uud ni 1,971 geh met too helbereer haragdaj baina.bug-05

20:46 - urt nertei buyu "Програм хангамжийн инженерчлэлийн үндсэн ойлголт, системийн шинжилгээ, зохиомж, хөгжүүлэлт, тестчилэл болон чанарын баталгаажуулалтын практик гарын авлага" gesen nertei nom nemsen.

ur dun - husnegt maani evdersen tuhain nomnii garchigiin hemjeend aragshaa tulsan baina. bug-06

20:47 - say hurtleh niit testiin hureend 11 nom nemsen baih uyd niit nomiin too 12 gej haragdaj baina. bug-07

20:49 - haih heseg deer "clean" gej bicheed haih tovch darsan.

ur dun - Clean Code gesen nertei nom oldsongui. bug-08

20:50 - haih heseg deer "martin" gej bciheed haih tovch darsan.

ur dun - C.Martin gesen zohiolchtoi nom oldsongui. bug-08

20:52 - neg nom ustgaj uzeed tooluuriig shalgasan.

ur dun - neg nom ustgah uyd niit nomiin too zuv hasagdaj baigaach husnegtiin muriin toogoor toolood baih shig baina.bug-07

20:59 - buruu ugugdultei POST huseltiig /api/books endpointruu ilgeesen.

ur dun - HTTP/1.1 200 ok statustai bolon {"result":"OK"} hariu butsaasan.
huleegdsen ur dun - HTTP/1.1 400 Bad request

bug-09 - API buruu ugugdliig zuvshuurch bn.

bug iin todoruulga:

bug-01: zaaval bugluh talbariin batalgaajuulalt ajillahgui baina.

bug-02: Nomiin oni batalgaajuulalt ajillahgui.

bug-03: Davhardsan nom nemegdej baina.

bug-04: Ustgah tovchni ungu ulaan bish nogoon ungutei baina.

bug-05: Nomiin on buruu formattai haragdah.

bug-06: Urt garchig husnegtiin bairlaliig evdej baina .

bug-07: Niit nomiin tooluur zuruutei baina.

bug-08: Garchig, zohiolchoor haih uyd ajillahgui baina zaaval buten bichuulj baij garj irj bn.

bug-09: API buruu ugugduld HTTP 400 bish HTTP 200 butsaj baina.

## Саадууд

Тестийн явцад техникийн болон зохион байгуулалтын саад гараагүй.

## Цагийн задаргаа
- Setup: 5 минут  (8.3%)
- Тест дизайн ба гүйцэтгэл: 29 минут (48.3%)
- Согог судлах: 26 минут (48.3%)

Нийт: 60 минут 3 секунд (100%)