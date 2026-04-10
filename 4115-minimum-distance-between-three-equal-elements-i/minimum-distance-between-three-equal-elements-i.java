import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        // Step 1: store indices
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int ans = Integer.MAX_VALUE;

        // Step 2: check each list
        for (List<Integer> list : map.values()) {
            if (list.size() < 3) continue;

            // check consecutive triplets
            for (int i = 0; i <= list.size() - 3; i++) {
                int first = list.get(i);
                int last = list.get(i + 2);

                int distance = 2 * (last - first);
                ans = Math.min(ans, distance);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}