package main.distance;

import main.city.City;

/**
 * Created by shreyasmp on 6/7/18.
 *
 * Model Distance class which is like an edge between two points/cities here.
 *
 */

public class Distance {

    public City originCity;
    public City destinationCity;
    public int weight;
    public Distance nextCity;

    // Constructor
    public Distance(City originCity, City destinationCity, int weight) {
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.weight = weight;
        this.nextCity = null;
    }

    // Method to link the next City from previous visited City
    public Distance nextCity(Distance distance) {
        this.nextCity = distance;
        return this;
    }

    // Getter and Setters
    public City getOriginCity() {
        return originCity;
    }

    public void setOriginCity(City originCity) {
        this.originCity = originCity;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(City destinationCity) {
        this.destinationCity = destinationCity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Distance getNextCity() {
        return nextCity;
    }

    public void setNextCity(Distance nextCity) {
        this.nextCity = nextCity;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "originCity=" + originCity +
                ", destinationCity=" + destinationCity +
                ", weight=" + weight +
                ", nextCity=" + nextCity +
                '}';
    }
}
