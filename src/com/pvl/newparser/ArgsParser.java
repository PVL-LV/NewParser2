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

