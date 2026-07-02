class Solution:
    def sortedSquares(self, nums: List[int]) -> List[int]:
        res = []
        for num in nums:
            res.append(num*num)
        return sorted(res)