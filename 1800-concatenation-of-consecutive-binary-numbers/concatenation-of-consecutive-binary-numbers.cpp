class Solution {
public:
    int concatenatedBinary(int n) {
        const int MOD = 1e9 + 7;
        long long result = 0;
        int bitLength = 0;
        
        for (int i = 1; i <= n; i++) {
            
            // If i is a power of 2, increase bit length
            if ((i & (i - 1)) == 0) {
                bitLength++;
            }
            
            // Left shift result and add i
            result = ((result << bitLength) % MOD + i) % MOD;
        }
        
        return result;
    }
};