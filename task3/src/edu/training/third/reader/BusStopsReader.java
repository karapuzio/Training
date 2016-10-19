package edu.training.third.reader;

import edu.training.third.entity.BusStop;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Dell on 11.10.2016.
 */
public class BusStopsReader {
    private static final Logger LOGGER = LogManager.getLogger(BusStopsReader.class);
    private static final String SPLIT_STRING = "\\s";
    private static final int NAME_PARAMETER = 0;
    private static final int NUMBER_OF_PEOPLE_PARAMETER = 1;

    public static List<BusStop> busStopsReader(String fileName){
        List<String> busStopsList;
        List<BusStop> busStops = new ArrayList<>();
        try{
            busStopsList = Files.readAllLines(Paths.get(fileName));
        }
        catch (IOException e){
            LOGGER.log(Level.FATAL, "Do not correct file with the bus stops!", e);
            throw new RuntimeException("Do not correct file with the bus stops!", e);
        }
        for (String busStop : busStopsList){
            String[] listOfParameters = busStop.split(SPLIT_STRING);
            try {
                busStops.add(new BusStop(listOfParameters[NAME_PARAMETER], new AtomicInteger(Integer.parseInt(listOfParameters[NUMBER_OF_PEOPLE_PARAMETER]))));
            }
            catch (NumberFormatException e){
                LOGGER.log(Level.ERROR, "Not correct parameters for creating bus stop.", e);
            }
        }
        return busStops;
    }
}
