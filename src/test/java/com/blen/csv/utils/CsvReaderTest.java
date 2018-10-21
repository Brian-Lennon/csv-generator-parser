package com.blen.csv.utils;

import com.blen.csv.pojo.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CsvReaderTest {

    private CsvReader csvReader;
    private String filePath;

    @Before
    public void setUp() throws Exception {
        csvReader = new CsvReader(getTestFile());
    }

    @Test
    public void testReadCsv(){
        List<Person> people = csvReader.readCsv();
        assertEquals("The size of the array should be",101,people.size());
    }

    private String getTestFile(){
        return getClass().getClassLoader().getResource("testFile.csv").getFile();
    }
}