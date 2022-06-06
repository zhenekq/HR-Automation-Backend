package by.mifort.automation.hr.dev.config;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Main2 {

    public static void main(String []args){
        List<String> list = new ArrayList<>(List.of(
                "hhh",
                "qwwww",
                "aaa",
                "oooooooooo",
                "eru"
        ));
        System.out.println(sortByLength(list));
    }

    private static List<String> sortByLength(List<String> words){
        return words.stream()
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return Integer.compare(countVowels(o1), countVowels(o2));
                    }
                }).toList();
    }

    private static int countVowels(String str){
        int vowels = 0;
        for(int i=0;i<str.length();i++){
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u') {
                ++vowels;
            }
        }
        return vowels;
    }

}
