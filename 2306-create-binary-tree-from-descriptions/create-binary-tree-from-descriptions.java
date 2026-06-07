/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        // Map to store all nodes by their values
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
      
        // Set to track all nodes that are children (have a parent)
        Set<Integer> childrenSet = new HashSet<>();
      
        // Process each description to build the tree relationships
        for (int[] description : descriptions) {
            int parentValue = description[0];
            int childValue = description[1];
            int isLeft = description[2];
          
            // Create parent node if it doesn't exist
            if (!nodeMap.containsKey(parentValue)) {
                nodeMap.put(parentValue, new TreeNode(parentValue));
            }
          
            // Create child node if it doesn't exist
            if (!nodeMap.containsKey(childValue)) {
                nodeMap.put(childValue, new TreeNode(childValue));
            }
          
            // Establish parent-child relationship based on isLeft flag
            TreeNode parentNode = nodeMap.get(parentValue);
            TreeNode childNode = nodeMap.get(childValue);
          
            if (isLeft == 1) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
          
            // Mark this node as a child
            childrenSet.add(childValue);
        }
      
        // Find and return the root node (node that is not a child of any other node)
        for (Map.Entry<Integer, TreeNode> entry : nodeMap.entrySet()) {
            if (!childrenSet.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
      
        // Return null if no root found (should not happen with valid input)
        return null;
    }
}