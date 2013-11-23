package com.algo.model;

import com.algo.structures.LinkedList;
import com.algo.structures.ArrayList;


public class Station implements Comparable<Station> {
    private ArrayList<Route> routes = new ArrayList<>(); // Routes from the station
    private LinkedList<Station> sPath = new LinkedList<>(); // Shortest path stored in sequential list
    private String name; // Station name
    private Double distance = Double.POSITIVE_INFINITY;

    public Station(String name) {
        this.name = name;
    }

    // Getters
    public ArrayList<Route> getAllRoutes() {
        return this.routes;
    }

    public String getName() {
        return this.name;
    }

    public Double getDistance() {
        return this.distance;
    }

    public String getRouteLine(String to) {
        for (int i = 0; i < this.routes.getSize(); i++) {
            Route route = (Route) this.routes.get(i);
            if (route.getGoingTo().name.equals(to)) {
                return route.getLine();
            }
        }
        return null;
    }

    public LinkedList<Station> getSPath() {
        return this.sPath;
    }

    // Setters
    public void addRoute(Station goingTo, int length, String line) {
        this.routes.add(new Route(goingTo, length, line));
    }

    public void setDistance(Double dist) {
        this.distance = dist;
    }


    /**
     * Comparator
     */
    @Override
    public int compareTo(Station s) {
        return this.distance.compareTo(s.getDistance());
    }
}