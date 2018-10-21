package com.blen.csv.main;


import com.blen.csv.pojo.Person;
import com.blen.csv.utils.ArgumentValidator;
import com.blen.csv.utils.CsvWriter;
import com.blen.csv.utils.PersonGenerator;

import java.util.List;
import java.util.Random;

/**
 * The class solves the following problem:
 *
 * This program would have as input number of lines
 *
 * (N) in the file and file path (P).
 *
 * Then it would generate CSV file at local file system:
 *
 * path P that contains N lines, where each line would be in format:
 *
 * SomeFirstName,SomeLastName,RandomAge
 *
 * Header would also be generated.
 *
 * Make sure that generator can generate at least 100 unique combinations of FirstName,LastName pairs.
 *
 * Make sure that RandomAge is in interval: 1<age<100
 *
 * Make sure that N is in valid interval 1<N<10 000
 */
public class CsvGenerator {

    private Integer numberOfLines;
    private String filePath;
    private PersonGenerator personGenerator;
    private CsvWriter csvWriter;


    public CsvGenerator(String numberOfLines, String filePath){
        this.numberOfLines = Integer.parseInt(numberOfLines);
        this.filePath = filePath;
        personGenerator = new PersonGenerator();
        csvWriter = new CsvWriter();
    }

    public static void main(String[] args) {
        ArgumentValidator.validateGeneratorArguments(args);
        new CsvGenerator(args[0],args[1]).generateCsvUsingRandomValues();
    }

    private void generateCsvUsingRandomValues(){
        List<Person> people = personGenerator.generateRandomListOfPeople(numberOfLines);
        csvWriter.writeToCsv(people,filePath);
    }
}
