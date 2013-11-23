package com.algo.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.algo.model.Route;
import com.algo.model.Station;
import com.algo.structures.ArrayList;

public class StationTest {
	
	private Station s1;
	private Station s2;
	private Station s3;
	
	@Before 
	public void beforeEachTest() {
		s1 = new Station("Station1");
    	s2 = new Station("Station2");
    	s3 = new Station("Station3");
	}
	
    @Test
    public void testGetAllRoutes() throws Exception { 	
		s1.addRoute(s2, 2, "A");
		s1.addRoute(s3, 1, "A");
		
		ArrayList<Route> routes = s1.getAllRoutes();
		
		Route r1 = (Route) routes.get(0);
		String goigToName = r1.getGoingTo().getName();
		assertEquals(goigToName, "Station2");		
		
		Route r2 = (Route) routes.get(1);
		goigToName = r2.getGoingTo().getName();
		assertEquals(goigToName, "Station3");
    }

    @Test
    public void testGetName() throws Exception {
    	assertEquals(s1.getName(), "Station1");
    }

    @Test
    public void testGetDistance() throws Exception {
    	s1.setDistance(10.0);
    	assertEquals(s1.getDistance(), 10.0, 2);
    }

    @Test
    public void testGetRouteLine() throws Exception {
    	s1.addRoute(s2, 2, "A");
    	
    	assertEquals(s1.getRouteLine("Station2"), "A");
    }

    @Test
    public void testGetSPath() throws Exception {
    	s1.getSPath().add(s2);
    	s1.getSPath().add(s3);
    	
    	String stationName = s1.getSPath().get(0).getName();
    	assertEquals(stationName, "Station2");
    	
    	stationName = s1.getSPath().get(1).getName();
    	assertEquals(stationName, "Station3");
    }

    @Test
    public void testAddRoute() throws Exception {
    	s1.addRoute(s2, 10, "B");
    	
    	Route r = (Route) s1.getAllRoutes().get(0);
    	String stationName = r.getGoingTo().getName();
    	assertEquals(stationName, "Station2");
    }

    @Test
    public void testSetDistance() throws Exception {
    	s1.setDistance(15.0);
    	assertEquals(s1.getDistance(), 15.0, 2);
    }

}
