package com.pvl.newparser;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReaderAndWriter {

    private static  String inPath;
    private static String outPath;
    private static int numberOfChar;

    private static String fullBook = "";

    private static Map<String, Integer> listOfWords = new HashMap<>();

    public FileReaderAndWriter(List<UserParameters> userCommandList) {

        CommandGetter comGetter = new CommandGetter(userCommandList);

        inPath = comGetter.getInPath();
        outPath = comGetter.getOutPath();
        numberOfChar = comGetter.getNumberOfChar();
    }

    public void readFile() {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(inPath));
            String line;

            while ((line = br.readLine()) != null) {
                br.lines();
                fullBook += line + "\r\n";
            }
        } catch (Exception e) {
            System.out.println("Some mistake");
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void writeFile()  throws IOException  {

        String[] words = fullBook.split(" ");

        for (String i : words) {
            AddToMap atm = new AddToMap();
            atm.addWordToMap(listOfWords, i.trim().toLowerCase());
        }

        MapSort mapSort = new MapSort();
        listOfWords = mapSort.sortByValue(listOfWords);


        String wfp;
        Writer out = null;

        if (outPath != null) {
            try {
                out = new BufferedWriter(new FileWriter(outPath));

                for (Map.Entry<String, Integer> entry : listOfWords.entrySet()) {
                    wfp = (entry.getKey() + " / " + entry.getValue() + "\r\n");
                    out.write(wfp);
                }
            } finally {
                out.flush();
                out.close();
            }
        }else {
            for (Map.Entry<String, Integer> entry : listOfWords.entrySet()) {
                wfp = (entry.getKey() + " / " + entry.getValue() + "\r\n");

                System.out.println(wfp);
            }
        }

    }

    public static int getNumberOfChar() {
        return numberOfChar;
    }

}
