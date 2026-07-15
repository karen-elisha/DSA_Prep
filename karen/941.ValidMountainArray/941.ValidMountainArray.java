
class Solution {
    public boolean validMountainArray(int[] arr) {

        int peak = -1;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                peak = i;
                break;
            }
        }

        if (peak == -1 || peak == 0 || peak == arr.length - 1) {
            return false;
        }

        for (int i = 0; i < peak; i++) {
            if (arr[i] >= arr[i + 1]) {
                return false;
            }
        }

        for (int i = peak; i < arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                return false;
            }
        }

        return true;
    }
}