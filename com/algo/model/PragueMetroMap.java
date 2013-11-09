/**
 * Prague Metro - Journey Planner
 * Project for - COMP1555: Algorithms and Modelling
 * Authors:
 *      Mateusz Zatorski (000738254)
 *      Patrik Fuhrmann (000725089)
 *      Irmantas Marozas (000708431)
 */

/*
* class builds the prague metro map using inherited methods from MetroMap
* */
package com.algo.model;

public class PragueMetroMap extends MetroMap {

    public PragueMetroMap() {
        // Building up the Prague Tube!

        this.addStation("Dejvicka") // Stations
                .addStation("Hradcanska")
                .addStation("Malostranska")
                .addStation("Staromestka")
                .addStation("Mustek")
                .addStation("Narodni trida")
                .addStation("Karlovo namesti")
                .addStation("Andel")
                .addStation("Smichovske nadrazi")
                .addStation("Radlicka")
                .addStation("Jinonice")
                .addStation("Nove Butovice")
                .addStation("Hurka")
                .addStation("Luziny")
                .addStation("Luka")
                .addStation("Stodulky")
                .addStation("Zlicin")
                .addStation("Namesti Miru")
                .addStation("Jiriho z Podebrad")
                .addStation("Flora")
                .addStation("Zelivskeho")
                .addStation("Strasnicka")
                .addStation("Skalka")
                .addStation("Depo Hostivar")
                .addStation("Namesti Rebubliky")
                .addStation("Florenc")
                .addStation("Krizovka")
                .addStation("Invalidovna")
                .addStation("Palmovka")
                .addStation("Ceskomoravska")
                .addStation("Vysocanska")
                .addStation("Kolbenova")
                .addStation("Hloubetin")
                .addStation("Rajska zahrada")
                .addStation("Cerny Most")
                .addStation("Vltavska")
                .addStation("Nadrazi Holesovice")
                .addStation("Kobylisy")
                .addStation("Ladvi")
                .addStation("Hlavni nadrazi")
                .addStation("Muzeum")
                .addStation("I.P. Pavlova")
                .addStation("Vysehrad")
                .addStation("Prazskeho povstani")
                .addStation("Pankrac")
                .addStation("Budejovicka")
                .addStation("Kacerov")
                .addStation("Roztyly")
                .addStation("Chodov")
                .addStation("Opatov")
                .addStation("Haje")
                        // Routes
                        // (from, to, distance, line name)
                .addRoute("Dejvicka", "Hradcanska", 2, "A")
                .addRoute("Hradcanska", "Malostranska", 2, "A")
                .addRoute("Malostranska", "Staromestka", 2, "A")
                .addRoute("Staromestka", "Mustek", 2, "A")
                .addRoute("Mustek", "Namesti Rebubliky", 2, "B")
                .addRoute("Mustek", "Muzeum", 1, "A")
                .addRoute("Mustek", "Narodni trida", 1, "B")
                .addRoute("Narodni trida", "Karlovo namesti", 1, "B")
                .addRoute("Karlovo namesti", "Andel", 2, "B")
                .addRoute("Andel", "Smichovske nadrazi", 2, "B")
                .addRoute("Smichovske nadrazi", "Radlicka", 4, "B")
                .addRoute("Radlicka", "Jinonice", 3, "B")
                .addRoute("Jinonice", "Nove Butovice", 3, "B")
                .addRoute("Nove Butovice", "Hurka", 1, "B")
                .addRoute("Hurka", "Luziny", 2, "B")
                .addRoute("Luziny", "Luka", 1, "B")
                .addRoute("Luka", "Stodulky", 2, "B")
                .addRoute("Stodulky", "Zlicin", 3, "B")
                .addRoute("Namesti Rebubliky", "Florenc", 1, "B")
                .addRoute("Florenc", "Krizovka", 2, "B")
                .addRoute("Krizovka", "Invalidovna", 2, "B")
                .addRoute("Invalidovna", "Palmovka", 2, "B")
                .addRoute("Palmovka", "Ceskomoravska", 3, "B")
                .addRoute("Ceskomoravska", "Vysocanska", 2, "B")
                .addRoute("Vysocanska", "Kolbenova", 2, "B")
                .addRoute("Kolbenova", "Hloubetin", 3, "B")
                .addRoute("Hloubetin", "Rajska zahrada", 4, "B")
                .addRoute("Rajska zahrada", "Cerny Most", 2, "B")
                .addRoute("Florenc", "Vltavska", 2, "C")
                .addRoute("Vltavska", "Nadrazi Holesovice", 2, "C")
                .addRoute("Nadrazi Holesovice", "Kobylisy", 6, "C")
                .addRoute("Kobylisy", "Ladvi", 2, "C")
                .addRoute("Florenc", "Hlavni nadrazi", 2, "C")
                .addRoute("Hlavni nadrazi", "Muzeum", 1, "C")
                .addRoute("Muzeum", "I.P. Pavlova", 1, "C")
                .addRoute("I.P. Pavlova", "Vysehrad", 3, "C")
                .addRoute("Vysehrad", "Prazskeho povstani", 2, "C")
                .addRoute("Prazskeho povstani", "Pankrac", 2, "C")
                .addRoute("Pankrac", "Budejovicka", 2, "C")
                .addRoute("Budejovicka", "Kacerov", 2, "C")
                .addRoute("Kacerov", "Roztyly", 3, "C")
                .addRoute("Roztyly", "Chodov", 2, "C")
                .addRoute("Chodov", "Opatov", 3, "C")
                .addRoute("Opatov", "Haje", 3, "C")
                .addRoute("Muzeum", "Namesti Miru", 2, "A")
                .addRoute("Namesti Miru", "Jiriho z Podebrad", 2, "A")
                .addRoute("Jiriho z Podebrad", "Flora", 2, "A")
                .addRoute("Flora", "Zelivskeho", 2, "A")
                .addRoute("Zelivskeho", "Strasnicka", 3, "A")
                .addRoute("Strasnicka", "Skalka", 3, "A")
                .addRoute("Skalka", "Depo Hostivar", 2, "A");
    }
}
