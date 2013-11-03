/*
 * Ordinary Metro map
 */
package com.algo.model;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class MetroMap {
    private ArrayList<Station> stations = new ArrayList<>(); // List of Metro stations

    /**
     * Add new station to the map.
     *
     * @param name of the station
     */
    public MetroMap addStation(String name) {
        this.stations.add(new Station(name));
        return this;
    }


    /**
     * Adding new route on the map to the specific station.
     * As we are using directed graph we have to
     * add routes from-to and to-from as well.
     *
     * @param from   the station where the journey starts
     * @param to     the station - destination of the journey
     * @param length distance in-between the stations
     * @param line   name of the line
     */
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


    /**
     * Setting up starting point in the graph.
     * As we are using priority queue we need have
     * to setup this distance on this station to 0
     * and all stations distances will be infinity.
     *
     * @param from Name of the from station
     */
    public MetroMap setFromStation(String from) {
        Station station = getStation(from);
        station.setDistance(0.0);
        this.updateStation(station);

        return this;
    }


    /**
     * Get single station
     *
     * @param name Name of the station
     * @return object representation of the station
     */
    public Station getStation(String name) {
        for (Station s : this.stations) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        return null;
    }


    /**
     * Get all stations on the map.
     *
     * @return All stations as ArrayList
     */
    public ArrayList<Station> getAllStations() {
        return this.stations;
    }


    /**
     * Get all stations names from the map
     *
     * @return Stations names in array representation
     */
    public String[] getAllStationsNamesAsArray() {
        String[] arr = new String[this.getNumberOfStations()];

        int i = 0;
        for (Station s : this.stations) {
            arr[i] = s.getName();
            i++;
        }

        Arrays.sort(arr); // Sorting alphabetically

        return arr;
    }

    /**
     * Get the number of stations on the map
     *
     * @return Number of the stations
     */
    private int getNumberOfStations() {
        return this.stations.size();
    }


    /**
     * Updates specific station by new representation
     *
     * @param s Station to be updated
     */
    private void updateStation(Station s) {
        this.stations.remove(s);
        this.stations.add(s);
    }
}