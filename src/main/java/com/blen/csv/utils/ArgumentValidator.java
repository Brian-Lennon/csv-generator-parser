package com.blen.csv.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to verify arguments passed to main class
 * @see com.blen.csv.main.CsvGenerator
 * @see com.blen.csv.main.CsvParser
 */
public class ArgumentValidator {
    private static Logger LOGGER = Logger.getLogger(ArgumentValidator.class);
    private static int MIN_CSV_LINES=1;
    private static int MAX_CSV_LINES = 10000;



    public static void validateGeneratorArguments(String args[]){
        checkArgumentLength(args);

        if(!isNumberOfLinesValid(args[0])){
            throw new IllegalArgumentException(String.format("First argument: Must be an integer between 1 and 10000"));
        }

        if(!isFilePathValid(args[1])){
            throw new IllegalArgumentException(String.format("Second argument: Cannot write file to specified paths"));
        }
    }

    public static void validateParserArguments(String args[]){
        checkArgumentLength(args);

        if(!isFilePathValid(args[0])){
            throw new IllegalArgumentException(String.format("First argument: Cannot write file to specified paths"));
        }
        if(!isUrlValid(args[1])){
            throw new IllegalArgumentException(String.format("Second argument: Issue connecting to database with the url supplied "));
        }

    }

    private static void checkArgumentLength(String args[]){
        if (args == null || args.length != 2 ){
            throw new IllegalArgumentException(String.format("Invalid amount of arguments"));
        }
    }
    private static boolean isUrlValid(String url){
        try(Connection connection = DriverManager.getConnection(url, "root", "12shroot")){
            if (connection != null) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            LOGGER.error("Could not connect to Database",e);
            return false;
        }
    }

    private static boolean isNumberOfLinesValid(String firstArgument) {
        boolean argumentIsNumber = NumberUtils.isCreatable(firstArgument);
        boolean argumentIsInRange = ((Integer.parseInt(firstArgument)>=MIN_CSV_LINES) &&
                (Integer.parseInt(firstArgument)<=MAX_CSV_LINES));
        return  argumentIsNumber && argumentIsInRange;
    }

    private static boolean isFilePathValid(String filePath){
        return new File(filePath).getParentFile().exists();
    }


}
