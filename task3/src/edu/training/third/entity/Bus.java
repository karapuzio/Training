package edu.training.third.entity;

import edu.training.third.action.BusStopAction;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dell on 10.10.2016.
 */
public class Bus extends Thread{
    private Route route;
    private AtomicInteger numberOfPeople;
    public static final int MAX_NUMBER_OF_PEOPLE = 80;
    private static final BusStopAction BUS_STOP_ACTION = new BusStopAction();
    private static final Logger LOGGER = LogManager.getLogger(Bus.class);

    public Bus(Route route, AtomicInteger numberOfPeople) {
        this.route = route;
        this.numberOfPeople = numberOfPeople;
    }

    public AtomicInteger getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(AtomicInteger numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public void run(){
        for (BusStop busStop : route.getBusStops()){
            try{
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));
                BUS_STOP_ACTION.stopBus(busStop, this);
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));
                System.out.println("2" + busStop);
            }
            catch (InterruptedException e){
                LOGGER.log(Level.ERROR, e);
            }
        }
        return;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "route=" + route +
                ", numberOfPeople=" + numberOfPeople +
                ", BUS_STOP_ACTION=" + BUS_STOP_ACTION +
                '}';
    }
}
