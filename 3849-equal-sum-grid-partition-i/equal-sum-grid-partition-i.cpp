class Solution {
public:
    bool canPartitionGrid(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();

        long long total = 0;

        // Step 1: total sum
        for (auto &row : grid) {
            for (int val : row) {
                total += val;
            }
        }

        // If total is odd, impossible
        if (total % 2 != 0) return false;

        long long target = total / 2;

        // Step 2: Check horizontal cuts
        long long curr = 0;
        for (int i = 0; i < m - 1; i++) { // ensure non-empty below
            for (int j = 0; j < n; j++) {
                curr += grid[i][j];
            }
            if (curr == target) return true;
        }

        // Step 3: Check vertical cuts
        curr = 0;
        for (int j = 0; j < n - 1; j++) { // ensure non-empty right
            for (int i = 0; i < m; i++) {
                curr += grid[i][j];
            }
            if (curr == target) return true;
        }

        return false;
    }
};