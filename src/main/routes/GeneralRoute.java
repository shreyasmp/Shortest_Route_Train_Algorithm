package main.routes;

import main.city.City;
import main.distance.Distance;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shreyasmp on 6/7/18.
 *
 * General Route class which will provide the distance between two cities
 *
 */

public class GeneralRoute {

    // HashMap is considered for storing routes between cities
    public HashMap<City, Distance> routeTable;

    // Constructor
    public GeneralRoute() {
        this.routeTable = new HashMap<>();
    }

    /**
     * Method to find distance between two cities in specific path
     * @param cities
     * @return
     * @throws Exception
     */
    public int distanceBetweenCities(ArrayList<City> cities) throws Exception {

        int distance = 0;
        int depth = 0;

        // Base case: return 0 if no cities of only 1 city exists
        if(cities.size() < 2)
            return 0;

        // Check for city entry for each city in map
        for(int index = 0; index < cities.size() - 1; index++) {

            if(this.routeTable.containsKey(cities.get(index))) {
                Distance route = this.routeTable.get(cities.get(index));

                // if city exists , find next city and add distance and increment counter depth
                while(null != route) {
                    if(route.destinationCity.equals(cities.get(index + 1))) {
                        distance += route.weight;
                        depth++;
                        break;
                    }
                    route = route.nextCity;
                }
            }
            else
                throw new Exception("No Such Route");
        }

        // if counter is not same as cities - 1, no more routes between cities exist
        if(depth != cities.size() - 1)
            throw new Exception("No Such Route");

        return distance;
    }
}
