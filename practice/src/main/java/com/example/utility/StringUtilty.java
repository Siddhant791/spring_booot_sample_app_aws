package com.example.utility;

public class StringUtilty {

    public static String convertToUpperCase(String input) {
        String result = "";
        for(char c : input.toCharArray()) {
            if(c < 'a' || c > 'z') {
                result+= c;
                continue;
            }
            char out = (char) (c - ('a' - 'A'));
            result+= out;
        }
        return result;
    }

    public static String convertToLowerCase(String input) {
        String result = "";
        for(char c : input.toCharArray()) {
            if(c < 'A' || c > 'Z') {
                result+= c;
                continue;
            }
            char out = (char) (c + ('a' - 'A'));
            result+= out;
        }
        return result;
    }

    public static String convertString(String input, MyStringUtility function) {
        return function.convert(input);
    }

    @FunctionalInterface
    interface MyStringUtility {
        String convert(String input);
    }

    public static void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        int temp = 0;
        while (start < end) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

}