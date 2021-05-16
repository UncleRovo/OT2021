## Testausdokumentti
Sovelluksen sisäistä logiikkaa on testattu Unit-testeillä. Käyttöliittymää toteuttavat luokat ovat jätetty sen ulkopuolelle. Testit rakentuvat kahteen pakkaukseen, LogicTests ja UtilsTests.

#### UtilsTests
UtilsTests -pakkauksen testit testaavat, toimivatko esimerkiksi tiedostonimen haku oikein. Valitettavasti kaikkea ei voida testata, sillä esimerkiksi getFilePath -metodissa on haara, joka aktivoituu vain jos ohjelmaa suoritetaan JAR -tiedostosta.

#### LogicTests
LogicTestsin testit kartoittavat sovelluslogiikan toimintaa. Näistä haastavin ja suppein on ehdottomasti Battle -luokan testaus, sillä kyseinen luokka sisältää paljon satunnaisuutta ja tätä on vaikea testata.
