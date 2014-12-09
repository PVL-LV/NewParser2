package com.pvl.newparser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReaderAndWriter {

    private static  String inPath;
    private static String outPath;
    private static int numberOfChar;

    private static String fullBook = "";

    private static Map<String, Integer> listOfWords = new HashMap<>();
    public static List<UserParameters> userCommandList = new ArrayList<>();



    public  FileReaderAndWriter(List<UserParameters> userCommandList) {
       // this.userCommandList = userCommandList;

        CommandGetter comGetter = new CommandGetter(userCommandList);
        comGetter.getCommand();

        inPath = comGetter.getInPath();
        outPath = comGetter.getOutPath();
        numberOfChar = comGetter.getNumberOfChar();

    }


    public void readFile() {

//        CommandGetter comGetter = new CommandGetter(userCommandList);
//        comGetter.getCommand();
//
//        inPath = comGetter.getInPath();
//        outPath = comGetter.getOutPath();
//        numberOfChar = comGetter.getNumberOfChar();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(inPath));
            String line;

            while ((line = br.readLine()) != null) {
                br.lines();
                fullBook += line + "\r\n";
            }


        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void writeFile() {

        String[] words = fullBook.split(" ");

        for (String i : words) {

            AddToMap atm = new AddToMap();
            atm.addWordToMap(listOfWords, i.trim().toLowerCase());
        }

        MapSort mapSort = new MapSort();
        listOfWords = mapSort.sortByValue(listOfWords);

        String wfp;
        for (Map.Entry<String, Integer> entry : listOfWords.entrySet()) {
            wfp = (entry.getKey() + " / " + entry.getValue() + "\r\n");

            if (outPath != null) {
                Writer out = null;
                try {
                    out = new BufferedWriter(new FileWriter(outPath));
                    out.write(wfp);

                } catch (Exception e) {
                    System.out.println("Can't write to file " + outPath);
                    System.exit(0);
                } finally {
                    try {
                        out.flush();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                System.out.println(wfp);
            }
        }
    }

    public static int getNumberOfChar() {
        return numberOfChar;
    }

}
