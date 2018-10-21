package com.blen.csv.dao;

import com.blen.csv.pojo.Person;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.blen.csv.utils.Constants.*;
import static org.junit.Assert.*;

public class DatabaseAccessManagerTest {


    private DatabaseAccessManager classUnderTest;


    @Before
    public void setUp() throws Exception {
        classUnderTest = new DatabaseAccessManager(VALID_DATABASE_CONNECTION);
        truncateTable();
    }

    @Test
    public void testInsertRecordsInToDb()throws SQLException{
        List<Person> people = new ArrayList<>();
        Person p = new Person(FIRST_NAME,LAST_NAME,AGE);
        people.add(p);
        classUnderTest.insertRecordsInToDb(people);
        List<Person> peopleFromDb = classUnderTest.retrieveRecordsFromDb();
        assertEquals(people,peopleFromDb);
    }

    private void truncateTable() throws SQLException {
        classUnderTest.truncateTable();
    }
}