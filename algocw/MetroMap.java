/*
 * Ordinary Metro map
 */
package algocw;

import java.util.ArrayList;

public class MetroMap {
    private ArrayList<Station> stations = new ArrayList<Station>(); // List of Metro stations
    
    public MetroMap addStation(String name) {
        Station station = new Station(name);
        this.stations.add(station);
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
         this.setDistance(dist);
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
    
    public String[] getAllStationsAsArray() {
    	String[] arr = new String[this.getNumberOfStations()];
    	int i = 0;
    	
    	for (Station s : this.stations) {
    		arr[i] = s.getName();
    		i++;
        }
    	
    	return arr;
    }
    
    public int getNumberOfStations() {
    	return this.stations.size();
    }
    
    public void updateStation(Station s) {
        this.stations.remove(s);
        this.stations.add(s);
    }
}
