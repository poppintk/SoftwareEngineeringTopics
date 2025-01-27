class Solution {
    public int lengthOfLIS(int[] nums) {
        // state: index
        // dp[i]: length of longest increasing up to index i
        // choice: 
        // if (nums[i] > nums[j]) 
        //  dp[i] = Math.max(dp[i], dp[j] + 1);
        //base case:
        // dp 数组应该全部初始化为 1，因为子序列最少也要包含自己，所以长度最小为 1.
        // base case already inside
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int ans = 0;
        for(int k = 0; k < dp.length; k++){
            ans = Math.max(dp[k], ans);
        }
        
        return ans;
    }
}