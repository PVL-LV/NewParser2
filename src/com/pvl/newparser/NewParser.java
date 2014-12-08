package com.pvl.newparser;

import java.io.*;
import java.util.*;

public class NewParser {

    public static  String inPath;
    public static String outPath;
    public static int numberOfChar;

    private static Map<String, Integer> listOfWords = new HashMap<>();
    public static List<ParameterDefiner> commandList = new ArrayList<>();
    public static List<UserParameters> userCommandList = new ArrayList<>();

    static {
        commandList.add(new ParameterDefiner(ParameterDefiner.INPUT_PATH, ParameterDefiner.STRING_TYPE, true));
        commandList.add(new ParameterDefiner(ParameterDefiner.OUTPUT_PATH, ParameterDefiner.STRING_TYPE, false));
        commandList.add(new ParameterDefiner(ParameterDefiner.MIN_VALUE, ParameterDefiner.INTEGER_TYPE, false));
    }

    public static void main(String[] args) {

        ArgsParser ap = new ArgsParser(args);
        userCommandList = ap.parseAndAddToList();


        ParamValidator pv = new ParamValidator(commandList, userCommandList);

        try {
            pv.validate();
        } catch (ValidationException ve) {
             System.out.println(ve.getMessage());
             System.exit(1);
        }

        CommandGetter comGetter = new CommandGetter(userCommandList);
        comGetter.getCommand();

        inPath = comGetter.getInPath();
        outPath = comGetter.getOutPath();
        numberOfChar = comGetter.getNumberOfChar();


        String fullBook = "";

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
            }

            finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                Writer out = null;
                try {
                    out = new BufferedWriter(new FileWriter(outPath));
                    out.write(wfp);

                } catch (Exception e) {
                    System.out.println("Can't write to file " + outPath);
                    System.exit(0);
                }
                finally {
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

        System.out.println("Done!!!");
    }

    public static int getNumberOfChar() {
        return numberOfChar;
    }

}