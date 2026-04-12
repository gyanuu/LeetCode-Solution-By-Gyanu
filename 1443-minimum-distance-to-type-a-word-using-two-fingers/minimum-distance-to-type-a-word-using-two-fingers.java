class Solution {
    public int minimumDistance(String word) {
        // dp[other] represents the min distance where one finger is at 
        // word[i-1] and the 'other' finger is at index 'other'.
        // 26 represents the "hovering/start" state (dist = 0).
        int[] dp = new int[27];
        java.util.Arrays.fill(dp, 0);
        
        int n = word.length();
        
        // We iterate through the word starting from the second character
        // because the first character is always "free" (cost 0).
        for (int i = 0; i < n - 1; i++) {
            int curr = word.charAt(i) - 'A';
            int next = word.charAt(i + 1) - 'A';
            int[] nextDp = new int[27];
            java.util.Arrays.fill(nextDp, Integer.MAX_VALUE / 2);
            
            for (int other = 0; other <= 26; other++) {
                if (dp[other] == Integer.MAX_VALUE / 2) continue;
                
                // Option 1: Move the finger that is currently at 'curr' to 'next'
                nextDp[other] = Math.min(nextDp[other], dp[other] + getDist(curr, next));
                
                // Option 2: Move the 'other' finger to 'next'
                // If other == 26, it's the first time this finger is used (cost 0)
                int distOther = (other == 26) ? 0 : getDist(other, next);
                nextDp[curr] = Math.min(nextDp[curr], dp[other] + distOther);
            }
            dp = nextDp;
        }
        
        int minDistance = Integer.MAX_VALUE;
        for (int d : dp) minDistance = Math.min(minDistance, d);
        return minDistance;
    }
    
    private int getDist(int a, int b) {
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}