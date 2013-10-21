/*
 * Station object representation 
 */
package algocw;

import java.util.ArrayList;

/**
 *
 * @author Patres
 */
public class Station implements Comparable<Station> {
    private ArrayList<Route> routes = new ArrayList<>();
    private String name;
    private Integer distance = 9999999;
    public ArrayList<Station> sPath = new ArrayList<>();
    
    public Station() {
        this("");
    }
    
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
    
    public int getDistance() {
        return this.distance;
    }
    
    public Route getRoute(String to) {
        for (Route route : this.routes) {
            if (route.getGoingTo().name.equals(to)) {
                return route;
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
    
    public void setDistance(int dist) {
        this.distance = dist;
    }
    
    @Override
    public int compareTo(Station s) {
        return this.distance.compareTo(s.getDistance());
    }
}
