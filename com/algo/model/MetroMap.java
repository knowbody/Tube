package com.algo.model;

import com.algo.structures.HashTable;

import java.util.Arrays;
import java.util.Collection;


public class MetroMap {

    private HashTable stations = new HashTable();
    private String[] stationsStringArr;

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
        // Adding route to - from
        sTo.addRoute(sFrom, length, line);

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
        return this;
    }


    /**
     * Get single station
     *
     * @param name Name of the station
     * @return object representation of the station
     */
    public Station getStation(String name) {
        return (Station) this.stations.get(name);
    }


    /**
     * Get all stations on the map.
     *
     * @return All stations as ArrayList
     */
    public Collection<Station> getAllStations() {
        return stations.getValues();
    }


    /**
     * Get all stations names from the map
     *
     * @return Stations names in array representation
     */
    public String[] getAllStationsNamesAsArray() {
        // Checking if have to do hard work or it was done already
        if (stationsStringArr == null) {
            String[] arr = stations.getKeys();
            Arrays.sort(arr);
            stationsStringArr = arr;
        }

        return stationsStringArr;
    }

    /**
     * Get the number of stations on the map
     *
     * @return Number of the stations
     */
    /*private int getNumberOfStations() {
        return this.stations.getSize();
    }*/
}
