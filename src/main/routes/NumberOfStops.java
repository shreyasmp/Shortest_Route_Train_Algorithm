package main.routes;

import main.city.City;
import main.distance.Distance;

import java.util.HashMap;

/**
 * Created by shreyasmp on 6/8/18.
 *
 * Class to calculate number of stops before reaching to destination city from source city
 */

public class NumberOfStops {

    // HashMap is considered for storing routes between cities
    public HashMap<City, Distance> routeTable;

    // Constructor
    public NumberOfStops() {
        this.routeTable = new HashMap<>();
    }

    /**
     *  Method to recursively call routing finding algorithm
     *
     * @param begin
     * @param end
     * @param maxStops
     * @return
     * @throws Exception
     */
    public int numberOfStops(City begin, City end, int maxStops) throws Exception {
        return findMaxStops(begin, end, 0, maxStops);
    }

    /**
     *
     * Method calculates maximum stops needed from source to destination city with depth factor of tree
     *
     * @param begin
     * @param end
     * @param depth
     * @param maxStops
     * @return
     * @throws Exception
     */
    private int findMaxStops(City begin, City end, int depth, int maxStops) throws Exception {
        int routes = 0;

        // Base Case: to check if source and destination cities exist
        if(this.routeTable.containsKey(begin) && this.routeTable.containsKey(end)) {

            // if cities exists in route map, traverse all possible routes till reaching destination
            // Checking if depth of tree is within max limit and not out of bound
            // Mark visited city as you visit
            depth++;
            if(depth > maxStops)
                return 0;
            begin.cityVisited = true;
            Distance distance = this.routeTable.get(begin);
            while(distance != null) {

                // if destination city is reached, increment route counter
                if(distance.destinationCity.equals(end)) {
                    routes++;
                    distance = distance.nextCity;
                    continue;
                }
                // if destination city is not reached, recursively traverse until destination city is reached
                else if(!distance.destinationCity.cityVisited) {
                    routes += findMaxStops(distance.destinationCity, end, depth, maxStops);
                    depth--;
                }
                distance = distance.nextCity;
            }
        }
        else
            throw new Exception("No Such Route");

        begin.cityVisited = false;
        return routes;
    }
}
