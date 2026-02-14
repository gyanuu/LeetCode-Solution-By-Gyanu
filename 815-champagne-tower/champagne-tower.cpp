class Solution {
public:
    double champagneTower(int poured, int query_row, int query_glass) {
        // Create a 2D vector to store the amount of champagne in each glass.
        // Size 101x101 is sufficient as per problem constraints (max 100 rows).
        vector<vector<double>> tower(101, vector<double>(101, 0.0));

        // Pour all champagne into the top glass (0, 0)
        tower[0][0] = (double)poured;

        // Iterate through each row up to the query_row
        for (int i = 0; i < query_row; ++i) {
            for (int j = 0; j <= i; ++j) {
                // If a glass has more than 1 cup, calculate the overflow
                if (tower[i][j] > 1.0) {
                    double overflow = tower[i][j] - 1.0;
                    // Cap the current glass at 1.0 cup (it's full)
                    tower[i][j] = 1.0;
                    // Split the overflow equally into the two glasses below
                    tower[i + 1][j] += overflow / 2.0;
                    tower[i + 1][j + 1] += overflow / 2.0;
                }
            }
        }

        // The result is the amount in the queried glass, capped at 1.0 (as the
        // simulation naturally calculates the total flow through, not the final
        // capped value for the last row's distribution)
        return min(1.0, tower[query_row][query_glass]);
    }
};
