package spiralorder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyTest {
    public static void main(String[] args) {
        System.out.println(spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        int dir = 0, i = 0, j = 0;
        while(visited.size() < matrix.length * matrix[0].length) {
            String loc = "" + i + "," + j;
            if (i>=0 && j>=0 && j<matrix.length && i<matrix[0].length && visited.add(loc)) {
                ans.add(matrix[j][i]);
            } else {
                if(dir == 0) {
                    i--;
                } else if(dir == 1) {
                    j--;
                } else if(dir == 2) {
                    i++;
                } else if(dir == 3) {
                    j++;
                }
                dir = (++dir) % 4;
            }
            if(dir == 0) {
                i++;
            } else if(dir == 1) {
                j++;
            } else if(dir == 2) {
                i--;
            } else if(dir == 3) {
                j--;
            }
        }
        return ans;
    }
}
