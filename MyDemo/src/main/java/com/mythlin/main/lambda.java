package com.mythlin.main;

import java.util.Arrays;
import java.util.List;

/**
 * Created by huanglin on 2017/5/27.
 */
public class lambda {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("java","scala","python");
        //before java8
        for(String each:languages) {
            System.out.println(each);
        }
        //after java8
        languages.forEach(x -> System.out.println(x));
        languages.forEach(System.out::println);
    }

}
