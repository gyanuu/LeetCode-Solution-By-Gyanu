class Solution {
public:
    int minPartitions(string n) {
        int maxDigit = 0;
        
        for(char c : n) {
            maxDigit = max(maxDigit, c - '0');
            
            // Optimization: if 9 is found, we can stop early
            if(maxDigit == 9)
                return 9;
        }
        
        return maxDigit;
    }
};