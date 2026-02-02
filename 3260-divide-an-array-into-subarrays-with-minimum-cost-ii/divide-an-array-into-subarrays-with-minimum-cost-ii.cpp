class Solution {
public:
    long long minimumCost(vector<int>& nums, int k, int dist) {
        // The cost is always nums[0] + sum of (k-1) elements chosen from nums[1...n-1].
        // Constraint: The difference between the max index and min index of chosen elements <= dist.
        // This effectively means we are looking for the minimum sum of (k-1) elements 
        // within a sliding window of size (dist + 1).

        long long result = -1;
        long long sum_L = 0;
        int target = k - 1; // We need to pick this many additional elements
        int n = nums.size();
        
        // L stores the smallest 'target' values.
        // R stores the remaining values in the current window.
        multiset<int> L, R;
        
        for (int i = 1; i < n; ++i) {
            // 1. ADD NEW ELEMENT: nums[i]
            int val = nums[i];
            
            // Try inserting into L first
            if (L.size() < target) {
                L.insert(val);
                sum_L += val;
            } else {
                // L is full. Check if val belongs in L or R.
                int max_L = *L.rbegin();
                if (val < max_L) {
                    // val is smaller than the largest in L, so swap them
                    L.erase(prev(L.end())); // Remove largest from L
                    sum_L -= max_L;
                    R.insert(max_L);        // Move it to R
                    
                    L.insert(val);          // Insert val to L
                    sum_L += val;
                } else {
                    // val is larger, goes to R
                    R.insert(val);
                }
            }
            
            // 2. REMOVE OLD ELEMENT: nums[i - dist - 1]
            // We maintain a window of indices covering a distance of 'dist'.
            // The number of elements in the window can be up to dist + 1.
            // If i > dist + 1, the element at index (i - dist - 1) falls out.
            if (i > dist + 1) {
                int out_val = nums[i - dist - 1];
                
                // Check if the element to remove is in L
                auto it_L = L.find(out_val);
                if (it_L != L.end()) {
                    L.erase(it_L);
                    sum_L -= out_val;
                    
                    // L size decreased below target, refill from R if possible
                    if (!R.empty()) {
                        int min_R = *R.begin();
                        R.erase(R.begin());
                        L.insert(min_R);
                        sum_L += min_R;
                    }
                } else {
                    // If not in L, it must be in R
                    auto it_R = R.find(out_val);
                    if (it_R != R.end()) {
                        R.erase(it_R);
                    }
                }
            }
            
            // 3. UPDATE RESULT
            // If we have enough elements to form a valid set
            if (L.size() == target) {
                long long current_total = (long long)nums[0] + sum_L;
                if (result == -1 || current_total < result) {
                    result = current_total;
                }
            }
        }
        
        return result;
    }
};