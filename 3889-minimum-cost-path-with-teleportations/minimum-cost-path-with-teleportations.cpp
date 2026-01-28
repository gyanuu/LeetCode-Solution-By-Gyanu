class Solution {
public:
    int minCost(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        const long long INF = 1e18;

        vector<vector<vector<long long>>> dist(
            m, vector<vector<long long>>(n, vector<long long>(k + 1, INF))
        );

        vector<tuple<int,int,int>> cells;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                cells.emplace_back(grid[i][j], i, j);

        sort(cells.begin(), cells.end());

        // ptr[t] = how many teleportable cells already processed for teleport count t
        vector<int> ptr(k + 1, 0);

        priority_queue<
            tuple<long long,int,int,int>,
            vector<tuple<long long,int,int,int>>,
            greater<>
        > pq;

        dist[0][0][0] = 0;
        pq.push({0, 0, 0, 0});

        while (!pq.empty()) {
            auto [cost, i, j, t] = pq.top();
            pq.pop();

            if (cost != dist[i][j][t]) continue;

            if (i == m - 1 && j == n - 1)
                return cost;

            // Normal moves
            if (i + 1 < m) {
                long long nc = cost + grid[i + 1][j];
                if (nc < dist[i + 1][j][t]) {
                    dist[i + 1][j][t] = nc;
                    pq.push({nc, i + 1, j, t});
                }
            }

            if (j + 1 < n) {
                long long nc = cost + grid[i][j + 1];
                if (nc < dist[i][j + 1][t]) {
                    dist[i][j + 1][t] = nc;
                    pq.push({nc, i, j + 1, t});
                }
            }

            // Optimized teleport
            if (t < k) {
                while (ptr[t] < cells.size() &&
                       get<0>(cells[ptr[t]]) <= grid[i][j]) {

                    auto [val, x, y] = cells[ptr[t]];
                    if (cost < dist[x][y][t + 1]) {
                        dist[x][y][t + 1] = cost;
                        pq.push({cost, x, y, t + 1});
                    }
                    ptr[t]++;
                }
            }
        }

        return -1;
    }
};
