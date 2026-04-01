class Solution {
public:
    vector<int> survivedRobotsHealths(vector<int>& positions, vector<int>& healths, string directions) {
        int n = positions.size();
        
        vector<tuple<int,int,char,int>> robots;
        
        for(int i = 0; i < n; i++){
            robots.push_back({positions[i], healths[i], directions[i], i});
        }
        
        // sort by position
        sort(robots.begin(), robots.end());
        
        stack<int> st; // store indices of robots
        vector<int> currHealth = healths;
        
        for(int i = 0; i < n; i++){
            auto [pos, h, dir, idx] = robots[i];
            
            if(dir == 'R'){
                st.push(i);
            } 
            else {
                while(!st.empty()){
                    auto &[pos2, h2, dir2, idx2] = robots[st.top()];
                    
                    if(currHealth[idx2] > currHealth[idx]){
                        currHealth[idx2]--;
                        currHealth[idx] = 0;
                        break;
                    }
                    else if(currHealth[idx2] < currHealth[idx]){
                        currHealth[idx]--;
                        currHealth[idx2] = 0;
                        st.pop();
                    }
                    else{
                        currHealth[idx2] = 0;
                        currHealth[idx] = 0;
                        st.pop();
                        break;
                    }
                }
            }
        }
        
        vector<int> result;
        for(int i = 0; i < n; i++){
            if(currHealth[i] > 0){
                result.push_back(currHealth[i]);
            }
        }
        
        return result;
    }
};