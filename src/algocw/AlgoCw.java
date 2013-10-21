/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algocw;

import java.util.PriorityQueue;

/**
 *
 * @author Patrik Fuhrmann
 */
public class AlgoCw {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PriorityQueue<Station> pq = new PriorityQueue<>();

        String fromStation = "Nadrazi Holesovice";
        String toStation = "Krizovka";

        // Building up the Praque Tube!
        MetroMap praque = new MetroMap();
        praque.addStation("Malostranska")
                .addStation("Staromestka")
                .addStation("Mustek")
                .addStation("Namesti Miru")
                .addStation("NR")
                .addStation("Florence")
                .addStation("Krizovka")
                .addStation("Invalidovna")
                .addStation("Vltavska")
                .addStation("Nadrazi Holesovice")
                .addStation("Hlavni Nadrazi")
                .addStation("Muzeum")
                .addStation("I.P. Pavlova")
                .addStation("Vysehrad");
        praque.addRoute("Malostranska", "Staromestka", 2, "A")
                .addRoute("Staromestka", "Mustek", 2, "A")
                .addRoute("Mustek", "NR", 2, "B")
                .addRoute("Mustek", "Muzeum", 1, "A")
                .addRoute("NR", "Florence", 1, "B")
                .addRoute("Florence", "Krizovka", 2, "B")
                .addRoute("Krizovka", "Invalidovna", 2, "B")
                .addRoute("Florence", "Vltavska", 2, "C")
                .addRoute("Vltavska", "Nadrazi Holesovice", 2, "C")
                .addRoute("Florence", "Hlavni Nadrazi", 2, "C")
                .addRoute("Hlavni Nadrazi", "Muzeum", 1, "C")
                .addRoute("Muzeum", "I.P. Pavlova", 1, "C")
                .addRoute("I.P. Pavlova", "Vysehrad", 3, "C")
                .addRoute("Muzeum", "Namesti Miru", 2, "A");
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

                int newDistance = route.getLength() + station.getDistance(); // Getting final distance
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
            System.out.print("To " + station.getName() + " : " + station.getDistance());

            for (int i = 0; i < station.getSPath().size(); i++) {
                Station s = station.getSPath().get(i);
                //System.out.print(" --> " + s.getName() + "(" + s.getRoute(linux) + "");
                System.out.print(" --> " + s.getName() + "(A)");
            }
            System.out.println();
        }
    }
}
