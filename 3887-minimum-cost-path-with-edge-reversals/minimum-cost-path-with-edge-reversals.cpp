#include <vector>
#include <queue>
#include <climits>

using namespace std;

class Solution {
public:
    int minCost(int n, vector<vector<int>>& edges) {
        // Adjacency list: node -> {neighbor, weight}
        vector<vector<pair<int, int>>> adj(n);
        
        // Build the graph
        for (const auto& edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            
            // 1. Normal directed edge u -> v with cost w
            adj[u].push_back({v, w});
            
            // 2. Reversed edge v -> u with cost 2 * w
            // (Since u -> v is incoming to v, we can use v's switch to go v -> u)
            adj[v].push_back({u, 2 * w});
        }
        
        // Dijkstra's Algorithm
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        vector<int> dist(n, INT_MAX);
        
        // Start at node 0 with cost 0
        dist[0] = 0;
        pq.push({0, 0}); // {cost, u}
        
        while (!pq.empty()) {
            int d = pq.top().first;
            int u = pq.top().second;
            pq.pop();
            
            // If we found a shorter path to u already, skip
            if (d > dist[u]) continue;
            
            // If we reached the target, we can return the cost immediately
            // (Optional optimization, but standard Dijkstra usually continues)
            if (u == n - 1) return d;
            
            for (auto& edge : adj[u]) {
                int v = edge.first;
                int weight = edge.second;
                
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.push({dist[v], v});
                }
            }
        }
        
        // If dist[n-1] is still INT_MAX, node n-1 is unreachable
        return dist[n - 1] == INT_MAX ? -1 : dist[n - 1];
    }
};