package Algorithm.hash;

/**
 * @Description
 * @date 2019/11/26
 */
public class DuplicateNums {

    public boolean containsDuplicate(int[] nums) {
        Integer n[][] = new Integer[nums.length][nums.length];
        for (int i=0;i<nums.length;i++) {
            int a = Math.abs(nums[i]%nums.length);
            for (int j=0;j<n[a].length;j++) {
                if (n[a][j] != null && nums[i]==n[a][j]) {
                    return true;
                } else if(n[a][j] == null) {
                    n[a][j] = nums[i];
                    break;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int nums[] = new int[]{2,14,18,22,22};
        DuplicateNums n = new DuplicateNums();
        System.out.println(n.containsDuplicate(nums));
    }
}
