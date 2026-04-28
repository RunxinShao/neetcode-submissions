from typing import List


def get_index_of_seven(nums: List[int]) -> int:
    for i,n in enumerate(nums):
        if n == 7:
            return i
    return -1


def get_dist_between_sevens(nums: List[int]) -> int:
    count = 0
    first = -1
    for i,n in enumerate(nums):
        if n == 7 and count == 0:
            first = i
            count += 1
        elif n == 7 and count == 1:
            return i - first
    return first





# do not modify below this line
print(get_index_of_seven([1, 2, 3, 4, 5, 6, 7, 8, 9]))
print(get_index_of_seven([1, 2, 3, 4, 5, 6, 8, 9]))
print(get_index_of_seven([2, 4, 7, 5, 7, 8, 4, 2]))

print(get_dist_between_sevens([1, 2, 7, 4, 5, 6, 7, 8, 9]))
print(get_dist_between_sevens([2, 7, 7, 7, 8]))
print(get_dist_between_sevens([7, 4, 8, 4, 2, 7]))
