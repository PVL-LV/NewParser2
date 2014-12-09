package com.pvl.newparser;

import java.util.Map;

public class AddToMap {

    private static int numberOfChar = FileReaderAndWriter.getNumberOfChar();

    public static void addWordToMap(Map<String, Integer> map, String word) {

        if (map.containsKey(word)) {
            int value = map.get(word).intValue();

            map.put(word, (value + 1));

        } else {
            //check the number of char in the word
            if(word.length() >= numberOfChar){
                map.put(word, 1);
            }else {}
        }
    }
}

