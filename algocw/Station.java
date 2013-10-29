/*
 * Station object representation 
 */
package algocw;

import java.util.ArrayList;

public class Station implements Comparable<Station> {
    private ArrayList<Route> routes = new ArrayList<>(); // Routes from the station
    private ArrayList<Station> sPath = new ArrayList<>(); // Shortest path
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
        for (Route route : this.routes) {
            if (route.getGoingTo().name.equals(to)) {
                return route.getLine();
            }
        }
        return null;
    }
    
    public  ArrayList<Station> getSPath() {
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
