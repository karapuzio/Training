package edu.training.third.action;

import edu.training.third.entity.Bus;
import edu.training.third.entity.BusStop;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dell on 10.10.2016.
 */
public class BusStopAction {
    private static final Logger LOGGER = LogManager.getLogger(BusStopAction.class);

    public void stopBus(BusStop busStop, Bus bus){
        try {
            busStop.getSemaphore().acquire();
            System.out.println("1" + busStop);
            int numberPeopleLeaveTheBus = 0;
            int numberPeopleEnterTheBus = 0;
            if (bus.getNumberOfPeople().get() != 0) {
                numberPeopleLeaveTheBus = new Random().nextInt(bus.getNumberOfPeople().get());
            }
            if (busStop.getNumberOfPeople().get() != 0)
            {
                numberPeopleEnterTheBus = new Random().nextInt(busStop.getNumberOfPeople().get());
            }
            if (bus.getNumberOfPeople().get() - numberPeopleLeaveTheBus + numberPeopleEnterTheBus > Bus.MAX_NUMBER_OF_PEOPLE){
                numberPeopleEnterTheBus = Bus.MAX_NUMBER_OF_PEOPLE - bus.getNumberOfPeople().get() + numberPeopleLeaveTheBus;
            }
            bus.setNumberOfPeople(new AtomicInteger(bus.getNumberOfPeople().get() - numberPeopleLeaveTheBus + numberPeopleEnterTheBus));
            busStop.setNumberOfPeople(new AtomicInteger(busStop.getNumberOfPeople().get() + numberPeopleLeaveTheBus - numberPeopleEnterTheBus));
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));
        }
        catch (InterruptedException e){
            LOGGER.log(Level.ERROR, e, e);
        }
        finally {
            busStop.getSemaphore().release();
        }
    }
}
