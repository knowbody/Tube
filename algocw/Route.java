/*
 * Route object representation.
 */
package algocw;

public class Route {
    private Station goingTo; // Destination
    private int length; // Length of the route
    private String line; // Route line
    
    /**
     * New route object with predefined data 
     * 
     * @param to  Destination of the route
     * @param length  Length of the route
     * @param line  Line letter
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
    
    /**
     * Get route destination
     * 
     * @return Station which is destination
     */
    public Station getGoingTo() {
        return this.goingTo;
    }
    
    /**
     * Get line letter
     * 
     * @return Line letter
     */
    public String getLine() {
        return this.line;
    }
}
