class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        // Store all possible prefixes from arr1
        Set<Integer> prefixSet = new HashSet<>();
      
        // Generate all prefixes for each number in arr1
        // For example: 123 generates 123, 12, 1
        for (int number : arr1) {
            while (number > 0) {
                prefixSet.add(number);
                number /= 10;  // Remove the last digit
            }
        }
      
        // Track the maximum length of common prefix
        int maxPrefixLength = 0;
      
        // Check each number in arr2 for common prefixes
        for (int number : arr2) {
            // Generate prefixes for current number and check against prefixSet
            while (number > 0) {
                if (prefixSet.contains(number)) {
                    // Found a common prefix, update maximum length
                    int currentPrefixLength = String.valueOf(number).length();
                    maxPrefixLength = Math.max(maxPrefixLength, currentPrefixLength);
                    break;  // No need to check shorter prefixes
                }
                number /= 10;  // Remove the last digit to get shorter prefix
            }
        }
      
        return maxPrefixLength;
    }
}
