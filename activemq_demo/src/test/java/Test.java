import java.util.Arrays;
import java.util.Random;

/**
 * @Author: lnj999
 * @Description:
 * @Time: created on 2020/10/16 7:15
 */
public class Test {
    public static void main(String[] args) {
//        int arr[] = {10, 21, 1, 4};
//        Arrays.sort(arr);
//        System.out.println(Arrays.binarySearch(arr, 1));
        Random rd = new Random(9);
        Random rd2 = new Random(9);
        for (int i = 0; i < 5; i++) {
            System.out.println(rd.nextInt(10));
            System.out.println("***");
            System.out.println(rd2.nextInt(10));
        }

    }
}
