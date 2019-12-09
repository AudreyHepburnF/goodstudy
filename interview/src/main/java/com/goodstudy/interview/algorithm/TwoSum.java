package com.goodstudy.interview.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author congyaozhu
 * @date 2019-11-18 19:59
 * @description
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] result = twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println("[" + result[0] + " , " + result[1] + "]");
    }

    public static int[] twoSum(int[] nums, int target) {
        // 1. 定义用于返回存在元素的下标数组
        int[] result = new int[2];

        // 2. 判断nums是否为空
        if(nums == null || nums.length <=1){
            return result;
        }
        // 4. 定义一个Map，用来存储遍历的数及下标位置
        Map<Integer , Integer> map = new HashMap<>();
        // 3. 循环遍历nums
        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];

            // 5. 定义变量，用于记录 和为目标值的数
            int val = target - num;

            // 6. 判断要找的值，是否在map中存在
            if(map.containsKey(val)){
                result[0] = i;
                result[1] = map.get(val);
                return result;
            }else {
                map.put(num,i);
            }
        }
        return result;
    }
}
