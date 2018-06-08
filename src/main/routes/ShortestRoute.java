package main.routes;

import main.city.City;
import main.distance.Distance;

import java.util.HashMap;

/**
 * Created by shreyasmp on 6/8/18.
 *
 * Class to find the shortest path between two cities in map
 */

public class ShortestRoute {

    public HashMap<City, Distance> routeTable;

    // Constructor
    public ShortestRoute() {
        this.routeTable = new HashMap<>();
    }

    /**
     *  Recursive method for finding shortest route
     * @param begin
     * @param end
     * @return
     * @throws Exception
     */
    public int shortestRoutePossible(City begin, City end) throws Exception {
        return findShortestRoutePossible(begin, end, 0, 0);
    }

    /**
     *
     * Method to calculate shortest possible distance between two cities with distance weight sum as least
     *
     * @param begin
     * @param end
     * @param weight
     * @param shortestRoute
     * @return
     * @throws Exception
     */
    private int findShortestRoutePossible(City begin, City end, int weight, int shortestRoute) throws Exception{

        // Base case: checking if source and destination cities are in route map
        if(this.routeTable.containsKey(begin) && this.routeTable.containsKey(end)) {

            // if cities exists, traverse through all cities and possible routes until reaching destination
            // mark visited source city as visited
            begin.cityVisited = true;
            Distance distance = this.routeTable.get(begin);
            while(null != distance) {

                // if city is already visited or reached destination increase weight of distance
                if(distance.destinationCity == end || !distance.destinationCity.cityVisited)
                    weight += distance.weight;

                // if destination city is reached, compare weights reached until destination city and
                // compare the shortest route weight
                if(distance.destinationCity.equals(end)) {
                    if(shortestRoute == 0 || weight < shortestRoute)
                        shortestRoute = weight;
                    begin.cityVisited = false;
                    return shortestRoute;
                }

                else if(!distance.destinationCity.cityVisited) {
                    shortestRoute = findShortestRoutePossible(distance.destinationCity, end, weight, shortestRoute);
                    // As we are backtracking, reduce the weight
                    weight -= distance.weight;
                }
                distance = distance.nextCity;
            }
        }
        else
            throw new Exception("No Such Route");

        // Mark the visited city as unvisited at end of traversal flow ends
        begin.cityVisited = false;
        return shortestRoute;
    }
}
