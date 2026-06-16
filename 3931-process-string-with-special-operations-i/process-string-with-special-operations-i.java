class Solution {
    /**
     * Processes a string based on special character rules:
     * - Letters are appended to the result
     * - '*' removes the last character (backspace operation)
     * - '#' duplicates the current result string
     * - '%' reverses the current result string
     * 
     * @param s the input string to process
     * @return the processed string after applying all operations
     */
    public String processStr(String s) {
        // StringBuilder to efficiently build the result string
        StringBuilder result = new StringBuilder();
      
        // Iterate through each character in the input string
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                // Append letter characters directly to result
                result.append(c);
            } else if (c == '*') {
                // Remove last character (backspace operation)
                // Use Math.max to ensure length doesn't go below 0
                result.setLength(Math.max(0, result.length() - 1));
            } else if (c == '#') {
                // Duplicate the current result by appending it to itself
                result.append(result);
            } else if (c == '%') {
                // Reverse the current result string
                result.reverse();
            }
            // Other characters are ignored
        }
      
        // Convert StringBuilder to String and return
        return result.toString();
    }
}
