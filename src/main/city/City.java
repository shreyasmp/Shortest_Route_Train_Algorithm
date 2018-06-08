package main.city;

/**
 * Created by shreyasmp on 6/7/18.
 *
 * City Model Class which has city name and a boolean value for visited for tracking.
 * Class has toString, equals and hashcode methods overridden as we use the class as key
 *
 */

public class City {

    public String cityName;

    public boolean cityVisited;

    // Constructor
    public City(String cityName) {
        this.cityName = cityName;
        this.cityVisited = false;
    }

    // Getter and Setter Methods
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean isCityVisited() {
        return cityVisited;
    }

    public void setCityVisited(boolean cityVisited) {
        this.cityVisited = cityVisited;
    }

    // Overridden default methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (cityVisited != city.cityVisited) return false;
        return cityName != null ? cityName.equals(city.cityName) : city.cityName == null;

    }

    @Override
    public int hashCode() {
        if(this.cityName == null) return 0;
        return this.cityName.hashCode();
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", cityVisited=" + cityVisited +
                '}';
    }
}
