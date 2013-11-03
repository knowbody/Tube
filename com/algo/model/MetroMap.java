/**
 * Prague Metro - Journey Planner
 * Project for - COMP1555: Algorithms and Modelling
 * Authors:
 *      Mateusz Zatorski (000738254)
 *      Patrik Fuhrmann (000725089)
 *      Irmantas Marozas (000708431)
 */

/*
* class defines all of the methods needed to build a map
* */
package com.algo.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class MetroMap {
    private HashMap<String, Station> stations = new HashMap<>();

    /**
     * Add new station to the map.
     *
     * @param name of the station
     */
    public MetroMap addStation(String name) {
        this.stations.put(name, new Station(name));
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
    	return this.stations.get(name);
    }


    /**
     * Get all stations on the map.
     *
     * @return All stations as ArrayList
     */
    public Collection<Station> getAllStations() {
        return this.stations.values();
    }


    /**
     * Get all stations names from the map
     *
     * @return Stations names in array representation
     */
    public String[] getAllStationsNamesAsArray() {
        String[] arr = new String[this.getNumberOfStations()];

        int i = 0;
        for (Station s : this.getAllStations()) {
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
        this.stations.put(s.getName(), s);
    }
}
