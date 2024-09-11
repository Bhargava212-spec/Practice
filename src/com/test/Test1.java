package com.test;

import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Stream;

public class Test1 {

    public static void main(String[] args) {
        var map = new HashMap<Integer, String>();
        map.put(0, "Zero");
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
        map.put(100, "Hundred");
        map.put(1000, "Thousand");
        map.put(100000, "One Hundred Thousand");
        map.put(1000000, "Million");
        map.put(1000000000, "Billion");


        int num = 100000000;

        StringBuilder sb = new StringBuilder();
        getWords(num, map, sb);
        System.out.println(sb.toString().trim());
    }

    private static void getWords(int num, HashMap<Integer, String> map, StringBuilder sb) {
        if (map.containsKey(num)) {
            if (sb.isEmpty()) {
                if (num == 100) {
                    sb.append(map.get(1)).append(" ").append(map.get(num));
                    return;
                }
            }
            if (!sb.isEmpty() && num == 0) {
                return;
            }
            sb.append(map.get(num)).append(" ");
            return;
        }

        if (num > 10 && num < 100) { // till a hundred
            var temp = num / 10;
            sb.append(map.get(temp * 10)).append(" ");
            num = num % 10;
            getWords(num, map, sb);
        }

        if (num > 100 && num < 1000) { // till a thousand
            var temp = num / 100;
            sb.append(map.get(temp)).append(" ").append(map.get(100)).append(" ");
            num = num % (temp * 100);
            getWords(num, map, sb);
        }

        if (num > 1000 && num < 1000000) { // till a million
            var temp = num / 1000;
            if (map.containsKey(temp)) {
                sb.append(map.get(temp));
            } else {
                getWords(temp, map, sb);
            }
            sb.append(map.get(1000)).append(" ");
            num = num % (temp * 1000);
            getWords(num, map, sb);
        }


        if (num > 1000000 && num < 1000000000) { //till billion
            var temp = num / 1000000;
            if (map.containsKey(temp)) {
                if (temp == 100) {
                    sb.append("One").append(" ");
                }
                sb.append(map.get(temp)).append(" ");
            } else {
                getWords(temp, map, sb);
            }
            sb.append(map.get(1000000)).append(" ");
            num = num % (temp * 1000000);
            getWords(num, map, sb);
        }

        if (num > 1000000000) { //greater than a billion
            var temp = num / 1000000000;
            sb.append(map.get(temp)).append(" ").append(map.get(1000000000)).append(" ");
            num = num % (temp * 1000000000);
            getWords(num, map, sb);
        }
    }


}


