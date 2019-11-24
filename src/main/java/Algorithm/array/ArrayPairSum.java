package Algorithm.array;

/**
 * @Description
 * @date 2019/11/19
 */
public class ArrayPairSum {

    public int arrayPairSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        sort(nums, 0, nums.length - 1);
        int firstIndex = 0;
        int sendIndex = firstIndex + 1;
        int sum = 0;
        for (;firstIndex <= nums.length - 2; firstIndex=sendIndex+1,sendIndex=firstIndex+1) {
            sum += Math.min(nums[firstIndex], nums[sendIndex]);
        }
        return sum;
    }

    private void sort(int[] array, int i, int j) {
        if(i >= j){
            return;
        }
        int startIndex = i,endIndex = j, k =array[i];
        while(i < j){
            while(i < j && array[j] >= k){
                j--;
            }
            if(i<j){
                int t = array[j];
                array[j] = array[i];
                array[i] = t;
                i++;
            }

            while(i < j && array[i] <= k){
                i++;
            }
            if(i<j){
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
                j--;
            }
        }

        if(startIndex < i){
            sort(array, startIndex, i-1);
        }
        if(j < endIndex){
            sort(array, j+1, endIndex);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,4,3,2};
        ArrayPairSum s = new ArrayPairSum();
        System.out.println(s.arrayPairSum(nums));
    }
}
