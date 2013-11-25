package com.algo.model;

import com.algo.structures.ArrayList;

import java.util.PriorityQueue;


public class GUIModel {

    private PriorityQueue<Station> pq = new PriorityQueue<>();
    private MetroMap prague;
    private ArrayList<String> dt;

    private void doSearch(String from, String to) {

        // Getting brand new Prague tube instance
        prague = new PragueMetroMap();

        // Setting up starting point
        prague.setFromStation(from);

        // Setting up priority queue
        pq.addAll(prague.getAllStations());

        // Dijkstra Algorithm
        rudDijkstra();

        // Print out result
        printResults(from, to);
    }

    /**
     * Implementation of the Dijkstra algorithm
     * using priority queue method.
     */
    private void rudDijkstra() {
        while (!pq.isEmpty()) { // While priority queue is not empty
            Station station = pq.remove(); // Remove station from priority queue
            station.getSPath().add(station); // Add station to current path

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
        dt = new ArrayList<String>();
        if (fromStation.equalsIgnoreCase(toStation)) {
            dt.add("Destination of the route must be");
            dt.add("different than the starting point.");
            return;
        } else if (fromStation == null) {
            dt.add("Please select FROM station");
            //return;
        } else if (toStation == null) {
            dt.add("Please select TO station");
            return;
        }

        dt.add("FROM: " + fromStation + "\n");
        for (Station station : prague.getAllStations()) {
            if (station.getName().equalsIgnoreCase(toStation) || toStation == null) {
                dt.add("TO: " + station.getName());
                dt.add("DISTANCE: " + station.getDistance());
                dt.add(" ");
                dt.add("DESCRIPTION:");

                int numOfStations = station.getSPath().size();

                String lineName = "";
                for (int i = 0; i < numOfStations; ) {

                    Station s = station.getSPath().get(i);
                    String nextStation;
                    String lineCurrent = lineName;

                    // to display beginning line to take
                    if (i == 0) {
                        lineName = s.getRouteLine(station.getSPath().get(i + 1).getName());
                        dt.add("*** Take line " + lineName + " ***");
                        dt.add((i + 1) + ") " + s.getName());
                        i++;

                    } else {
                        dt.add((i + 1) + ") " + s.getName());
                        i++;

                        // Getting next station if not last already
                        if (i < numOfStations) {
                            nextStation = station.getSPath().get(i).getName();
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
}