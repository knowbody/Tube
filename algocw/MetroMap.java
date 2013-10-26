/*
 * Object representration of the metro map
 */
package algocw;

import java.util.ArrayList;

public class MetroMap {
    private ArrayList<Station> stations = new ArrayList<Station>(); // List of metro stations
    private Station lastS; // Holder for last inserted station
    
    public MetroMap addStation(String name) {
        Station station = new Station(name);
        this.stations.add(station);
        this.lastS = station;
        return this;
    }
    
    public MetroMap addRoute(String from, String to, int length, String line) {
        Station sFrom = getStation(from);
        Station sTo = getStation(to);
        
        // Adding route from - to
        sFrom.addRoute(sTo, length, line);
        updateStation(sFrom);
        
        // Adding route to - from
        sTo.addRoute(sFrom, length, line);
        updateStation(sTo);

        return this;
    }
    
    public MetroMap setDistance(Double dist) {
         this.lastS.setDistance(dist);
         return this;
    }
    
    public MetroMap setFromStation(String from) {
        Station station = getStation(from);
        station.setDistance(0.0);
        this.stations.remove(station);
        this.stations.add(station);
        return this;
    }
    
    public Station getStation(String name) {
        for (Station s : this.stations) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }
    
    public ArrayList<Station> getAllStations() {
        return this.stations;
    }
    
    public void updateStation(Station s) {
         this.stations.remove(s);
        this.stations.add(s);
    }
}
