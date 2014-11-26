package com.pvl.newparser;

import java.util.List;

public class ParamValidator {

    List <ParameterDefiner> commandList;
    List <UserParameters> userCommandList;


    public ParamValidator(List<ParameterDefiner> commandList,List <UserParameters> userCommandList) {
        this.commandList = commandList;
        this.userCommandList = userCommandList;
    }

    public void validate() throws ValidationException {

        for (ParameterDefiner p : commandList) {
            String defComName = p.getName();
            String defComType = p.getType();
            boolean defComRequired = p.isRequired();

            boolean requiredParamIsNotFound = p.isRequired();

            for (UserParameters up : userCommandList) {
                    String userComName = up.getName();
                    String userComValue = up.getValue();

                if (defComName.equals(userComName) && userComValue != null) {
                    if (defComType.equals(ParameterDefiner.INTEGER_TYPE) && !isInteger(userComValue)) {
                        throw new ValidationException("Value for parameter < " + userComValue + "> should be an Integer");
                    }

                    if (defComRequired) {
                        requiredParamIsNotFound = false;
                    } else {
                        if (requiredParamIsNotFound) {
                            throw new ValidationException("Required param " + p.getName() + " is not found");
                        }
                    }
                }
            }

        }
    }

    private Boolean isInteger(String str) {
        try{
            Integer.parseInt(str);
            return Boolean.TRUE;
        } catch (NumberFormatException e){
            return Boolean.FALSE;
        }
    }

    //TODO for testing, need to delete it later
    String inPath;
    String ouPath = "/home/pvl/Desktop/output.txt" ;
    Integer miValue = 3;

    public String getInputPath() {
        return inPath;
    }

    public String getOutputPath() {
        return ouPath;
    }

    public int getMinValue() {
        return miValue;
    }
}
