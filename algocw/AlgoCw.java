/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algocw;

import java.util.PriorityQueue;

public class AlgoCw {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PriorityQueue<Station> pq = new PriorityQueue<>();

        String fromStation = "CM";
        String toStation = "Krizovka";

        // Building up the Prague Tube!
        MetroMap praque = new MetroMap();
        praque.addStation("Dejvicka") // Stations
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
        // Setting up starting point
        praque.setFromStation(fromStation);

        // Building priority queue
        pq.addAll(praque.getAllStations());

        // Dijkstra Algorithm
        while (!pq.isEmpty()) { // While priority queue is not empty
            Station station = pq.remove(); // Remove station from priority queque
            station.getSPath().add(station); // Add station to current path

            for (Route route : station.getAllRoutes()) { // Repeat this for all routes from station
                Station nextStation = route.getGoingTo(); // Next station in route

                Double newDistance = route.getLength() + station.getDistance(); // Getting final distance
                if (newDistance < nextStation.getDistance()) {

                    // Updating distance straight in priority queue
                    nextStation.setDistance(newDistance);
                    pq.remove(nextStation);
                    pq.add(nextStation);

                    // Adding shortest path to next station
                    nextStation.getSPath().addAll(station.getSPath());
                }
            }
        }

        System.out.print("Shortest routes from " + fromStation);
        System.out.println();
        for (Station station : praque.getAllStations()) {
            System.out.print("To " + station.getName() + " : " + station.getDistance() + " -------->> ");
            
            int numOfStations = station.getSPath().size();
            for (int i = 0; i < numOfStations; i++) {
                Station s = station.getSPath().get(i);
                String nextStation;
                
                System.out.print(s.getName() + "");
                
                // Getting next station
                if ((i+1) < numOfStations) {
                	nextStation = station.getSPath().get(i+1).getName();
                	//System.out.print(" --> " + s.getName() + "(" + s.getRoute(station.getSPath().get(i+1).) + "");
                	String routeLine = s.getRouteLine(nextStation);
                	
                    System.out.print(" --(" + s.getRouteLine(nextStation) +")--> ");
                }
                
                
            }
            System.out.println();
        }
    }
}
