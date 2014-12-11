package com.pvl.newparser;

import java.io.*;
import java.util.*;

public class NewParser {

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

        FileReaderAndWriter frw = new FileReaderAndWriter(userCommandList);
            frw.readFile();
        try {
            frw.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Done!!!");
    }

}