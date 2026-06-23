class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        #b -> a
        # construct DAG
        # 
        graph = defaultdict(list)
        in_degree = defaultdict(int)
        for a, b in prerequisites:
            graph[b].append(a)
            in_degree[a] += 1
        #  bfs, stop if cannot find any 0 indegree course
        q = deque(i for i in range(numCourses) if in_degree[i] == 0)
        count = 0
        while q:
            c = q.popleft()
            count += 1
            neighbours = graph[c]
            for neighbour in neighbours:
                in_degree[neighbour] -= 1
                if in_degree[neighbour] == 0:
                    q.append(neighbour)
        
        return count == numCourses