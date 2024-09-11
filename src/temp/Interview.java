package temp;

import com.test.TreeNode;

import java.math.BigInteger;
import java.util.*;

public class Interview {


    public int[] addFractions(int[] fraction1, int[] fraction2) {
        int num1 = fraction1[0];
        int den1 = fraction1[1];

        int num2 = fraction2[0];
        int den2 = fraction2[1];

        int numerator = (num1 * den2) + (num2 * den1);
        int denominator = den1 * den2;

        int a = numerator;
        int b = denominator;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        numerator /= a;
        denominator /= a;
        return new int[]{numerator, denominator};
    }

    public static long dotProduct(int[] array1, int[] array2) {
        long sum = 0;
        if (array1.length != array2.length) {
            return -1;
        }
        for (int i = 0; i < array1.length; i++) {
            sum += array1[i] + array2[i];
        }
        return sum;
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
        for (int i = 1; i <= exp; i++) {
            res = res * base;
        }
        return res;
    }

    public static ArrayList<Integer> primeFactorization(int x) {
        ArrayList<Integer> list = new ArrayList<>();
        while (x % 2 == 0) {
            list.add(2);
            x = x / 2;
        }
        for (int i = 3; i < Math.sqrt(x); i += 2) {
            while (x % i == 0) {
                list.add(i);
                x = x / i;
            }
        }
        if (x > 2) {
            list.add(x);
        }
        return list;
    }

    public static double squareRoot(double x) {
        double temp;
        double sqrt = x / 2;
        do {
            temp = sqrt;
            sqrt = Math.abs((temp + x / temp) / 2);
        } while (temp - sqrt != 0);
        return sqrt;
    }

    public static String vulgarToDecimal(Long numerator, Long denominator) {
        if (numerator == 0 || denominator == 0) {
            return "0";
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
            rem *= 10;
            quo = rem / d;
            rem = rem % d;
            sb.append(quo);
        }
        return sb.toString();
    }

    public static double shortestDistance(String document, String word1, String word2) {
        String[] arr = document.split("\\s+");
        double index1 = 0;
        double index2 = 0;
        int wordLength = 0;
        double res = Double.MAX_VALUE;
        for (String word : arr) {
            if (word.equals(word1)) {
                index1 = wordLength + (word.length() / 2.0);
            } else if (word.equals(word2)) {
                index2 = wordLength + (word.length() / 2.0);
            }
            if (index2 > 0 && index1 > 0) {
                res = Math.min(res, Math.abs(index1 - index2));
            }
            wordLength += word.length() + 1;
        }
        return res == Double.MAX_VALUE ? -1 : res;
    }

