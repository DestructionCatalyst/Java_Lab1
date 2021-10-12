package ru.mephi.komzavladislav.lab2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class Lab2 {

    public static void main(String[] args) {
        String input = "aa aaa aa bb bbb bbbb bb ddd uuu iii ooo";
        System.out.println(parse(input, " "));
        System.out.println(deduplicate(input, " "));
    }

    public static String parse(String input, String delimiter){
        String[] words = input.split(delimiter);

        HashMap<String, Integer> tokens = new HashMap<>();

        for (String word: words){
            if (tokens.containsKey(word)){
                tokens.put(word, tokens.get(word) + 1);
            }
            else{
                tokens.put(word, 1);
            }
        }

        StringBuilder res = new StringBuilder();

        for (Map.Entry<String, Integer> entry: tokens.entrySet()){
            res.append(entry.getKey());
            res.append(": ");
            res.append(entry.getValue());
            res.append('\n');
        }

        return res.toString();
    }

    public static String deduplicate(String input, String delimiter){
        String[] words = input.split(delimiter);

        LinkedHashSet<String> tokens = new LinkedHashSet<>(Arrays.asList(words));

        StringBuilder res = new StringBuilder();

        for(String token: tokens){
            res.append(token);
            res.append(delimiter);
        }

        return res.toString();
    }
}
