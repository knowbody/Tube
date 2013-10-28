/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algocw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.PriorityQueue;

import javax.swing.JList;
import javax.swing.SwingUtilities;

public class AlgoCw implements ActionListener, Runnable {
	private static PriorityQueue<Station> pq = new PriorityQueue<>();
	private static MetroMap prague = new PragueMetroMap();
	private static  JList listFrom;
	private static  JList listTo;
	
	public AlgoCw() {
		// Running GUI
		SwingUtilities.invokeLater(this);
	}
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	new AlgoCw();
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
    
    private static void doSearch(String from, String to) {
    	String fromStation = from;
        String toStation = to;
        
        // Getting brain new Prague tube instance
        prague = new PragueMetroMap();
     
        // Setting up starting point
        prague.setFromStation(fromStation);

        // Setting up priority queue
        pq.addAll(prague.getAllStations());

        // Run Dijkstra Algorithm
        dijkstra();
        
        // Print out result
        printResults(fromStation);
    }
    
    private static void dijkstra() {
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

                    // Adding shortest path to the next station
                    nextStation.getSPath().addAll(station.getSPath());
                }
            }
        }
    }
    
    private static void printResults(String fromStation) {
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String from = (String) listFrom.getSelectedValue();
        String to = (String) listTo.getSelectedValue();
        
        if (from == null) {
        	System.out.println("Please select FROM station.");
        } else {
        	doSearch(from, to);
        }
    }

	@Override
	public void run() {
		MainGui ex = new MainGui(this, prague);
        ex.setVisible(true);
        listFrom = ex.getListFrom();
        listTo = ex.getListTo();
	}
}
