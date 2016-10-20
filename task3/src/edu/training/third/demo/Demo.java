package edu.training.third.demo;

import edu.training.third.entity.AllBusStops;
import edu.training.third.entity.Bus;
import edu.training.third.entity.BusStop;
import edu.training.third.entity.Route;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dell on 10.10.2016.
 */
public class Demo {
    private static final Logger LOGGER = LogManager.getLogger(Demo.class);
    private static final int NUMBER_OF_BUSES = 5;
    private static final int MAX_LENGTH_ROUTE = 20;

    public static void main(String... args){
        AllBusStops allBusStops = AllBusStops.getInstance();
        LOGGER.log(Level.DEBUG, allBusStops);
        List<BusStop> busStops = allBusStops.getAllBusStops();
        int numberOfAllStops = busStops.size();
        List<Bus> buses = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_BUSES; i++){
            int lengthRoute = random.nextInt(MAX_LENGTH_ROUTE);
            List<BusStop> busStopsList = new ArrayList<>();
            for (int j = 0; j < lengthRoute; j++){
                int index = random.nextInt(numberOfAllStops);
                busStopsList.add(busStops.get(index));
            }
            Route route = new Route(busStopsList);
            buses.add(new Bus(route, new AtomicInteger(random.nextInt(Bus.MAX_NUMBER_OF_PEOPLE))));
        }
        for (Bus bus : buses){
            bus.start();
        }
    }
}
