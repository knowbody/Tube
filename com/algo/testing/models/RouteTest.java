package com.algo.testing.models;

import com.algo.model.Route;
import com.algo.model.Station;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouteTest {

    private Station s1;
    private Route r1;

    @Before
    public void beforeEachTest() {
        s1 = new Station("Station1");
        r1 = new Route(s1, 2, "A");
    }

    @Test
    public void testGetLength() throws Exception {
        assertEquals(r1.getLength(), 2.0, 2);
    }

    @Test
    public void testGetGoingTo() throws Exception {
        assertEquals(r1.getGoingTo().getName(), "Station1");
    }

    @Test
    public void testGetLine() throws Exception {
        assertEquals(r1.getLine(), "A");
    }

}
