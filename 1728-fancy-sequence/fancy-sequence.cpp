class Fancy {
public:
    const long MOD = 1e9+7;
    vector<long> arr;
    long mul = 1, add = 0;

    long modInverse(long x) {
        long res = 1, p = MOD-2;
        while(p){
            if(p&1) res = (res*x)%MOD;
            x = (x*x)%MOD;
            p >>= 1;
        }
        return res;
    }

    Fancy() {
        
    }
    
    void append(int val) {
        long v = ((val - add + MOD) % MOD) * modInverse(mul) % MOD;
        arr.push_back(v);
    }
    
    void addAll(int inc) {
        add = (add + inc) % MOD;
    }
    
    void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }
    
    int getIndex(int idx) {
        if(idx >= arr.size()) return -1;
        return (arr[idx] * mul + add) % MOD;
    }
};