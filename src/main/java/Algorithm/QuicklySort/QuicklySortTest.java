package Algorithm.QuicklySort;

/**
 * @author Cain
 * @Package Algorithm.QuicklySort
 * @Description
 * @date 2018/8/10
 */
public class QuicklySortTest {

    private static int[] array = {2,9,5,7,1,3,4,8,0,6};

    public static void sort(int[] array, int i, int j){
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
        sort(array, 0, array.length-1);
        for(int i : array){
            System.out.println(i);
        }
    }
}
