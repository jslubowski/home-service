package com.jslubowski.mainservice.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {

    @Test
    @DisplayName("Testing reading api key text file")
    void testReadingFile(){
        String expected = "";
        String actual = ReadFile.readApiKeyFromTextFile();
        System.out.println(actual);
        assertEquals(expected, actual, () -> "Should return " + expected + " but returned " + actual);
    }

}