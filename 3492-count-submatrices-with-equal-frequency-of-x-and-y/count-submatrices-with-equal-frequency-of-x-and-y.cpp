class Solution {
public:
    int numberOfSubmatrices(vector<vector<char>>& grid) {
        int m = grid.size(), n = grid[0].size();
        
        vector<vector<int>> pref(m, vector<int>(n, 0));
        vector<vector<int>> countX(m, vector<int>(n, 0));
        
        int ans = 0;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                
                int val = 0;
                if(grid[i][j] == 'X') val = 1;
                else if(grid[i][j] == 'Y') val = -1;
                
                pref[i][j] = val;
                countX[i][j] = (grid[i][j] == 'X');
                
                if(i > 0) {
                    pref[i][j] += pref[i-1][j];
                    countX[i][j] += countX[i-1][j];
                }
                if(j > 0) {
                    pref[i][j] += pref[i][j-1];
                    countX[i][j] += countX[i][j-1];
                }
                if(i > 0 && j > 0) {
                    pref[i][j] -= pref[i-1][j-1];
                    countX[i][j] -= countX[i-1][j-1];
                }
                
                if(pref[i][j] == 0 && countX[i][j] > 0) {
                    ans++;
                }
            }
        }
        
        return ans;
    }
};