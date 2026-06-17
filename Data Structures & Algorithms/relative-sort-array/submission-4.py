class Solution:
    def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
        counter = Counter(arr1)
        res =[]
        for x in arr2:
            if x in arr1:
                res.extend([x] * counter[x])
                del counter[x]
        
        for x in sorted(arr1):
            if x not in arr2:
                res.append(x)
        return res