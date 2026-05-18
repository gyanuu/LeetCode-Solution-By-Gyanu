class Solution {
    public int minJumps(int[] arr) {
        // Build a graph where key is the value and value is list of indices with that value
        Map<Integer, List<Integer>> valueToIndicesMap = new HashMap<>();
        int arrayLength = arr.length;
      
        // Populate the map with all indices for each value
        for (int index = 0; index < arrayLength; index++) {
            valueToIndicesMap.computeIfAbsent(arr[index], k -> new ArrayList<>()).add(index);
        }
      
        // Track visited indices to avoid revisiting
        boolean[] visited = new boolean[arrayLength];
      
        // BFS queue to store indices to process
        Deque<Integer> queue = new ArrayDeque<>();
      
        // Start BFS from index 0
        queue.offer(0);
        visited[0] = true;
      
        // BFS to find minimum steps to reach the last index
        for (int steps = 0; ; steps++) {
            // Process all nodes at current level
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                int currentIndex = queue.poll();
              
                // Check if we've reached the target (last index)
                if (currentIndex == arrayLength - 1) {
                    return steps;
                }
              
                // Jump to all indices with the same value
                List<Integer> sameValueIndices = valueToIndicesMap.get(arr[currentIndex]);
                for (int nextIndex : sameValueIndices) {
                    if (!visited[nextIndex]) {
                        visited[nextIndex] = true;
                        queue.offer(nextIndex);
                    }
                }
              
                // Clear the list to avoid redundant checks in future iterations
                // This optimization prevents revisiting the same value group
                sameValueIndices.clear();
              
                // Jump to adjacent indices (left and right)
                for (int nextIndex : new int[] {currentIndex - 1, currentIndex + 1}) {
                    if (nextIndex >= 0 && nextIndex < arrayLength && !visited[nextIndex]) {
                        visited[nextIndex] = true;
                        queue.offer(nextIndex);
                    }
                }
            }
        }
    }
}
