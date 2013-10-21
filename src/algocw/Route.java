/*
 * Route object representation. Each station can have 0 to 2 routes
 */
package algocw;

/**
 *
 * @author Patrik Fuhrmann
 */
public class Route {
    private Station goingTo; // Destination
    private int length; // Length of the route
    private String line; // Route line
    
    /**
     * New null object
     */
    public Route() {
        this(null, 0, "");
    }
    
    /**
     * New object with predefined data 
     * 
     * @param to  Destination of the route
     * @param length  Length of the route
     */
    public Route(Station to, int length, String line) {
        this.goingTo = to;
        this.length = length;
        this.line = line;
    }
    
    /**
     * Get length of the route
     * 
     * @return  Length of the route
     */
    public int getLength() {
        return this.length;
    }
    
    public Station getGoingTo() {
        return this.goingTo;
    }
}
