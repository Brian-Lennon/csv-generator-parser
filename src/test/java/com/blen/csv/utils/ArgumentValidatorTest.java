package com.blen.csv.utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static com.blen.csv.utils.Constants.*;

public class ArgumentValidatorTest {
    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test(expected = IllegalArgumentException.class)
    public void testValidateGeneratorArgumentsInvalidArgsLength() {
        String args[] = null;
        ArgumentValidator.validateGeneratorArguments(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateGeneratorArgumentsInvalidNumberOfLines() throws IOException {
        File tempFile = folder.newFile(FILE_NAME);
        String args[] = {INVALID_LINES_ALLOWED, tempFile.getAbsolutePath()};
        ArgumentValidator.validateGeneratorArguments(args);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testValidateGeneratorArgumentsInvalidFilePath() {
        String args[] = {VALID_LINES_ALLOWED, createInvalidFolderLocation()};
        ArgumentValidator.validateGeneratorArguments(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateParserArgumentsInvalidFilePath() {
        String args[] = {createInvalidFolderLocation(),VALID_DATABASE_CONNECTION};
        ArgumentValidator.validateParserArguments(args);
    }
    @Test(expected = IllegalArgumentException.class)
    public void validateParserArgumentsInvalidUrl() throws IOException {
        File tempFile = folder.newFile(FILE_NAME);
        String args[] = {tempFile.getAbsolutePath(),INVALID_DATABASE_CONNECTION};
        ArgumentValidator.validateParserArguments(args);
    }

    private String createInvalidFolderLocation(){
        return folder.toString()+"/someFolder/"+FILE_NAME;
    }
}