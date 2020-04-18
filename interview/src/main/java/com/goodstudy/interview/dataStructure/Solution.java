package com.goodstudy.interview.dataStructure;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  15:05 2019-06-20
 * @Description :
 */
public class Solution {

    public static void main(String[] args) {
        int arrays[][]={{1,4,7,9},{2,7,8,11},{4,8,12,19}};
        if(Find(12,arrays)){
            System.out.println("包含该字");
        }else{
            System.out.println("不包含本字");
        }
    }

    /**
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     *  * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *  * 解题思路： 循环数组长度，行从0开始，列从行最后一位开始
     *  *          判断给定的值，是否大于当前行的最大值，如果大于，直接行++，否则列--
     * @param target
     * @param array
     * @return
     */
    public static boolean Find(int target, int [][] array) {
        int row = 0 ;
        int col = array[0].length-1;
        while(row <= array.length-1 && col >=0){
            if(target == array[row][col]){
                return true;
            }else if(target > array[row][col]){
                row++;
            }else{
                col--;
            }
        }
        return false;
    }

    /**
     * 删除链表的倒数第N个节点
     *      给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     *
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * @param head
     * @param n
     * @return
     */
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        ListNode dummy = new ListNode(0);
//        dummy.next = head;
//
//        ListNode first = dummy;
//        ListNode second = dummy;
//
//        // first先移动n次，与second保留n个间隔
//        for (int i = 1; i <= n+1; i++) {
//            first = first.next;
//        }
//
//        // 判断first是否是尾节点
//        while (first != null){
//            first = first.next;
//            second = second.next;
//        }
//
//        // 将second的next节点与second.next.next节点关联
//        second.next = second.next.next;
//        return dummy.next;
//
//    }
}
