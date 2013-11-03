package com.algo.model;

import java.util.PriorityQueue;

public class GUIModel {

    private PriorityQueue<Station> pq = new PriorityQueue<Station>();
    private MetroMap prague = new PragueMetroMap();
    private String details;

    private void doSearch(String from, String to) {
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
        printResults(fromStation, toStation); // to changed to toStation
    }

    private void dijkstra() {
        while (!pq.isEmpty()) { // While priority queue is not empty
            Station station = pq.remove(); // Remove station from priority queue
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

    private void printResults(String fromStation, String toStation) {
        details = "From: " + fromStation + "\n";
        for (Station station : prague.getAllStations()) {
            if (station.getName() == toStation || toStation == null) {
                details += "To: " + station.getName() + "\nDistance: " + station.getDistance() + "\nDescription:\n";
                int numOfStations = station.getSPath().size();

                String lineName = "";
                for (int i = 0; i < numOfStations; ) {

                    Station s = station.getSPath().get(i);
                    String nextStation;
                    String lineCurrent = lineName;

                    details += s.getName() + "\n";
                        details += "line " + s.getRouteLine(nextStation) + "\n"

                    // Getting next station if not last already
                    if (i < numOfStations) {
                        nextStation = station.getSPath().get(i).getName();
                        lineName = s.getRouteLine(nextStation);

                        // if the previous line is the same as next do nothing, otherwise say to change line
                        if (lineCurrent.equalsIgnoreCase(lineName)) {
                        } else {
                            details += " - change for line " + lineName + "\n";
                        }
                    }
                }
                details += "\n";
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

    public String getDetails() {
        return details;
    }

}

