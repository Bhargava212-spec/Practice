package custom;

public class NumArray {

    int[] arr;
    int[] pref;

    public NumArray(int[] nums) {
        pref = new int[nums.length];
        pref[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pref[i] = pref[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        var sum = 0;
        while (left <= right) {
            sum = sum + arr[left];
            left++;
        }
        return sum;

    }

    public void update(int index, int val) {
        arr[index] = val;
    }
}
