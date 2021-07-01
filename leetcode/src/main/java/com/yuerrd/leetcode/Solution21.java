package com.yuerrd.leetcode;

/**
 * @author yuerrd
 */
public class Solution21 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = new ListNode();
        ListNode tmp = head;
        while (l1 != null && l2 != null) {
            if (l1 == null) {
                tmp.next = l2;
                break;
            }
            if (l2 == null) {
                tmp.next = l1;
                break;
            }
            if (l1.val < l2.val) {
                tmp.val = l1.val;
                l1 = l1.next;
            } else {
                tmp.val = l2.val;
                l2 = l2.next;
            }
            if (l1 == null || l2 == null) {
                tmp = tmp.next = l1 == null ? l2 : l1;
            } else {
                tmp = tmp.next = new ListNode();
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Solution21 solution21 = new Solution21();
//        l1 = [1,2,4],
//        l2 = [1,3,4]
        ListNode l1 = null;
        ListNode l2 = null;

        l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
//        l2 = new ListNode();
        ListNode result = solution21.mergeTwoLists(l1, l2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
