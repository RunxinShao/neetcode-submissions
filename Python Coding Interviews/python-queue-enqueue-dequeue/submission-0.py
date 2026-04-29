
from collections import deque


def rotate_list(arr: list[int], k: int) -> deque[int]:
    q = deque(arr)

    while k > 0:
        q.append(q.popleft())
        k -= 1
    return q
    



# do not modify below this line
print(rotate_list([1, 2, 3, 4, 5], 0))
print(rotate_list([1, 2, 3, 4, 5], 1))
print(rotate_list([1, 2, 3, 4, 5], 2))
print(rotate_list([1, 2, 3, 4, 5], 3))
print(rotate_list([1, 2, 3, 4, 5], 4))
print(rotate_list([1, 2, 3, 4, 5], 5))
