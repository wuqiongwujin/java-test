package Algorithm.array;

/**
 * @Description
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
 *
 * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 *
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * @date 2019/11/17
 */
public class PivotIndex {
    public int pivotIndex(int[] nums) {
        if (nums==null || nums.length < 3) {
            return -1;
        }
        int privotIndex = 0;
        int leftSum = 0;
        int rightSum = 0;
        for (int i=1; i<nums.length; i++) {
            rightSum += nums[i];
        }
        for (int i=0; i<=nums.length-1; i++) {
            if (i > 0) {
                leftSum += nums[i-1];
                rightSum -= nums[i];
            }
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 7, 3, 6, 5, 6};
        PivotIndex p = new PivotIndex();
        System.out.println(p.pivotIndex(nums));
    }
}
