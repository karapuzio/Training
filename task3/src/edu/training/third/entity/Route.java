package edu.training.third.entity;

import java.util.List;

/**
 * Created by Dell on 10.10.2016.
 */
public class Route {
    private List<BusStop> busStops;

    public Route(List<BusStop> busStops) {
        this.busStops = busStops;
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }

    public void setBusStops(List<BusStop> busStops) {
        this.busStops = busStops;
    }

    @Override
    public String toString() {
        return "Route{" +
                "busStops=" + busStops +
                '}';
    }
}
