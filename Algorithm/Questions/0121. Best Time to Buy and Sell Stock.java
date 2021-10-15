class Solution {
    // single day to buy one stock and choosing a different day in the future to sell that stock. (only one transaction)
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            profit = Math.max(prices[i] - min, profit);
        }
        
        return profit;
    }
}