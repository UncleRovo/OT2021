## Arkkitehtuurikuvaus
### Rakenne
Ohjelma sisältää neljä pakkausta, mainprogram on vastuussa itse ohjelman käynnistämisestä, kun taas kpsquest antaa pääohjelmalle graafisen käyttöliittymän. Pakkaus useruis sisältää käyttöliittymän rakentamiseen tarvittavat työkalut, data taas pysyväismuistiin ja tiedostopolkujen käsittelyyn liittyvät asiat.

<img src="https://github.com/UncleRovo/OT2021/blob/main/dokumentaatio/kuvat/pakkauskaavio.png">

mainprogram sisältää luokan main, joka puolestaan käynnistää kpsquest -pakkauksen luokan KPSQuest main-metodin. Näin siksi, jotta JavaFX-komponentit toimisivat halutusti. KPSQuest sisältää oliomuuttujana useruis -pakkauksen luokan MainMenu ilmentymän, joka antaa KPSQuest'in Stage -oliolle erilaisia Scene -olioita. Scene -oliot rakennetaan joko suoraan luokissa useruis.MainMenu tai useruis.OverWorldUI, tai sitten käyttäen apuna luokkaa data.GraphicsBuilder tai data.Utils.

### Päätoiminnallisuudet

Uuden tallennuksen luominen pelissä:

<img src="https://github.com/UncleRovo/OT2021/blob/main/dokumentaatio/kuvat/skv1.jpg">

Olemassaolevan tallennuksen aloittaminen:

<img src="https://github.com/UncleRovo/OT2021/blob/main/dokumentaatio/kuvat/skv2.jpg">

Tallennusten poistaminen:

<img src="https://github.com/UncleRovo/OT2021/blob/main/dokumentaatio/kuvat/skv3.jpg">

Luokka Savefiles hallinnoi pelitallennuksia Savefile -luokasta tehtyjen olioiden avulla. Jos yksittäiseen Savefile -olion oliomuuttujaan on merkitty pelin alku todeksi, lataa päävalikko pelinäkymän (Overworld). Jos ei, pelaaja pääsee antamaan pelitallennukselleen nimen, ja peli merkitään alkaneeksi.

### Tallennus

Pelitiedot tallennetaan tiedostoon saves.txt seuraavalla formaatilla:

save_1_0

save_2_0

save_3_0

Eri arvot on eroteltu toisistaan alaviivalla. Keskimmäinen arvo on pelitallennuksen numero, ja oikeanpuolimmaisin arvo on tallennuksen (eli siis pelaajan) nimi. Jos nimi on pelkkä nolla, tunnistaa järjestelmä pelin uudeksi (ei-alkaneeksi).

### Grafiikat

Pelin grafiikat piirretään ImageView -olioina ruudulle. Tausta piirretään sellaisenaan .jpg-muodossa. Jos kyseessä on muunlainen graafinen elementti, käyttää ohjelma bluescreen-tekniikkaa erottelemaan itse grafiikan sinisestä taustasta. Tästä vastaa GraphicsBuilder -luokan metodi getImageWithoutBlue(). Poistettava sinisen sävy on hex-koodina 0000ff.
