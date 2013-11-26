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
    private ArrayList<String> dt;
    private Date time;
    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    Calendar cal = Calendar.getInstance();
    Calendar localT = Calendar.getInstance();
    Calendar offPeak = Calendar.getInstance();
    Calendar diffSignal9AM = Calendar.getInstance();
    Calendar diffSignal4PM = Calendar.getInstance();
    Calendar diffSignal7PM = Calendar.getInstance();
    Calendar diffSignal12PM = Calendar.getInstance();
 
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
        // setting up calendars for time comparison
        offPeak.setTime(time);
        localT.setTime(time);
        diffSignal9AM.setTime(time);
        diffSignal4PM.setTime(time);
        diffSignal7PM.setTime(time);
        diffSignal12PM.setTime(time);
        offPeak.set(Calendar.HOUR_OF_DAY, 9);
        offPeak.set(Calendar.MINUTE, 30);
        diffSignal9AM.set(Calendar.HOUR_OF_DAY, 9);
        diffSignal4PM.set(Calendar.HOUR_OF_DAY, 16);
        diffSignal7PM.set(Calendar.HOUR_OF_DAY, 19);
        diffSignal12PM.set(Calendar.HOUR_OF_DAY, 24);
 
        dt = new ArrayList();
        if (fromStation.equalsIgnoreCase(toStation)) {
            dt.add("Destination of the route must be");
            dt.add("different than the starting point.");
            return;
        }
        dt.add("FROM: " + fromStation + "\n");
        for (Station station : prague.getAllStations()) {
            if (station.getName().equalsIgnoreCase(toStation) || toStation == null) {
                String localTime = dateFormat.format(getTime());
                dt.add("TO: " + station.getName());
                // distance calculated (number of dots * 0.5 km)
                dt.add("DISTANCE: " + (station.getDistance() * 0.5) + "km");
                // total time calculated (average tube speed 9m/s)
 
                dt.add("STARTING TIME: " + localTime);
                dt.add(" ");
                dt.add("DESCRIPTION:");
                int numOfStations = station.getSPath().size();
 
                String lineName = "";
                long ranWaitTime = 0;
                double totalJourneyTime = 0.0;
                for (int i = 0; i < numOfStations;) {
                    Station s = station.getFromSPath(i);
                    String nextStation;
                    String lineCurrent = lineName;
 
                    // to display beginning line to take
                    if (i == 0) {
                        cal.setTime(time);
                        lineName = s.getRouteLine(station.getFromSPath(i + 1).getName());
                        dt.add("*** Take line " + lineName + " ***");
                        dt.add((i + 1) + ") " + s.getName());
                        i++;
                        double total = (double) (((station.getFromSPath(i).getDistance() * 500) / 9) / 60);
                        totalJourneyTime += total;
                        dt.add("Time to next station: " + (int) total + "min " + convertTime(total) + "s");
                        localTime = dateFormat.format(addTime((int) total, convertTime(total)).getTime());
                    } else {
                        dt.add((i + 1) + ") " + s.getName());
                        i++;
                        // Getting next station if not last already
                        if (i < numOfStations) {
                            // Total journey time between current and next station
                            double total = (double) ((((station.getFromSPath(i).getDistance() - station.getFromSPath(i - 1).getDistance()) * 500) / 9) / 60);
 
                            localT = addTime((int) total, convertTime(total)); // adding journey time
 
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
                            dt.add("Time to next station: " + (int) total + "min " + convertTime(total) + "s"
                                    + " | Total time: " + (int) totalJourneyTime + "min " + convertTime(totalJourneyTime) + "s");
 
                            nextStation = station.getFromSPath(i).getName();
                            lineName = s.getRouteLine(nextStation);
 
                            // if the previous line is the same as next do nothing, otherwise say to change line
                            if (!lineCurrent.equalsIgnoreCase(lineName)) {
                                dt.add(" ");
                                dt.add("*** change for line " + lineName + " ***");
                                if (peak) {
                                    ranWaitTime = Math.round(Math.random() * 10);
                                } else {
                                    ranWaitTime = Math.round(Math.random() * 3);
                                }
                                if (ranWaitTime != 0) {
                                    localT = addTime((int) ranWaitTime, 0); // add wait time
                                    dt.add("*** Wait for next train " + ranWaitTime + " min.");
                                }
                            }
                        }
                    }
                }
                dt.add(" ");
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
        return dt;
    }
 
    public void setMap(MetroMap map) {
        this.prague = map;
    }
 
    public void setPQ(PriorityQueue<Station> pq) {
        this.pq = pq;
    }
 
    public void setTime(Date time) {
        this.time = time;
    }
 
    public Date getTime() {
        return time;
    }
 
    private int convertTime(double total) {
        int whole = (int) total;
        double fraction = total - whole;
        fraction = fraction * 0.6;
        double fractionR = Math.round(fraction * 100.0);
 
        return (int) fractionR;
    }
 
    private Calendar addTime(int m, int s) {
        cal.add(Calendar.MINUTE, m);
        cal.add(Calendar.SECOND, s);
        return cal;
    }
}