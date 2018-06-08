package test.traintest;

import main.city.City;
import main.distance.Distance;
import main.routes.GeneralRoute;
import main.routes.NumberOfStops;
import main.routes.RoutesWithinCity;
import main.routes.ShortestRoute;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by shreyasmp on 6/8/18.
 */

public class TestClass {

    static GeneralRoute generalRoute;
    static NumberOfStops numberOfStops;
    static ShortestRoute shortestRoute;
    static RoutesWithinCity routesWithinCity;

    static City A;
    static City B;
    static City C;
    static City D;
    static City E;
    public ArrayList<City> route = new ArrayList<>();

    /**
     *  Setup Test method for building graph for cities connected by distance weights
     * @throws Exception
     */

    @BeforeClass
    public static void setUp() throws Exception {
        generalRoute = new GeneralRoute();
        numberOfStops = new NumberOfStops();
        shortestRoute = new ShortestRoute();
        routesWithinCity = new RoutesWithinCity();

        A = new City("A");
        B = new City("B");
        C = new City("C");
        D = new City("D");
        E = new City("E");

        generalRoute.routeTable.put(A, new Distance(A, B, 5).nextCity(new Distance(A, D, 5).nextCity(new Distance(A, E, 7))));
        generalRoute.routeTable.put(B, new Distance(B, C, 4));
        generalRoute.routeTable.put(C, new Distance(C, D, 8).nextCity(new Distance(C, E, 2)));
        generalRoute.routeTable.put(D, new Distance(D, C, 8).nextCity(new Distance(D, E, 6)));
        generalRoute.routeTable.put(E, new Distance(E, B, 3));

        numberOfStops.routeTable.put(A, new Distance(A, B, 5).nextCity(new Distance(A, D, 5).nextCity(new Distance(A, E, 7))));
        numberOfStops.routeTable.put(B, new Distance(B, C, 4));
        numberOfStops.routeTable.put(C, new Distance(C, D, 8).nextCity(new Distance(C, E, 2)));
        numberOfStops.routeTable.put(D, new Distance(D, C, 8).nextCity(new Distance(D, E, 6)));
        numberOfStops.routeTable.put(E, new Distance(E, B, 3));

        shortestRoute.routeTable.put(A, new Distance(A, B, 5).nextCity(new Distance(A, D, 5).nextCity(new Distance(A, E, 7))));
        shortestRoute.routeTable.put(B, new Distance(B, C, 4));
        shortestRoute.routeTable.put(C, new Distance(C, D, 8).nextCity(new Distance(C, E, 2)));
        shortestRoute.routeTable.put(D, new Distance(D, C, 8).nextCity(new Distance(D, E, 6)));
        shortestRoute.routeTable.put(E, new Distance(E, B, 3));

        routesWithinCity.routeTable.put(A, new Distance(A, B, 5).nextCity(new Distance(A, D, 5).nextCity(new Distance(A, E, 7))));
        routesWithinCity.routeTable.put(B, new Distance(B, C, 4));
        routesWithinCity.routeTable.put(C, new Distance(C, D, 8).nextCity(new Distance(C, E, 2)));
        routesWithinCity.routeTable.put(D, new Distance(D, C, 8).nextCity(new Distance(D, E, 6)));
        routesWithinCity.routeTable.put(E, new Distance(E, B, 3));
    }

    // Test cases as per the input requirements
    @Test
    public void test_DistanceBetween_ABC() throws Exception {
        route.add(A);
        route.add(B);
        route.add(C);
        assertEquals(9, generalRoute.distanceBetweenCities(route));
        route.clear();
    }

    @Test
    public void test_DistanceBetween_AD() throws Exception {
        route.add(A);
        route.add(D);
        assertEquals(5, generalRoute.distanceBetweenCities(route));
        route.clear();
    }

    @Test
    public void test_DistanceBetween_ADC() throws Exception {
        route.add(A);
        route.add(D);
        route.add(C);
        assertEquals(13, generalRoute.distanceBetweenCities(route));
        route.clear();
    }

    @Test
    public void test_DistanceBetween_AEBCD() throws Exception {
        route.add(A);
        route.add(E);
        route.add(B);
        route.add(C);
        route.add(D);
        assertEquals(22, generalRoute.distanceBetweenCities(route));
        route.clear();
    }

    @Test
    public void test_DistanceBetween_AED() throws Exception {
        route.add(A);
        route.add(E);
        route.add(D);
        assertEquals(-1, generalRoute.distanceBetweenCities(route));
        route.clear();
    }

    @Test
    public void test_NumberOfStops_CC3() throws Exception {
        int stops = numberOfStops.numberOfStops(C, C, 3);
        assertEquals(2, stops);
    }

    @Test
    public void test_NumberOfSteps_AC4() throws Exception {
        int stops = numberOfStops.numberOfStops(A, C, 4);
        assertEquals(4, stops);
    }

    @Test
    public void test_ShortestRoutePossible_AC() throws Exception {
        int shortRoute = shortestRoute.shortestRoutePossible(A, C);
        assertEquals(9, shortRoute);
    }

    @Test
    public void test_ShortestRoutePossible_BB() throws Exception {
        int shortRoute = shortestRoute.shortestRoutePossible(B, B);
        assertEquals(9, shortRoute);
    }

    @Test
    public void test_RoutesWithin_CC30() throws Exception {
        int routesWithinCC = routesWithinCity.numberOfRoutesWithinCity(C, C, 30);
        assertEquals(7, routesWithinCC);
    }
}
