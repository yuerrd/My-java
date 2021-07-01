package com.yuerrd.leetcode;

/**
 * @author yuerrd
 */
public class Solution19 {


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        while (n > 0) {
            first = first.next;
            n--;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public static void main(String[] args) {
        Solution19 solution19 = new Solution19();
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        ListNode node = new ListNode(1, new ListNode(2));
//        ListNode node = new ListNode(1);

        ListNode res = solution19.removeNthFromEnd(node, 1);
        System.out.println();
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
