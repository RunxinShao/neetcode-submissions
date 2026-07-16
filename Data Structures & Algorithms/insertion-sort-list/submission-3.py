class Solution:
    def insertionSortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        cur = head
        dummy = ListNode(0)
        # dummy.next = head  <-- Removed to start with an empty sorted list
        while cur:
            prev = dummy
            while prev.next and prev.next.val < cur.val:
                prev = prev.next
            nxt = cur.next

           
            cur.next = prev.next
            prev.next = cur

            cur = nxt
        return dummy.next