package com.blen.csv.utils;

import com.blen.csv.pojo.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PersonGeneratorTest {

    private PersonGenerator classUnderTest;

    @Before
    public void setUp(){
        classUnderTest = new PersonGenerator();
    }

    @Test
    public void testGenerateRandomListOfPeople_verifyThatTheListReturnedIsCorrectSize() {
        List<Person> people = classUnderTest.generateRandomListOfPeople(10);
        assertEquals("The size of the array should be ", 10,people.size());
    }

    @Test
    public void testGenerateRandomListOfPeople_verifyTheGeneratorCanGenerateAtLeast100UniqueCombinations () {
        // Use Set to to hold objects as it does not allow duplicates, the Person class contains hashcode and equals
        // methods that uses firstName and lastName. This is used by HashSet to check if duplicates exists so this test
        // will check if over 100 unique names are created by the generator.

        Set<Person> peopleSet = new HashSet<>();
        List<Person> people = classUnderTest.generateRandomListOfPeople(100);
        peopleSet.addAll(people);
        assertEquals("Generator should be able to generate at least 100 unique names", 100,peopleSet.size());
        peopleSet.clear();
        peopleSet.addAll(classUnderTest.generateRandomListOfPeople(1000));
        assertTrue("Generator should be able to produce more than 100 or more unique people",peopleSet.size()>=100);
    }
}