from collections import defaultdict
class TimeMap:

    def __init__(self):
        self.timemap = defaultdict(list)

    def set(self, key: str, value: str, timestamp: int) -> None:
        self.timemap[key].append([timestamp, value])

    def get(self, key: str, timestamp: int) -> str:
        if key not in self.timemap:
            return ""
        
        # 1. 过滤出所有 小于或等于 目标 timestamp 的记录
        valid_pairs = [p for p in self.timemap[key] if p[0] <= timestamp]
        
        # 2. 如果没有一条符合条件的记录，返回空字符串
        if not valid_pairs:
            return ""
            
        # 3. 找出这些记录中 timestamp 最大的那一个（根据 p[0] 比较）
        best_pair = max(valid_pairs, key=lambda p: p[0])
        return best_pair[1]