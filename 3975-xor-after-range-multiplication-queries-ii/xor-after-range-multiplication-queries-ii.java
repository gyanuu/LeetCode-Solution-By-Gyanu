import java.util.*;

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int MOD = 1_000_000_007;
        int B = (int) Math.sqrt(n);

        // 1. Separate queries by small k and large k
        List<int[]>[] smallKQueries = new List[B + 1];
        
        for (int[] q : queries) {
            int k = q[2];
            if (k <= B) {
                if (smallKQueries[k] == null) smallKQueries[k] = new ArrayList<>();
                smallKQueries[k].add(q);
            } else {
                // Large k: Process immediately (Brute force is O(sqrt(N)))
                int l = q[0], r = q[1], v = q[3];
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) ((1L * nums[i] * v) % MOD);
                }
            }
        }

        // 2. Process small k using Multiplicative Difference Arrays
        for (int k = 1; k <= B; k++) {
            if (smallKQueries[k] == null) continue;

            // Process each remainder group [0...k-1]
            for (int rem = 0; rem < k; rem++) {
                int groupSize = (n - 1 - rem) / k + 1;
                if (groupSize <= 0) continue;

                long[] diff = new long[groupSize + 1];
                Arrays.fill(diff, 1);
                boolean hasQuery = false;

                for (int[] q : smallKQueries[k]) {
                    int l = q[0], r = q[1], v = q[3];
                    if (l % k == rem) {
                        int startIdx = (l - rem) / k;
                        int endIdx = (r - rem) / k;
                        
                        diff[startIdx] = (diff[startIdx] * v) % MOD;
                        diff[endIdx + 1] = (diff[endIdx + 1] * modInverse(v, MOD)) % MOD;
                        hasQuery = true;
                    }
                }

                if (hasQuery) {
                    long runningMul = 1;
                    for (int i = 0; i < groupSize; i++) {
                        runningMul = (runningMul * diff[i]) % MOD;
                        if (runningMul != 1) {
                            int actualIdx = i * k + rem;
                            nums[actualIdx] = (int) ((1L * nums[actualIdx] * runningMul) % MOD);
                        }
                    }
                }
            }
        }

        // 3. Final XOR sum
        int xor = 0;
        for (int x : nums) xor ^= x;
        return xor;
    }

    private long modInverse(long n, int mod) {
        return power(n, mod - 2, mod);
    }

    private long power(long x, long y, int mod) {
        long res = 1;
        x %= mod;
        while (y > 0) {
            if ((y & 1) == 1) res = (res * x) % mod;
            y = y >> 1;
            x = (x * x) % mod;
        }
        return res;
    }
}