import java.util.*;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int rev = reverse(nums[i]);

            // Check if current number exists as reverse before
            if (map.containsKey(nums[i])) {
                minDist = Math.min(minDist, i - map.get(nums[i]));
            }

            // Store reversed value
            map.put(rev, i);
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    private int reverse(int num) {
        int rev = 0;
        while (num > 0) {
            rev = rev * 10 + num % 10;
            num /= 10;
        }
        return rev;
    }
}