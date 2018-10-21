# CSV Random Generator and Parser

Requirements
-----
* Java 8
* MySQL


Usage
-----
Clone down repo and run:
```
mvn clean install
```

This generates two jars in the project directory:
* CsvGenerator.jar
* CsvParser.jar

CsvGenerator
-----
To run CsvGenerator.jar:
```
java -jar CsvGenerator.jar <Numeber Of lines> <csv file loction including name>
```
CsvParser
-----
Ensure your MySQL database is started, the script below can be used to set up the table Person.
```
create_table.sql 
```


To run CsvParser.jar:
```
java -jar CsvParser.jar <csv file loction including name> jdbc:mysql://localhost:3306/<database name>
```

Performance
-----
Some of the issues with performance are the delay in uploading the list of person objects into the database, 
this could be solved with both batching of statements and uses of threads. What could improve this further 
is it could be designed using JPA (Java Persistence API). This will allow objects to be mapped in the database, 
it also hides a lot of the SQL from the developer, it allows for batching of statements into transactions.
An example of this is: 

```
EntityManager entityManager = entityManagerFactory()
    .createEntityManager();
     
EntityTransaction entityTransaction = entityManager
    .getTransaction();
 
try {
    entityTransaction.begin();
 
    for (int i = 0; i < entityCount; i++) {
        if (i > 0 && i % batchSize == 0) {
            entityTransaction.commit();
            entityTransaction.begin();
 
            entityManager.clear();
        }
 
        //Create Your object
         
        entityManager.persist(YourObeject);
    }
 
    entityTransaction.commit();
```

Other performance impacts are located around the writing to csv.

One option is to use:
```
      File file = new File(filePath);

      InputStream fis = new FileInputStream(file);

      BufferedReader buffReader = new BufferedReader(new InputStreamReader(fis));

      //skip(1) will skip the first line which is the header
      inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());

      br.close();
``` 