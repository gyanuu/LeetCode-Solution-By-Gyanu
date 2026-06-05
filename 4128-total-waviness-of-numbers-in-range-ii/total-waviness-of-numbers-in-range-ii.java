class Solution {
    // Dimensions: [index][prev_digit + 1][prev2_digit + 1][isTight][isLeadingZero]
    // We change the DP to return a pair of values: [Total Waviness, Total Valid Numbers Formed]
    // To memoize efficiently, we use a 6D array where the last dimension separates these two counts.
    private Long[][][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return countWavinessUntil(num2) - countWavinessUntil(num1 - 1);
    }

    private long countWavinessUntil(long num) {
        if (num < 100) return 0;

        String s = Long.toString(num);
        int n = s.length();
        
        // 0 for Waviness Sum, 1 for Count of Valid Numbers
        memo = new Long[n][11][11][2][2][2];
        
        return dp(0, 0, 0, 1, 1, s)[0];
    }

    /**
     * @return long[] where:
     * res[0] = total accumulated waviness for all valid suffixes
     * res[1] = total count of valid numbers that can be formed from this state
     */
    private long[] dp(int idx, int prev, int prev2, int isTight, int isLeadingZero, String s) {
        // Base case: 0 waviness accumulated, but exactly 1 valid number completed
        if (idx == s.length()) {
            return new long[]{0, 1};
        }

        // Return cached result if already calculated
        if (memo[idx][prev][prev2][isTight][isLeadingZero][0] != null) {
            return new long[]{
                memo[idx][prev][prev2][isTight][isLeadingZero][0],
                memo[idx][prev][prev2][isTight][isLeadingZero][1]
            };
        }

        long totalWavinessSum = 0;
        long totalValidNumbers = 0;
        int limit = (isTight == 1) ? (s.charAt(idx) - '0') : 9;

        for (int digit = 0; digit <= limit; digit++) {
            int nextTight = (isTight == 1 && digit == limit) ? 1 : 0;
            int nextLeadingZero = (isLeadingZero == 1 && digit == 0) ? 1 : 0;

            // Compute values recursively for the rest of the digits
            int nextPrev = (nextLeadingZero == 1) ? 0 : digit + 1;
            int nextPrev2 = (nextLeadingZero == 1) ? 0 : prev;

            long[] nextResult = dp(idx + 1, nextPrev, nextPrev2, nextTight, nextLeadingZero, s);
            long downstreamWaviness = nextResult[0];
            long downstreamWays = nextResult[1];

            long currentWavinessContribution = 0;

            // Check if the PREVIOUS digit (idx - 1) forms a peak or a valley
            if (isLeadingZero == 0 && prev > 0 && prev2 > 0) {
                int actualPrev = prev - 1;
                int actualPrev2 = prev2 - 1;

                boolean isPeak = (actualPrev > actualPrev2) && (actualPrev > digit);
                boolean isValley = (actualPrev < actualPrev2) && (actualPrev < digit);

                if (isPeak || isValley) {
                    currentWavinessContribution = 1;
                }
            }

            // The contribution of the current position is multiplied by how many valid numbers branch out from here
            totalWavinessSum += downstreamWaviness + (currentWavinessContribution * downstreamWays);
            totalValidNumbers += downstreamWays;
        }

        // Save both values to the memoization array
        memo[idx][prev][prev2][isTight][isLeadingZero][0] = totalWavinessSum;
        memo[idx][prev][prev2][isTight][isLeadingZero][1] = totalValidNumbers;

        return new long[]{totalWavinessSum, totalValidNumbers};
    }
}