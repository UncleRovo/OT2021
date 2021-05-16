# KPS-Quest
[Käyttöohje](https://github.com/UncleRovo/OT2021/blob/main/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/UncleRovo/OT2021/blob/main/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/UncleRovo/OT2021/blob/main/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/UncleRovo/OT2021/blob/main/dokumentaatio/testausdokumentti.md)

[Työaikakirjanpito](https://github.com/UncleRovo/OT2021/blob/main/dokumentaatio/tuntikirjanpito.md)

[Uusin release](https://github.com/UncleRovo/OT2021/releases/tag/viikko7)

KPS-Quest on interaktiivinen peli, jossa pelaajan tavoitteena on päihittää Rival -niminen hahmo Kivi Paperi Sakset -pelissä. KPS -eriä otellaan rahasta, ja toisen rahat vienyt voittaa. Pelin häviää pelaamalla kaikki rahansa, jolloin menetät Rivalin kunniotuksen, toisaalta taas voittaa tyhjentämällä Rivalin lompakon ja täten ansaiten hänen kunniotuksensa.

Lataa viimeisin release koneellesi, ja noudata käyttöohjeessa listattuja asennusohjeita.

### Tärkeimmät komennot:

#### Ohjelman käynnistys:
```
mvn compile exec:java -Dexec.mainClass=mainprogram.Main
```

#### JAR-tiedoston luonti:
```
mvn package
```

#### Testaukseen liittyvät komennot:
Testien ajo
```
mvn test
```
Testikattavuuden raportointi:
```
mvn jacoco:report
```

#### Checkstyle:
```
mvn jxr:jxr checkstyle:checkstyle
```

#### Javadocin luonti:
```
mvn javadoc:javadoc
```
