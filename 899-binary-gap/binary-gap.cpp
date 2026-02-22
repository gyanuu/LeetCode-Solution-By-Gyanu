class Solution {
public:
    int binaryGap(int n) {
        int last = -1;      // last position of 1
        int maxGap = 0;
        int pos = 0;        // current bit position
        
        while(n > 0) {
            if(n & 1) {     // if current bit is 1
                if(last != -1) {
                    maxGap = max(maxGap, pos - last);
                }
                last = pos;
            }
            n >>= 1;        // shift right
            pos++;
        }
        
        return maxGap;
    }
};