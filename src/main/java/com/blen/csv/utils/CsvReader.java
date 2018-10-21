package com.blen.csv.utils;

import com.blen.csv.pojo.Person;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class reads a csv file from a specified
 * location and converts it to a list of Person
 * objects
 */
public class CsvReader {
    private static Logger LOGGER = Logger.getLogger(CsvReader.class);
    private static String DELIMITER=",";
    private String filePath;

    public CsvReader(String filePath){
        this.filePath = filePath;
    }

    /**
     * Reads a csv file. The location is specified
     * when this object is instantiated.
     *
     * @return List of Person
     */
    public List<Person> readCsv(){
        LOGGER.info("Reading Csv from: " + filePath);
        List<Person> people =  new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains(CsvWriter.HEADER)){
                    continue;
                }
                String[] personDetails = line.split(DELIMITER);
                people.add(new Person(personDetails[0],personDetails[1],Integer.parseInt(personDetails[2])));
            }
        } catch (IOException e) {
            LOGGER.error("Something went wrong",e);
        }
        return people;
    }
}
