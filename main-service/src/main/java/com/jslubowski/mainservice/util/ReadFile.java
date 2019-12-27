package com.jslubowski.mainservice.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ReadFile {

    /*
    This class reads a text file in order to use API key.
    The .txt file should contain only API keys
     */

    public static String readApiKeyFromTextFile(){

        Scanner scanner = null;
        String apiKey = null;
        Resource resource = new ClassPathResource("static\\api_keys.txt");

        try(InputStream inputTextFile = resource.getInputStream()){
            scanner = new Scanner(inputTextFile);
            while(scanner.hasNextLine()){
                apiKey = scanner.nextLine();
            }
        }catch(IOException e){
            e.printStackTrace();
            return apiKey;
        }

        return apiKey;
    }
}
