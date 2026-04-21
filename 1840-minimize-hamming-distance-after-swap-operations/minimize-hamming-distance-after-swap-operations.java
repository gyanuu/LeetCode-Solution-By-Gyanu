import java.util.*;

class Solution {

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if(parent[x] != x)
                parent[x] = find(parent[x]); // Path compression
            return parent[x];
        }

        void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);

            if(pa == pb) return;

            if(rank[pa] < rank[pb]) {
                parent[pa] = pb;
            }
            else if(rank[pb] < rank[pa]) {
                parent[pb] = pa;
            }
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
        }
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {

        int n = source.length;

        DSU dsu = new DSU(n);

        // Build connected components
        for(int[] swap : allowedSwaps) {
            dsu.union(swap[0], swap[1]);
        }

        // Store source frequencies for each component
        Map<Integer, Map<Integer, Integer>> groups = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int root = dsu.find(i);

            groups.putIfAbsent(root, new HashMap<>());

            Map<Integer, Integer> freq = groups.get(root);

            freq.put(source[i], freq.getOrDefault(source[i], 0) + 1);
        }

        int mismatch = 0;

        // Match target values
        for(int i = 0; i < n; i++) {
            int root = dsu.find(i);

            Map<Integer, Integer> freq = groups.get(root);

            if(freq.getOrDefault(target[i], 0) > 0) {
                freq.put(target[i], freq.get(target[i]) - 1);
            } else {
                mismatch++;
            }
        }

        return mismatch;
    }
}