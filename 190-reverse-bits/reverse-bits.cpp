class Solution {
public:
    int reverseBits(int n) {
        unsigned int result = 0;
        
        for(int i = 0; i < 32; i++) {
            result <<= 1;        // result ko left shift karo
            result |= (n & 1);   // n ka last bit add karo
            n >>= 1;             // n ko right shift karo
        }
        
        return result;
    }
};
