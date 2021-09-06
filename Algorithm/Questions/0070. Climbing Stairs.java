class Solution {
    public int climbStairs(int n) {
        // let dp[i] be number ways to climb to start i
        // dp[i] = dp[i - 1] + dp[i - 2];
        
        int[] dp = new int[n + 1];
        
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
}