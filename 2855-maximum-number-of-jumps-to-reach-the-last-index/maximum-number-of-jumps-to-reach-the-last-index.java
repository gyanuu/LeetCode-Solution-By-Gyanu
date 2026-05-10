class Solution {
    // Memoization array to store the maximum jumps from each index
    private Integer[] memo;
    // Input array of numbers
    private int[] nums;
    // Length of the input array
    private int n;
    // Maximum allowed difference between elements for a valid jump
    private int target;

    /**
     * Finds the maximum number of jumps from index 0 to index n-1
     * where each jump from index i to j is valid if |nums[i] - nums[j]| <= target
     * 
     * @param nums   The input array of integers
     * @param target The maximum allowed difference for a valid jump
     * @return The maximum number of jumps, or -1 if no path exists
     */
    public int maximumJumps(int[] nums, int target) {
        // Initialize instance variables
        this.n = nums.length;
        this.target = target;
        this.nums = nums;
        this.memo = new Integer[n];
      
        // Calculate the maximum jumps starting from index 0
        int result = dfs(0);
      
        // Return -1 if no valid path exists, otherwise return the result
        return result < 0 ? -1 : result;
    }

    /**
     * Depth-first search with memoization to find maximum jumps from index i to n-1
     * 
     * @param i The current index
     * @return The maximum number of jumps from index i to n-1, or negative if impossible
     */
    private int dfs(int i) {
        // Base case: reached the last index
        if (i == n - 1) {
            return 0;
        }
      
        // Check if result is already computed
        if (memo[i] != null) {
            return memo[i];
        }
      
        // Initialize with a very small value (representing impossible path)
        int maxJumps = -(1 << 30);
      
        // Try jumping to all subsequent indices
        for (int j = i + 1; j < n; j++) {
            // Check if jump from i to j is valid (difference <= target)
            if (Math.abs(nums[i] - nums[j]) <= target) {
                // Recursively find maximum jumps from j and add 1 for current jump
                maxJumps = Math.max(maxJumps, 1 + dfs(j));
            }
        }
      
        // Store and return the result
        memo[i] = maxJumps;
        return maxJumps;
    }
}