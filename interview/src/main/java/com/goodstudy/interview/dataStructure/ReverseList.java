package com.goodstudy.interview.dataStructure;

/**
 * 反转一个单链表。
 * 示例：
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class ReverseList {

    public ListNode reverse(ListNode head) {
        // 设置前一个节点为null
        ListNode prev = null;
        // 设置head为当前节点
        ListNode curr = head;
        while(head != null){
            ListNode tempNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNode;
        }
        return prev;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}