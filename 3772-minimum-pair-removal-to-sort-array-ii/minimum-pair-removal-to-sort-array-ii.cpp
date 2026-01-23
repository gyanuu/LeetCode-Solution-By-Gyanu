class Solution {
public:
    int minimumPairRemoval(vector<int>& input) {
        int n = input.size();
        if (n <= 1) return 0;

        vector<long long> nums(input.begin(), input.end());
        vector<int> prev(n), next(n);
        vector<bool> alive(n, true);

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }

        // Count initial bad adjacent pairs
        int badCount = 0;
        for (int i = 0; i + 1 < n; i++) {
            if (nums[i] > nums[i + 1]) badCount++;
        }

        priority_queue<
            pair<long long,int>,
            vector<pair<long long,int>>,
            greater<>
        > pq;

        for (int i = 0; i + 1 < n; i++) {
            pq.push({nums[i] + nums[i + 1], i});
        }

        int ops = 0;

        while (badCount > 0) {
            auto [sum, i] = pq.top();
            pq.pop();

            int j = next[i];
            if (j >= n || !alive[i] || !alive[j]) continue;
            if (nums[i] + nums[j] != sum) continue;

            int l = prev[i];
            int r = next[j];

            // Remove old bad relations
            if (l != -1 && nums[l] > nums[i]) badCount--;
            if (nums[i] > nums[j]) badCount--;
            if (r < n && nums[j] > nums[r]) badCount--;

            // Merge
            nums[i] += nums[j];
            alive[j] = false;
            next[i] = r;
            if (r < n) prev[r] = i;

            // Add new bad relations
            if (l != -1 && nums[l] > nums[i]) badCount++;
            if (r < n && nums[i] > nums[r]) badCount++;

            if (l != -1) pq.push({nums[l] + nums[i], l});
            if (r < n) pq.push({nums[i] + nums[r], i});

            ops++;
        }

        return ops;
    }
};
