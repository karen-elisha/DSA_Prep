class Solution {
    public int countDigitOccurrences(int[] nums, int digit) {
        int count = 0;
        int d;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != 0) {
                d = nums[i] % 10;
                if (d == digit) {
                    count++;
                }
                nums[i] = nums[i] / 10;
            }

        }
        return count;

    }
}