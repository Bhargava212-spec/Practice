package com.test;


import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws IOException {
//        int[] a = {1, 4, 5, 2, 12, 34, 2, 3, 11, 34, 40};
//        int[] intArray = {1, 4, 5, 2, 12, 34, 2, 2, 3, 11, 34, 40};
//        var set = new HashSet<Integer>();
//        Arrays.stream(a).boxed().filter(var -> !set.add(var)).distinct().collect(Collectors.toList());
//        Arrays.stream(a).boxed().sorted(Comparator.reverseOrder()).limit(2).skip(1).findFirst().orElse(-1);
//        var list = Arrays.stream(a).boxed().collect(Collectors.toList());
//        System.out.println(list);
//        list.stream().mapToInt(Integer::intValue).toArray();


//        Integer a1 = 100;
//        Integer a2 = 100;
//        Integer a3 = 500;
//        Integer a4 = 500;
//
//        if (a1 == a2) {
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }
//
//        if (a3 == a4) {
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }
        kthCharacter(5);
    }


    //
//        IntSummaryStatistics summaryStatistics = Stream.of(1,2,3)
//                .collect(summarizingInt(Integer::intValue));
//
//        TreeNode root1 = new TreeNode(3);
//        root1.left = new TreeNode(5);
//        root1.right = new TreeNode(1);
//        root1.left.left = new TreeNode(6);
//        root1.left.right = new TreeNode(2);
//        root1.left.right.left = new TreeNode(7);
//        root1.left.right.right = new TreeNode(4);
//        root1.right.left = new TreeNode(9);
//        root1.right.right = new TreeNode(8);
//
//        // Constructing root2 tree
//        TreeNode root2 = new TreeNode(3);
//        root2.left = new TreeNode(5);
//        root2.right = new TreeNode(1);
//        root2.left.left = new TreeNode(6);
//        root2.left.right = new TreeNode(7);
//        root2.right.left = new TreeNode(4);
//        root2.right.right = new TreeNode(2);
//        root2.right.right.left = new TreeNode(9);
//        root2.right.right.right = new TreeNode(8);
//        maxPoints(new int[][]{{0, 0}, {1, -1}, {1, 1}});


    public static boolean checkValidString(String s) {
        var stack = new Stack<Character>();
        var arr = s.toCharArray();
        var count = 0;
        for (var ch : arr) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == '*') {
                count++;
            } else if (!stack.isEmpty() && stack.peek() == '(' && ch == ')') {
                stack.pop();
            } else if (ch == ')' && count > 0) {
                count--;
            } else {
                return false;
            }
        }
        while (!stack.isEmpty() && stack.peek() == '(' && count > 0) {
            stack.pop();
            count--;
        }
        return stack.isEmpty();
    }

    public static boolean[] isArraySpecial(int[] nums, int[][] queries) {
        var res = new boolean[queries.length];
        var map = new HashMap<int[], Boolean>();
        for (var i = 0; i < queries.length; i++) {
            var temp = queries[i];
            var start = temp[0];
            var end = temp[1];
            if (start == end) {
                res[i] = true;
                continue;
            }

            if (end - start == 1) {
                res[i] = !(nums[start] % 2 == nums[end] % 2);
                continue;
            }
            if (map.containsKey(temp)) {
                res[i] = map.get(temp);
                continue;
            } else {
                if (!map.isEmpty()) {

                    var bool = map.entrySet().stream().filter(var -> var.getKey()[0] >= start && var.getKey()[1] <= end).limit(1).map(Map.Entry::getValue).findFirst().orElse(null);

                    if (bool != null) {
                        res[i] = bool;
                        continue;
                    }
                }
            }
            var count = 0;
            for (var j = start; j < end; j++) {
                if (nums[j] % 2 == nums[j + 1] % 2) {
                    count = count + 1;
                    map.put(temp, false);
                    break;
                }
            }
            if (count == 0) {
                res[i] = true;
                map.put(temp, true);
            }

        }
        return res;
    }

    public static boolean canJump(int[] nums) {
        var len = nums.length;
        var max = 0;

        for (var i = 0; i < len - 1; i++) {

            if (i > max) {
                return false;
            }

            max = Math.max(max, i + nums[i]);

            if (max >= len - 1) {
                return true;
            }
        }
        return false;
    }

    public static int countDays(int days, int[][] meetings) {
        var list = new ArrayList<Integer>();
        for (var arr : meetings) {
            var start = arr[0];
            var end = arr[1];
            if (list.contains(start)) {
                days = days + 1;
            }
            if (list.contains(end)) {
                days = days + 1;
            }
            list.add(start);
            list.add(end);
            var diff = end - start;
            if (diff > 1) {
                diff = diff + 1;
            }
            days = days - diff;
        }
        return days;
    }

    public static long maximumSubarraySum(int[] arr, int k) {
        long res = 0;
        var len = arr.length;
        for (int i = 0; i < len - k; i++) {
            var temp = Arrays.copyOfRange(arr, i, i + k);
            var unique = Arrays.stream(temp).distinct().count();
            if (k == unique) {
                long sum = IntStream.of(temp).sum();
                res = Math.max(res, sum);
            }
        }
        return res;
    }

    public static List<Integer> lastVisitedIntegers(int[] nums) {

        List<Integer> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        var prev = nums[0];
        if (prev != -1) {
            list.add(prev);
        } else {
            res.add(-1);
        }
        var count = 0;
        for (var i = 1; i < nums.length; i++) {
            var num = nums[i];
            if (num != -1) {
                list.addFirst(num);
                prev = num;
            } else {
                if (list.size() < count + 1) {
                    res.add(-1);
                } else {
                    res.add(list.get(count));
                }
                if (prev == -1) {
                    count++;
                } else {
                    count = 0;
                }
                prev = -1;
            }
        }
        return res;

    }

