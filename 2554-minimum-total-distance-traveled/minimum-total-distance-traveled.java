import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        // Step 1: Sort positions
        // Collections.sort for the List, Arrays.sort for the 2D array
        Collections.sort(robot);
        Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));

        int n = robot.size();
        int m = factory.length;

        // dp[i][j] = min distance for robot[i...] using factory[j...]
        long[][] dp = new long[n + 1][m + 1];

        // Initialize with a large value (using Long.MAX_VALUE / 2 to avoid overflow)
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }

        // Base case: If no robots are left, distance is 0
        for (int j = 0; j <= m; j++) {
            dp[n][j] = 0;
        }

        // Step 2: Dynamic Programming
        for (int j = m - 1; j >= 0; j--) {
            for (int i = n; i >= 0; i--) {
                // Option 1: Skip this factory
                dp[i][j] = dp[i][j + 1];

                // Option 2: Use this factory for 'k' robots
                long currentDistanceSum = 0;
                int limit = factory[j][1];
                
                for (int k = 1; k <= limit && i + k <= n; k++) {
                    // Calculate distance for the k-th robot assigned to this factory
                    currentDistanceSum += Math.abs((long)robot.get(i + k - 1) - factory[j][0]);
                    
                    if (dp[i + k][j + 1] != Long.MAX_VALUE / 2) {
                        dp[i][j] = Math.min(dp[i][j], currentDistanceSum + dp[i + k][j + 1]);
                    }
                }
            }
        }

        return dp[0][0];
    }
}