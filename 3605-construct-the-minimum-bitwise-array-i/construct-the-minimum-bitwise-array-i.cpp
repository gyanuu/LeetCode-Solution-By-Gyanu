#include <vector>

class Solution {
public:
    std::vector<int> minBitwiseArray(std::vector<int>& nums) {
        int n = nums.size();
        std::vector<int> ans(n);

        for (int i = 0; i < n; ++i) {
            int target = nums[i];
            bool found = false;
            
            // Brute force check from 0 up to target
            // Since we need the minimum, we stop at the first match
            for (int x = 0; x <= target; ++x) {
                if ((x | (x + 1)) == target) {
                    ans[i] = x;
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                ans[i] = -1;
            }
        }
        
        return ans;
    }
};
