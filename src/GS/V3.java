package GS;

import java.util.*;

public class V3 {

    public static void main(String[] args) {
//        getBestAverageGrade(new String[][]{{"Bobby", "87"}, {"Charles", "100"}, {"Eric", "64"}, {"Charles", "22"}});
        System.out.println(decodeString("2[abc]3[cd]ef"));
    }

    public static int[] merge(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int[] res = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (arr1[i] < arr2[j]) {
                res[k++] = arr1[i];
                i++;
            } else {
                res[k++] = arr2[j];
                j++;
            }
        }
        while (i < m) {
            res[k++] = arr1[i];
            i++;
        }
        while (j < n) {
            res[k++] = arr2[j];
            j++;
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static int[] mergeDesc(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int[] res = new int[m + n];
        int i = m - 1, j = n - 1, k = 0;
        while (i >= 0 && j >= 0) {
            if (arr1[i] < arr2[j]) {
                res[k++] = arr2[j];
                j--;
            } else {
                res[k++] = arr1[i];
                i--;
            }
        }
        while (i >= 0) {
            res[k++] = arr1[i];
            i--;
        }
        while (j >= 0) {
            res[k++] = arr2[j];
            j--;
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static Set<List<Integer>> getAllPairs(int[] arr, int target) {
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            int rem = target - num;
            if (seen.contains(rem)) {
                res.add(Arrays.asList(rem, num));
            }
            seen.add(num);
        }
        System.out.println(res);
        return res;
    }

    public static boolean isPowerOfTen(int num) {
        if (num < 1) {
            return false;
        }
        while (num % 10 == 0) {
            num /= 10;
        }
        System.out.println(num == 1);
        return num == 1;
    }

    public static String mostFrequentIp(String[] arr) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : arr) {
            String temp = str.split("-")[0].trim();
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        int max = 0;
        for (int num : map.values()) {
            max = Math.max(num, max);
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

    public static int getBestAverageGrade(String[][] arr) {
        Map<String, int[]> map = new HashMap<>();
        for (var strs : arr) {
            String student = strs[0];
            int grade = Integer.parseInt(strs[1]);
            map.putIfAbsent(strs[0], new int[2]);
            map.get(student)[0] += grade;
            map.get(student)[1]++;
        }

        int average = 0;
        for (var temp : map.values()) {
            average = Math.max(average, temp[0] / temp[1]);
        }
        System.out.println(average);
        return average;
    }

    public static int findLengthOfCycle(int[] arr, int start) {
        int slow = start;
        int count = 0;
        while (count < arr.length) {
            int fast = slow;
            int steps = 0;
            while (fast < arr.length) {
                fast = arr[fast];
                steps++;
                if (slow == fast) {
                    System.out.println(steps);
                    return steps;
                }
            }
            slow = arr[slow];
            count++;
        }
        return -1;
    }

    public static char getFirstNonRepeatingCharacter(String str) {
        int[] ascii = new int[26];
        for (char ch : str.toCharArray()) {
            int asc = ((int) ch) - 97;
            ascii[asc]++;
        }
        char c = 0;
        for (char ch : str.toCharArray()) {
            int asc = ((int) ch) - 97;
            if (ascii[asc] == 1) {
                c = ch;
                break;
            }
        }
        return c;
    }

    public static String fractionToDecimal(long n, long d) {
        if (d == 0) {
            return Long.toString(n);
        }
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        if ((n > 0 && d < 0) || (n < 0 && d > 0)) {
            sb.append("-");
        }
        n = Math.abs(n);
        d = Math.abs(d);
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
            rem *= 10;
            quo = rem / d;
            rem = rem % d;
            sb.append(quo);
        }
        return sb.toString();
    }

    public static int rockCollector(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 1; i < m; i++) {
            grid[n - 1][i] += grid[n - 1][i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            grid[i][0] += grid[i + 1][0];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 1; j < m; j++) {
                grid[i][j] += Math.max(grid[i][j - 1], grid[i + 1][j]);
            }
        }
        return grid[0][m - 1];
    }

    public static int trappingRainWater(int[] arr) {
        int res = 0;
        int leftMax = Integer.MIN_VALUE;
        int rightMax = leftMax;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            leftMax = Math.max(leftMax, arr[left]);
            rightMax = Math.max(rightMax, arr[right]);
            if (leftMax < rightMax) {
                res += leftMax - arr[left];
                left++;
            } else {
                res += rightMax - arr[right];
                right--;
            }
        }
        return res;
    }

    public static int[] finalStandingOfRobot(String str) {
        int x = 0;
        int y = 0;
        for (char ch : str.toCharArray()) {
            switch (ch) {
                case 'U' -> y++;
                case 'D' -> y--;
                case 'L' -> x--;
                case 'R' -> x++;
            }
        }
        return new int[]{x, y};
    }

    public static String reduceString(String str, int k) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i - 1) == str.charAt(i)) {
                count++;
                if (count == k) {
                    i++;
                    if (i >= str.length()) {
                        break;
                    }
                    count = 1;
                }
            } else {
                sb.append(Character.toString(str.charAt(i - 1)).repeat(count));
                count = 1;
            }
        }
        if (count == k) {
            return sb.toString();
        }
        return sb.append(Character.toString(str.charAt(str.length() - 1)).repeat(count)).toString();
    }

    public static String encode(String str) {
        if (str.trim().isEmpty()) {
            return "";
        }
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i - 1) == str.charAt(i)) {
                count++;
            } else {
                sb.append(str.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        sb.append(str.charAt(str.length() - 1)).append(count);
        return sb.toString();
    }

    public static boolean isPalindrome(int num) {
        int temp = num;
        int result = 0;
        while (num != 0) {
            int rem = num % 10;
            num /= 10;
            result = (result * 10) + rem;
        }
        System.out.println(result);
        System.out.println(result == temp);
        return result == temp;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = ((m + n) / 2) + 1;
        int[] res = new int[len];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n && k < len) {
            if (nums1[i] < nums2[j]) {
                res[k++] = nums1[i];
                i++;
            } else {
                res[k++] = nums2[j];
                j++;
            }
        }
        while (i < m && k < len) {
            res[k++] = nums1[i];
            i++;
        }
        while (j < n && k < len) {
            res[k++] = nums2[j];
            j++;
        }
        if ((m + n) % 2 == 0) {
            return (res[res.length - 1] + res[res.length - 2]) / 2.0;
        }
        return res[res.length - 1];
    }

    public static String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> builderStack = new Stack<>();
        StringBuilder string = new StringBuilder();
        int num = 0;
        for (char ch : s.toCharArray()) {
            switch (ch) {
                case '[' -> {
                    numStack.push(num);
                    num = 0;
                    builderStack.push(string);
                    string = new StringBuilder();
                }
                case ']' -> {
                    StringBuilder temp = string;
                    string = builderStack.pop();
                    string.repeat(temp, numStack.pop());
                }
                default -> {
                    if (ch >= '0' && ch <= '9') {
                        num = (num * 10) + ch - '0';
                    } else {
                        string.append(ch);
                    }
                }
            }
        }
        return string.toString();
    }
}
