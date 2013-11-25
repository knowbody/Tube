package com.algo.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.algo.model.MetroMap;
import com.algo.model.Route;
import com.algo.model.Station;

public class MetroMapTest {
	
	private MetroMap map;
	
	@Before 
	public void beforeEachTest() {
		map = new MetroMap();
    	map.addStation("Station1");
		map.addStation("Station2");
		map.addStation("Station3");
	}
	
    @Test
    public void testAddStation() throws Exception {
    	assertEquals(map.getStation("Station1").getName(), "Station1");
    }

	@Test
	public void testAddRoute() throws Exception {
		map.addRoute("Station1", "Station2", 10, "C");
		
		// From - To
		Route r1 = (Route) map.getStation("Station1").getAllRoutes().get(0);
		String stationName = r1.getGoingTo().getName();
    	assertEquals(stationName, "Station2");
    	
    	// T - From
		Route r2 = (Route) map.getStation("Station2").getAllRoutes().get(0);
		stationName = r2.getGoingTo().getName();
    	assertEquals(stationName, "Station1");
	}

	@Test
	public void testSetFromStation() throws Exception {
		map.setFromStation("Station1");
		
		// From station must be 0
		double d = map.getStation("Station1").getDistance();
		assertEquals(d, 0.0, 2);
		
		// Other stations must be infinity
		d = map.getStation("Station2").getDistance();
		assertTrue(Double.isInfinite(d));
		d = map.getStation("Station3").getDistance();
		assertTrue(Double.isInfinite(d));
	}

	@Test
	public void testGetStation() throws Exception {
		String stationName = map.getStation("Station2").getName();
		assertEquals(stationName, "Station2");
	}

	@Test
	public void testGetAllStations() throws Exception {
		Collection<Station> stations = map.getAllStations();
		
		int i = 1;
		for (Station s : stations) {
			if (i <= stations.size()) {
				String stationName = s.getName();
				assertEquals(stationName, "Station" + i);
			}
			i++;
		}
	}

	@Test
	public void testGetAllStationsNamesAsArray() throws Exception {
		String[] stations = map.getAllStationsNamesAsArray();
		
		for (int i = 0; i < stations.length; i++) {
			assertEquals(stations[i], "Station" + (i + 1));
		}
	}
	
}
