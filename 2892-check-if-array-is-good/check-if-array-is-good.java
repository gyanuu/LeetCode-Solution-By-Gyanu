class Solution {
    public boolean isGood(int[] nums) {
        // Calculate n based on array length (array should have n+1 elements)
        int n = nums.length - 1;
      
        // Create frequency array to count occurrences of each number
        // Size 201 to handle constraint where numbers can be up to 200
        int[] frequency = new int[201];
      
        // Count frequency of each number in the input array
        for (int number : nums) {
            frequency[number]++;
        }
      
        // Check if the largest number n appears exactly twice
        if (frequency[n] != 2) {
            return false;
        }
      
        // Check if all numbers from 1 to n-1 appear exactly once
        for (int i = 1; i < n; i++) {
            if (frequency[i] != 1) {
                return false;
            }
        }
      
        // All conditions satisfied - array is "good"
        return true;
    }
}