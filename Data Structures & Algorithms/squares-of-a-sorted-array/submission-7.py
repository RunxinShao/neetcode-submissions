class Solution:
    def sortedSquares(self, nums: List[int]) -> List[int]:
        l = 0
        r = len(nums)-1
        res = [0] * len(nums)
        index = len(res)-1
        while l <= r:
            if nums[l] * nums[l] < nums[r] * nums[r]:
                res[index] = nums[r] * nums[r]
                index -= 1
                r -= 1
            else:
                res[index] = nums[l] * nums[l]
                index -= 1
                l += 1
        return res