//    public int countTime(String time) {
//
//    }

    public static int compare(String s1, String s2) {
        return (s1 + s2).compareTo(s2 + s1);
    }

    public static int compare1(String s1, String s2) {
        return (s2 + s1).compareTo(s1 + s2);
    }

    public static Character getFirstRepeatingChar(String s) {
        var ch = s.chars().mapToObj(c -> (char) c).filter(var -> (s.indexOf(Character.toString(var)) != s.lastIndexOf(Character.toString(var)))).limit(1).findFirst().orElse(null);

        return ch;
    }

    public static int findWinningPlayer(int[] skills, int k) {
        var list = new ArrayList<Integer>();
        for (var num : skills) {
            list.add(num);
        }
        LinkedList<Integer> deque = new LinkedList<>(list);
        if (k > deque.size()) {
            return list.indexOf(Collections.max(list));
        }
        var temp = k;
        Integer fn = null;
        while (k > 0) {
            var num1 = deque.removeFirst();
            var num2 = deque.removeFirst();
            if (fn != null && Math.max(num1, num2) == fn) {
                k--;
                if (!num1.equals(fn)) {
                    deque.addLast(num1);
                    deque.addFirst(num2);
                } else {
                    deque.addLast(num2);
                    deque.addFirst(num1);
                }
            } else {
                if (num1 > num2) {
                    fn = num1;
                    deque.addFirst(num1);
                    deque.addLast(num2);
                } else {
                    fn = num2;
                    deque.addFirst(num2);
                    deque.addLast(num1);
                }
                k = temp - 1;
            }
        }
        return list.indexOf(fn);
    }

    public static String maximumNumber(String num, int[] change) {//262/279
        var sb = new StringBuilder();
        for (var i = 0; i < num.length(); i++) {
            var temp = Character.getNumericValue(num.charAt(i));
            if (change[i] > temp) {
                sb.append(change[i]);
            } else {
                sb.append(temp);
            }
        }
        return sb.toString();
    }

    public static long countCompleteDayPairs1(int[] hours) {
        var cnt = 0;
        var map = new HashMap<Integer, Integer>();

        for (int i = 0; i < hours.length; i++) {
            int num = hours[i];
            num = num % 24;
            var temp = Math.abs(24 - num) % 24;
            if (map.containsKey(temp)) {
                cnt = cnt + map.get(temp);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }


        return cnt;
    }

    public static int minTimeToType(String word) {
        var list = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
        var currIndex = 0;
        var midIndex = 12;
        var minTime = 0;
        var charArr = word.toCharArray();
        for (var c : charArr) {
            var index = list.indexOf(c);
            if (index == currIndex) {
                minTime++;
            } else if (index < midIndex) {
                if (currIndex < midIndex) {
                    minTime = minTime + Math.abs(index - currIndex) + 1;
                } else {
                    minTime = minTime + Math.min(Math.abs(currIndex - index), (list.size() - currIndex) + index) + 1;
                }
            } else {
                if (currIndex > midIndex) {
                    minTime = minTime + Math.abs(index - currIndex) + 1;
                } else {
                    minTime = minTime + Math.min(Math.abs(currIndex - index), (list.size() - index) + currIndex) + 1;
                }
            }
            currIndex = index;
        }
        return minTime;
    }

    public static int calculate(String s) {
        s = s.trim().replaceAll("\\s+", "");
        var sb = new StringBuilder(s);
        calculateHelper(sb);
        if (sb.isEmpty()) {
            return 0;
        }
        if (sb.charAt(0) == '-') {
            return -Integer.parseInt(sb.substring(1, sb.length()));
        }
        return Integer.parseInt(sb.toString());
    }

    private static void calculateHelper(StringBuilder trim) {
        if (trim.isEmpty()) {
            return;
        }
        if (!Character.isDigit(trim.charAt(trim.length() - 1))) {
            trim.replace(trim.length() - 1, trim.length(), "");
        }
        if (trim.toString().contains("/")) {
            if (trim.toString().matches("^[0-9]")) {
                return;
            }
            var index = trim.indexOf("/");
            var arr = getValidIndexes(index, trim.toString());
            var str1 = trim.substring(arr[0], index);
            var str2 = trim.substring(index + 1, arr[1] + 1);
            int num;
            int num1;
            if (str1.charAt(0) == '-') {
                num = -Integer.parseInt(str1.substring(1));
            } else {
                num = Integer.parseInt(str1);
            }
            if (str2.charAt(0) == '-') {
                num1 = -Integer.parseInt(str2.substring(1));
            } else {
                num1 = Integer.parseInt(str2);
            }
            var tempNum = num / num1;
            var string = "";
            if (tempNum != 0) {
                string = Integer.toString(tempNum);
            }
            trim.replace(arr[0], arr[1] + 1, string);
            calculateHelper(trim);
        }
        if (trim.toString().contains("*")) {
            if (trim.toString().matches("^[0-9]")) {
                return;
            }
            var index = trim.indexOf("*");
            var arr = getValidIndexes(index, trim.toString());
            var str1 = trim.substring(arr[0], index);
            var str2 = trim.substring(index + 1, arr[1] + 1);
            int num;
            int num1;
            if (str1.charAt(0) == '-') {
                num = -Integer.parseInt(str1.substring(1));
            } else {
                num = Integer.parseInt(str1);
            }
            if (str2.charAt(0) == '-') {
                num1 = -Integer.parseInt(str2.substring(1));
            } else {
                num1 = Integer.parseInt(str2);
            }
            trim.replace(arr[0], arr[1] + 1, Integer.toString(num * num1));
            calculateHelper(trim);
        }
        if (trim.toString().contains("+")) {
            if (trim.toString().matches("^[0-9]")) {
                return;
            }
            var index = trim.indexOf("+");
            if (index == trim.length() - 1) {
                trim.replace(trim.length() - 1, trim.length(), "");
                index = -1;
            }
            if (index == -1) {
                return;
            }
            var arr = getValidIndexes(index, trim.toString());
            var str1 = trim.substring(arr[0], index);
            var str2 = trim.substring(index + 1, arr[1] + 1);
            int num;
            int num1;
            var st = "";
            if (str1.charAt(0) == '-') {
                num = -Integer.parseInt(str1.substring(1));
                st = "+";
            } else {
                num = Integer.parseInt(str1);
            }
            if (str2.charAt(0) == '-') {
                num1 = -Integer.parseInt(str2.substring(1));
            } else {
                num1 = Integer.parseInt(str2);
            }
            trim.replace(arr[0], arr[1] + 1, st.concat(Integer.toString(num + num1)));
            calculateHelper(trim);
        }
        if (trim.toString().contains("-")) {
            if (trim.toString().matches("^[0-9]")) {
                return;
            }

            if (!Character.isDigit(trim.charAt(trim.length() - 1))) {
                trim.replace(trim.length() - 1, trim.length(), "");
            }
            var index = trim.indexOf("-");
            if (index == 0) {
                index = trim.lastIndexOf("-");
                if (index == 0) {
                    return;
                }
            }
            var arr = getValidIndexes(index, trim.toString());
            var str1 = trim.substring(arr[0], index);
            var str2 = trim.substring(index + 1, arr[1] + 1);
            int num;
            int num1;
            var st = "";
            if (str1.charAt(0) == '-') {
                num = -Integer.parseInt(str1.substring(1));
                if (arr[0] != 0) {
                    st = "+";
                }
            } else {
                num = Integer.parseInt(str1);
            }
            if (str2.charAt(0) == '-') {
                num1 = -Integer.parseInt(str2.substring(1));
            } else {
                num1 = Integer.parseInt(str2);
            }
            trim.replace(arr[0], arr[1] + 1, st.concat(Integer.toString(num - num1)));
            calculateHelper(trim);
        }
    }

    public static int[] getValidIndexes(int index, String str) {
        var temp1 = index - 1;
        var arr = new int[2];
        var flag = false;
        while (!flag && (temp1 >= 0 && str.charAt(temp1) == '-') || temp1 >= 0 && Character.isDigit(str.charAt(temp1))) {
            if (str.charAt(temp1) == '-') {
                flag = true;
            }
            arr[0] = temp1;
            if (flag) {
                break;
            }
            temp1--;
        }
        index = index + 1;
        while (index <= str.length() - 1 && Character.isDigit(str.charAt(index))) {
            arr[1] = index;
            index++;
        }
        return arr;
    }

    private static boolean isKeyPresent(Map<Integer, Map> map, int check) {

        if (map.containsKey(check)) {
            return true;
        }

        Queue<Map> que = new LinkedList<>();
        map.values().stream().filter(Objects::nonNull).forEach(que::offer);
        while (!que.isEmpty()) {
            int size = que.size();
            for (var i = 0; i < size; i++) {
                var temp = que.poll();
                if (temp.containsKey(check)) {
                    return true;
                }
                temp.values().stream().filter(Objects::nonNull).forEach(var -> que.offer((Map) var));
            }
        }
        return false;
    }

    public static void getItenary(List<String> list) {
        var startList = list.stream().map(var -> var.trim().split("-")[0]).toList();
        var endList = list.stream().map(var -> var.trim().split("-")[1]).toList();

        String start = null;
        for (var city : startList) {
            if (!endList.contains(city)) {
                start = city;
                break;
            }
        }

        var index = startList.indexOf(start);

        while (index != -1) {
            var to = endList.get(index);
            System.out.print("start -> to ,");
            start = to;
            index = startList.indexOf(start);
        }

    }

    public static int minimumOperations(int[] nums) {
        var res = 0;
        for (var num : nums) {
            if (num % 3 != 0) {
                res = res + Math.min(getNegative(num - 1), getPositive(num + 1));
            }
        }
        return res;
    }

    private static int getPositive(int num) {
        var count = 0;
        while (num % 3 != 0) {
            count++;
            num++;
        }
        return count;
    }

    private static int getNegative(int num) {
        var count = 0;
        while (num % 3 != 0) {
            if (num < 0) {
                return Integer.MAX_VALUE;
            }
            count++;
            num--;
        }
        return count;
    }

    public static String lastNonEmptyString(String s) {
        var list = new ArrayList<String>();
        var sb = new StringBuilder(s);
        var res = "";
        while (!sb.isEmpty()) {
            for (var i = 0; i < sb.length(); i++) {
                var st = Character.toString(sb.charAt(i));
                if (!list.contains(st)) {
                    sb.replace(i, i + 1, "");
                    list.add(st);
                    i = i - 1;
                }
            }
            list.clear();
            if (!sb.isEmpty()) {
                res = sb.toString();
            }
            if (sb.isEmpty() && res.isEmpty()) {
                res = s;
            }
        }
        return res;
    }

    public static String intToRoman(int num) {
        var map = new HashMap<Integer, String>();
        map.put(1, "I");
        map.put(2, "II");
        map.put(3, "III");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(6, "VI");
        map.put(7, "VII");
        map.put(8, "VIII");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(20, "XX");
        map.put(30, "XXX");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(60, "LX");
        map.put(70, "LXX");
        map.put(80, "LXXX");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(200, "CC");
        map.put(300, "CCC");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(600, "DC");
        map.put(700, "DCC");
        map.put(800, "DCCC");
        map.put(900, "CM");
        map.put(1000, "M");
        var sb = new StringBuilder();
        getRoman(num, map, sb);
        return sb.toString();
    }

    public static void getRoman(int num, Map<Integer, String> map, StringBuilder sb) {
        if (map.containsKey(num)) {
            sb.append(map.get(num));
            return;
        }
        if (num > 1 && num < 100) {
            var temp = num / 10;
            sb.append(map.get(temp * 10));
            num = num % (temp * 10);
            getRoman(num, map, sb);
        }
        if (num > 100 && num < 1000) {
            var temp = num / 100;
            sb.append(map.get(temp * 100));
            num = num % (temp * 100);
            getRoman(num, map, sb);
        }
        if (num > 1000) {
            var temp = num / 1000;
            sb.append(map.get(1000).repeat(temp));
            num = num % (temp * 1000);
            getRoman(num, map, sb);
        }
    }

    public static int[] queryResults(int limit, int[][] queries) {
        var len = queries.length;
        var res = new int[len];
        var ballList = new ArrayList<Integer>();
        var colorList = new ArrayList<Integer>();
        var prev = 0;
        for (var i = 0; i < len; i++) {
            var count = prev;
            var temp = queries[i];
            if (!ballList.contains(temp[0]) && !colorList.contains(temp[1])) {
                count = count + 1;
                prev = count;
                ballList.add(temp[0]);
                colorList.add(temp[1]);
            } else {
                var index = ballList.indexOf(temp[0]);
                if (ballList.contains(temp[0]) && colorList.contains(temp[1])) {
                    if (colorList.get(index) == temp[1]) {
                        res[i] = count;
                        continue;
                    } else {
                        count = count - 1;
                        prev = count;
                    }
                } else if (ballList.contains(temp[0])) {
                    if (colorList.contains(temp[1])) {
                        count = count - 1;
                        prev = count;
                    }
                    colorList.set(index, temp[1]);
                } else if (colorList.contains(temp[1])) {
                    res[i] = count;
                    continue;
                } else {
                    count = count - 1;
                    prev = count;
                }
            }
            res[i] = count;
        }
        return res;
    }

    public static String longestWord(String[] words) {
        var list = Stream.of(words).sorted(Comparator.comparing(String::length).reversed()).toList();
        var res = "";
        for (var str : list) {
            var flag = true;
            var temp = str;
            while (str.length() > 1) {
                str = str.substring(0, str.length() - 1);
                if (!list.contains(str)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (res.isEmpty()) {
                    res = temp;
                } else {
                    if (temp.length() >= res.length()) {
                        if (res.compareTo(temp) > 0) {
                            res = temp;
                        }
                    }
                }
            }
        }
        return res;
    }

    public static String findLongestWord(String s, List<String> dictionary) {
        var res = "";
        for (var str : dictionary) {
            if (str.length() < res.length()) {
                continue;
            }
            var arr = str.toCharArray();
            var index = 0;
            var flag = true;
            for (var ch : arr) {
                var tempStr = Character.toString(ch);
                var temp = s.substring(index);
                if (temp.contains(tempStr)) {
                    index = index + temp.indexOf(tempStr) + 1;
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (str.length() > res.length()) {
                    res = str;
                } else if (res.isEmpty()) {
                    res = str;
                } else {
                    if (res.compareTo(str) > 0) {
                        res = str;
                    }
                }
            }
        }
        return res;
    }

    public static int numberOfAlternatingGroups(int[] colors, int k) {
        var res = 0;
        var len = colors.length;
        if (len < k) {
            return res;
        }
        var start = 0;
        while (start + k < len) {
            var temp = start + k;
            var start1 = start + 1;
            var prev = colors[start];
            var flag = true;
            while (start1 <= temp) {
                if (prev == colors[start1]) {
                    flag = false;
                    break;
                }
                prev = colors[start1];
                start1++;
            }
            if (flag) {
                res++;
            }
            start++;
        }
        start = len - k;
        while (start < len) {
            var rem = k - (len - start);
            var start1 = start + 1;
            var prev = colors[start];
            var flag = true;
            while (start1 < len) {
                if (prev == colors[start1]) {
                    flag = false;
                    break;
                }
                prev = colors[start1];
                start1++;
            }
            start1 = 0;
            while (flag && start1 < rem) {
                if (prev == colors[start1]) {
                    flag = false;
                    break;
                }
                prev = colors[start1];
                start1++;
            }
            if (flag) {
                res++;
            }
            start++;
        }
        return res;
    }

    public static String getEncryptedString(String s, int k) {
        var len = s.length();
        var sb = new StringBuilder();
        var arr = s.toCharArray();
        for (var i = 0; i < len; i++) {
            if (i + k < len) {
                sb.append(arr[i + k]);
            } else {
                var temp = k - (len - i);
                if (temp >= len) {
                    temp = temp % len;
                }
                sb.append(arr[temp]);
            }
        }
        return sb.toString();
    }

    public static int minimumChairs(String s) {
        var res = 0;
        var len = s.length();
        var available = 0;
        for (var i = 0; i < len; i++) {
            var ch = s.charAt(i);
            if (ch == 'E') {
                if (available != 0) {
                    available--;
                } else {
                    res++;
                }
            } else {
                available++;
            }
        }
        return res;
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        var list = new ArrayList<>(Arrays.asList(words));
        var res = new ArrayList<String>();
        while (!list.isEmpty()) {
            var temp = maxWidth;
            var tempList = new ArrayList<String>();
            while (!list.isEmpty() && temp <= maxWidth) {
                var word = list.getFirst();
                if (word.length() <= temp) {
                    temp = temp - words.length + 1;
                    tempList.add(list.removeFirst());
                } else {
                    break;
                }
            }
            var count = tempList.size();
            var rem = (maxWidth - count) - temp;
            var str = String.join(" ".repeat(rem / count), tempList);
            res.add(str);
        }
        return res;
    }

    public static boolean canMakeSquare(char[][] grid) {
        var len = grid.length;
        var col = grid[0].length;
        for (var i = 0; i < len; i++) {
            for (var j = 0; j < col; j++) {
                var count = 1;
                if (grid[i][j] == 'W') {
                    if (j + 1 < col && i + 1 < len) {
                        if (grid[i + 1][j] == 'W') {
                            count++;
                        }
                        if (grid[i + 1][j + 1] == 'W') {
                            count++;
                        }
                        if (grid[i][j + 1] == 'W') {
                            count++;
                        }

                        if (count >= 3) {
                            return true;
                        }
                    }

                    if (j - 1 >= 0 && i + 1 < len) {
                        count = 1;

                        if (grid[i + 1][j] == 'W') {
                            count++;
                        }
                        if (grid[i + 1][j - 1] == 'W') {
                            count++;
                        }
                        if (grid[i][j - 1] == 'W') {
                            count++;
                        }

                        if (count >= 3) {
                            return true;
                        }
                    }

                } else {
                    if (j + 1 < col && i + 1 < len) {
                        if (grid[i + 1][j] == 'B') {
                            count++;
                        }
                        if (grid[i + 1][j + 1] == 'B') {
                            count++;
                        }
                        if (grid[i][j + 1] == 'B') {
                            count++;
                        }
                        if (count >= 3) {
                            return true;
                        }
                    }

                    if (j - 1 >= 0 && i + 1 < len) {
                        count = 1;
                        if (grid[i + 1][j] == 'B') {
                            count++;
                        }
                        if (grid[i + 1][j - 1] == 'B') {
                            count++;
                        }
                        if (grid[i][j - 1] == 'B') {
                            count++;
                        }
                        if (count >= 3) {
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    public static String reverseParentheses(String s) {
        var sb = new StringBuilder(s);
        while (s.contains(")")) {
            var index = s.indexOf(')');
            var temp = index - 1;
            while (s.charAt(temp) != '(') {
                temp--;
            }
            sb.replace(temp, index + 1, new StringBuilder(s.substring(temp + 1, index)).reverse().toString());
            s = sb.toString();
        }
        return s;
    }

    public static int maximumGain(String s, int x, int y) {
        var first = "";
        var second = "";
        var num1 = x;
        var num2 = y;
        if (x > y) {
            first = "ab";
            second = "ba";
        } else {
            first = "ba";
            second = "ab";
            num1 = y;
            num2 = x;
        }
        var sb = new StringBuilder(s);
        var res = 0;
        while (s.contains(first) || s.contains(second)) {
            int index;
            if (s.contains(first)) {
                index = s.indexOf(first);
                res = res + num1;
            } else {
                index = s.indexOf(second);
                res = res + num2;
            }
            sb.replace(index, index + 2, "");
            s = sb.toString();
        }
        return res;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        var rightIndex = matrix[0].length;
        var rightStart = 0;
        var downStart = 1;
        var initial = 0;
        var last = matrix.length - 1;
        var list = new ArrayList<Integer>();
        while (initial <= last) {
            var temp = rightStart;
            while (temp < rightIndex) {
                list.add(matrix[initial][temp]);
                temp++;
            }
            rightStart++;
            rightIndex--;
            if (downStart > last) {
                break;
            }
            var stat = temp - 1;
            temp = downStart;
            while (temp <= last) {
                list.add(matrix[temp][stat]);
                temp++;
            }
            var leftStart = rightIndex - 1;
            if (leftStart < rightStart - 1) {
                break;
            }
            while (leftStart >= rightStart - 1) {
                list.add(matrix[last][leftStart]);
                leftStart--;
            }
            var upStart = last - 1;
            if (upStart < initial) {
                break;
            }
            while (upStart > initial) {
                list.add(matrix[upStart][rightStart - 1]);
                upStart--;
            }
            if (rightStart == rightIndex) {
                break;
            }
            initial++;
            downStart++;
            last--;
        }
        return list;
    }

    public static int[][] generateMatrix(int n) {
        var matrix = new int[n][n];
        var rightIndex = n;
        var rightStart = 0;
        var downStart = 1;
        var initial = 0;
        var last = n - 1;
        var num = 1;
        while (initial <= last && num <= n * n) {
            var temp = rightStart;
            while (temp < rightIndex) {
                matrix[initial][temp] = num;
                num++;
                temp++;
            }
            rightStart++;
            rightIndex--;
            if (downStart > last) {
                break;
            }
            var stat = temp - 1;
            temp = downStart;
            while (temp <= last) {
                matrix[temp][stat] = num;
                num++;
                temp++;
            }
            var leftStart = rightIndex - 1;
            if (leftStart < rightStart - 1) {
                break;
            }
            while (leftStart >= rightStart - 1) {
                matrix[last][leftStart] = num;
                num++;
                leftStart--;
            }
            var upStart = last - 1;
            if (upStart < initial) {
                break;
            }
            while (upStart > initial) {
                matrix[upStart][rightStart - 1] = num;
                num++;
                upStart--;
            }
            if (rightStart == rightIndex) {
                break;
            }
            initial++;
            downStart++;
            last--;
            System.out.println(Arrays.toString(matrix));
        }
        return matrix;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        var sb1 = new StringBuilder();
        var sb2 = new StringBuilder();
        while (Objects.nonNull(l1) || Objects.nonNull(l2)) {
            if (Objects.nonNull(l1)) {
                sb1.append(l1.val);
                l1 = l1.next;
            }
            if (Objects.nonNull(l2)) {
                sb2.append(l2.val);
                l2 = l2.next;
            }
        }
        var str1 = sb1.reverse().toString();
        var str2 = sb2.reverse().toString();
        if (sb1.length() > sb2.length()) {
            str1 = sb2.reverse().toString();
            str2 = sb1.reverse().toString();
        }
        var carry = 0;
        var res = new ArrayList<Integer>();
        for (var i = 0; i < str1.length(); i++) {
            var sum = carry + Character.getNumericValue(str1.charAt(i)) + Character.getNumericValue(str2.charAt(i));
            res.add(sum % 10);
            carry = sum / 10;
        }
        var rem = str2.length() - str1.length();
        if (rem > 0) {
            for (var i = str1.length(); i < str2.length(); i++) {
                var sum = carry + Character.getNumericValue(str2.charAt(i));
                res.add(sum % 10);
                carry = sum / 10;
            }
        }
        if (carry != 0) {
            res.add(carry);
        }
        ListNode node = new ListNode(0);
        ListNode temp = node;
        for (int i = 0; i < res.size(); i++) {
            temp.next = new ListNode(res.get(i));
            temp = temp.next;
        }
        return node.next;
    }

    public static ListNode arrayToListNode(int[] arr) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummyHead.next;
    }

    public static void setZeroes(int[][] matrix) {
        List<List<Integer>> list = new ArrayList<>();
        for (var arr : matrix) {
            List<Integer> temp = new ArrayList<>();
            for (var i = 0; i < arr.length; i++) {
                if (arr[i] == 0) {
                    temp.add(i);
                }
            }
            list.add(temp);
        }
        var rowLen = matrix[0].length;
        for (var i = 0; i < list.size(); i++) {
            if (list.get(i).isEmpty()) {
                continue;
            }
            var temp = list.get(i);
            for (var num : temp) {
                var start = 0;
                while (start < rowLen) {
                    matrix[i][start] = 0;
                    start++;
                }
                start = 0;
                while (start < matrix.length) {
                    matrix[start][num] = 0;
                    start++;
                }
            }
        }
    }

    public static void reverseStrWithSpecialCharacters(String str) {
        var arr = str.toCharArray();
        var start = 0;
        var end = arr.length - 1;
        while (start < end) {
            if (Character.isLetter(arr[start]) && Character.isLetter(arr[end])) {
                var temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            } else if (!Character.isLetter(arr[start]) && !Character.isLetter(arr[end])) {
                start++;
                end--;
            } else if (!Character.isLetter(arr[start]) && Character.isLetter(arr[end])) {
                start++;
            } else {
                end--;
            }
        }
        System.out.println(new String(arr));
    }

    public static int[] findDiagonalOrder(int[][] mat) {
        //1  2  3  4
        //5  6  7  8
        //9  10 11 12
        //13 14 15 16
        var len = mat.length * mat[0].length;
        var arr = new int[len];
        var index = 1;
        arr[0] = mat[0][0];
        var start = 1;
        var curr = 0;
        var upStart = 0;
        if (mat[0].length == 1) {
            return Arrays.stream(mat).flatMapToInt(Arrays::stream).toArray();
        } else if (mat.length == 1) {
            return mat[0];
        }
        while (index < len) {
            var temp = curr;
            var down = start;
            while (down >= 0) {
                if (temp <= mat.length - 1 && index < len) {
                    arr[index++] = mat[temp][down];
                    temp++;
                    down--;
                } else {
                    break;
                }
            }
            if (temp - 1 == mat.length - 1) {
                upStart = upStart + 1;
                temp--;
            }
            var num = upStart;
            while (num < mat[0].length) {
                if (num <= mat[0].length - 1 && index < len && temp >= 0) {
                    arr[index++] = mat[temp][num];
                    num = num + 1;
                    if (num <= mat[0].length - 1) {
                        temp--;
                    }
                } else {
                    break;
                }
            }
            var n = 0;
            if (temp != 0) {
                curr = curr - 1;
                n = 1;
            }
            start = n + num - 1;
            curr++;
            upStart++;
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }

    public static String licenseKeyFormatting(String s, int k) {
        var sb = new StringBuilder();
        for (var i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                if (Character.isLetter(ch)) {
                    sb.append(Character.toUpperCase(ch));
                } else {
                    sb.append(ch);
                }
            }
        }
        var len = sb.length();
        if (k >= len) {
            return sb.toString();
        }
        var rem = len % k;
        var res = new StringBuilder();
        if (rem != 0) {
            res.append(sb.substring(0, rem)).append("-");
        }
        while (rem + k <= sb.length()) {
            res.append(sb.substring(rem, rem + k)).append("-");
            rem = rem + k;
        }
        return res.substring(0, res.length() - 1);
    }

    public static boolean isLongPressedName(String name, String typed) {
        if (name.equals(typed)) {
            return true;
        }
        if (name.length() >= typed.length()) {
            return false;
        }
        var count = 0;
        var idx = 0;
        for (var i = 0; i < name.length(); i++) {
            var start = i + 1;
            var curr = name.charAt(i);
            while (start < name.length() && curr == name.charAt(start)) {
                start++;
            }
            if (idx == typed.length()) {
                return false;
            }
            for (var j = idx; j < typed.length(); ) {
                if (curr != typed.charAt(j)) {
                    return false;
                }
                var temp = j + 1;
                while (temp < typed.length() && curr == typed.charAt(temp)) {
                    temp++;
                }
                if (temp - j < start - i) {
                    return false;
                } else if (temp - j != start - i) {
                    count++;
                }
                idx = temp;
                break;
            }
            i = start - 1;
        }
        return count > 0 && typed.length() - idx < 1;
    }

    public static boolean validPalindrome(String s) {
        var sb = new StringBuilder(s);
        var temp = new StringBuilder(s);
        if (s.contentEquals(temp.reverse())) {
            return true;
        }
        var start = 0;
        var end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return isPalindromeWithDeletion(sb, start) || isPalindromeWithDeletion(sb, end);
            } else {
                var s1 = start + 1;
                var e1 = end - 1;
                if (s1 < s.length() && s1 < end && s.charAt(s1) != s.charAt(e1)) {
                    if (s.charAt(start) == s.charAt(s1) && isPalindromeWithDeletion(sb, start)) {
                        return true;
                    } else if (s.charAt(end) == s.charAt(e1) && isPalindromeWithDeletion(sb, end)) {
                        return true;
                    }
                }
            }
            start++;
            end--;
        }
        return true;
    }

    private static boolean isPalindromeWithDeletion(StringBuilder sb, int index) {
        var temp = new StringBuilder(sb.toString());
        temp.replace(index, index + 1, "");
        return isPalindrome(temp.toString());
    }

    private static boolean isPalindrome(String s) {
        var start = 0;
        var end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return getLeaf(root1).equals(getLeaf(root2));
    }

    private static List<Integer> getLeaf(TreeNode node) {
        var list = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current.left == null && current.right == null) {
                list.add(current.val);
            }
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return list;
    }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        if (head == null) {
            if (k == 0) {
                return new ListNode[]{};
            } else {
                return new ListNode[k];
            }
        }
        var list = new ArrayList<Integer>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        var size = list.size();
        var num = 0;
        var rem = 0;
        if (size < k) {
            num = 1;
        } else {
            num = size / k;
            rem = size % k;
        }
        var res = new ListNode[k];
        var count = 0;
        var index = num;
        while (count < k) {
            if (count == 0) {
                index = index + rem;
                var node = listToListNode(list.subList(0, index));
                res[count] = node;
            } else {
                if (index >= size) {
                    res[count] = new ListNode(0).next;
                } else {
                    var node = listToListNode(list.subList(index, index + num));
                    res[count] = node;
                    index = index + num;
                }
            }
            count++;
        }
        return res;
    }

    public static ListNode listToListNode(List<Integer> list) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for (var val : list) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummyHead.next;
    }

    public static String shiftingLetters(String s, int[] shifts) {
        var arr = s.toCharArray();
        var len = shifts.length;
        var sum = IntStream.of(shifts).sum();
        var prev = 0;
        for (int i = 0; i < len; i++) {
            int num = sum - prev;
            var test = arr[i] - 96;
            var temp = test + num;
            temp = temp > 26 ? (temp % 26 == 0 ? 26 : temp % 26) : temp;
            arr[i] = (char) (temp + 96);
            prev = prev + shifts[i];
        }
        return new String(arr);
    }

    public static boolean canAliceWin(int[] nums) {

        var sum1 = IntStream.of(nums).filter(var -> var < 10).sum();
        var sum2 = IntStream.of(nums).filter(var -> var > 10).sum();
        if (sum1 == sum2) {
            return false;
        }
        return true;
    }

    public static List<String> removeComments(String[] source) {
        var res = new ArrayList<String>();
        var isBlockStart = false;
        var isBlockEnd = false;
        var sb = new StringBuilder();
        for (var str : source) {
            if (str.contains("/*") && !isBlockStart) {
                var index = str.indexOf("/*");
                if (str.contains("//")) {
                    var index1 = str.indexOf("//");
                    if (index1 < index) {
                        if (index1 != 0) {
                            res.add(str.substring(0, index1));
                        }
                        continue;
                    }
                }
                if (index != 0) {
                    sb.append(str, 0, index);
                }
                isBlockStart = true;
                if (str.contains("*/")) {
                    var index1 = str.lastIndexOf("*/");
                    if (index + 1 != index1) {
                        if (index1 + 1 != str.length() - 1) {
                            sb.append(str.substring(index1 + 2));
                        }
                        isBlockEnd = true;
                    }
                }
            } else if (isBlockStart && str.contains("*/")) {
                var index = str.indexOf("*/");
                if (index + 1 != str.length() - 1) {
                    sb.append(str.substring(index + 2));
                }
                if (sb.toString().contains("//")) {
                    str = sb.toString();
                    sb = new StringBuilder();
                    index = str.indexOf("//");
                    if (index != 0) {
                        sb.append(str, 0, index);
                    }
                }
                isBlockEnd = true;
            }
            if (isBlockStart && isBlockEnd) {
                if (!sb.isEmpty()) {
                    res.add(sb.toString());
                }
                isBlockStart = false;
                isBlockEnd = false;
                sb = new StringBuilder();
                continue;
            }
            if (str.contains("//") && !isBlockStart) {
                var index = str.indexOf("//");
                if (str.contains("/*") && str.contains("*/")) {
                    var index1 = str.indexOf("/*");
                    if (index1 > index) {
                        if (index != 0) {
                            res.add(str.substring(0, index));
                            continue;
                        }
                    }
                    var index2 = str.lastIndexOf("*/");
                    if (index1 != 0) {
                        sb.append(str, 0, index1);
                    }
                    if (index2 != str.length() - 1) {
                        sb.append(str.substring(index2 + 2));
                    }
                    str = sb.toString();
                    index = str.indexOf("//");
                }
                if ((str.contains("*/") || str.contains("/*")) && index != -1) {
                    if (index != 0) {
                        res.add(str.substring(0, index));
                    }
                    continue;
                }

                if (index != 0 && index != -1) {
                    res.add(str.substring(0, index));
                    continue;
                }
                if (!sb.isEmpty()) {
                    res.add(sb.toString());
                    sb = new StringBuilder();
                    continue;
                } else {
                    res.add(" ");
                    continue;
                }

            }
            if (!isBlockStart) {
                res.add(str);
            }
        }
        return res;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        var start = 0;
        var size = wordDict.size();
        while (start < size) {
            var sb = new StringBuilder(s);
            wordBreakHelper(sb, wordDict, start);
            if (sb.isEmpty()) {
                return true;
            }
            start++;
        }
        return false;
    }

    private static void wordBreakHelper(StringBuilder sb, List<String> wordDict, int start) {
        var str = "";
        if (start < wordDict.size()) {
            str = wordDict.get(start);
        } else {
            var flag = wordDict.stream().anyMatch(sb.toString()::contains);
            if (flag) {
                wordBreakHelper(sb, wordDict, 0);
            } else {
                return;
            }
        }
        var st = sb.toString();
        if (st.contains(str)) {
            var index = st.indexOf(str);
            sb.replace(st.indexOf(str), index + str.length(), "");
        } else {
            if (start - 1 > 0) {
                wordBreakHelper(sb, wordDict, start - 1);
            }
        }
        if (sb.isEmpty()) {
            return;
        }
        if (start < wordDict.size()) {
            start = start + 1;
            wordBreakHelper(sb, wordDict, start);
        }
    }

    public static int maxArea(int[] height) { //Container with most water
        var start = 0;
        var max = 0;
        var end = height.length - 1;
        while (start < end) {
            var h = end - start;
            var width = Math.min(height[end], height[start]);
            max = Math.max(max, h * width);
            if (height[start] < height[end]) {
                start++;
            } else if (height[start] > height[end]) {
                end--;
            } else {
                start++;
                end--;
            }
        }
        return max;
    }

    public static int trap(int[] height) {
        var start = 0;
        var end = height.length - 1;
        var rightMax = Integer.MIN_VALUE;
        var leftMax = rightMax;
        var res = 0;
        while (start < end) {
            leftMax = Math.max(leftMax, height[start]);
            rightMax = Math.max(rightMax, height[end]);
            if (leftMax < rightMax) {
                res = res + leftMax - height[start];
                start++;
            } else {
                res = res + rightMax - height[end];
                end--;
            }
        }
        return res;
    }

    public static int[] productExceptSelf(int[] nums) {
        var len = nums.length;
        var res = new int[len];
        var leftProduct = 1;
        var rightProduct = 1;
        for (var i = 0; i < len; i++) {
            res[i] = leftProduct;
            leftProduct = leftProduct * nums[i];
        }
        for (var i = len - 1; i >= 0; i--) {
            res[i] = res[i] * rightProduct;
            rightProduct = rightProduct * nums[i];
        }
        return res;
    }

    public static int evalRPN(String[] tokens) {
        var stack = new Stack<Integer>();
        for (var str : tokens) {
            if (Character.isDigit(str.charAt(str.length() - 1))) {
                stack.push(Integer.parseInt(str));
            } else if (str.equals("+")) {
                if (stack.size() < 2) {
                    return stack.peek();
                }
                var num1 = stack.pop();
                var num2 = stack.pop();
                stack.push(num2 + num1);
            } else if (str.equals("*")) {
                if (stack.size() < 2) {
                    return stack.peek();
                }
                var num1 = stack.pop();
                var num2 = stack.pop();
                stack.push(num2 * num1);
            } else if (str.equals("-")) {
                if (stack.size() < 2) {
                    return stack.peek();
                }
                var num1 = stack.pop();
                var num2 = stack.pop();
                stack.push(num2 - num1);
            } else {
                if (stack.size() < 2) {
                    return stack.peek();
                }
                var num1 = stack.pop();
                var num2 = stack.pop();
                stack.push(num2 / num1);
            }
        }
        return stack.peek();
    }

    //1,2,3,4,5
    public static ListNode reverseList(ListNode head) {
        ListNode next;
        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static String decodeString(String input) {
        var numStack = new Stack<Integer>();
        if (!input.contains("[")) {
            if (Character.isDigit(input.charAt(0))) {
                var temp = input.replaceAll("[^0-9]", "");
                return input.substring(temp.length()).repeat(Integer.parseInt(temp));
            } else {
                return input;
            }
        }
        var sb = new StringBuilder();
        var open = -1;
        for (var i = 0; i < input.length(); i++) {
            if (open != -1 && input.charAt(i) != '[') {
                if (input.charAt(i) == ']') {
                    var num = numStack.pop();
                    var temp = sb.toString().repeat(num);
                    var t = new StringBuilder(input);
                    var len = Integer.toString(num).length();
                    input = t.replace(open - len, i + 1, temp).toString();
                    if (input.lastIndexOf("[") != -1) {
                        i = -1;
                    } else {
                        break;
                    }
                    open = -1;
                    sb = new StringBuilder();
                    continue;
                }
                if (Character.isDigit(input.charAt(i))) {
                    var temp = i + 1;
                    while (temp < input.length()) {
                        if (!Character.isDigit(input.charAt(temp))) {
                            break;
                        }
                        temp++;
                    }
                    numStack.push(Integer.parseInt(input.substring(i, temp)));
                    i = temp - 1;
                    continue;
                }
                sb.append(input.charAt(i));
                continue;
            }
            if (Character.isDigit(input.charAt(i))) {
                var temp = i + 1;
                while (temp < input.length()) {
                    if (!Character.isDigit(input.charAt(temp))) {
                        break;
                    }
                    temp++;
                }
                numStack.push(Integer.parseInt(input.substring(i, temp)));
                i = temp - 1;
            } else if (input.charAt(i) == '[') {
                open = i;
                sb = new StringBuilder();
            } else if (!input.contains("[")) {
                break;
            }
        }
        return input;
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        var res = new StringBuilder();
        if ((numerator < 0 && denominator > 0) || numerator > 0 && denominator < 0) {
            res.append("-");
        }
        long n = Math.abs(numerator);
        long d = Math.abs(denominator);
        long quo = n / d;
        long rem = n % d;
        res.append(quo);
        if (rem == 0) {
            return res.toString();
        }
        res.append(".");
        var map = new HashMap<Long, Integer>();
        while (rem != 0) {
            if (map.containsKey(rem)) {
                res.insert(map.get(rem), "(").append(")");
                return res.toString();
            }
            map.put(rem, res.length());
            rem = rem * 10;
            quo = rem / d;
            rem = rem % d;
            res.append(Math.abs(quo));
        }
        return res.toString();
    }

    public static int winningPlayerCount(int n, int[][] pick) {
        var list = new ArrayList<Integer>();
        var map = new HashMap<Integer, List<Integer>>();
        for (var arr : pick) {
            var num = arr[0];
            if (!map.containsKey(num)) {
                list = new ArrayList<>();
                list.add(arr[1]);
                map.put(num, list);
            } else {
                var temp = map.get(num);
                temp.add(arr[1]);
                map.put(num, temp);
            }
        }
        var res = 0;
        for (var entry : map.entrySet()) {
            var temp = entry.getValue().stream().collect(Collectors.groupingBy(var -> var, Collectors.counting()));
            if (temp.values().stream().anyMatch(var -> var == entry.getKey() + 1)) {
                res++;
            }
        }
        return res;
    }

    public static int removeDuplicates(int[] nums) {
        var len = nums.length;
        if (len <= 2) {
            return len;
        }
        var start = 2;
        for (var i = 2; i < len; i++) {
            var curr = nums[i];
            var prev1 = nums[start - 1];
            var prev2 = nums[start - 2];
            if (curr != prev1 || curr != prev2) {
                nums[start++] = curr;
            }
        }
        return start;
    }

    public static int maxSumContiguosSubArray(int[] array) {
        var pref = 0;
        var max = Integer.MIN_VALUE;
        for (var i = 0; i < array.length; i++) {
            pref = pref + array[i];
            max = Math.max(max, pref);
            if (pref < 0) {
                pref = 0;
            }
        }
        return max;
    }

    public static int maxProduct(int[] nums) {
        double pref = 1;
        double suff = 1;
        double max = Integer.MIN_VALUE;
        var len = nums.length;
        for (var i = 0; i < len; i++) {
            if (pref == 0) {
                pref = 1;
            }
            if (suff == 0) {
                suff = 1;
            }
            pref = pref * nums[i];
            suff = suff * nums[len - i - 1];
            max = Math.max(max, Math.max(pref, suff));
        }
        return (int) max;
    }

    public static int[] corpFlightBookings(int[][] bookings, int n) {
        var res = new int[n];
        for (var arr : bookings) {
            var start = arr[0] - 1;
            var end = arr[1] - 1;
            var seats = arr[2];
            while (start <= end) {
                res[start] = res[start] + seats;
                start++;
            }
        }
        return res;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        var len = nums.length;
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        for (var i = 0; i < len - 3; i++) {
            for (var j = i + 1; j < len - 2; j++) {
                var start = j + 1;
                var end = len - 1;
                while (start < end) {
                    long sum = nums[i] + nums[j] + nums[start] + nums[end];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
                        start++;
                        end--;
                    } else if (sum < target) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    public static List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        var len = nums.length;
        Set<List<Integer>> res = new HashSet<>();
        for (var i = 0; i < len - 2; i++) {
            var start = i + 1;
            var end = len - 1;
            while (start < end) {
                long sum = nums[i] + nums[start] + nums[end];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    start++;
                    end--;
                } else if (sum < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return new ArrayList<>(res);
    }

    public static int lengthOfCycle(int[] arr, int startIndex) {
        var map = new HashMap<Integer, Integer>();
        var count = 0;
        while (startIndex >= 0 && startIndex < arr.length) {
            if (map.containsKey(startIndex)) {
                return count - map.get(startIndex);
            }
            map.put(startIndex, count);
            count++;
            startIndex = arr[startIndex];
        }
        return -1;
    }

    public static int lengthOfCycle1(int[] arr, int startIndex) {
        int slow = startIndex;
        int count = 0;
        while (count < arr.length) {
            int fast = slow;
            int steps = 0;
            while (fast >= 0 && fast < arr.length) {
                fast = arr[fast];
                steps++;
                if (fast == slow) {
                    return steps;
                }
            }
            slow = arr[slow];
            count++;
        }
        return -1;
    }

    public static int finalPositionOfSnake(int n, List<String> commands) {

        int index = 1;
        int row = 1;

        for (String str : commands) {
            switch (str) {
                case "UP" -> row--;
                case "DOWN" -> row++;
                case "LEFT" -> index--;
                case "RIGHT" -> index++;
            }
        }
        int val = (n * row) - 1;
        int start = (val + 1) - n;
        return start + index - 1;
    }

    public static boolean checkMeta(String str1, String str2) {
        if (str1.equals(str2)) {
            return false;
        }
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] arr2 = str2.toCharArray();
        char[] arr1 = str1.toCharArray();
        for (int i = 0; i < str1.length(); i++) {
            if (arr1[i] != arr2[i]) {
                if (i + 1 < str1.length() && arr2[i + 1] != arr2[i]) {
                    char temp = arr2[i];
                    arr2[i] = arr2[i + 1];
                    arr2[i + 1] = temp;
                    if (Arrays.equals(arr1, arr2)) {
                        return true;
                    } else {
                        temp = arr2[i + 1];
                        arr2[i + 1] = arr2[i];
                        arr2[i] = temp;
                    }
                }
                if (i - 1 >= 0 && arr2[i - 1] != arr2[i]) {
                    char temp = arr2[i];
                    arr2[i] = arr2[i - 1];
                    arr2[i - 1] = temp;
                    if (Arrays.equals(arr1, arr2)) {
                        return true;
                    } else {
                        temp = arr2[i - 1];
                        arr2[i - 1] = arr2[i];
                        arr2[i] = temp;
                    }
                }
            }
        }
        return false;
    }

//    public static ListNode modifiedList(int[] nums, ListNode head) {
//        Set<Integer> set = new HashSet<>();
//        for(int num : nums){
//            set.add(num);
//        }
//        while (head != null) {
//            var num = head.val;
//
//            head = head.next;
//        }
//        return
//    }

    public static String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i + 3 <= s.length() && s.substring(i, i + 3).contains("#")) {
                sb.append((char) (Integer.parseInt(s.substring(i, i + 2)) + 96));
                i = i + 2;
            } else {
                int start = i;
                while (start < s.length() && start + 3 <= s.length() && !s.substring(start, start + 3).contains("#")) {
                    sb.append((char) (Character.getNumericValue(s.charAt(start)) + 96));
                    start++;
                }
                i = start - 1;
            }
        }
        return sb.toString();
    }

    public static int maxRepeating(String sequence, String word) {
        if (sequence.equalsIgnoreCase(word)) {
            return 1;
        }
        int seqLength = sequence.length();
        int wordLength = word.length();
        int res = 0;
        for (int i = 0; i < seqLength; i++) {
            if ((i + wordLength <= seqLength && sequence.substring(i, i + wordLength).equalsIgnoreCase(word))) {
                res++;
                i = i + wordLength - 1;
            }
        }
        return res;
    }

    public static int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 1; i < m; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < n; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[n - 1][m - 1];
    }

    public static int uniquePaths(int n, int m) {
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            grid[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            grid[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                grid[i][j] = grid[i][j - 1] + grid[i - 1][j];
            }
        }
        return grid[n - 1][m - 1];
    }

    //1,1
//-1,-1
//0,0
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        if (obstacleGrid[n - 1][m - 1] == 1 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        obstacleGrid[0][0] = 1;
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[i][0] == 1) {
                obstacleGrid[i][0] = -1;
            } else if (obstacleGrid[i - 1][0] == -1) {
                obstacleGrid[i][0] = -1;
            } else {
                obstacleGrid[i][0] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[0][i] == 1) {
                obstacleGrid[0][i] = -1;
            } else if (obstacleGrid[0][i - 1] == -1) {
                obstacleGrid[0][i] = -1;
            } else {
                obstacleGrid[0][i] = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = -1;
                    continue;
                }
                if (obstacleGrid[i][j - 1] == -1 && obstacleGrid[i - 1][j] == -1) {
                    obstacleGrid[i][j] = 0;
                } else if (obstacleGrid[i][j - 1] != -1 && obstacleGrid[i - 1][j] != -1) {
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1] + obstacleGrid[i - 1][j];
                } else {
                    obstacleGrid[i][j] = Math.max(obstacleGrid[i][j - 1], obstacleGrid[i - 1][j]);
                }
            }
        }
        return obstacleGrid[n - 1][m - 1];
    }

    public static int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        int res = Integer.MAX_VALUE;
        int diff = res;
        for (int i = 0; i < len - 2; i++) {
            int start = i + 1;
            int end = len - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum == target) {
                    res = target;
                    diff = 0;
                    start++;
                    end--;
                } else if (sum < target) {
                    if (Math.abs(target - sum) < diff) {
                        res = sum;
                        diff = Math.abs(target - sum);
                    }
                    start++;
                } else {
                    if (Math.abs(target - sum) < diff) {
                        res = sum;
                        diff = Math.abs(target - sum);
                    }
                    end--;
                }
            }
        }
        return res;
    }

    public static int maxPoints(int[][] points) {
        TreeSet<Integer> x = new TreeSet<>();
        TreeSet<Integer> y = new TreeSet<>();
        for (int i = 0; i < points.length; i++) {
            int temp1 = points[i][0];
            int temp2 = points[i][1];
            if (temp1 < 0) {
                y.add(temp1);
            } else {
                x.add(temp1);
            }
            if (temp2 < 0) {
                x.add(temp2);
            } else {
                y.add(temp2);
            }
        }
        int temp = 1;
        int xMax = 1;
        var list = new ArrayList<>(x);
        for (int i = 1; i < list.size(); i++) {
            if (Math.abs(list.get(i) - list.get(i - 1)) == 1) {
                xMax++;
            } else {
                temp = xMax;
                xMax = 1;
            }
        }
        xMax = Math.max(temp, xMax);
        temp = 1;
        int yMax = 1;
        list = new ArrayList<>(y);
        for (int i = 1; i < list.size(); i++) {
            if (Math.abs(list.get(i) - list.get(i - 1)) == 1) {
                yMax++;
            } else {
                temp = yMax;
                yMax = 1;
            }
        }
        yMax = Math.max(temp, yMax);
        return Math.max(xMax, yMax);
    }

    public static void posAndNeg(int[] arr) {
        // Write your code here
        Queue<Integer> pos = new LinkedList<>();
        Queue<Integer> neg = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0) {
                pos.add(arr[i]);
            } else {
                neg.add(arr[i]);
            }
        }
        boolean isPos = true;
        int count = 0;
        while (!pos.isEmpty() || !neg.isEmpty()) {
            if (isPos) {
                arr[count++] = pos.poll();
                isPos = false;
            } else {
                arr[count++] = neg.poll();
                isPos = true;
            }
        }
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        for (int i = size - 2; i >= 0; i--) {
            var currSize = triangle.get(i).size();
            for (int j = 0; j < currSize; j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) +
                        (Math.min(triangle.get(i + 1).get(j),
                                triangle.get(i + 1).get(j + 1))));
            }
        }
        return triangle.getFirst().getFirst();
    }

    public static int[] getFinalState(int[] nums, int k, int multiplier) {
        while (k > 0) {
            int minIndex = 0;
            int min = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (min < nums[i]) {
                    min = nums[i];
                    minIndex = i;
                }
            }
            nums[minIndex] *= multiplier;
            k--;
        }
        return nums;
    }

    public static int maxVowels(String s, int k) {
        var set = Set.of('a', 'e', 'i', 'o', 'u');
        int vCount = 0;
        for (int i = 0; i < k; i++) {
            if (set.contains(s.charAt(i))) {
                vCount++;
            }
        }
        int max = vCount;
        int count = 0;
        for (int i = k; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                vCount++;
            }
            if (set.contains(s.charAt(count))) {
                vCount--;
            }
            count++;
            max = Math.max(max, vCount);
        }
        return max;
    }

    public static String longestNiceSubstring(String s) {
        if (s.length() <= 1) {
            return "";
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        String res = "";
        int len = 0;
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && (Character.toLowerCase(ch) == stack.peek() || Character.toUpperCase(ch) == stack.peek())) {
                if (sb.isEmpty()) {
                    sb.append(stack.peek()).append(ch);
                } else {
                    sb.append(ch);
                }
            } else {
                stack.clear();
                if (sb.length() > len) {
                    res = sb.toString();
                    len = res.length();
                }
                sb = new StringBuilder();
                stack.push(ch);
            }
        }
        return !sb.isEmpty() && sb.length() > len ? sb.toString() : res;
    }

    private static String getCoordinate(char c1, int n1) {
        String str1 = "";
        if (c1 == 'a' || c1 == 'c' || c1 == 'e' || c1 == 'g') {
            if (n1 % 2 != 0) {
                str1 = "b";
            } else {
                str1 = "w";
            }
        } else if (c1 == 'b' || c1 == 'd' || c1 == 'f' || c1 == 'h') {
            if (n1 % 2 == 0) {
                str1 = "b";
            } else {
                str1 = "w";
            }
        }
        return str1;
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (words.length == 0) {
            return list;
        }
        int varLength = words[0].length();
        HashMap<String, Integer> hm = new HashMap<>();
        for (String word : words) {
            hm.put(word, hm.getOrDefault(word, 0) + 1);
        }
        HashMap<String, Integer> hmapCopy = new HashMap<>(hm);
        int count = 0;
        for (int i = 0; i < s.length() - varLength - 1; i = i + varLength) {
            String sub = s.substring(i, i + varLength);
            if (hm.containsKey(sub) && hm.get(sub) > 0) {
                count++;
                hm.put(sub, hm.get(sub) - 1);
                if (count == words.length) {
                    int actual = varLength * words.length;
                    list.add(i - (actual - varLength));
                    count = 0;
                    hm = new HashMap<>(hmapCopy);
                }
            } else {
                i = i - varLength;
                count = 0;
                hm = new HashMap<>(hmapCopy);
            }
        }
        return list;
    }

    public static char kthCharacter(int k) {
        String str = "a";
        while (str.length() < k) {
            StringBuilder sb = new StringBuilder(str);
            for (char ch : str.toCharArray()) {
                int num = ch - 97;
                if (num == 25) {
                    sb.append('a');
                } else {
                    char ch1 = (char) (num + 1 + 97);
                    sb.append(ch1);
                }
            }
            str = sb.toString();
        }
        return str.charAt(k - 1);
    }

    public char kthCharacter1(long k, int[] operations) { // hard //758/901
        StringBuilder sb = new StringBuilder("a");
        for (int num : operations) {
            if (sb.length() >= k) {
                break;
            }
            if (num == 0) {
                sb.append(sb);
            } else {
                sb = getKthChar(sb.toString());
            }
        }
        return sb.charAt((int) k - 1);
    }

    public StringBuilder getKthChar(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (char ch : str.toCharArray()) {
            int num = ch - 97;
            if (num == 25) {
                sb.append('a');
            } else {
                char ch1 = (char) (num + 1 + 97);
                sb.append(ch1);
            }
        }
        return sb;
    }

    public boolean isArraySpecial(int[] nums) {
        if (nums.length < 2) {
            return true;
        }
        for (var i = 1; i < nums.length; i++) {
            if ((nums[i] % 2 == 0 && nums[i - 1] % 2 == 0) || (nums[i] % 2 != 0 && nums[i - 1] % 2 != 0)) {
                return false;
            }
        }
        return true;
    }

    public int duplicateNumbersXOR(int[] nums) {
        var map = new HashMap<Integer, Integer>();
        for (var num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        var res = 0;
        for (var entry : map.entrySet()) {
            if (entry.getValue() == 2) {
                res = res ^ entry.getKey();
            }
        }
        return res;
    }

    public String findLatestTime(String s) {
        var arr = s.split(":");
        if (arr[0].equals("??") && arr[1].equals("??")) {
            return "11:59";
        }
        var str1 = arr[0];
        var res = new StringBuilder();
        if (str1.equals("??")) {
            res.append("11");
        } else {
            if (Character.isDigit(str1.charAt(0))) {
                res.append(str1.charAt(0));
                if (Character.isDigit(str1.charAt(1))) {
                    res.append(str1.charAt(1));
                } else {
                    if (Character.getNumericValue(str1.charAt(0)) == 1) {
                        res.append(1);
                    } else {
                        res.append(9);
                    }
                }
            } else {
                if (Character.isDigit(str1.charAt(1))) {
                    if (Character.getNumericValue(str1.charAt(1)) == 1 || Character.getNumericValue(str1.charAt(1)) == 0) {
                        res.append(1).append(str1.charAt(1));
                    } else {
                        res.append(0).append(str1.charAt(1));
                    }
                } else {
                    res.append("11");
                }
            }
        }

        res.append(":");
        var str2 = arr[1];

        if (str2.equals("??")) {
            res.append("59");
        } else {
            if (Character.isDigit(str2.charAt(0))) {
                res.append(str2.charAt(0));
                if (Character.isDigit(str2.charAt(1))) {
                    res.append(str2.charAt(1));
                } else {
                    res.append(9);
                }
            } else {
                if (Character.isDigit(str2.charAt(1))) {
                    res.append(5).append(str2.charAt(1));
                } else {
                    res.append(59);
                }
            }
        }
        return res.toString();
    }

    public String maximumTime(String time) {
        var arr = time.split(":");
        if (arr[0].equals("??") && arr[1].equals("??")) {
            return "23:59";
        }
        var str1 = arr[0];
        var res = new StringBuilder();
        if (str1.equals("??")) {
            res.append("23");
        } else {
            if (Character.isDigit(str1.charAt(0))) {
                res.append(str1.charAt(0));
                if (Character.isDigit(str1.charAt(1))) {
                    res.append(str1.charAt(1));
                } else {
                    if (Character.getNumericValue(str1.charAt(0)) == 2) {
                        res.append(3);
                    } else {
                        res.append(9);
                    }
                }
            } else {
                if (Character.isDigit(str1.charAt(1))) {
                    if (Character.getNumericValue(str1.charAt(1)) > 3) {
                        res.append(1).append(str1.charAt(1));
                    } else {
                        res.append(2).append(str1.charAt(1));
                    }
                } else {
                    res.append(23);
                }
            }
        }
        res.append(":");
        var str2 = arr[1];
        if (str2.equals("??")) {
            res.append("59");
        } else {
            if (Character.isDigit(str2.charAt(0))) {
                res.append(str2.charAt(0));
                if (Character.isDigit(str2.charAt(1))) {
                    res.append(str2.charAt(1));
                } else {
                    res.append(9);
                }
            } else {
                if (Character.isDigit(str2.charAt(1))) {
                    res.append(5).append(str2.charAt(1));
                } else {
                    res.append(59);
                }
            }
        }
        return res.toString();
    }

    public int longestValidParentheses(String s) {
        var stack = new Stack<Character>();
        var arr = s.toCharArray();
        var res = 0;
        for (var ch : arr) {
            if (ch == '(') {
                stack.push(ch);
            } else if (!stack.isEmpty() && ch == ')' && stack.peek() == '(') {
                stack.pop();
                res = res + 2;
            }
        }
        return res;
    }

    public String largestNumber(int[] nums) {
        var str = Arrays.stream(nums).boxed().map(String::valueOf).sorted((var1, var2) -> (var2 + var1).compareTo(var1 + var2)).collect(Collectors.joining());

        if (new BigInteger(str).equals(BigInteger.ZERO)) {
            return "0";
        }

        return str;

    }

    public long smallestNumber(long num) {
        if (num == 0) {
            return 0;
        }

        Comparator<String> comparator = (str1, str2) -> {
            if (num < 0) {
                return (str2 + str1).compareTo(str1 + str2);
            }
            return (str1 + str2).compareTo(str2 + str1);
        };
        if (num < 0) {
            return -smallestNumber(Math.abs(num), Solution::compare1, true);
        }
        return smallestNumber(num, Solution::compare, false);
    }

    public long smallestNumber(long num, Comparator<String> cmp, boolean isNegative) {


        var str = String.valueOf(num).chars().mapToObj(var -> (char) var).map(String::valueOf).sorted(cmp).collect(Collectors.joining());

        if (str.charAt(0) == '0') {
            var arr = str.toCharArray();
            var index = 0;
            if (isNegative) {
                index = getMax(arr);
            } else {
                index = getMin(str);
            }
            arr[0] = arr[index];
            arr[index] = '0';
            str = new String(arr);
        }
        return Long.parseLong(str);
    }

    private int getMin(String arr) {
        var val = arr.chars().mapToObj(var -> (char) var).map(Character::getNumericValue).filter(var -> var > 0).min(Comparator.comparingInt(Integer::intValue)).get();
        return arr.indexOf(Integer.toString(val));
    }

    private int getMax(char[] arr) {
        var index = 0;
        var max = Character.getNumericValue(arr[0]);
        for (var i = 1; i < arr.length; i++) {
            if (Character.getNumericValue(arr[i]) > max) {
                index = i;
                max = Character.getNumericValue(arr[i]);
            }
        }
        return index;
    }

    public String clearDigits(String s) {
        var stack = new Stack<Character>();
        var arr = s.toCharArray();
        for (var ch : arr) {
            if (Character.isDigit(ch)) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty() ? "" : stack.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public int countCompleteDayPairs(int[] hours) {
        var result = 0;
        for (var i = 0; i < hours.length; i++) {
            for (var j = i + 1; j < hours.length; j++) {
                if ((hours[i] + hours[j]) % 24 == 0) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    public double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        Deque<Integer> que = new LinkedList<>();

        for (var num : nums) {
            que.addLast(num);
        }
        var len = nums.length / 2;
        var avg = Double.MAX_VALUE;
        while (len-- > 0) {
            avg = Math.min(avg, ((double) que.pollFirst() + (double) que.pollLast()) / 2.0);
        }
        return avg;
    }

    public String countAndSay(int n) {
        var str = "1";
        if (n == 1) {
            return str;
        }
        var temp = 2;
        while (temp <= n) {
            var len = str.length();
            var prev = str.charAt(0);
            var sb = new StringBuilder();
            var count = 1;
            for (var i = 1; i < len; i++) {
                var curr = str.charAt(i);
                if (prev == curr) {
                    count++;
                } else {
                    sb.append(count).append(prev);
                    count = 1;
                    prev = curr;
                }
            }
            str = sb.append(count).append(prev).toString();
            temp++;
        }
        return str;
    }

    public int[][] modifiedMatrix(int[][] matrix) {
        var len = matrix.length;
        for (var i = 0; i < len; i++) {
            var temp = matrix[i];
            for (var j = 0; j < temp.length; j++) {
                if (temp[j] == -1) {
                    var curr = 0;
                    var max = Integer.MIN_VALUE;
                    while (curr < len) {
                        max = Math.max(max, matrix[curr][j]);
                        curr++;
                    }
                    matrix[i][j] = max;
                }
            }
        }
        return matrix;
    }

    public String clearStars(String s) {
        var cnt = s.chars().mapToObj(c -> (char) c).filter(var -> var == '*').count();
        if (cnt >= s.length() - cnt) {
            return "";
        }
        var arr = s.toCharArray();
        List<String> list = new ArrayList<>();
        for (var ch : arr) {
            if (ch == '*') {
                var min = Collections.min(list);
                var index = list.lastIndexOf(min);
                list.remove(index);
            } else {
                list.add(Character.toString(ch));
            }
        }
        return String.join("", list);
    }

    public int numberOfAlternatingGroups(int[] colors) {
        var len = colors.length;
        var res = 0;
        if (len < 3) {
            return res;
        }
        var temp1 = colors[len - 2];
        var temp2 = colors[len - 1];
        if (temp1 != temp2 && temp2 != colors[0]) {
            res++;
        }
        if (temp2 != colors[0] && colors[0] != colors[1]) {
            res++;
        }
        var start = 0;
        var mid = 1;
        var end = 2;
        while (end < len) {
            if (colors[start] != colors[mid] && colors[mid] != colors[end]) {
                res++;
            }
            start++;
            mid++;
            end++;
        }
        return res;
    }

    public int equalPairs(int[][] grid) {
        var len = grid.length;
        var rowMap = new HashMap<Integer, int[]>();
        var columnMap = new HashMap<Integer, int[]>();
        for (var i = 0; i < len; i++) {
            rowMap.put(i, grid[i]);
        }
        for (var i = 0; i < len; i++) {
            var col = new int[len];
            for (var j = 0; j < len; j++) {
                col[j] = grid[j][i];
            }
            columnMap.put(i, col);
        }
        var res = 0;
        for (var i = 0; i < len; i++) {
            for (var j = 0; j < len; j++) {
                if (Arrays.equals(rowMap.get(i), columnMap.get(j))) {
                    res++;
                }
            }
        }
        return res;
    }

    public boolean satisfiesConditions(int[][] grid) {
        var len = grid.length;
        var col = grid[0].length;
        if (col == 1 && len == 1) {
            return true;
        }
        if (col == 1) {
            for (int i = 1; i < len; i++) {
                if (grid[i][0] != grid[i - 1][0]) {
                    return false;
                }
            }
            return true;
        }
        for (var i = 0; i < len; i++) {
            for (var j = 0; j < col; j++) {
                if (j + 1 < col && grid[i][j] != grid[i][j + 1]) {
                    return false;
                }
                if (i + 1 < len && grid[i][j] == grid[i + 1][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int findPermutationDifference(String s, String t) {
        var res = 0;
        for (var i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            var index = t.indexOf(ch);
            res = res + Math.abs(i - index);
        }
        return res;
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        var list = new ArrayList<>(Arrays.stream(grid).flatMapToInt(Arrays::stream).boxed().toList());
        Collections.rotate(list, k);
        List<List<Integer>> res = new ArrayList<>();
        var len = grid.length;
        var start = 0;
        var len1 = grid[0].length;
        var index = 0;
        while (start < len) {
            var temp = index + len1;
            res.add(list.subList(index, temp));
            index = temp;
            start++;
        }
        return res;
    }

    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        var len = queries.length;
        var res = new long[len];
        var list = new ArrayList<Integer>();
        for (var num : nums) {
            list.add(num);
        }
        for (var i = 0; i < len; i++) {
            var temp = queries[i];
            list.set(temp[0], null);
            var tempList = list.stream().filter(Objects::nonNull).sorted(Comparator.naturalOrder()).limit(temp[1]).toList();
            if (tempList.size() < temp[1]) {
                res[i] = 0;
                break;
            } else {
                var prev = 0;
                for (var num : tempList) {
                    var index = list.subList(prev, list.size()).indexOf(num);
                    list.set(prev + index, null);
                }
            }
            res[i] = list.parallelStream().filter(Objects::nonNull).mapToInt(Integer::intValue).sum();
        }
        return res;
    }

    public int returnToBoundaryCount(int[] nums) {
        var res = 0;
        var temp = 0;
        for (var num : nums) {
            temp = temp + num;
            if (temp == 0) {
                res++;
            }
        }
        return res;
    }

    public int minimumRightShifts(List<Integer> nums) {
        var list = new ArrayList<>(nums);
        Collections.sort(list);
        var len = list.size();

        if (list.equals(nums)) {
            return 0;
        }

        while (len > 0) {
            Collections.rotate(nums, -1);
            if (list.equals(nums)) {
                return list.size() - len;
            }
            len--;
        }
        return -1;
    }

    public int minLength(String s) {
        var sb = new StringBuilder(s);
        while (s.contains("AB") || s.contains("CD")) {
            var index = s.indexOf("AB");
            if (index != -1) {
                sb.replace(index, index + 2, "");
            } else {
                index = s.indexOf("CD");
                sb.replace(index, index + 2, "");
            }
            s = sb.toString();
        }
        return s.length();
    }

    public String makeSmallestPalindrome(String s) {
        var arr = s.toCharArray();
        var start = 0;
        var end = arr.length - 1;
        while (start < end) {
            if (arr[start] != arr[end]) {
                if (arr[start] > arr[end]) {
                    arr[start] = arr[end];
                } else {
                    arr[end] = arr[start];
                }
            }
            start++;
            end--;
        }
        return new String(arr);
    }

    public void wiggleSort(int[] nums) {
        var list = new ArrayList<Integer>();
        for (var num : nums) {
            list.add(num);
        }
        Collections.sort(list);
        var start = 0;
        while (!list.isEmpty()) {
            nums[start] = list.removeFirst();
            if (!list.isEmpty()) {
                start = start + 1;
                nums[start] = list.removeLast();
            }
            start++;
        }
    }

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        var matrix = new int[m][n];
        var rightIndex = n;
        var rightStart = 0;
        var downStart = 1;
        var initial = 0;
        var last = m - 1;
        while (initial <= last) {
            var temp = rightStart;
            while (temp < rightIndex) {
                matrix[initial][temp] = Objects.nonNull(head) ? head.val : -1;
                if (Objects.nonNull(head)) {
                    head = head.next;
                }
                temp++;
            }
            rightStart++;
            rightIndex--;
            if (downStart > last) {
                break;
            }
            var stat = temp - 1;
            temp = downStart;
            while (temp <= last) {
                matrix[temp][stat] = Objects.nonNull(head) ? head.val : -1;
                if (Objects.nonNull(head)) {
                    head = head.next;
                }
                temp++;
            }
            var leftStart = rightIndex - 1;
            if (leftStart < rightStart - 1) {
                break;
            }
            while (leftStart >= rightStart - 1) {
                matrix[last][leftStart] = Objects.nonNull(head) ? head.val : -1;
                if (Objects.nonNull(head)) {
                    head = head.next;
                }
                leftStart--;
            }
            var upStart = last - 1;
            if (upStart < initial) {
                break;
            }
            while (upStart > initial) {
                matrix[upStart][rightStart - 1] = Objects.nonNull(head) ? head.val : -1;
                if (Objects.nonNull(head)) {
                    head = head.next;
                }
                upStart--;
            }
            if (rightStart == rightIndex) {
                break;
            }
            initial++;
            downStart++;
            last--;
        }
        return matrix;
    }

    public int hammingDistance(int x, int y) {
        var first = x;
        var second = y;
        if (x < y) {
            first = y;
            second = x;
        }
        var str1 = Integer.toBinaryString(first);
        var str2 = Integer.toBinaryString(second);
        var len = str1.length();
        str2 = "0".repeat(len - str2.length()) + str2;
        var res = 0;
        for (var i = 0; i < len; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                res++;
            }
        }
        return res;
    }

    public int totalHammingDistance(int[] nums) {
        var max = IntStream.of(nums).max().getAsInt();
        var temp = Integer.toBinaryString(max);
        var arr = new String[nums.length];
        for (var i = 0; i < nums.length; i++) {
            var st = Integer.toBinaryString(nums[i]);
            arr[i] = "0".repeat(temp.length() - st.length()) + st;
        }
        var res = 0;
        for (var i = 0; i < nums.length; i++) {
            var str1 = arr[i];
            for (var j = i + 1; j < nums.length; j++) {
                var str2 = arr[j];
                for (var k = 0; k < temp.length(); k++) {
                    if (str1.charAt(k) != str2.charAt(k)) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        var rootVal = subRoot.val;
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            var size = que.size();
            while (size > 0) {
                var node = que.poll();
                if (node.val == rootVal && areIdentical(node, subRoot)) {
                    return true;
                }
                if (Objects.nonNull(node.right)) {
                    que.offer(node.right);
                }
                if (Objects.nonNull(node.left)) {
                    que.offer(node.left);
                }
                size--;
            }
        }
        return false;
    }

    private boolean areIdentical(TreeNode node, TreeNode subRoot) {
        if (node == null && subRoot == null) {
            return true;
        }
        if (node == null || subRoot == null) {
            return false;
        }
        return node.val == subRoot.val && areIdentical(node.left, subRoot.left) && areIdentical(node.right, subRoot.right);
    }

    public String getSmallestString(String s) {
        var arr = s.toCharArray();
        var prev = Character.getNumericValue(arr[0]);
        for (var i = 1; i < arr.length; i++) {
            var curr = Character.getNumericValue(arr[i]);
            if (prev % 2 == curr % 2 && prev > curr) {
                var temp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = temp;
                break;
            }
            prev = curr;
        }
        return new String(arr);
    }

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> que = new LinkedList<>();
        var set = new HashSet<Integer>();
        que.offer(root);
        var res = Integer.MAX_VALUE;
        while (!que.isEmpty()) {
            var size = que.size();
            while (size > 0) {
                var node = que.poll();
                var val = node.val;
                if (!set.isEmpty()) {
                    if (!set.contains(val)) {
                        res = getMin(set, val, res);
                        set.add(val);
                    }
                } else {
                    set.add(val);
                }
                if (node.left != null) {
                    if (!set.contains(val)) {
                        res = getMin(set, node.left.val, res);
                        set.add(node.left.val);
                    }
                    que.offer(node.left);
                }
                if (node.right != null) {
                    if (!set.contains(val)) {
                        res = getMin(set, node.right.val, res);
                        set.add(node.right.val);
                    }
                    que.offer(node.right);
                }
                size--;
            }
        }
        return res;
    }

    public int getMin(Set<Integer> set, int val, int res) {
        if (!set.contains(val)) {
            res = Math.min(res, set.stream().map(var -> Math.abs(var - val)).min(Comparator.comparing(Integer::intValue)).get());
            set.add(val);
        }
        return res;
    }

    public boolean findTarget(TreeNode root, int k) {
        var set = new HashSet<Integer>();
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (set.contains(k - node.val)) {
                return true;
            }
            set.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return false;
    }

    public int[][] sortTheStudents(int[][] score, int k) {
        var map = new TreeMap<Integer, int[]>(Comparator.reverseOrder());
        for (var arr : score) {
            map.put(arr[k], arr);
        }
        return map.values().toArray(int[][]::new);
    }

    public int countPoints(String rings) {
        var map = new HashMap<Character, HashSet<Character>>();
        for (var i = 1; i < rings.length(); i += 2) {
            var ch = rings.charAt(i);
            HashSet<Character> set;
            if (map.containsKey(ch)) {
                set = map.get(ch);
            } else {
                set = new HashSet<>();
            }
            set.add(rings.charAt(i - 1));
            map.put(ch, set);
        }
        return (int) map.values().stream().filter(var -> var.size() == 3).count();
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        var res = 0;
        Queue<Node> que = new LinkedList<>(root.children);
        res = res + 1;
        while (!que.isEmpty()) {
            var size = que.size();
            while (size > 0) {
                var node = que.poll();
                que.addAll(node.children);
                size--;
            }
            res++;
        }
        return res;
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        var rows = matrix.length;
        var col = matrix[0].length;
        if (col == 1 || rows == 1) {
            return true;
        }
        for (var i = 0; i < rows; i++) {
            var arr = matrix[i];
            for (var j = 0; j < col; j++) {
                var num = arr[j];
                var index = j + 1;
                var start = i + 1;
                while (index < col && start < rows) {
                    if (matrix[start][index] != num) {
                        return false;
                    }
                    index++;
                    start++;
                }
            }
        }
        return true;
    }

    public int minDiffInBST(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        var list = new ArrayList<Integer>();
        while (!que.isEmpty()) {
            var node = que.poll();
            list.add(node.val);
            if (node.left != null) {
                que.add(node.left);
            }
            if (node.right != null) {
                que.add(node.right);
            }
        }
        if (list.size() < 2) {
            return list.getFirst();
        }
        Collections.sort(list);
        return Math.min(list.get(1) - list.getFirst(), list.getLast() - list.get(list.size() - 2));
    }

    public int minChanges(int n, int k) {
        if (k > n) {
            return -1;
        }
        var str1 = Integer.toBinaryString(n);
        var str2 = Integer.toBinaryString(k);
        var len = str1.length();
        if (len > str2.length()) {
            str2 = "0".repeat(len - str2.length()) + str2;
        }
        var res = 0;
        for (var i = 0; i < len; i++) {
            if (str1.charAt(i) == '1' && str2.charAt(i) == '0') {
                res++;
            } else if (str1.charAt(i) == '0' && str2.charAt(i) == '1') {
                res = -1;
                break;
            }
        }
        return res;
    }

    public List<String> findRepeatedDnaSequences(String s) {
        var len = s.length();
        var res = new HashSet<String>();
        var set = new HashSet<String>();
        for (var i = 0; i + 9 < len; i++) {
            var sub = s.substring(i, i + 10);
            if (!set.add(sub)) {
                res.add(sub);
            }
        }
        return new ArrayList<>(res);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        var temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    //[3,4,5,1,2]
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] < nums[r]) r = m;
            else l = m + 1;
        }
        return nums[l];
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        var len = nums.length;
        Arrays.sort(nums);
        for (var i = 0; i < len; i++) {
            while (i > 0 && i < len - 2 && nums[i] == nums[i - 1]) {
                i++;
            }
            var start = i + 1;
            var end = len - 1;
            while (start < end) {
                if (nums[start] + nums[end] + nums[i] > 0) {
                    end--;
                } else if (nums[start] + nums[end] + nums[i] < 0) {
                    start++;
                } else {
                    res.add(Arrays.asList(nums[start], nums[i], nums[end]));
                    start++;
                    end--;
                }
                while (start > i + 1 && start < end && nums[start] == nums[start - 1]) {
                    start++;
                }
                while (end < len - 1 && end > start && nums[end] == nums[end + 1]) {
                    end--;
                }
            }
        }
        return res;
    }

    public int findPeakElement(int[] nums) {

        var len = nums.length;
        if (len == 1) {
            return 0;
        }

        if (nums[0] > nums[1]) {
            return 0;
        }

        if (nums[len - 1] > nums[len - 2]) {
            return len - 1;
        }

        var start = 0;
        var end = len - 2;

        while (start < end) {
            var mid = start + (end - start) / 2;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] > nums[mid - 1]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public int findMin1(int[] nums) {
        var start = 0;
        var end = nums.length - 1;
        var min = Integer.MAX_VALUE;
        while (start <= end) {
            var mid = (start + end) / 2;
            if (nums[start] <= nums[mid]) {
                start = mid + 1;
                min = Math.min(min, nums[start]);
            } else {
                min = Math.min(min, nums[mid]);
                end = mid - 1;
            }
        }
        return min;
    }

    public boolean isValidBST(TreeNode root) {

        if (root == null) {
            return true;
        }

        if ((root.left == null && root.right == null)) {
            return true;
        }

        if (root.left == null && root.right != null && root.val < root.right.val) {
            return true;
        }

        if (root.right == null && root.left != null && root.val > root.left.val) {
            return true;
        }
        return (root.right.val > root.val && root.left.val < root.val) && isValidBST(root.left) && isValidBST(root.right);
    }

    public int distinctPrimeFactors(int[] nums) {
        long product = 1;
        for (var num : nums) {
            product = product * num;
        }
        Set<Integer> hs = new HashSet<>();
        while (product % 2 == 0) {
            hs.add(2);
            product = product / 2;
        }
        for (var i = 3; i < Math.sqrt(product); i = i + 2) {
            while (product % i == 0) {
                hs.add(i);
                product = product / i;
            }
        }
        if (product > 2) {
            hs.add((int) product);
        }
        return hs.size();
    }

    public boolean isValidSudoku(char[][] board) {
        for (var i = 0; i < 9; i++) {
            var set = new HashSet<Character>();
            for (var j = 0; j < 9; j++) {
                if (Character.isDigit(board[j][0]) && !set.add(board[j][0])) {
                    return false;
                }
            }
        }
        return isValidSudoku(0, 9, 0, 9, board) &&
                isValidSudoku(0, 3, 0, 3, board) &&
                isValidSudoku(0, 3, 3, 6, board) &&
                isValidSudoku(0, 3, 6, 9, board) &&
                isValidSudoku(3, 6, 0, 3, board) &&
                isValidSudoku(3, 6, 3, 6, board) &&
                isValidSudoku(3, 6, 6, 9, board) &&
                isValidSudoku(6, 9, 0, 3, board) &&
                isValidSudoku(6, 9, 3, 6, board) &&
                isValidSudoku(6, 9, 6, 9, board);
    }

    public boolean isValidSudoku(int start, int end, int startIndex, int endIndex, char[][] board) {
        for (var i = start; i < end; i++) {
            var set = new HashSet<Character>();
            for (var j = startIndex; j < endIndex; j++) {
                if (Character.isDigit(board[i][j]) && !set.add(board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }


//    public int smallestValue(int n) {
//
//    }

    public int furthestDistanceFromOrigin(String moves) {
        int x = 0;
        int blank = 0;
        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'L') {
                x--;
            } else if (moves.charAt(i) == 'R') {
                x++;
            } else {
                blank++;
            }
        }
        return Math.abs(x) + blank;
    }

    public boolean lemonadeChange(int[] bills) {
        int fiveCount = 0;
        int tenCount = 0;
        for (int num : bills) {
            if (num == 5) {
                fiveCount++;
            } else if (num == 10) {
                tenCount++;
                if (fiveCount == 0) {
                    return false;
                }
                fiveCount--;
            } else {
                if (tenCount >= 1 && fiveCount >= 1) {
                    tenCount--;
                    fiveCount--;
                } else if (fiveCount >= 3) {
                    fiveCount -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Map<String, TreeSet<String>> map = new HashMap<>();
        for (var list : accounts) {
            int size = set.size();
            set.addAll(list.subList(1, list.size()));
            if (size + list.size() > set.size()) {
                map.get(list.getFirst()).addAll(list.subList(1, list.size()));
            } else {
                map.put(list.getFirst(), new TreeSet<>(list.subList(1, list.size())));
            }
        }
        for (var entry : map.entrySet()) {
            List<String> list = new ArrayList<>();
            list.add(entry.getKey());
            list.addAll(entry.getValue());
            res.add(list);
        }
        return res;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            carry = sum / 10;
            sb.append(sum % 10);
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + carry;
            carry = sum / 10;
            sb.append(sum % 10);
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + carry;
            carry = sum / 10;
            sb.append(sum % 10);
            l2 = l2.next;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        ListNode node = new ListNode(0);
        ListNode temp = node;
        for (int i = 0; i < sb.length(); i++) {
            temp.next = new ListNode(Character.getNumericValue(sb.charAt(i)));
            temp = temp.next;
        }
        return node.next;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            var arr = str.toCharArray();
            Arrays.sort(arr);
            String canonical = String.valueOf(arr);
            if (map.containsKey(canonical)) {
                map.get(canonical).add(str);
            } else {
                List<String> set = new ArrayList<>();
                set.add(str);
                map.put(canonical, set);
            }
        }
        return new ArrayList<>(map.values());
    }

    public String maximumOddBinaryNumber(String s) {
        if (s.length() == 1) {
            return s;
        }
        String res = s.chars().mapToObj(var -> Character.toString((char) var)).sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2)).collect(Collectors.joining());
        if (res.charAt(res.length() - 1) == 1) {
            return res;
        }
        var arr = res.toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == '1') {
                arr[i] = '0';
                arr[arr.length - 1] = '1';
                break;
            }
        }
        return String.valueOf(arr);
    }

    public int maxDistance(List<List<Integer>> arrays) {
        var min = arrays.getFirst().getFirst();
        var max = arrays.getFirst().getLast();
        int res = Integer.MIN_VALUE;
        for (var i = 1; i < arrays.size(); i++) {
            var currMin = arrays.get(i).getFirst();
            var currMax = arrays.get(i).getLast();
            res = Math.max(res, Math.abs(min - currMax));
            res = Math.max(res, Math.abs(max - currMin));
            min = Math.min(min, currMin);
            max = Math.max(max, currMax);
        }
        return res;
    }

    public boolean check(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        if (nums[nums.length - 1] > nums[0]) {
            count++;
        }
        return count <= 1;
    }

    public int[] resultsArray(int[] nums, int k) {
        for (int i = 0; i < nums.length - k; i++) {
            int num = nums[i];
            for (int j = i + 1; j < i + k && j < nums.length; j++) {
                if (nums[j] - num == 1) {
                    num = nums[j];
                } else {
                    num = -1;
                    break;
                }
            }
            nums[i] = num;
        }
        return Arrays.copyOfRange(nums, 0, nums.length - k + 1);
    }

    public long maximumSubarraySum1(int[] arr, int k) {
        var len = arr.length;
        long max = 0;
        for (int i = 0; i < len + 1 - k; i++) {
            Set<Integer> temp = new HashSet<>();
            temp.add(arr[i]);
            int sum = arr[i];
            var flag = true;
            for (int j = i + 1; j < i + k; j++) {
                if (!temp.add(arr[j])) {
                    flag = false;
                    break;
                } else {
                    sum += arr[j];
                }
            }
            if (flag) {
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    public int minSteps(int n) {
        int steps = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                steps += i;
                n /= i;
            }
        }
        return steps;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head, slow = head, meetingPoint = null;
        boolean isCycle = false;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                meetingPoint = fast;
                isCycle = true;
                break;
            }
        }
        if (isCycle) {
            ListNode traversal = head;
            while (traversal != meetingPoint) {
                traversal = traversal.next;
                meetingPoint = meetingPoint.next;
            }
            return meetingPoint;
        }
        return null;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            int j = i + k;
            while (j < nums.length) {
                if (nums[i] == nums[j]) {
                    return true;
                }
                j++;
            }
        }
        return false;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int len = nums.length;
        int[] res = new int[len - k + 1];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < k && i < len; i++) {
            max = Math.max(max, nums[i]);
        }
        int count = 0;
        res[count++] = max;
        for (int i = k; i < len; i++) {
            if (i + 1 == len && count == 1) {
                res[count++] = max;
                continue;
            }
            max = Math.max(max, nums[i]);
            res[count++] = max;
        }
        return res;
    }

    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public double findMaxAverage(int[] nums, int k) {
        int total = 0;
        for (int i = 0; i < k; i++) {
            total += nums[i];
        }
        double max = (double) total / k;
        int counter = 0;
        for (int i = k; i < nums.length; i++) {
            total += nums[i] - nums[counter++];
            max = Math.max(max, (double) total / k);
        }
        return max;
    }

    public String decodeAtIndex(String s, int k) {

        Stack<Integer> stack = new Stack<>();


        return s;
    }

    public int chalkReplacer(int[] chalk, int k) {
        long sum = 0;
        for (int num : chalk) {
            sum += num;
        }
        k = (int) (k % sum);
        for (int i = 0; i < chalk.length; i++) {
            if (chalk[i] > k) {
                return i;
            }
            k -= chalk[i];
        }
        return -1;
    }

    public int generateKey(int num1, int num2, int num3) {
        String str1 = Integer.toString(num1);
        String str2 = Integer.toString(num2);
        String str3 = Integer.toString(num3);
        if (str1.length() < 4) {
            str1 = "0".repeat(4 - str1.length()) + str1;
        }
        if (str2.length() < 4) {
            str2 = "0".repeat(4 - str2.length()) + str2;
        }
        if (str3.length() < 4) {
            str3 = "0".repeat(4 - str3.length()) + str3;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(Math.min(str1.charAt(i) - '0', Math.min(str2.charAt(i) - '0', str3.charAt(i) - '0')));
        }
        return Integer.parseInt(sb.toString());
    }

    public Node flatten(Node head) {

        if (head == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        Node res = new Node(0);
        Node temp = res;
        Stack<Node> stack = new Stack<>();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.child == null) {
                temp.next = new Node(node.val);
                if (Objects.nonNull(node.next)) {
                    queue.add(node.next);
                }
            } else {
                temp.next = new Node(node.val);
                stack.push(node);
                queue.add(node.child);
            }

        }

        return head;
    }

    public String stringHash(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i += k) {
            var st = s.substring(i, i + k);
            var sum = 0;
            for (var ch : st.toCharArray()) {
                sum = sum + ((int) ch - 97);
            }
            sum %= 26;
            char c = (char) (97 + sum);
            sb.append(c);
        }
        return sb.toString();
    }

    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        char c1 = coordinate1.charAt(0), c2 = coordinate2.charAt(0);
        int n1 = coordinate1.charAt(1) - '0', n2 = coordinate2.charAt(1) - '0';
        return getCoordinate(c1, n1).equals(getCoordinate(c2, n2));
    }

    public String convertDateToBinary(String date) {
        Function<String, String> function = (str) -> {
            int num = Integer.parseInt(str);
            return Integer.toBinaryString(num);
        };
        return Stream.of(date.split("-")).map(function).collect(Collectors.joining("-"));
    }

    public List<Integer> stableMountains(int[] height, int threshold) {
        var res = new ArrayList<Integer>();
        for (var i = 1; i < height.length; i++) {
            if (height[i - 1] > threshold) {
                res.add(i);
            }
        }
        return res;
    }

    public int[] getSneakyNumbers(int[] nums) {
        var map = new HashMap<Integer, Integer>();
        for (var num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map.entrySet().stream().filter(var -> var.getValue() == 2).mapToInt(Map.Entry::getKey).toArray();
    }

    public boolean reportSpam(String[] message, String[] bannedWords) {

        var map1 = new HashMap<String, Integer>();
        var map2 = new HashMap<String, Integer>();

        for (var str : message) {
            map1.put(str, map1.getOrDefault(str, 0) + 1);
        }

        for (var str : bannedWords) {
            map2.put(str, map2.getOrDefault(str, 0) + 1);
        }

        int count = 0;
        for (var entry : map2.entrySet()) {
            if (map1.containsKey(entry.getKey())) {
                int val = map1.get(entry.getKey());
                if (val > entry.getValue()) {
                    count += val;
                } else {
                    count += Math.min(entry.getValue(), val);
                }
            }
            if (count >= 2) {
                return true;
            }
        }
        return false;
    }

    public int minElement(int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int num : nums) {
            int sum = 0;
            while (num > 0) {
                sum = sum + num % 10;
                num = num / 10;
            }
            res = Math.min(res, sum);
        }
        return res;
    }

}







