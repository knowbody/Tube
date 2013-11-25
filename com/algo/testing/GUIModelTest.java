package com.algo.testing;

import static org.junit.Assert.assertEquals;

import java.util.PriorityQueue;

import org.junit.Test;

import com.algo.model.PragueMetroMap;
import com.algo.model.Station;
import com.algo.model.GUIModel;
import com.algo.structures.LinkedList;

public class GUIModelTest {
	
    @Test
    /*
     * Testing correctness of Dijkstra algorithm
     */
    public void testDijkstra() throws Exception {
    	// Setting up map
    	PragueMetroMap prague = new PragueMetroMap();
        prague.setFromStation("Dejvicka");
        
        // Setting up priority queue
        PriorityQueue<Station> pq = new PriorityQueue<>();
        pq.addAll(prague.getAllStations());
        
        // Setting up model
        GUIModel m = new GUIModel();
        m.setMap(prague);
        m.setPQ(pq);
        
        // Running Dijkstra
        m.runDijkstra();
        
        // Test distance is correct
        double distance = prague.getStation("Depo Hostivar").getDistance();
        assertEquals(distance, 25, 2);
        
        distance = prague.getStation("Mustek").getDistance();
        assertEquals(distance, 8, 2);
        
        // Test shortest path is correct
        LinkedList<Station> path = prague.getStation("Invalidovna").getSPath();
        
        // First station same as station from
        assertEquals(path.get(0).getName(), "Dejvicka");
        
        // Last station same as station to
        assertEquals(path.get(path.size()).getName(), "Invalidovna");
        
        // We know that 6th station should be Namesti Republiky
        assertEquals(path.get(5).getName(), "Namesti Republiky");
    }

}
