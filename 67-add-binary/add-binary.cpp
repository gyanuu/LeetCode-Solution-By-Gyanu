class Solution {
public:
    std::string addBinary(std::string a, std::string b) {
        std::string ans;
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry) {
            // Add current bits and carry
            if (i >= 0) {
                carry += a[i] - '0'; // Convert char to int
                i--;
            }
            if (j >= 0) {
                carry += b[j] - '0'; // Convert char to int
                j--;
            }

            // Append the current sum bit (carry % 2) to the result string
            ans += (carry % 2) + '0'; // Convert int to char

            // Update the carry for the next iteration (carry / 2)
            carry /= 2;
        }

        // The result is built in reverse order, so reverse it at the end
        std::reverse(ans.begin(), ans.end());
        return ans;
    }
};
