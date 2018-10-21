package com.blen.csv.utils;

import com.blen.csv.pojo.Person;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static com.blen.csv.utils.Constants.FILE_NAME;
import static org.junit.Assert.*;

public class CsvWriterTest {

    private CsvWriter classUnderTest;
    private CsvReader csvReader;
    private PersonGenerator personGenerator;
    private File tempFile;

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        classUnderTest = new CsvWriter();
        personGenerator = new PersonGenerator();
        tempFile = folder.newFile(FILE_NAME);
        csvReader = new CsvReader(tempFile.getAbsolutePath());
    }

    @Test
    public void testWriteToCsv(){
        List<Person> peopleToWrite = personGenerator.generateRandomListOfPeople(5);
        classUnderTest.writeToCsv(peopleToWrite, tempFile.getAbsolutePath());
        assertTrue(tempFile.exists());
        List<Person> peopleToRead = csvReader.readCsv();
        assertEquals(peopleToRead,peopleToWrite);
    }
}