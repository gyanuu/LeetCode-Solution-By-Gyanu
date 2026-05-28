class Trie {
    // Constant representing infinity for initialization
    private static final int INFINITY = 1 << 30;
  
    // Array to store child nodes for each letter (a-z)
    private Trie[] children = new Trie[26];
  
    // Minimum length of word stored at or below this node
    private int minLength = INFINITY;
  
    // Index of the word with minimum length at or below this node
    private int wordIndex = INFINITY;

    /**
     * Inserts a word into the trie in reverse order
     * Updates minimum length and corresponding index at each node
     * @param word the word to insert
     * @param index the original index of the word in the container
     */
    public void insert(String word, int index) {
        Trie currentNode = this;
      
        // Update root node's minimum length if necessary
        if (currentNode.minLength > word.length()) {
            currentNode.minLength = word.length();
            currentNode.wordIndex = index;
        }
      
        // Insert characters in reverse order (from last to first)
        for (int charPos = word.length() - 1; charPos >= 0; charPos--) {
            int charIndex = word.charAt(charPos) - 'a';
          
            // Create new node if path doesn't exist
            if (currentNode.children[charIndex] == null) {
                currentNode.children[charIndex] = new Trie();
            }
          
            // Move to child node
            currentNode = currentNode.children[charIndex];
          
            // Update minimum length at current node if necessary
            if (currentNode.minLength > word.length()) {
                currentNode.minLength = word.length();
                currentNode.wordIndex = index;
            }
        }
    }

    /**
     * Queries the trie for the longest matching suffix
     * Returns the index of the shortest word with matching suffix
     * @param word the query word to match
     * @return index of the best matching word
     */
    public int query(String word) {
        Trie currentNode = this;
      
        // Traverse the trie following the reverse path of the query word
        for (int charPos = word.length() - 1; charPos >= 0; charPos--) {
            int charIndex = word.charAt(charPos) - 'a';
          
            // Stop if no matching path exists
            if (currentNode.children[charIndex] == null) {
                break;
            }
          
            // Move to child node
            currentNode = currentNode.children[charIndex];
        }
      
        // Return the index of the shortest word found
        return currentNode.wordIndex;
    }
}

/**
 * Solution class for finding words with matching suffixes
 */
class Solution {
    /**
     * Finds indices of words in container that have longest matching suffix
     * with each query word. Among matches, returns the shortest word.
     * @param wordsContainer array of container words
     * @param wordsQuery array of query words
     * @return array of indices corresponding to best matches for each query
     */
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        // Build trie from container words
        Trie trie = new Trie();
        for (int i = 0; i < wordsContainer.length; i++) {
            trie.insert(wordsContainer[i], i);
        }
      
        // Process each query word
        int queryCount = wordsQuery.length;
        int[] result = new int[queryCount];
        for (int i = 0; i < queryCount; i++) {
            result[i] = trie.query(wordsQuery[i]);
        }
      
        return result;
    }
}