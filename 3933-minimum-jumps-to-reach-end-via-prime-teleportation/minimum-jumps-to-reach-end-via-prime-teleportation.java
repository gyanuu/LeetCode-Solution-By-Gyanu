import java.util.*;

class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;
        int maxVal = Arrays.stream(nums).max().getAsInt();

        // 1. Sieve of Eratosthenes to find primes up to maxVal
        boolean[] isPrime = new boolean[maxVal + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int p = 2; p * p <= maxVal; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= maxVal; i += p) isPrime[i] = false;
            }
        }

        // 2. Map values to their indices
        Map<Integer, List<Integer>> valToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valToIndices.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // 3. BFS Setup
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        Set<Integer> usedPrimes = new HashSet<>();
        queue.offer(0);
        visited[0] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int curr = queue.poll();
                if (curr == n - 1) return steps;

                // Move 1: Adjacent
                int[] nextMoves = {curr - 1, curr + 1};
                for (int next : nextMoves) {
                    if (next >= 0 && next < n && !visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }

                // Move 2: Prime Teleportation
                int p = nums[curr];
                if (isPrime[p] && !usedPrimes.contains(p)) {
                    usedPrimes.add(p);
                    for (int multiple = p; multiple <= maxVal; multiple += p) {
                        for (int idx : valToIndices.getOrDefault(multiple, Collections.emptyList())) {
                            if (!visited[idx]) {
                                visited[idx] = true;
                                queue.offer(idx);
                            }
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
