package ai0825;

import java.util.Arrays;
import java.util.Collections;

public class ReverseList {
    public static void main(String[] args) {
        String[] nctwish = {"시온", "리쿠", "유우시", "재희", "료", "사쿠야"};
        System.out.println("원본:" + Arrays.toString(nctwish));
        Collections.reverse(Arrays.asList(nctwish));
        System.out.println("반전(역순):" + Arrays.toString(nctwish));
    }
}
