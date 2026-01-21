#include <vector>
#include <cmath>

class Solution {
public:
    std::vector<int> minBitwiseArray(std::vector<int>& nums) {
        int n = nums.size();
        std::vector<int> ans(n);
        
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 2) {
                // No non-negative integer x satisfies x | (x+1) = 2
                ans[i] = -1;
            } else {
                // All other primes are odd (ending in ...1)
                // We need to find the length of the trailing set bits (1s)
                int temp = nums[i];
                int trailingOnes = 0;
                
                while (temp & 1) {
                    temp >>= 1;
                    trailingOnes++;
                }
                
                // To minimize x, flip the MSB of the trailing 1-block.
                // For example, if nums[i] is 15 (1111), trailingOnes is 4.
                // We subtract 2^(4-1) = 8 to get 7 (0111).
                ans[i] = nums[i] - (1 << (trailingOnes - 1));
            }
        }
        
        return ans;
    }
};