package com.pvl.newparser;

import java.util.*;

public class ArgsParser {

    private String[] args;
    private UserParameters usPar;
    private List<UserParameters> userParameters = new ArrayList<UserParameters>();

    public ArgsParser(String[] args) {
        this.args = args;
    }

    public List<UserParameters> parseAndAddToList() {
        String[] argm;
        for (String s : args) {
            argm = splitter(s);
            usPar = new UserParameters(argm[0], argm[1]);
            userParameters.add(usPar);
        }
        return userParameters;
    }

    private String[] splitter(String sp) {

        String [] splittedArgs = sp.split("=");
        return splittedArgs;
    }
}


//private String firstArg = "inPath";
//private String secondArg = "outPath";
//private String thirdArg = "minValue";

//    private String iPath;
//    private String oPath;
//    private Integer mValue;
//
//    public String getInputPath() {
//        String[] inArgs ;
//
//            if (args != null) {
//                try {
//                    inArgs = args[0].split("=");
//
//                    if (inArgs[0].equals(firstArg) && (!inArgs[1].isEmpty())) {
//                        iPath = inArgs[1];
//                    } else {
//                        System.out.println("You have to print inPath=<path>");
//                        args[0] = new Scanner(System.in).next();
//                    }
//                } catch (Exception e) {
//                    System.out.println(e);
//                    System.out.println("err You have to print correct \"inPath=<path>\"");
//                }
//            } else {
//
//                System.out.println(" 2 You have to print inPath=<path>");
//                args[0] = new Scanner(System.in).next();
//
//            }
//
//
//        return iPath;
//    }
//
//
//
//    public String getOutputPath() {
//        if (args.length >= 2 && args[1] != null) {
//
//            String[] outArgs;
//
//            try {
//                outArgs = args[1].split("=");
//
//                if (outArgs[0].equals(secondArg) && (!outArgs[1].isEmpty())) {
//                    oPath = outArgs[1];
//                } else {
//                    System.out.println("You have to print: outPath=<path>");
//
//                    args[1] = new Scanner(System.in).next();
//                }
//            } catch (Exception e) {
//                System.out.println("err You have to print: \"outPath=<path>\"");
//                args[1] = new Scanner(System.in).next();
//            }
//        }else {
//            oPath = null;
//        }
//
//        return oPath;
//    }
//
//
//
//    public Integer getMinValue() {
//
//        if (args.length > 2 && args[2] != null) {
//            String[] valueArgs;
//            try {
//                valueArgs = args[2].split("=");
//                if (valueArgs[0].equals(thirdArg) && (!valueArgs[1].isEmpty())) {
//                    try {
//                        mValue = Integer.parseInt(valueArgs[1]);
//                    } catch (NumberFormatException e) {
//                        System.err.println("Argument minValue= " + args[2] + " must be an integer.");
//
//                        args[2] = new Scanner(System.in).next();
//                    }
//                } else {
//                    System.out.println("You have to print: minValue=<value> ");
//                    args[2] = new Scanner(System.in).next();
//                }
//            } catch (Exception e) {
//                System.out.println("err You have to print: minValue=<number> ");
//                args[2] = new Scanner(System.in).next();
//            }
//        }else {
//            mValue = 0;
//        }
//
//        return mValue;
//    }

