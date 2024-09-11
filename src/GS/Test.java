package GS;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(merge(new int[]{1, 2, 3, 4}, new int[]{9, 10, 11, 47})));
    }


    public static int[] merge(int[] array1, int[] array2) {
        int m = array1.length;
        int n = array2.length;
        int i = 0, j = 0, k = 0;
        int[] res = new int[m + n];
        while (i < m && j < n) {
            if (array1[i] <= array2[j]) {
                res[k++] = array1[i++];
            } else {
                res[k++] = array2[j++];
            }
        }
        while (i < m) {
            res[k++] = array1[i++];
        }
        while (j < n) {
            res[k++] = array2[j++];
        }
        return res;
    }

    public Set<List<Integer>> findPairs(int[] array, int k) {
        Set<Integer> seen = new HashSet<>();
        Set<List<Integer>> res = new HashSet<>();
        for (int num : array) {
            int rem = k - num;
            if (seen.contains(rem)) {
                res.add(Arrays.asList(num, rem));
            }
            seen.add(num);
        }
        return res;
    }

    public boolean isPowerOfTen(int num) {
        if (num <= 0) {
            return false;
        }
        while (num % 10 == 0) {
            num = num / 10;
        }
        return num == 1;
    }

    public String getMostFrequentIp(String[] arr) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : arr) {
            String temp = str.split("-")[0].trim();
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        String res = "";
        int max = Integer.MIN_VALUE;
        for (var entry : map.entrySet()) {
            if (entry.getValue() > max) {
                res = entry.getKey();
                max = entry.getValue();
            }
        }
        return res;
    }

    public static int getBestAverageGrade(String[][] arr) {
        Map<String, int[]> map = new HashMap<>();
        for (String[] temp : arr) {
            String student = temp[0];
            int grade = Integer.parseInt(temp[1]);
            map.putIfAbsent(student, new int[2]);
            map.get(student)[0] += grade;
            map.get(student)[1] += 1;
        }
        int res = Integer.MIN_VALUE;
        for (int[] student : map.values()) {
            int totalSum = student[0];
            int count = student[1];
            res = Math.max(res, totalSum / count);
        }
        return res;
    }

    public Character getFirstNonRepeating(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.indexOf(str.charAt(i)) == str.lastIndexOf(str.charAt(i))) {
                return str.charAt(i);
            }
        }
        return null;
    }

    public Character getFirstNonRepeating1(String str) {
        Map<Character, Integer> hm = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            hm.put(str.charAt(i), hm.getOrDefault(str.charAt(i), 0) + 1);
        }
        for (var entry : hm.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return null;
    }

    public int findLengthOfCycle(int[] arr, int startIndex) {
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

    public String fractionToReccuringDecimal(int numerator, int denominator) {
        if (numerator == 0 || denominator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        if ((numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0)) {
            res.append("-");
        }
        long n = Math.abs(numerator);
        long d = Math.abs(denominator);
        int quo = (int) (n / d);
        int rem = (int) (n % d);
        res.append(quo);
        if (rem == 0) {
            return res.toString();
        }
        res.append(".");
        Map<Integer, Integer> map = new HashMap<>();
        while (rem != 0) {
            if (map.containsKey(rem)) {
                res.insert(map.get(rem), "(").append(")");
                return res.toString();
            }
            map.put(rem, res.length());
            rem = rem * 10;
            quo = (int) (rem / d);
            res.append(quo);
            rem = (int) (rem % d);

        }
        return res.toString();
    }

    public int trap(int[] arr) {
        int res = 0;
        int leftMax = Integer.MIN_VALUE;
        int rightMax = leftMax;
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            leftMax = Math.max(leftMax, arr[left]);
            rightMax = Math.max(rightMax, arr[right]);
            if (leftMax <= rightMax) {
                res = res + leftMax - arr[left];
                left++;
            } else {
                res = res + rightMax - arr[right];
                right--;
            }
        }
        return res;
    }

    // teest ,2
    public String reduceString(String str, int count) {
        class Pair {
            final Character ch;
            int count;

            public Pair(Character ch, int count) {
                this.ch = ch;
                this.count = count;
            }
        }
        Stack<Pair> stack = new Stack<>();
        for (Character c : str.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().ch == c) {
                stack.peek().count++;
                if (stack.peek().count == count) {
                    stack.pop();
                }
            } else {
                stack.push(new Pair(c, 1));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Pair p : stack) {
            sb.append(Character.toString(p.ch).repeat(p.count));
        }
        return sb.reverse().toString();
    }

    public boolean judgeCircle(String moves) {
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;
        for (char ch : moves.toCharArray()) {
            switch (ch) {
                case 'U' -> up++;
                case 'D' -> down++;
                case 'L' -> left++;
                case 'R' -> right++;
            }
        }
        return left == right && up == down;
    }

    public int[] judgeCircle1(String moves) {
        int x = 0;
        int y = 0;
        for (char ch : moves.toCharArray()) {
            switch (ch) {
                case 'U' -> y++;
                case 'D' -> y--;
                case 'L' -> x--;
                case 'R' -> x++;
            }
        }
        return new int[]{x, y};
    }

    public static int[] addFractions(int[] fraction1, int[] fraction2) {

        int num1 = fraction1[0];
        int den1 = fraction1[1];

        int num2 = fraction2[0];
        int den2 = fraction2[1];
        int simpleDen = lcm(den1, den2);

        int simpleNum = (num1 * (simpleDen / den1)) + (num2 * (simpleDen / den2));

        int gcd = gcd(simpleNum, simpleDen);
        simpleNum = simpleNum / gcd;
        simpleDen = simpleDen / gcd;
        return new int[]{simpleNum, simpleDen};
    }

    public static int lcm(int num1, int num2) {
        return (num1 * num2) / (gcd(num1, num2));
    }

    public static int gcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    public static List<Integer> primeFactorization(int x) {

        List<Integer> res = new ArrayList<>();

        while (x % 2 == 0) {
            x = x / 2;
            res.add(2);
        }

        for (int i = 3; i < Math.sqrt(x); i += 2) {
            while (x % i == 0) {
                x = x / i;
                res.add(i);
            }
        }

        if (x > 2) {
            res.add(x);
        }

        return res;
    }

    public static double shortestDistance(String document, String word1, String word2) {
        document = document.toLowerCase();
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();
        List<Integer> pos1 = getAllpositions(word1, document);
        List<Integer> pos2 = getAllpositions(word2, document);

        List<Double> mid1 = getMidPoints(pos1, word1.length());
        List<Double> mid2 = getMidPoints(pos2, word2.length());

        double shortDistance = Double.MAX_VALUE;
        for (Double m1 : mid1) {
            for (Double m2 : mid2) {
                shortDistance = Math.min(shortDistance, Math.abs(m1 - m2));
            }
        }
        return shortDistance;
    }

    private static List<Double> getMidPoints(List<Integer> pos1, int length) {
        List<Double> list = new ArrayList<>();
        for (int pos : pos1) {
            list.add(pos + (length / 2.0));
        }
        return list;
    }

    private static List<Integer> getAllpositions(String word1, String document) {
        List<Integer> list = new ArrayList<>();
        int index = document.indexOf(word1);
        while (index != -1) {
            list.add(index);
            index = document.indexOf(word1, index + 1);
        }
        return list;
    }

    private static Integer minimalSteps(String ingredients) {
        int len = ingredients.length();
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            if (i % 2 == 1 && ingredients.substring(0, (i / 2) + 1).equals(ingredients.substring((i / 2) + 1, i + 1))) {
                dp[i] = dp[i / 2] + 1;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }
        return dp[len - 1];
    }


}
