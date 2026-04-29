from typing import List


def reverse_list(arr: List[int]) -> List[int]:
    stack = []
    for i in range(len(arr)):
        stack.append(arr.pop())
    return stack


# do not modify below this line
print(reverse_list([1, 2, 3]))
print(reverse_list([3, 2, 1, 4, 6, 2]))
print(reverse_list([1, 9, 7, 3, 2, 1, 4, 6, 2]))
