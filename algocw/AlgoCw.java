/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algocw;

import java.util.PriorityQueue;

public class AlgoCw {
	private static PriorityQueue<Station> pq = new PriorityQueue<>();
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fromStation = "CM";
        String toStation = "Krizovka";

        // Getting Prague tube map
        MetroMap prague = new PragueMetroMap();
     
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
                    updatePQ(nextStation);

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
   	
                    System.out.print(" --(" + s.getRouteLine(nextStation) +")--> ");
                }
                
                
            }
            System.out.println();
        }
    }
    
    /**
     * Here we are updating station in the priority queue.
     * 
     * @param s	 Station which should get updated
     */
    private static void updatePQ(Station s) {
    	 pq.remove(s);
         pq.add(s);
    }
}
