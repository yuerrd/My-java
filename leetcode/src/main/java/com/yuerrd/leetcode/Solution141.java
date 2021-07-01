package com.yuerrd.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yuerrd
 */
public class Solution141 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static boolean hasCycle(ListNode head) {
      if (head == null ||head.next == null){
          return false;
      }
      ListNode slowNode = head;
      ListNode fastNode = head.next;
      while (slowNode != fastNode){
          if (fastNode == null || fastNode.next == null){
              return false;
          }
          slowNode = slowNode.next;
          fastNode = fastNode.next.next;
      }
      return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode two = new ListNode(2);
        head.next = two;
        two.next = new ListNode(0).next = new ListNode(-4).next = two;
        System.out.println(hasCycle(head));
    }
}
