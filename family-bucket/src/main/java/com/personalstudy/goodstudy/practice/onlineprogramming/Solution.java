package com.personalstudy.goodstudy.practice.onlineprogramming;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  15:05 2019-06-20
 * @Description :在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
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
}
