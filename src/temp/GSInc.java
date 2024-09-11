package temp;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GSInc {

    public static void main(String[] args) {
        System.out.println(longestWord("oet",new String[]{"to","toe","toes"}));
    }

    public static int[] addFractions(int[] fraction1, int[] fraction2) {
        var num1 = fraction1[0];
        var den1 = fraction1[1];

        var num2 = fraction2[0];
        var den2 = fraction2[1];

        int lcmDenominator = lcm(den1, den2);
        int convertedNum1 = num1 * (lcmDenominator / den1);
        int convertedNum2 = num2 * (lcmDenominator / den2);

        int resultNum = convertedNum1 + convertedNum2;

        int gcdResult = gcd(resultNum, lcmDenominator);
        int simplifiedNum = resultNum / gcdResult;
        int simplifiedDen = lcmDenominator / gcdResult;

        return new int[]{simplifiedNum, simplifiedDen};
    }

    private static int lcm(int den1, int den2) {
        return (den1 * den2) / gcd(den1, den2);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public long dotProduct(int[] array1, int[] array2) {
        long res = 0;
        for (var i = 0; i < array1.length; i++) {
            res = res + ((long) array1[i] * array2[i]);
        }
        return res;
    }

    public static boolean isPowerOf10(int x) {
        while (x % 10 == 0) {
            x = x / 10;
        }
        return x == 1;
    }

    public static double power(double base, int exp) {
        return Math.pow(base, exp);
    }

    public static double squareRoot(double x) {
        int start = 0;
        int end = (int) x;
        var res = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            var square = mid * mid;
            if (square == x) {
                return mid;
            } else if (square < x) {
                start = mid + 1;
                res = mid;
            } else {
                end = mid - 1;
            }
        }
        return res;
    }

    public static String vulgarToDecimal(Long numerator, Long denominator) {
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

    public  static Set<String> longestWord(String letters, String[] dict) {
        Set<String> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : letters.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (String str : dict) {
            if (str.length() <= letters.length()) {
                var flag = str.chars().mapToObj(c -> (char) c).allMatch(map::containsKey);
                if (flag) {
                    set.add(str);
                }
            }
        }
        return set;
    }

    public String findTopIpaddress(String[] lines) {
        var map = Stream.of(lines).map(var -> var.split("-")[0]).
                collect(Collectors.groupingBy(var -> var, Collectors.counting()));
        long max = Collections.max(map.values());
        var sb = new StringBuilder();
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

    public Character findFirst(String input) {
        Character ch = null;
        for (var i = 0; i < input.length(); i++) {
            if (input.indexOf(input.charAt(i)) == input.lastIndexOf(input.charAt(i))) {
                ch = input.charAt(i);
                break;
            }
        }
        return ch;
    }

    public static Set<Set<String>> group(List<String> words) {
        var map = new HashMap<String, Set<String>>();
        for (var str : words) {
            var arr = str.toCharArray();
            Arrays.sort(arr);
            var canonical = new String(arr);
            if (map.containsKey(canonical)) {
                map.get(canonical).add(str);
            } else {
                var set = new HashSet<String>();
                set.add(str);
                map.put(canonical, set);
            }
        }
        return new HashSet<>(map.values());
    }

    public static int[] longestUniformSubstring(String input) {
        var len = input.length();
        var startPos = -1;
        var endPos = -1;
        var res = 0;
        for (var i = 0; i < len; i++) {
            var curr = input.charAt(i);
            var start = i + 1;
            while (start < len && curr == input.charAt(start)) {
                start++;
            }
            if (start - i != 1 && start - i > res) {
                res = start - i;
                startPos = i;
                endPos = start - 1;
            }
            i = start - 1;
        }
        return new int[]{startPos, endPos};
    }

    public static String rle(String input) {
        var sb = new StringBuilder();
        var curr = input.charAt(0);
        var count = 1;
        for (var i = 1; i < input.length(); i++) {
            if (input.charAt(i) == curr) {
                count = count + 1;
            } else {
                sb.append(curr).append(count);
                curr = input.charAt(i);
                count = 1;
            }
        }
        sb.append(curr).append(count);
        return sb.toString();
    }

    public static String findMissingLetters(String sentence) {
        var sb = new StringBuilder();
        sentence = sentence.toLowerCase();
        for (var ch = 'a'; ch <= 'z'; ch++) {
            if (!sentence.contains(Character.toString(ch))) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static String reverseStr(String str) {
        var arr = str.toCharArray();
        var start = 0;
        var end = str.length() - 1;
        while (start < end) {
            var temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return new String(arr);
    }

    public static int findMin(int[] a) { //
        var start = 0;
        var end = a.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (a[mid] > a[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return a[start];
    }

    public static Integer bestAverageGrade(String[][] scores) {
        var map = Stream.of(scores).
                collect(Collectors.groupingBy(arr1 -> arr1[0],
                        Collectors.averagingInt(arr -> Integer.parseInt(arr[1]))));
        return Collections.max(map.values()).intValue();
    }

    public static int pascal(int col, int row) {
        if (row <= 0 || col > row) {
            return -1;
        }
        List<List<Integer>> list = new ArrayList<>();
        list.add(List.of(1));
        for (int i = 1; i < row; i++) {
            var prev = list.get(i - 1);
            var current = new ArrayList<Integer>();
            current.add(1);
            var j = 1;
            while (j < i) {
                var sum = prev.get(j - 1) + prev.get(j);
                current.add(sum);
                j++;
            }
            current.add(1);
            list.add(current);
        }
        System.out.println(list);
        return list.get(row - 1).get(col);
    }

    public static double findMedianSortedArrays(int[] A, int[] B) {
        return IntStream.concat(Arrays.stream(A), Arrays.stream(B)).average().orElse(0);
    }

    public static Set<String> uniqueTuples(String input, int len) {
        var length = input.length();
        if (len >= length) {
            return Collections.singleton(input);
        }
        var set = new HashSet<String>();
        for (var i = 0; i <= length - len; i++) {
            set.add(input.substring(i, i + len));
        }
        return set;
    }

    //    Given 2 ascending sorted arrays, merge the arrays into a single array
    public int[] mergeSortedArray(int[] arr1, int[] arr2) {
        return IntStream.concat(IntStream.of(arr1), IntStream.of(arr2)).sorted().toArray();
    }

//    Length of longest substring without repeating characters
//    String s = "abcabcd"

    public static int getLongestUniqueString(String str) {
        var set = new HashSet<Character>();
        var res = 0;
        for (var i = 0; i < str.length(); i++) {
            var ch = str.charAt(i);
            if (!set.add(ch)) {
                res = Math.max(res, set.size());
                set.clear();
                i = i - 1;
            }
        }
        return Math.max(res, set.size());
    }

//    input: "/gH?yZx"
//    output: "/xZ?yHg"

    public static String reverseStringWithSpecialChars(String str) {
        var arr = str.toCharArray();
        var start = 0;
        var end = str.length() - 1;
        while (start < end) {
            var flag1 = Character.isLetter(arr[start]) || Character.isDigit(arr[start]);
            var flag2 = Character.isLetter(arr[end]) || Character.isDigit(arr[end]);
            if (flag2 && flag1) {
                var temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            } else if (!flag1 && flag2) {
                start++;
            } else if (flag1) {
                end--;
            } else {
                start++;
                end--;
            }
        }
        return new String(arr);
    }

    public static List<Integer> primeFactorization(int n) {
        var list = new ArrayList<Integer>();
        while (n % 2 == 0) {
            n /= 2;
            list.add(2);
        }
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            while (n % i == 0) {
                n /= i;
                list.add(i);
            }

        }
        if (n > 2) {
            list.add(n);
        }
        return list;
    }

    public static String reduceString(String str, int k) {
        class Pair {
            Character character;
            int count;

            public Pair(Character ch, int count) {
                this.character = ch;
                this.count = count;
            }
        }
        Stack<Pair> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().character == ch) {
                stack.peek().count++;
                if (stack.peek().count == k) {
                    stack.pop();
                }
            } else {
                stack.push(new Pair(ch, 1));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair p = stack.pop();
            sb.append(Character.toString(p.character).repeat(p.count));
        }
        return sb.reverse().toString();
    }

    public void rotate(int[][] matrix) {
        var len = matrix.length;
        for (var i = 0; i < len; i++) {
            for (var j = i + 1; j < len; j++) {
                var temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (var arr : matrix) {
            var start = 0;
            var end = len - 1;
            while (start < end) {
                var temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }
        }
    }

//    1 1   0 1  1 0
//    0 1   1 1  1 1
//
//    1 1
//    1 0

    public boolean findRotation(int[][] mat, int[][] target) {
        var len = mat.length;
        var num = 1;
        while (num <= len * 2) {
            for (var i = 0; i < len; i++) {
                for (var j = i + 1; j < len; j++) {
                    var temp = mat[i][j];
                    mat[i][j] = mat[j][i];
                    mat[j][i] = temp;
                }
            }
            for (var arr : mat) {
                var start = 0;
                var end = len - 1;
                while (start < end) {
                    var temp = arr[start];
                    arr[start] = arr[end];
                    arr[end] = temp;
                    start++;
                    end--;
                }
            }
            if (Arrays.deepEquals(mat, target)) {
                return true;
            }
            num++;
        }
        return false;
    }

    public int climbStairs(int n) {
        int prev1 = 1;
        int prev2 = 1;
        for (int i = 2; i <= n; i++) {
            int cur = prev1 + prev2;
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static int subArrayExceedsSum(int[] arr, int target) {
        var minLength = Integer.MAX_VALUE;
        var currSum = 0;
        var start = 0;
        for (var i = 0; i < arr.length; i++) {
            currSum = currSum + arr[i];
            while (currSum >= target) {
                minLength = Math.min(minLength, i - start + 1);
                currSum = currSum - arr[start];
                start++;
            }
        }
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }


}
