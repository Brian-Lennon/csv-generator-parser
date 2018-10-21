package com.blen.csv.utils;

import com.blen.csv.pojo.Person;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

/**
 * This class writes a list of Person object to CSV
 */
public class CsvWriter {

    private static final Logger LOGGER = Logger.getLogger(CsvWriter.class);

    public static final String HEADER = "firstName,lastName,age";
    private static final String NEW_LINE = "\n";

    /**
     * Write list of Person objects to CSV file
     * @param people
     *          List of people to write
     * @param filePath
     *          Location and the name of the file to be written
     */
    public void writeToCsv(List<Person> people, String filePath){
        LOGGER.info("Writing "+  people.size()+" people to csv in location "+ filePath);
        try(FileWriter writer = new FileWriter(filePath)){
            writer.append(HEADER);
            for (Person person: people){
                writer.append(NEW_LINE);
                writer.append(person.toString());
            }
            writer.flush();
        } catch (IOException e) {
            LOGGER.error("Exception occured",e);
        }
    }
}
