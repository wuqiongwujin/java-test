package Algorithm.dfs;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 目标和
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 一共有5种方法让最终目标和为3。
 * @date 2019/11/16
 */
public class TargetSum {

    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        AtomicInteger K = new AtomicInteger(0);
        sum(nums, 0, 0, S, K);
        return K.intValue();
    }

    private void sum(int[] nums, int index, int sum, int S, AtomicInteger K) {
        int cur = nums[index];
        if (index == nums.length-1) {
            if ((sum + cur) == S) {
                K.getAndAdd(1);
            }
            if((sum - cur ) == S) {
                K.getAndAdd(1);
            }
            return;
        }
        int addIndex = ++index;
        int subIndex = addIndex;
        sum(nums, addIndex, sum+cur, S, K);
        sum(nums, subIndex, sum-cur, S, K);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,0};
        int S = 1;
        TargetSum t = new TargetSum();
        System.out.println(t.findTargetSumWays(nums, S));
    }

}
