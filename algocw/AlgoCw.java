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

        // Getting Prague tube map
        MetroMap prague = new PragueMap().getMap();
     
        // Setting up starting point
        prague.setFromStation(fromStation);

        // Building priority queue
        pq.addAll(prague.getAllStations());

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
        for (Station station : prague.getAllStations()) {
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
