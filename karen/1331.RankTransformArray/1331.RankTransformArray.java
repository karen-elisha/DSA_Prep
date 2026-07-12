import java.util.*;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int a[] = arr.clone();
        int res[] = new int[arr.length];
        Arrays.sort(a);
        int rank = 1;
        for (int i = 0; i < a.length; i++) {
            if (!map.containsKey(a[i])) {
                map.put(a[i], rank);
                rank++;
            }
        }
        for (int j = 0; j < arr.length; j++) {
            res[j] = map.get(arr[j]);
        }
        return res;
    }

}
