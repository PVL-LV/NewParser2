package com.pvl.newparser;

import java.io.*;
import java.util.*;

public class NewParser {

    public static  String inPath;
    public static String outPath;
    public static int numberOfChar;

    static List<ParameterDefiner> commandList;

    static Map<String, Integer> listOfWords = new HashMap<String, Integer>();

    static {
        commandList.add(new ParameterDefiner("inputPath", ParameterDefiner.STRING_TYPE, true));
        commandList.add(new ParameterDefiner("outputPath", ParameterDefiner.STRING_TYPE, false));
        commandList.add(new ParameterDefiner("minValue", ParameterDefiner.INTEGER_TYPE, false));
    }


    public static void main(String[] args) {

        ArgsParser ap = new ArgsParser(args);
        List<UserParameters> userCommandList = ap.parseAndAddToList();


        ParamValidator pv = new ParamValidator(commandList, userCommandList);
        //TODO somthing wrong with Exeptions
        try {
            pv.validate();
        } catch (ValidationException ve) {
             System.out.println(ve.getMessage());
             System.exit(1);
        }

        inPath = pv.getInputPath();
        outPath = pv.getOutputPath();
        numberOfChar = pv.getMinValue();


        String fullBook = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(inPath));
            String line;

            while ((line = br.readLine()) != null) {
                br.lines();
                fullBook += line + "\r\n";
            }
            br.close();

        } catch (Exception e) {
            System.out.println(e);
        }


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
                try {
                    Writer out = new BufferedWriter(new FileWriter(outPath));
                    out.write(wfp);
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    System.out.println("Can't write to file " + outPath);
                    System.exit(0);
                }
            } else {
                System.out.println(wfp);
            }
        }


        System.out.println("Done!!!");
    }

    public static int getNumberOfChar() {
        return numberOfChar;
    }


}