class Solution {
    struct Seg {
        int n;
        vector<int> mn, mx, lz;
        Seg(int n=0): n(n), mn(4*n), mx(4*n), lz(4*n) {}
        void build(int idx, int l, int r, const vector<int>& a) {
            if (l == r) {
                mn[idx] = mx[idx] = a[l];
                return;
            }
            int m = (l + r) >> 1;
            build(idx<<1, l, m, a);
            build(idx<<1|1, m+1, r, a);
            mn[idx] = min(mn[idx<<1], mn[idx<<1|1]);
            mx[idx] = max(mx[idx<<1], mx[idx<<1|1]);
        }
        void apply(int idx, int v) {
            mn[idx] += v;
            mx[idx] += v;
            lz[idx] += v;
        }
        void push(int idx) {
            if (lz[idx]) {
                apply(idx<<1, lz[idx]);
                apply(idx<<1|1, lz[idx]);
                lz[idx] = 0;
            }
        }
        void add(int idx, int l, int r, int ql, int qr, int v) {
            if (ql > r || qr < l) return;
            if (ql <= l && r <= qr) {
                apply(idx, v);
                return;
            }
            push(idx);
            int m = (l + r) >> 1;
            add(idx<<1, l, m, ql, qr, v);
            add(idx<<1|1, m+1, r, ql, qr, v);
            mn[idx] = min(mn[idx<<1], mn[idx<<1|1]);
            mx[idx] = max(mx[idx<<1], mx[idx<<1|1]);
        }
        int findRightmost(int idx, int l, int r, int ql, int qr, int target) {
            if (ql > r || qr < l) return -1;
            if (mn[idx] > target || mx[idx] < target) return -1;
            if (l == r) return l;
            push(idx);
            int m = (l + r) >> 1;
            int res = findRightmost(idx<<1|1, m+1, r, ql, qr, target);
            if (res != -1) return res;
            return findRightmost(idx<<1, l, m, ql, qr, target);
        }
        int point(int idx, int l, int r, int pos) {
            if (l == r) return mn[idx];
            push(idx);
            int m = (l + r) >> 1;
            if (pos <= m) return point(idx<<1, l, m, pos);
            return point(idx<<1|1, m+1, r, pos);
        }
    };

public:
    int longestBalanced(vector<int>& nums) {
        int n = nums.size();
        if (!n) return 0;

        vector<int> vals = nums;
        sort(vals.begin(), vals.end());
        vals.erase(unique(vals.begin(), vals.end()), vals.end());

        int k = vals.size();
        vector<vector<int>> pos(k);
        for (int i = 0; i < n; i++) {
            int id = lower_bound(vals.begin(), vals.end(), nums[i]) - vals.begin();
            pos[id].push_back(i);
        }

        vector<int> sign(n);
        for (int i = 0; i < n; i++)
            sign[i] = (nums[i] & 1) ? -1 : 1;

        vector<int> C(n, 0);
        for (int id = 0; id < k; id++)
            if (!pos[id].empty())
                C[pos[id][0]] += sign[pos[id][0]];

        vector<int> pref(n);
        pref[0] = C[0];
        for (int i = 1; i < n; i++)
            pref[i] = pref[i - 1] + C[i];

        Seg seg(n);
        seg.build(1, 0, n - 1, pref);

        vector<int> idxPtr(k, 0);
        int res = 0;

        for (int l = 0; l < n; l++) {
            if (n - l <= res) break;

            int target = l ? seg.point(1, 0, n - 1, l - 1) : 0;
            int r = seg.findRightmost(1, 0, n - 1, l, n - 1, target);
            if (r != -1)
                res = max(res, r - l + 1);

            int id = lower_bound(vals.begin(), vals.end(), nums[l]) - vals.begin();
            int pidx = idxPtr[id];
            int p = pos[id][pidx];
            int q = (pidx + 1 < (int)pos[id].size()) ? pos[id][pidx + 1] : n;
            if (p <= q - 1)
                seg.add(1, 0, n - 1, p, q - 1, -sign[p]);
            idxPtr[id]++;
        }

        return res;
    }
};