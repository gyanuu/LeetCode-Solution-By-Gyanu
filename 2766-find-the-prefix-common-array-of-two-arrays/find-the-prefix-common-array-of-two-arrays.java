class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];
      
        // Frequency arrays to track occurrences of each number (1 to n)
        // in prefixes of A and B respectively
        int[] frequencyA = new int[n + 1];  // Index 0 unused, elements are from 1 to n
        int[] frequencyB = new int[n + 1];  // Index 0 unused, elements are from 1 to n
      
        // Process each position to build prefix arrays
        for (int i = 0; i < n; i++) {
            // Increment frequency for current elements
            frequencyA[A[i]]++;
            frequencyB[B[i]]++;
          
            // Count common elements in current prefixes
            // An element is common if it appears in both prefix arrays
            for (int value = 1; value <= n; value++) {
                // Add minimum occurrence count to get common elements
                result[i] += Math.min(frequencyA[value], frequencyB[value]);
            }
        }
      
        return result;
    }
}
