class Solution {
public:
    int maxSideLength(vector<vector<int>>& mat, int threshold) {
        int m = mat.size(), n = mat[0].size();

        // Step 1: Prefix Sum
        vector<vector<int>> pre(m + 1, vector<int>(n + 1, 0));

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = mat[i-1][j-1]
                          + pre[i-1][j]
                          + pre[i][j-1]
                          - pre[i-1][j-1];
            }
        }

        int low = 0, high = min(m, n), ans = 0;

        // Step 2: Binary Search
        while (low <= high) {
            int mid = (low + high) / 2;
            bool possible = false;

            for (int i = mid; i <= m && !possible; i++) {
                for (int j = mid; j <= n; j++) {
                    int sum = pre[i][j]
                            - pre[i-mid][j]
                            - pre[i][j-mid]
                            + pre[i-mid][j-mid];

                    if (sum <= threshold) {
                        possible = true;
                        break;
                    }
                }
            }

            if (possible) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }
};
