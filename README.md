# RandevuApp
Bu uygulama Kotlin ve Firebase ile Android cihazlar için geliştirilmiştir. Bu uygulamanın amacı randevu hizmetlerini tek bir çatı altında yönetmektir.
Uygulamanın kullanımı sadece yetkili kişiler üzerinde gerçekleşmektedir. Bu kişiler yönetici ve randevu alan kullanıcılar olarak belirlenmiştir. Bu kullanıcıların tüm yetki tanımları Firebase Firestore üzerinde kayıtlıdır.
Firebase Authentication hizmeti ile kullanıcıların giriş için gerekli bilgileri alınır ve oturum açma işlemi gerçekleşir. Firebase Firestore ise kullancıların yetki tanımlarının olduğu bilgileri tutmaktadır ve kullanıcıların girişlerine göre tanımlı olan yetki bilgisi için ilgili grafik araryüz açılmaktadır.
Yönetici kullancını grafik arayüzü ilgili takvim günündeki her bir kullanıcının randevu aldığı saat bilgisi, kullanıcı email bilgisi ile eşleşen isim bilgisi gözükmektedir. Yönetici kullanıcı isterse bu randevuları iptal etmek seçeneğini kullanabilir. Yönetici kullanıcı ayrıca tüm aktif olan randevuların bilgisi hakkında bilgi alabilmektedir. Bunun için açılacak olan ekranda ise her bir kullanıcını email ile eşleşen ismi, randevu tarihi, randevu saati yer almaktadır. Yönetici bu randevuları isteğe bağlı olarak iptal edebilmektedir.
Randevu alan kullanıcıların grafik arayüzü ise ilgili takvim günündeki boş olan randevu saatlerini görüntüleyip istediğini seçebilmektedir. Her bir kullanıcı, kendisine ait tüm aktif randevularını ise tarih, saat bilgisi olarak görüntülemektedir. Kullanıcı isteğe bağlı olarak önceden seçtiği randevuları iptal edebilmektedir.

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
