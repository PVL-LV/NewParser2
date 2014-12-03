package com.pvl.newparser;

public class ParameterDefiner {

    public static final String STRING_TYPE = "String";
    public static final String INTEGER_TYPE = "Integer";

    public static final String INPUT_PATH = "inputPath";
    public static final String OUTPUT_PATH = "outputPath";
    public static final String MIN_VALUE = "minValue";


    private String name;
    private String type;
    private boolean required;

    public ParameterDefiner(String name, String type, boolean required) {
        this.name = name;
        this.type = type;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
