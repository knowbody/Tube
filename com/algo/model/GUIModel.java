package com.algo.model;

import com.algo.structures.ArrayList;

import java.util.PriorityQueue;


public class GUIModel {

    private PriorityQueue<Station> pq = new PriorityQueue<>();
    private MetroMap prague;
    private ArrayList<String> dt;
    private String time;

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
     * Implementation of the Dijkstra algorithm
     * using priority queue method.
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
        dt = new ArrayList();
        if (fromStation.equalsIgnoreCase(toStation)) {
            dt.add("Destination of the route must be");
            dt.add("different than the starting point.");
            return;
        }
        dt.add("FROM: " + fromStation + "\n");
        for (Station station : prague.getAllStations()) {
            if (station.getName().equalsIgnoreCase(toStation) || toStation == null) {
                dt.add("TO: " + station.getName());
                // distance calculated (number of dots * 0.5 km)
                dt.add("DISTANCE: " + (station.getDistance() * 0.5) + "km");
                // total time calculated (average tube speed 9m/s)
                dt.add("AVERAGE TOTAL TIME: " + (int)((station.getDistance() * 500)/9)/60 + " min");
                dt.add("STARTING TIME: " + getTime());
                dt.add(" ");
                dt.add("DESCRIPTION:");

                int numOfStations = station.getSPath().size();

                String lineName = "";
                for (int i = 0; i < numOfStations; ) {

                    Station s = station.getFromSPath(i);
                    String nextStation;
                    String lineCurrent = lineName;

                    // to display beginning line to take
                    if (i == 0) {
                        lineName = s.getRouteLine(station.getFromSPath(i + 1).getName());
                        dt.add("*** Take line " + lineName + " ***");
                        dt.add((i + 1) + ") " + s.getName());
                        i++;
                        double total = (double)(((station.getFromSPath(i).getDistance() * 500)/9)/60);
                    	dt.add("Time to next station: " + (int)total + "min "+convertTime(total)+"s");
                    } else {
                        dt.add((i + 1) + ") " + s.getName());
                        i++;

                        // Getting next station if not last already
                        if (i < numOfStations) {
                        	double total = (double)((((station.getFromSPath(i).getDistance() - station.getFromSPath(i-1).getDistance()) * 500)/9)/60);
                        	double totalJourneyTime = (double)(((station.getFromSPath(i).getDistance() * 500)/9)/60);
                        	dt.add("Time to next station: " + (int)total + "min " + convertTime(total) + "s" +
                                	" | Total time: " +(int)totalJourneyTime + "min " + convertTime(totalJourneyTime) + "s");
                            nextStation = station.getFromSPath(i).getName();
                            lineName = s.getRouteLine(nextStation);

                            // if the previous line is the same as next do nothing, otherwise say to change line
                            if (lineCurrent.equalsIgnoreCase(lineName)) {
                            } else {
                                dt.add(" ");
                                dt.add("*** change for line " + lineName + " ***");
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
    
    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
    
    private int convertTime(double total){
    	int whole = (int)total;
    	double fraction = total-whole;
    	fraction = fraction*0.6;
    	double fractionR = Math.round(fraction*100.0);
    	
    	return (int)fractionR;
	}
}