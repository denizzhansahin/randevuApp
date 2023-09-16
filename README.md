# RandevuApp

<div align="center">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/032bfd1d-e822-4b8d-acb8-6899c3eaa680" width="33%">
</div>


Bu uygulama Kotlin ve Firebase ile Android cihazlar için geliştirilmiştir. Bu uygulamanın amacı randevu hizmetlerini tek bir çatı altında yönetmektir.
Uygulamanın kullanımı sadece yetkili kişiler üzerinde gerçekleşmektedir. Bu kişiler yönetici ve randevu alan kullanıcılar olarak belirlenmiştir. Bu kullanıcıların tüm yetki tanımları Firebase Firestore üzerinde kayıtlıdır.
Firebase Authentication hizmeti ile kullanıcıların giriş için gerekli bilgileri alınır ve oturum açma işlemi gerçekleşir. Firebase Firestore ise kullancıların yetki tanımlarının olduğu bilgileri tutmaktadır ve kullanıcıların girişlerine göre tanımlı olan yetki bilgisi için ilgili grafik araryüz açılmaktadır.
Yönetici kullancını grafik arayüzü ilgili takvim günündeki her bir kullanıcının randevu aldığı saat bilgisi, kullanıcı email bilgisi ile eşleşen isim bilgisi gözükmektedir. Yönetici kullanıcı isterse bu randevuları iptal etmek seçeneğini kullanabilir. Yönetici kullanıcı ayrıca tüm aktif olan randevuların bilgisi hakkında bilgi alabilmektedir. Bunun için açılacak olan ekranda ise her bir kullanıcını email ile eşleşen ismi, randevu tarihi, randevu saati yer almaktadır. Yönetici bu randevuları isteğe bağlı olarak iptal edebilmektedir.
Randevu alan kullanıcıların grafik arayüzü ise ilgili takvim günündeki boş olan randevu saatlerini görüntüleyip istediğini seçebilmektedir. Her bir kullanıcı, kendisine ait tüm aktif randevularını ise tarih, saat bilgisi olarak görüntülemektedir. Kullanıcı isteğe bağlı olarak önceden seçtiği randevuları iptal edebilmektedir.
Gizlilik Politikası : https://www.denizhansahin.com/p/gizlilik-politikamz-randevuapp.html 

# Kullanılan Teknolojiler

<div align="center">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/7615863c-b7f5-4230-9e61-6f26c2c579b1" width="33%">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/f295445f-9e7c-4785-a0f5-a4f173e5b4a8" width="33%">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/f1640074-7658-480f-a8c3-3e6646bc351a" width="33%">
</div>

## Firebase Kullanım Şeması:
kullaniciBilgileri : Bu bir collectiondur, kullanıcıların Firebase Authentication ile ilgili ID bilgilerine göre isimlendirilen documentler vardır

kullaniciBilgileri -> Firebase Authentication ID (document) : Bu bir document olarak görev yapmaktadır. İçerisinde yer alan field isimleri sırası ile : eposta, firebasID, isim, kullanici_ID, yetkili

tarihler : Bu bir collectiondur ve içerisinde GG-AA-YY formatına uygun tarihler için documentler vardır.

traihler -> GG-AA-YY (document) : Bu documentler içerisinde Saat:Dakika formatına göre saat fieldlar olmaktadır. Bu fieldların değeri ise default olarak "yok" olarak ayarlanmaktadır.

## Önemli:
Bu sistemde yer alan kullaniciBilgileri -> Firebase Authentication ID -> yetkili için eğer değer "Evet" ise bu yönetici kullanıcı için grafik arayüz açmaktadır. "Hayır" ise randevu alan kullanıcı için grafik arayüz açılmaktadır.

Bu sistemde yer alan kullaniciBilgileri -> Firebase Authentication ID (document) -> isim için ise yöetici kullanıcının her bir randevu işlemindeki kullanıcını isimlerini uygulama üzerinden görüntülemesi için kullanılmaktadır.

Bu uygulama içinde özellike Android CalendarView ve RecyclerView kullanılmıştır. İlgili Data Class ile ilgili bilgiler yine bu Data Class özelliği esas alınarak değer alan bir ArrayList kullanılmıştır ve bu ArrayList bir Adapter activity içinde RecyclerView'da listelenme sağlanmıştır. Firebase Firestore işlemleri için özellikle Adapter sınıfları kullanılmıştır ve esneklik açısından verimli olduğu düşünülmüştür.

## Kullanılan Adapter dosyaları : 

GenelAdapter : Randevu alan kullanıcıların randevu işlemlerini yapmaktadır. MainActivity5 içinden gelen ilgili tarih bilgisine ait olan boş randevu bilgilerine göre ilgili buton tıklama işlemleri yapılır.

GenelAdapterMyRandevu : Randevu alan kullanıcıların tüm randevu bilgilerini görüntülemeyi sağlar. İlgili randevuları iptal etmek için ilgili buton işlemlerini yapmaktadır. İlgili kaynak bilgilier MainActivity6 üzerinden almaktadır.

YoneticiRandevu : MainActivity7 içinden gelen bilgileri kullanmaktadır. Yönetici kullanıcı istediği bir takvim günündeki randevuları görüntülüyebilir ve iptal etmek için ilgili buton işlemlerini kullanmaktadır.

TumRandevularYonetici : Yöneticinin tüm randevuları görüntülemesini ve iptal etmesini sağlamaktadır. İlgili bilgileri MainActivity8 içinden almaktadır.


## Not:
GenelAdapter : cardview isimli bir XML kullanır.

GenelAdapterMyRandevu : my_randevu_cardview isimli bir XML kullanır.

YoneticiRandevu : my_randevu_cardview isimli bir XML kullanır.

TumRandevularYonetici : my_randevu_cardview isimli XML kullanır.

## Ekran Görüntüleri:

<div align="center">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/5d69be50-e407-44e4-b3e8-b119d3cb3d2b" width="33%">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/a16cc052-95cb-424c-a8f3-154f5fe38ba0" width="33%">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/3f53376d-e531-421d-802d-234bb10fde7e" width="33%">
</div>
<div align="center">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/bce1ed42-ed8a-4c0b-a123-6d899d0dac51" width="33%">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/206f8117-1f1d-46ac-a2a1-74b00cb9b0dd" width="33%">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/9f2237cd-9d66-4b07-a7f1-918df7fe5996" width="33%">
</div>
<div align="center">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/6ba66017-96fb-49df-bce1-a5bb7d99a092" width="33%">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/de8ca920-4a0d-4a86-b8d4-8d2d15e48e2b" width="33%">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/515203e8-e5df-4768-aab5-83e11a7450a7" width="33%">
</div>
<div align="center">
    <img src="https://github.com/denizzhansahin/randevuApp/assets/95483485/e2332886-9a1f-4569-821f-5ecb953db0c8" width="33%">
</div>
