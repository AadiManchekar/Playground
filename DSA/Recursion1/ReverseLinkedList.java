package Recursion1;

import Base.ListNode;

public class ReverseLinkedList {
    // Leetcode: https://leetcode.com/explore/learn/card/recursion-i/251/scenario-i-recurrence-relation/2378/

    // iterative approach to reverse a linked list
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public ListNode iterativeReverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode current = head;
        ListNode prev = null;

        while (current != null) {
            ListNode newNode = current.next;
            current.next = prev;
            prev = current;
            current = newNode;
        }
        return prev;
    }

    // recursive approach to reverse a linked list
    // Time Complexity: O(n)
    // Space Complexity: O(n) due to recursion stack
    private ListNode newHead;
    
    public ListNode reverseList(ListNode head) {
        reverse(head);      
        return newHead;
    }
    
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            newHead = head;
            return head;
        }
        
        reverse(head.next).next = head;
        // break the cyclic loop
        head.next = null;
        return head;
    }
}
