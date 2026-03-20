class Solution {
public:
    vector<vector<int>> minAbsDiff(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> ans(m - k + 1, vector<int>(n - k + 1));

        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {

                vector<int> vals;

                // extract submatrix
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        vals.push_back(grid[x][y]);
                    }
                }

                // remove duplicates
                sort(vals.begin(), vals.end());
                vals.erase(unique(vals.begin(), vals.end()), vals.end());

                int minDiff = INT_MAX;

                for (int t = 1; t < vals.size(); t++) {
                    minDiff = min(minDiff, vals[t] - vals[t - 1]);
                }

                ans[i][j] = (vals.size() <= 1) ? 0 : minDiff;
            }
        }

        return ans;
    }
};