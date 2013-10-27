/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algocw;

public class PragueMap {

	private MetroMap map = new MetroMap();
	
    /**
     * @param args the command line arguments
     */
    public PragueMap() {
        // Building up the Prague Tube!
        
        this.map.addStation("Dejvicka") // Stations
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
                .addStation("NR")
                .addStation("Florence")
                .addStation("Krizovka")
                .addStation("Invalidovna")
                .addStation("Palmovka")
        		.addStation("CM")
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
                .addRoute("Dejvicka", "Hradcanska", 2, "A")
        		.addRoute("Hradcanska", "Malostranska", 2, "A")
        		.addRoute("Malostranska", "Staromestka", 2, "A")
                .addRoute("Staromestka", "Mustek", 2, "A")
                .addRoute("Mustek", "NR", 2, "B")
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
                .addRoute("NR", "Florence", 1, "B")
                .addRoute("Florence", "Krizovka", 2, "B")
                .addRoute("Krizovka", "Invalidovna", 2, "B")
                .addRoute("Invalidovna", "Palmovka", 2, "B")
                .addRoute("Palmovka", "CM", 3, "B")
                .addRoute("CM", "Vysocanska", 2, "B")
                .addRoute("Vysocanska", "Kolbenova", 2, "B")
                .addRoute("Kolbenova", "Hloubetin", 3, "B")
                .addRoute("Hloubetin", "Rajska zahrada", 4, "B")
                .addRoute("Rajska zahrada", "Cerny Most", 2, "B")
                .addRoute("Florence", "Vltavska", 2, "C")
                .addRoute("Vltavska", "Nadrazi Holesovice", 2, "C")
                .addRoute("Nadrazi Holesovice", "Kobylisy", 6, "C")
                .addRoute("Kobylisy", "Ladvi", 2, "C")
                .addRoute("Florence", "Hlavni nadrazi", 2, "C")
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
    
    public MetroMap getMap() {
    	return this.map;
    }
}
