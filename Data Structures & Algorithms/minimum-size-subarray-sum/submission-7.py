class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        total_sum = 0
        res = float("inf")
        left = 0
        for i in range(len(nums)):
            total_sum += nums[i]
            while total_sum >= target:
                res = min(res, i-left+1)
                total_sum -= nums[left]
                left += 1
                
        return 0 if  res == float("inf") else int(res)