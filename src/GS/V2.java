package GS;

import java.io.IOException;
import java.util.*;

public class V2 {

    public static void main(String[] args) throws IOException {
    }

    static class A {
        public void print() {
            System.out.println("From Class A");
        }
    }

    static class B extends A {
        public void print() {
            System.out.println("From Class B");
        }
    }

    static class C extends B {
        public void print() {
            System.out.println("From Class C");
        }

        public void print_C() {
            System.out.println("From print_C of Class C");
        }
    }


    public static int[] addFractions(int[] fraction1, int[] fraction2) {
        int num1 = fraction1[0];
        int den1 = fraction1[1];

        int num2 = fraction2[0];
        int den2 = fraction2[1];

        int resultNumerator = (num1 * den2) + (num2 * den1);
        int resultDenominator = den1 * den2;

        int finalGcd = finalGcd(resultNumerator, resultDenominator);

        resultNumerator /= finalGcd;
        resultDenominator /= finalGcd;

        System.out.println(resultNumerator + " " + resultDenominator);
        return new int[]{resultNumerator, resultDenominator};
    }

    private static int finalGcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static long dotProduct(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            return -1;
        }
        long res = 0;
        for (int i = 0; i < array1.length; i++) {
            res += array1[i] + array2[i];
        }
        return res;
    }

    public static boolean isPowerOf10(int x) {
        if (x <= 0) {
            return false;
        }
        while (x % 10 == 0) {
            x /= 10;
        }
        return x == 1;
    }

    public static double power(double base, int exp) {
        double res = 1;
        if (exp == 0) {
            return res;
        }
        for (int i = 1; i <= exp; i++) {
            res = res * base;
        }
        System.out.println(res);
        return res;
    }

    public static ArrayList<Integer> primeFactorization(int x) {
        ArrayList<Integer> res = new ArrayList<>();
        while (x % 2 == 0) {
            x /= 2;
            res.add(2);
        }
        for (int i = 3; i < Math.sqrt(x); i += 2) {
            while (x % i == 0) {
                res.add(i);
                x /= i;
            }
        }
        if (x > 2) {
            res.add(x);
        }
        System.out.println(res);
        return res;
    }

    //babylonian approach
    // heron approach
    public static double squareRoot(double n) {
        double temp;
        double sqrt = n / 2;
        do {
            temp = sqrt;
            sqrt = Math.abs((temp + n / temp) / 2);
        } while (temp - sqrt != 0);
        System.out.println(sqrt);
        return sqrt;
    }

    public static String vulgarToDecimal(Long numerator, Long denominator) {
        if (numerator == 0) {
            return "0";
        }
        if (denominator == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if ((numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0)) {
            sb.append("-");
        }
        long n = Math.abs(numerator);
        long d = Math.abs(denominator);

        long quo = n / d;
        long rem = n % d;

        sb.append(quo);
        if (rem == 0) {
            return sb.toString();
        }
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (rem != 0) {
            if (map.containsKey(rem)) {
                return sb.insert(map.get(rem), "(").append(")").toString();
            }
            map.put(rem, sb.length());
            rem = rem * 10;
            quo = rem / d;
            rem = rem % d;
            sb.append(quo);
        }
        return sb.toString();
    }

    public static Set<Set<String>> group(List<String> words) {
        Map<String, Set<String>> map = new HashMap<>();
        for (String str : words) {
            var arr = str.toCharArray();
            Arrays.sort(arr);
            String canonical = String.valueOf(arr);
            if (map.containsKey(canonical)) {
                map.get(canonical).add(str);
            } else {
                Set<String> set = new HashSet<>();
                set.add(str);
                map.put(canonical, set);
            }
        }
        System.out.println(map.values());
        return new HashSet<>(map.values());
    }

    public static double shortestDistance(String document, String word1, String word2) {
        String[] arr = document.split("\\s+");
        double res = Double.MAX_VALUE;
        double index1 = 0;
        double index2 = 0;
        int wordCount = 0;
        for (String str : arr) {
            if (word1.equals(str)) {
                index1 = wordCount + word1.length() / 2.0;
            } else if (word2.equals(str)) {
                index2 = wordCount + word2.length() / 2.0;
            }
            if (index1 > 0 && index2 > 0) {
                res = Math.min(res, Math.abs(index2 - index1));
            }
            wordCount += str.length() + 1;
        }
        System.out.println(res);
        return res;
    }

    public static Set<String> longestWord(String letters, List<String> list) {
        Set<String> res = new HashSet<>();
        boolean[] ascii = new boolean[26];
        for (char ch : letters.toCharArray()) {
            ascii[(int) ch - 97] = true;
        }
        int max = 0;
        for (String string : list) {
            boolean flag = false;
            for (char c : string.toCharArray()) {
                if (ascii[(int) c - 97]) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (string.length() > max) {
                    max = string.length();
                    res.clear();
                    res.add(string);
                } else if (string.length() == max) {
                    res.add(string);
                }
            }
        }
        System.out.println(res);
        return res;
    }

    public static String findTopIpaddress(String[] lines) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : lines) {
            String temp = str.split("-")[0].trim();
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        int max = 0;
        for (int num : map.values()) {
            max = Math.max(max, num);
        }
        StringBuilder sb = new StringBuilder();
        for (var entry : map.entrySet()) {
            if (entry.getValue() == max) {
                if (!sb.isEmpty()) {
                    sb.append(",").append(entry.getKey());
                } else {
                    sb.append(entry.getKey());
                }
            }
        }
        return sb.toString();
    }

    public static char findFirst(String input) {
        char res = '0';
        int[] ascii = new int[26];
        for (char c : input.toCharArray()) {
            int num = ((int) c) - 97;
            ascii[num]++;
        }

        for (char c : input.toCharArray()) {
            int num = ((int) c) - 97;
            if (ascii[num] == 1) {
                res = c;
                break;
            }
        }
        return res;
    }

    public static int[] longestUniformSubstring(String input) {
        int from = 0;
        int count = 1;
        int max = 0;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i - 1) == input.charAt(i)) {
                count++;
            } else {
                if (count > max) {
                    max = count;
                    from = i - count;
                }
                count = 1;
            }
        }
        System.out.println(from + "  " + max);
        return new int[]{from, max};
    }

//    public static String rle(String input) {
//    }

    public static int optimalPath(int[][] grid) {
        int n = grid.length;
        int col = grid[0].length;

        for (int i = 1; i < n; i++) {
            grid[n - 1][i] += grid[n - 1][i - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            grid[i][0] += grid[i + 1][0];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 1; j < col; j++) {
                grid[i][j] += Math.max(grid[i + 1][j], grid[i][j - 1]);
            }
        }
        System.out.println(grid[0][col - 1]);
        return grid[0][col - 1];
    }

    public static int whoIsElected(int n, int k) {
        int pos = 0;
        for (int i = 1; i <= n; i++) {
            pos = (pos + k) % i;
        }
        return pos + 1;
    }

    public static boolean isPalindrome(int num) {
        int temp = 0;
        int original = num;
        while (num != 0) {
            int rem = num % 10;
            temp = (temp * 10) + rem;
            num /= 10;
        }
        return original == temp;

    }


}
