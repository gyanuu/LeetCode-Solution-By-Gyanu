class Solution {
    /**
     * Finds the minimum element in a rotated sorted array that may contain duplicates.
     * Uses binary search with special handling for duplicate values.
     * 
     * @param nums the rotated sorted array
     * @return the minimum element in the array
     */
    public int findMin(int[] nums) {
        // Initialize two pointers for binary search
        int left = 0;
        int right = nums.length - 1;
      
        // Continue searching while the search space has more than one element
        while (left < right) {
            // Calculate the middle index using bit shift (equivalent to division by 2)
            int mid = (left + right) >> 1;
          
            // Case 1: Middle element is greater than rightmost element
            // This means the minimum is in the right half (rotation point is on the right)
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } 
            // Case 2: Middle element is less than rightmost element
            // This means the right half is sorted, so minimum is in left half (including mid)
            else if (nums[mid] < nums[right]) {
                right = mid;
            } 
            // Case 3: Middle element equals rightmost element
            // Cannot determine which half contains minimum, safely shrink from right
            else {
                right--;
            }
        }
      
        // When left equals right, we've found the minimum element
        return nums[left];
    }
}