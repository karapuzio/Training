package edu.training.third.entity;

import edu.training.third.reader.BusStopsReader;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Dell on 10.10.2016.
 */
public class AllBusStops {
    private static AllBusStops instance;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static List<BusStop> allBusStops;
    private static final String FILE_NAME = "files/AllBusStops.txt";

    private AllBusStops(List<BusStop> allBusStops) {
        this.allBusStops = allBusStops;
    }

    public static AllBusStops getInstance(){
        if (!instanceCreated.getAndSet(true)){
            lock.lock();
            try{
                if (instance == null){
                    instance = new AllBusStops(BusStopsReader.busStopsReader(FILE_NAME));
                }
            }
           finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public List<BusStop> getAllBusStops() {
        return allBusStops;
    }

    public void add(BusStop busStop){
        allBusStops.add(busStop);
    }

    @Override
    public String toString() {
        return "AllBusStops{" +
                "allBusStops=" + allBusStops +
                '}';
    }
}
