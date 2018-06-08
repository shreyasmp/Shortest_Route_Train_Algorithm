package main.routes;

import main.city.City;
import main.distance.Distance;

import java.util.HashMap;

/**
 * Created by shreyasmp on 6/8/18.
 *
 * Class to provide possible routes between cities
 *
 */

public class RoutesWithinCity {

    public HashMap<City, Distance> routeTable;

    // Constructor
    public RoutesWithinCity() {
        this.routeTable = new HashMap<>();
    }

    /**
     * Method to recursively call number of routes within cities
     * @param begin
     * @param end
     * @param maxDistances
     * @return
     * @throws Exception
     */
    public int numberOfRoutesWithinCity(City begin, City end, int maxDistances) throws Exception {
        return findNumberOfRoutesWithinCity(begin, end, 0, maxDistances);
    }

    /**
     *
     * Method to find the max number of routes between cities
     * @param begin
     * @param end
     * @param weight
     * @param maxDistance
     * @return
     * @throws Exception
     */
    private int findNumberOfRoutesWithinCity(City begin, City end, int weight, int maxDistance) throws Exception {
        int routes = 0;

        // Base case: to check if source city and destination city are in route table
        if(this.routeTable.containsKey(begin) && this.routeTable.containsKey(end)) {
            Distance distance = this.routeTable.get(begin);

            // traverse all cities if distance between them exists checking for destination cities
            while(null != distance) {
                weight += distance.weight;

                // continue traversing even if distance between cities is less than max distance until it reaches max
                if(weight <= maxDistance) {
                    if(distance.destinationCity.equals(end)) {
                        routes++;
                        routes += findNumberOfRoutesWithinCity(distance.destinationCity, end, weight, maxDistance);
                        distance = distance.nextCity;
                        continue;
                    }
                    else {
                        // decrementing the distance weight values as we backtrack
                        routes += findNumberOfRoutesWithinCity(distance.destinationCity, end, weight, maxDistance);
                        weight -= distance.weight;
                    }
                }
                else
                    weight -= distance.weight;

                distance = distance.nextCity;
            }
        }
        else
            throw new Exception("No Such Route");

        return routes;
    }
}
