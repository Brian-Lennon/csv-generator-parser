package com.blen.csv.utils;

import com.blen.csv.pojo.Person;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * This class generates Person objects with random entries for first name, last name and age.
 */
public class PersonGenerator {
    private static int LOWEST_AGE =1;
    private static int HIGHEST_AGE = 100;

    private static final String[] FIRSTNAMES = {
            "Ada", "Albert", "Alexandra", "Alfredo", "Allen", "Andre", "Angelica",
            "Anna", "Anthony", "Antonio", "Ashley", "Audrey", "Beatrice",
            "Benjamin", "Billy", "Bobby", "Bradley", "Bryant", "Candace",
            "Carole", "Carrie", "Claire", "Clifford", "Clint", "Clyde", "Cory",
            "Dale", "Danielle", "Daryl", "Delia", "Devin", "Douglas", "Eddie",
            "Ella", "Erica", "Erika", "Eva", "Frank", "Gayle", "George", "Georgia",
            "Geraldine", "Gina", "Gwen", "Hector", "Homer", "Irene", "James",
            "Jamie", "Jeremiah", "Joann", "Josefina", "Juan", "Karen", "Kenneth",
            "Laurie", "Lee", "Leland", "Leroy", "Levi", "Lewis", "Lillian",
            "Lillie", "Lorenzo", "Louise", "Lucas", "Lynn", "Marc", "Marcella",
            "Marlon", "Marvin", "Micheal", "Miranda", "Miriam", "Misty", "Naomi",
            "Natasha", "Nelson", "Oliver", "Pete", "Rafael", "Randall", "Raul",
            "Rebecca", "Reginald", "Roger", "Ruby", "Rufus", "Sabrina", "Sean",
            "Steven", "Stuart", "Terence", "Terry", "Van", "Velma", "Vincent",
            "Wanda", "Willard", "Winifred"
    };
    private static final String[] LASTNAMES = {
            "Adkins", "Aguilar", "Anderson", "Armstrong", "Arnold", "Bailey",
            "Banks", "Barrett", "Bates", "Bennett", "Bowers", "Bradley", "Brown",
            "Bryant", "Buchanan", "Bush", "Butler", "Cain", "Carlson", "Carroll",
            "Cummings", "Diaz", "Doyle", "Duncan", "Dunn", "Fernandez", "Foster",
            "Fowler", "Fox", "Francis", "French", "Garrett", "Gill", "Glover",
            "Goodwin", "Gordon", "Grant", "Griffin", "Gross", "Guerrero", "Hale",
            "Harvey", "Holland", "Ingram", "Jacobs", "James", "Lamb", "Lowe",
            "Lucas", "Mann", "Marshall", "Martin", "Martinez", "May", "Mcdaniel",
            "Mendoza", "Meyer", "Moody", "Moreno", "Nelson", "Nichols", "Norton",
            "Obrien", "Osborne", "Padilla", "Page", "Parks", "Parsons", "Payne",
            "Pearson", "Powell", "Reese", "Reeves", "Reyes", "Reynolds",
            "Richardson", "Rios", "Ross", "Russell", "Saunders", "Sharp", "Simon",
            "Smith", "Steele", "Stephens", "Stokes", "Summers", "Thomas",
            "Thompson", "Tyler", "Wagner", "Ward", "Washington", "Watkins",
            "Watson", "Weber", "West", "Willis", "Young", "Zimmerman"};

    private static Logger LOGGER = Logger.getLogger(PersonGenerator.class);

    private Random random;

    public PersonGenerator(){
        random = new Random();
    }

    /**
     * Generate a list of people
     * @param numberOfPeopleToGenerate
     * @return List of Person objects
     */
    public List<Person> generateRandomListOfPeople(Integer numberOfPeopleToGenerate){
        LOGGER.info("Generating list of Random people size: "+ numberOfPeopleToGenerate);
        List<Person> people = new ArrayList<>();
        while(people.size()<numberOfPeopleToGenerate){
            Person personToAdd = generatePerson();
            if(checkIfListContainsThePerson(people,personToAdd)) {
                personToAdd = generatePerson();
            }
            people.add(personToAdd);
        }
        return people;
    }

    private boolean checkIfListContainsThePerson(List<Person> people, Person personToAdd) {
        for (Person p : people){
            if (personToAdd.equals(p)){
                return true;
            }
        }
        return false;
    }

    private Person generatePerson() {
        String firstName = FIRSTNAMES[random.nextInt(FIRSTNAMES.length)];
        String lastName = LASTNAMES[random.nextInt(LASTNAMES.length)];
        int age = getRandomAge();
        return new Person(firstName,lastName,age);
    }

    private int getRandomAge(){
        return random.nextInt(HIGHEST_AGE-LOWEST_AGE) + LOWEST_AGE;
    }
}
