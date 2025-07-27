package Recursion1;

import Base.ListNode;

// LeetCode: https://leetcode.com/explore/learn/card/recursion-i/250/principle-of-recursion/1681/
public class SwapNodes {
    public ListNode swapPairs(ListNode head) {
        if(head == null) {
            return null;
        }
        
        if(head.next == null) {
            return head;
        }
        
        ListNode node2 = head.next;
        
        // save reference to node2's next
        ListNode node3 = node2.next;
        
        // swap
        node2.next = head;
        head.next = swapPairs(node3);
        
        return node2;
    }
}

