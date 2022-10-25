package largestsum;

public class MyTest {
    public static void main(String[] args) {
        int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Maximum contiguous sum is " + maxSubArraySum(a));
    }
    static int sum(int a[], int s, int e) {
        int sum=0;
        while (s<=e) {
            sum += a[s++];
        }
        return sum;
    }
    static int maxSubArraySum(int a[]) {
        int maxsum;
        int i = 0, j = a.length - 1;
        int mod = 0;
        maxsum = sum(a, i, j);

        while (i<j) {
            if(a[i]<=a[j]) {
                if(maxsum < (maxsum - mod - a[i])) maxsum -= a[i]; else mod += a[i];
                i++;
            } else {
                if(maxsum < (maxsum - mod - a[j])) maxsum -= a[j]; else mod += a[j];
                j--;
            }
        }

        return maxsum;
    }
}