    public static int secondSmallest(int[] arr) {
        if (arr.length < 2) {
            return 0;
        }
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < first) {
                second = first;
                first = arr[i];
            } else if (arr[i] < second && second != first) {
                second = arr[i];
            }
        }
        System.out.println(second);
        return second;
    }

    public static int[] mergeArrays(int[] arr1, int[] arr2) {
        int len1 = arr1.length, len2 = arr2.length;
        int[] res = new int[len1 + len2];
        int i = 0, j = 0, k = 0;
        while (i < len1 && j < len2) {
            if (arr1[i] <= arr2[j]) {
                res[k++] = arr1[i++];
            } else {
                res[k++] = arr2[j++];
            }
        }
        while (i < len1) {
            res[k++] = arr1[i++];
        }
        while (j < len2) {
            res[k++] = arr2[j++];
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    public static Set<List<Integer>> getPairs(int[] arr, int target) {
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            int rem = target - num;
            if (seen.contains(rem)) {
                res.add(Arrays.asList(rem, num));
            }
            seen.add(num);
        }
        return res;
    }

    public static int myAtoi(String s) {
        s = s.trim();
        if (s.isEmpty() || s.isBlank()) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        char ch = s.charAt(0);
        if (ch == '+' || s.charAt(0) == '-' || Character.isDigit(ch)) {
            sb.append(ch);
        } else if (Character.isLetter(ch) || !(Character.isDigit(ch))) {
            return 0;
        }
        for (int i = 1; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
            } else {
                break;
            }
        }
        if (sb.isEmpty() || (sb.length() == 1 && !Character.isDigit(sb.charAt(0)))) {
            return 0;
        }
        BigInteger big = new BigInteger(sb.toString());
        return big.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0 ? Integer.MAX_VALUE : (big.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0 ? Integer.MIN_VALUE : Integer.parseInt(sb.toString()));
    }

    public static char firstNonRepeating(String str) {
        int[] chars = new int[26];
        for (char ch : str.toCharArray()) {
            chars[((int) ch) - 97]++;
        }
        char res = '0';
        for (char ch : str.toCharArray()) {
            if (chars[((int) ch) - 97] == 1) {
                res = ch;
                break;
            }
        }
        return res;
    }

    public static String mostFrequentIp(String[] arr) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : arr) {
            String temp = str.split("-")[0].trim();
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        int max = Collections.max(map.values());
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

    public static double getBestAverage(String[][] input) {
        Map<String, int[]> map = new HashMap<>();
        for (String[] arr : input) {
            String student = arr[0];
            map.putIfAbsent(student, new int[2]);
            map.get(student)[0] += Integer.parseInt(arr[1]);
            map.get(student)[1] += 1;
        }
        double res = Double.MIN_VALUE;
        for (var arr : map.values()) {
            res = Math.max(res, ((double) arr[0] / arr[1]));
        }
        return res;
    }

    public static int lengthOfCycle(int[] arr, int start) {
        int slow = start;
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
                fast++;
            }
            count++;
            slow = arr[slow];
        }
        return -1;
    }

    class BinaryTree {
        Set<Integer> set;
        TreeNode node;

        public BinaryTree() {
            this.set = new HashSet<>();
            this.node = null;
        }

        public void put(int val) {
            node = insert(node, val);
            set.add(val);
        }

        private TreeNode insert(TreeNode node, int val) {
            if (node == null) {
                return new TreeNode(val);
            }
            if (node.val == val) {
                return node;
            }
            if (val > node.val) {
                node.left = insert(node.left, val);
            } else {
                node.right = insert(node.right, val);
            }
            return node;
        }

        public boolean contains(int val) {
            return set.contains(val);
        }

        public List<Integer> inOrderTraversal() {
            List<Integer> al = new ArrayList<>();
            inOrderTraversal(al, node);
            return al;
        }

        private void inOrderTraversal(List<Integer> al, TreeNode node) {
            if (node == null) {
                return;
            }
            inOrderTraversal(al, node.left);
            al.add(node.val);
            inOrderTraversal(al, node.right);
        }
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
        for (int i = n - 2; i >= 0; i++) {
            for (int j = 1; j < m; j++) {
                grid[i][j] += Math.max(grid[i + 1][j], grid[i][j - 1]);
            }
        }
        return grid[0][m - 1];
    }

    class MyDeque<T> {
        class CustNode<T> {
            T val;
            CustNode next;
            CustNode prev;

            CustNode(T val) {
                this.val = val;
            }
        }

        private CustNode head;
        private CustNode tail;
        int size;

        public MyDeque() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addFirst(T val) {
            if (isEmpty()) {
                head = new CustNode(val);
                tail = head;
            } else {
                CustNode newNode = new CustNode(val);
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
            size++;
        }

        public void addLast(T val) {
            if (isEmpty()) {
                tail = new CustNode(val);
                head = tail;
            } else {
                CustNode newNode = new CustNode(val);
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }

        public T peekFirst() {
            if (isEmpty()) {
                throw new RuntimeException("Deque is Empty");
            }
            return (T) head.val;
        }

        public T peekLast() {
            if (isEmpty()) {
                throw new RuntimeException("Deque is Empty");
            }
            return (T) tail.val;
        }

        public T removeFirst() {
            if (isEmpty()) {
                throw new RuntimeException("Deque is Empty");
            }
            T val = (T) head.val;
            head = head.next;
            if (head == null) {
                tail = null;
                size = 0;
            } else {
                head.prev = null;
                size--;
            }
            return val;
        }

        public T removeLast() {
            if (isEmpty()) {
                throw new RuntimeException("Deque is Empty");
            }

            T val = (T) tail.val;
            tail = tail.prev;
            if (tail == null) {
                head = null;
                size = 0;
            } else {
                tail.next = null;
                size--;
            }
            return val;
        }
    }

    public static int trapRainWater(int[] arr) {
        int res = 0;
        int leftMax = 0, rightMax = 0, start = 0, end = arr.length - 1;
        while (start < end) {
            leftMax = Math.max(leftMax, arr[start]);
            rightMax = Math.max(rightMax, arr[end]);
            if (leftMax < rightMax) {
                res += leftMax - arr[start];
                start++;
            } else {
                res += rightMax - arr[end];
                end--;
            }
        }
        return res;
    }

    public static int[] finalStandingPosition(String str) {
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

    public static String reduceString(String str, int target) {
        class Pair {
            Character character;
            int count;

            Pair(char ch, int count) {
                this.character = ch;
                this.count = count;
            }
        }
        Stack<Pair> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().character == ch) {
                stack.peek().count++;
                if (stack.peek().count == target) {
                    stack.pop();
                }
            } else {
                stack.push(new Pair(ch, 1));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            var pair = stack.pop();
            sb.append(Character.toString(pair.character).repeat(pair.count));
        }
        return sb.reverse().toString();
    }

    public static String rle(String str) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        char prev = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            if (prev == str.charAt(i)) {
                count++;
            } else {
                sb.append(prev).append(count);
                prev = str.charAt(i);
                count = 1;
            }
        }
        return sb.append(prev).append(count).toString();
    }

    public static boolean isPalindrome(int num) {
        int temp = num;
        int res = 0;
        while (temp != 0) {
            int rem = temp % 10;
            res = (res * 10) + rem;
            temp /= 10;
        }
        return num == res;
    }

    public static Set<String> longestWord(String letters, String[] arr) {

        Set<String> res = new HashSet<>();

        boolean[] ascii = new boolean[26];

        for (char ch : letters.toCharArray()) {
            int asc = ((int) ch) - 97;
            ascii[asc] = true;
        }
        int max = 0;
        for (String str : arr) {
            boolean flag = false;
            for (char ch : str.toCharArray()) {
                int asc = ((int) ch) - 97;
                if (ascii[asc]) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag && max <= str.length()) {
                if (max == str.length()) {
                    res.add(str);
                } else {
                    res.clear();
                    res.add(str);
                }
                max = str.length();
            }
        }
        return res;
    }

    public static Set<Set<String>> groupAnagrams(List<String> list) {
        Map<String, Set<String>> map = new HashMap<>();
        for (String str : list) {
            char[] arr = str.toCharArray();
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
        return new HashSet<>(map.values());
    }

    public static int[] longestUniformSubstring(String input) {
        int index = 0;
        int res = 0;
        int count = 1;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i - 1) == input.charAt(i)) {
                count++;
            } else {
                if (count < res) {
                    index = i - count;
                    res = count;
                }
                count = 1;
            }
        }
        return new int[]{index, res};
    }

    public static String findMissingLetters(String sentence) {
        StringBuilder sb = new StringBuilder();
        int[] ascii = new int[26];

        for (char ch : sentence.toCharArray()) {
            if (Character.isLetter(ch)) {
                int asc = ((int) ch) - 97;
                ascii[asc] = 1;
            }
        }
        for (int i = 0; i < ascii.length; i++) {
            if (ascii[i] == 0) {
                char ch = (char) (97 + i);
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static String reverseStr(String str) {
        char[] arr = str.toCharArray();
        int start = 0, end = arr.length - 1;
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return String.valueOf(arr);
    }

    public static int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            } else if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    class MyMap<K, V> {

        class Entry<K, V> {
            K key;
            V value;
            Entry next;

            Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }

            public K getKey() {
                return key;
            }

            public V getValue() {
                return value;
            }
        }

        public static final float DEFAULT_LOAD_FACTOR = 0.75f;
        public static final int DEFAULT_CAPACITY = 16;

        private int capacity;
        private float loadFactor;
        private int size;
        private Entry<K, V>[] table;

        public MyMap() {
            this(DEFAULT_LOAD_FACTOR, DEFAULT_CAPACITY);
        }

        public MyMap(float loadFactor, int capacity) {
            this.loadFactor = loadFactor;
            this.capacity = capacity;
            this.table = new Entry[capacity];
        }

        private int hashcode(K key) {
            return key == null ? 0 : Math.abs(key.hashCode() % capacity);
        }

        public void put(K key, V value) {
            int index = hashcode(key);
            Entry entry = table[index];

            while (entry != null) {
                if (entry.getKey() == key) {
                    entry.value = value;
                    return;
                }
                entry = entry.next;
            }

            Entry entry1 = new Entry<>(key, value);
            entry1.next = table[index];
            table[index] = entry1;
            size++;

            if (size > capacity * loadFactor) {
                resize();
            }
        }

        private void resize() {
            int newSize = capacity * 2;
            table = Arrays.copyOf(table, newSize);
            capacity = newSize;
        }

        public V get(K key) {
            int index = hashcode(key);
            Entry entry = table[index];
            while (entry != null) {
                if (entry.getKey() == key) {
                    return (V) entry.getValue();
                }
                entry = entry.next;
            }
            return null;
        }

        public void remove(K key) {
            int index = hashcode(key);
            Entry entry = table[index];
            Entry prev = null;
            while (entry != null) {
                if (entry.getKey() == key) {
                    if (prev == null) {
                        table[index] = entry.next;
                    } else {
                        prev.next = entry.next;
                    }
                    size--;
                    return;
                }
                prev = entry;
                entry = entry.next;
            }
        }
    }

    public static double findMedianSortedArrays(int[] A, int[] B) {
        double sum = 0;
        for (int num : A) {
            sum += num;
        }
        for (int num : B) {
            sum += num;
        }
        return sum / (A.length + B.length);
    }

    public static int subArrayExceedsSum(int[] arr, int target) {
        int sum = 0;
        int start = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            while (sum >= target) {
                min = Math.min(min, i - start + 1);
                sum -= arr[start];
                start++;
            }
        }
        System.out.println(min);
        return min;
    }

    public static int whoIsElected(int n, int k) {
        int pos = 0;
        for (int i = 1; i <= n; i++) {
            pos = (pos + i) % k;
        }
        return pos;
    }

    public static Integer countSteps(Integer n) {
        int prev1 = 1;
        int prev2 = 1;
        for (int i = 2; i < n; i++) {
            int curr = prev2 + prev1;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    public static Integer minimalSteps(String ingredients) {
        int len = ingredients.length();
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            if (i % 2 == 1 && ingredients.substring(0, (i / 2) + 1).equals(ingredients.substring((i / 2) + 1, i))) {
                dp[i] = dp[i / 2] + 1;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }
        return dp[len - 1];
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


}