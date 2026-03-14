class Solution {
public:
    string result;
    int cnt = 0;

    void backtrack(int n, int k, string &curr) {
        if (cnt >= k) return;

        if (curr.size() == n) {
            cnt++;
            if (cnt == k) result = curr;
            return;
        }

        for (char ch = 'a'; ch <= 'c'; ch++) {
            if (!curr.empty() && curr.back() == ch) continue;
            curr.push_back(ch);
            backtrack(n, k, curr);
            curr.pop_back();
        }
    }

    string getHappyString(int n, int k) {
        string curr = "";
        backtrack(n, k, curr);
        return result;
    }
};