import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        
        List<Integer> list = new ArrayList<>();
        
        // Step 1: Flatten grid into list
        for (int[] row : grid) {
            for (int val : row) {
                list.add(val);
            }
        }
        
        // Step 2: Check feasibility
        int base = list.get(0);
        for (int val : list) {
            if ((val - base) % x != 0) {
                return -1;
            }
        }
        
        // Step 3: Sort and find median
        Collections.sort(list);
        int median = list.get(list.size() / 2);
        
        // Step 4: Calculate operations
        int operations = 0;
        for (int val : list) {
            operations += Math.abs(val - median) / x;
        }
        
        return operations;
    }
}