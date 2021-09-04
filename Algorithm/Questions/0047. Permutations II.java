class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;
        
        Arrays.sort(nums);
        
        boolean[] visited = new boolean[nums.length];
        backtracking(nums, visited, ans, new ArrayList<>());
        
        return ans;
    }
    
    private void backtracking(int[] nums, boolean[] visited, List<List<Integer>> ans, List<Integer> path) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            if (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue; // Not skip same identical number to choose next different numbers
            visited[i] = true;
            path.add(nums[i]);
            backtracking(nums, visited, ans, path);
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }
}