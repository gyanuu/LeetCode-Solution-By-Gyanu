class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        
        int sum = 0;
        int f0 = 0;
        
        // Step 1: calculate sum and F(0)
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f0 += i * nums[i];
        }
        
        int max = f0;
        int prev = f0;
        
        // Step 2: use recurrence relation
        for (int k = 1; k < n; k++) {
            int curr = prev + sum - n * nums[n - k];
            max = Math.max(max, curr);
            prev = curr;
        }
        
        return max;
    }
}