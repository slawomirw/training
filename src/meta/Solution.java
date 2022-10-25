package meta;

/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public static void main(String[] args) {
        int n=6;
        int[][] arr = spiral(n);
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /*Situation, Task, Action,  */
    private static boolean oneEditApart(String s1, String s2) {
        if(s1.length() + s2.length() == 1) {
            return true;
        } else if (Math.abs(s1.length() - s2.length())>1) {
            return false;
        }
        int fix1 = 0, fix2 = 0;
        for(int i=0; i<Math.min(s1.length()-fix1, s2.length()-fix2); i++) {
            if(s1.charAt(i + fix1) != s2.charAt(i + fix2)) {
                if(fix1==0 && fix2==0) {
                    if(s1.length() > s2.length()) {
                        fix2 = 1;
                    } else if(s1.length() < s2.length()) {
                        fix1 = 1;
                    } else {
                        fix2 = 1;
                        fix1 = 1;
                    }
                    i--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private static int spiral(int[][] arr, int step, int val) {
        for(int c = step; c < arr[0].length - step; c++) {
            arr[step][c] = val++;
        }
        for(int r = step + 1; r < arr.length - step; r++) {
            arr[r][arr.length - step - 1] = val++;
        }
        for(int c = arr.length - step - 2; c >= step; c--) {
            arr[arr.length - step - 1][c] = val++;
        }
        for(int r = arr.length - step - 2; r > step; r--) {
            arr[r][step] = val++;
        }
        return val;
    }

    private static void spiral(int[][] arr) {
        int i=0, j=0;
        int di=0, dj=1;
        int val = 1;
        boolean passed = true;

        while(passed) {
            passed = false;
            while(i>=0 && j>=0 && i<arr.length && j<arr.length && arr[i][j] == 0) {
                arr[i][j] = val++;
                i = i + di;
                j = j + dj;
                passed = true;
            }
            if(di==0 && dj==1) { di=1; dj=0; i=i+di; j--; continue;}
            if(di==1 && dj==0) { di=0; dj=-1; j=j+dj; i--; continue;}
            if(di==0 && dj==-1) { di=-1; dj=0; i=i+di; j++; continue;}
            if(di==-1 && dj==0) { di=0; dj=1; j=j+dj; i++; }
        }
    }

    public static int[][] spiral(int n) {
        if(n==0) {
            return new int[0][0];
        } else if (n==1) {
            return new int[][]{{1}};
        }

        int[][] arr = new int[n][n];

        //int val = 1;
        //for(int i=0; i<=(n + 1) / 2; i++) {
        //  val = spiral(arr, i, val);
        //}
        spiral(arr);

        return arr;
    }
}
