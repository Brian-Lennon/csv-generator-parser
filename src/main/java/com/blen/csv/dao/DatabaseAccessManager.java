package com.blen.csv.dao;

import com.blen.csv.pojo.Person;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a Data Access Object and if responsible in
 * writing and retrieving objects from a database specified by a url
 */
public class DatabaseAccessManager {

    private static final Logger LOGGER = Logger.getLogger(DatabaseAccessManager.class);

    private static final String INSERT_OBJECT_INTO_DB = "INSERT INTO csv_generator_parser.people"
            + "(firstName, lastName, age) " + "VALUES"
            + "('%s','%s', %d);";

    private static final String RETRIEVE_ALL_OBJECTS_FROM_DB = "SELECT * FROM csv_generator_parser.people;";

    private static final String TRUNCATE_TABLE_STATEMENT = "TRUNCATE `csv_generator_parser`.`people`;";

    private static final String USER = "root";

    private static final String PASSWORD = "12shroot";

    private static final String FIRST_NAME_COLUMN = "firstName";
    private static final String LAST_NAME_COLUMN = "lastName";
    private static final String AGE_COLUMN = "age";

    private String url;


    public DatabaseAccessManager(String url){
        this.url = url;
    }

    /**
     * Insert a list of objects into database
     *
     * @param people List of Person object to written to database
     * @throws SQLException
     *          If connection can not be made to Database or there is a issue with inserting data
     */
    public void insertRecordsInToDb(List<Person> people) throws SQLException {
        Connection dbConnection = getDBConnection();
        Statement statement = dbConnection.createStatement();

        LOGGER.info("Inserting records into database.");

        for(Person p:people){
            String formattedStatement = String.format(INSERT_OBJECT_INTO_DB,p.getFirstName(),p.getLastName(),p.getAge());
            statement.executeUpdate(formattedStatement);
        }
        dbConnection.close();
        LOGGER.info("Records are inserted into People table!");
    }

    /**
     * Retrieve All Person objects from the database.
     * @return List of Person objects from the database
     * @throws SQLException
     */
    public List<Person> retrieveRecordsFromDb() throws SQLException {

        Connection dbConnection = getDBConnection();
        Statement statement = dbConnection.createStatement();
        List<Person> people = new ArrayList<>();

        LOGGER.info("Retrieving Records From Database");
        ResultSet rs = statement.executeQuery(RETRIEVE_ALL_OBJECTS_FROM_DB);

        while(rs.next()){
            Person p = new Person(rs.getString(FIRST_NAME_COLUMN),rs.getString(LAST_NAME_COLUMN),rs.getInt(AGE_COLUMN));
            people.add(p);
        }

        dbConnection.close();

        return people;
    }

    protected void truncateTable()throws SQLException{
        Connection dbConnection = getDBConnection();
        Statement statement = dbConnection.createStatement();
        statement.executeUpdate(TRUNCATE_TABLE_STATEMENT);
    }


    protected Connection getDBConnection() throws SQLException {
        Connection dbConnection = DriverManager.getConnection(url, USER, PASSWORD);
        return dbConnection;
    }
}
