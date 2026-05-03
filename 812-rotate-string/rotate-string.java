class Solution {
    public boolean rotateString(String s, String goal) {
        // Step 1: length check
        if (s.length() != goal.length()) return false;

        // Step 2: check substring
        String doubled = s + s;
        return doubled.contains(goal);
    }
}