package com.algo.model;
 
import com.algo.structures.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
import java.util.PriorityQueue;
 
public class GUIModel {
 
    private PriorityQueue<Station> pq = new PriorityQueue<>();
    private MetroMap prague;
    private ArrayList<String> details; // Details for printing results
    private Date time;

    // Calendars for time comparisons
    private Calendar cal = Calendar.getInstance();
    private Calendar localT = Calendar.getInstance();
    private Calendar offPeak = Calendar.getInstance();
    private Calendar diffSignal9AM = Calendar.getInstance();
    private Calendar diffSignal4PM = Calendar.getInstance();
    private Calendar diffSignal7PM = Calendar.getInstance();
    private Calendar diffSignal12PM = Calendar.getInstance();
 
    private void doSearch(String from, String to) {
        // Getting brand new Prague tube instance
        prague = new PragueMetroMap();
 
        // Setting up starting point
        prague.setFromStation(from);
 
        // Setting up priority queue
        pq.addAll(prague.getAllStations());
 
        // Dijkstra Algorithm
        runDijkstra();
 
        // Print out result
        printResults(from, to);
    }
 
    /**
     * Implementation of the Dijkstra algorithm using priority queue method.
     */
    public void runDijkstra() {
        while (!pq.isEmpty()) { // While priority queue is not empty
            Station station = pq.remove(); // Remove station from priority queue
            station.addToSPath(station); // Add station to shortest path
 
            ArrayList<Route> routes = station.getAllRoutes();
            for (int i = 0; i < routes.getSize(); i++) {
                Route route = (Route) routes.get(i); // Repeat this for all routes from station
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
 
    private void printResults(String fromStation, String toStation) {
    	// Dates stuff
    	DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    	String localTime = dateFormat.format(getTime());
    	setupCalendars();
    	
        details = new ArrayList<String>(); 
        
        if (fromStation.equalsIgnoreCase(toStation)) {
            details.add("Destination of the route must be");
            details.add("different than the starting point.");
            return;
        }
        
        // Traverse all stations from the map
        for (Station station : prague.getAllStations()) {
        	// Do all the printing just for the desired station to
            if (station.getName().equalsIgnoreCase(toStation) || toStation == null) { 
                // List head
                details.add("FROM: " + fromStation + "\n");
                details.add("TO: " + station.getName());
                details.add("DISTANCE: " + (station.getDistance() * 0.5) + "km"); // Distance calculated (number of dots * 0.5 km)
                details.add("STARTING TIME: " + localTime); // Journey starting time
                details.add(" ");
                details.add("DESCRIPTION:");

                String lineName = "";
                long ranWaitTime = 0;
                double totalJourneyTime = 0.0;
                
                int numOfStations = station.getSPath().size();
                for (int i = 0; i < numOfStations;) {
                    Station s = station.getFromSPath(i);
                    String nextStation;
                    String lineCurrent = lineName;
 
                    // Display beginning line to take
                    if (i == 0) {
                        lineName = s.getRouteLine(station.getFromSPath(i + 1).getName());
                        details.add("*** Take line " + lineName + " ***");
                        details.add((i + 1) + ") " + s.getName());
                        i++;
                        double total = (double) (((station.getFromSPath(i).getDistance() * 500) / 9) / 60);
                        totalJourneyTime += total;
                        details.add("Time to next station: " + (int) total + "min " + extractFractionAsSeconds(total) + "s");
                        localTime = dateFormat.format(addTime((int) total, extractFractionAsSeconds(total)).getTime());
                    } else {
                        details.add((i + 1) + ") " + s.getName());
                        i++;
                        // Getting next station if not last already
                        if (i < numOfStations) {
                            // Total journey time between current and next station
                            double total = (double) ((((station.getFromSPath(i).getDistance() - station.getFromSPath(i - 1).getDistance()) * 500) / 9) / 60);
 
                            localT = addTime((int) total, extractFractionAsSeconds(total)); // adding journey time
 
                            if (ranWaitTime != 0) {
                                totalJourneyTime += ranWaitTime;
                                ranWaitTime = 0;
                            }
 
                            Boolean peak = false;
                            if (localT.compareTo(offPeak) > 0) { // Check if train is travelling after peak time
                                peak = true;
                            }
 
                            Boolean specialTime = false;
                            // Checking if travelling during special signaling operation hours
                            if (lineCurrent.equals("C")) {
                                if ((localT.compareTo(diffSignal9AM) > 0 && localT.compareTo(diffSignal4PM) < 0) || (localT.compareTo(diffSignal7PM) > 0 && localT.compareTo(diffSignal12PM) < 0)) { // Check if train is travelling after peak time
                                    specialTime = true;
                                    total /= 2;
                                }
                            }
                            totalJourneyTime += total;
                            details.add("Time to next station: " + (int) total + "min " + extractFractionAsSeconds(total) + "s"
                                    + " | Total time: " + (int) totalJourneyTime + "min " + extractFractionAsSeconds(totalJourneyTime) + "s");
 
                            nextStation = station.getFromSPath(i).getName();
                            lineName = s.getRouteLine(nextStation);
 
                            // if the previous line is the same as next do nothing, otherwise say to change line
                            if (!lineCurrent.equalsIgnoreCase(lineName)) {
                                details.add(" ");
                                details.add("*** change for line " + lineName + " ***");
                                if (peak) {
                                    ranWaitTime = Math.round(Math.random() * 10);
                                } else {
                                    ranWaitTime = Math.round(Math.random() * 3);
                                }
                                if (ranWaitTime != 0) {
                                    localT = addTime((int) ranWaitTime, 0); // add wait time
                                    details.add("*** Wait for next train " + ranWaitTime + " min.");
                                }
                            }
                        }
                    }
                }
                details.add(" ");
            }
        }
    }
 
    private void updatePQ(Station s) {
        pq.remove(s);
        pq.add(s);
    }
 
    public void setDoSearch(String from, String to) {
        doSearch(from, to);
    }
 
    public ArrayList<String> getDetails() {
        return details;
    }
 
    public void setTime(Date time) {
        this.time = time;
    }
 
    public Date getTime() {
        return time;
    }
 
    /**
     * Convert minutes fractions to actual seconds
     * 
     * @param totalM  Minutes with fraction
     * @return Number of seconds
     */
    private int extractFractionAsSeconds(double totalM) {
        int whole = (int) totalM;
        double fraction = totalM - whole;
        fraction = fraction * 0.6;
        double fractionR = Math.round(fraction * 100.0);
 
        return (int) fractionR;
    }
 
    /**
     * Append time to calendar
     * 
     * @param m  Minutes
     * @param s  Seconds
     * @return
     */
    private Calendar addTime(int m, int s) {
        cal.add(Calendar.MINUTE, m);
        cal.add(Calendar.SECOND, s);
        return cal;
    }
    
    /**
     * Setting up calendars for time comparisons
     */
    private void setupCalendars() {
    	cal.setTime(time);
    	
        offPeak.setTime(time);
        offPeak.set(Calendar.HOUR_OF_DAY, 9);
        offPeak.set(Calendar.MINUTE, 30);
        
        localT.setTime(time);
        
        diffSignal9AM.setTime(time);
        diffSignal9AM.set(Calendar.HOUR_OF_DAY, 9);
        
        diffSignal4PM.setTime(time);
        diffSignal4PM.set(Calendar.HOUR_OF_DAY, 16);
        
        diffSignal7PM.setTime(time);
        diffSignal7PM.set(Calendar.HOUR_OF_DAY, 19);
        
        diffSignal12PM.setTime(time);
        diffSignal12PM.set(Calendar.HOUR_OF_DAY, 24);
    }
    
    // Dependency injection for testing
    public void setMap(MetroMap map) {
        this.prague = map;
    }
    // Dependency injection for testing
    public void setPQ(PriorityQueue<Station> pq) {
        this.pq = pq;
    }
}