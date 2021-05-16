# KPS-Quest
[Käyttöohje](https://github.com/UncleRovo/OT2021/blob/main/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/UncleRovo/OT2021/blob/main/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/UncleRovo/OT2021/blob/main/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/UncleRovo/OT2021/blob/main/dokumentaatio/tuntikirjanpito.md)

KPS-Quest on interaktiivinen peli, jossa pelaajan tavoitteena on päihittää Rival -niminen hahmo Kivi Paperi Sakset -pelissä. KPS -eriä otellaan rahasta, ja toisen rahat vienyt voittaa. Pelin häviää pelaamalla kaikki rahansa, jolloin menetät Rivalin kunniotuksen, toisaalta taas voittaa tyhjentämällä Rivalin lompakon ja täten ansaiten hänen kunniotuksensa.

Lataa viimeisin release koneellesi, ja noudata käyttöohjeessa listattuja asennusohjeita.

### Ohjelman testaaminen:

#### Tapa 1:

Lataa kansio KPSQUEST koneellesi, navigoi terminaalissa kyseisen kansion sisälle ja suorita komento: mvn compile exec:java -Dexec.mainClass=mainprogram.Main

##### Tapa 2:

Lataa tiedosto KPSQUEST-1.0-SNAPSHOT.jar ja suorita komento: java -jar KPSQUEST-1.0-SNAPSHOT.jar 

Ainakin laitoksen koneilla tiedoston voi avata ilman terminaalia klikkaamalla tiedostoa Hiiren oikealla painikkeella ja valitsemalla valikosta "Open in JDK 11 Runtime Environment"
