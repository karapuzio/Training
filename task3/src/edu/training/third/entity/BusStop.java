package edu.training.third.entity;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dell on 10.10.2016.
 */
public class BusStop {
    private String name;
    private AtomicInteger numberOfPeople;
    private static final int BUS_STOP_SIZE = 3;
    private final Semaphore semaphore = new Semaphore(BUS_STOP_SIZE, true);

    public BusStop(String name, AtomicInteger numberOfPeople) {
        this.name = name;
        this.numberOfPeople = numberOfPeople;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AtomicInteger getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(AtomicInteger numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        BusStop busStop = (BusStop) object;
        if (numberOfPeople != busStop.numberOfPeople){
            return false;
        }
        return !(name != null ? !name.equals(busStop.name) : busStop.name != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (numberOfPeople != null ? numberOfPeople.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BusStop{" +
                "name='" + name + '\'' +
                ", numberOfPeople=" + numberOfPeople +
                '}';
    }
}
