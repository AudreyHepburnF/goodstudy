package com.personalstudy.goodstudy;

import java.util.Arrays;

/**
 * @author : <a href="mailto:congyaozhu@ebnew.com">congyaozhu</a>
 * @Date : Created in  9:05 2019-08-14
 * @Description :   插入排序
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[]  arr = {12,1,3,46,5,0,-3,12,35,16};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        for(int i = 1 ; i<arr.length ; i++){
            // 暂存数据
            int insertValue = arr[i];
            // 前一个索引
            int j = i-1;
            // 元素区与有序区从右向左依次赋值
            for(; j>=0&&insertValue<arr[j];j--){
                arr[j+1] = arr[j];
            }
            // 将暂存数据插入
            arr[j+1] = insertValue;
        }
    }
}
