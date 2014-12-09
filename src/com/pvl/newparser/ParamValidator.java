package com.pvl.newparser;

import java.util.ArrayList;
import java.util.List;

public class ParamValidator {

    List <ParameterDefiner> commandList = new ArrayList<>();
    List <UserParameters> userCommandList = new ArrayList<>();


    public ParamValidator(List<ParameterDefiner> commandList,List <UserParameters> userCommandList) {
        this.commandList = commandList;
        this.userCommandList = userCommandList;
    }

    public void validate() throws ValidationException {



        for (ParameterDefiner p : commandList) {
            String defComName = p.getName();
            String defComType = p.getType();
            boolean defComRequired = p.isRequired();

            for (UserParameters up : userCommandList) {

                String userComName = up.getName();
                String userComValue = up.getValue();


                if (defComName.equals(userComName) && userComValue != null && userComValue.length() > 0) {
                    if (defComType.equals(ParameterDefiner.INTEGER_TYPE) && !isInteger(userComValue)) {
                        throw new ValidationException("Value for parameter < " + userComValue + "> should be an Integer");
                    }
                    defComRequired = false;
                }
            }
            if (defComRequired) {
                throw new ValidationException("Required param " + p.getName() + " is not found");
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

}
