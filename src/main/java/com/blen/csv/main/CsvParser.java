package com.blen.csv.main;

import com.blen.csv.dao.DatabaseAccessManager;
import com.blen.csv.pojo.Person;
import com.blen.csv.utils.ArgumentValidator;
import com.blen.csv.utils.CsvReader;

import java.sql.SQLException;
import java.util.List;

/**
 * This class solves the following problem.
 *
 * This program would have as input file path (P) and database URL (U).
 *
 * It would read CSV file from file P. This is file generated by CSV generator program from Task 1.
 *
 * CSV parser would read CVS file line by line (skipping header)
 *
 * and would write those lines into database that can be found at URL U.
 *
 * This URL can be JDBC connection string if you work with Java and MySQL or MongoDB
 *
 * connection string if you decide to use MongoDB.
 *
 * Similar for other databases. You can choose any database that is available today.
 *
 * Every line in CSV file becomes one row (or document) in target database.
 *
 */
public class CsvParser {

    private CsvReader csvReader;
    private DatabaseAccessManager databaseAccessManager;

    public CsvParser(String filePath,String url){
        csvReader = new CsvReader(filePath);
        databaseAccessManager = new DatabaseAccessManager(url);
    }

    public static void main(String[] args) throws SQLException {
        ArgumentValidator.validateParserArguments(args);
        CsvParser csvParser = new CsvParser(args[0],args[1]);
        List<Person> people = csvParser.parseCsvFile();
        csvParser.uploadToDatabase(people);
    }

    private void uploadToDatabase(List<Person> people) throws SQLException {
         databaseAccessManager.insertRecordsInToDb(people);
    }

    public List<Person> parseCsvFile(){
        return csvReader.readCsv();
    }

}
