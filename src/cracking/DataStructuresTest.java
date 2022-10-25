package cracking;

public class DataStructuresTest {
    public static void main(String[] args) {
        testOneAway();
    }

    public static void print2dMatrix(byte[][] array) {
        for(int j=0; j<array.length; j++) {
            for(int i=0; i<array[0].length; i++) {
                System.out.print("" + array[j][i]);
                if(i<array[0].length - 1) {
                    System.out.print(",\t");
                } else {
                    System.out.print("|\t");
                }
            }
            System.out.println("");
        }
    }

    public static void print2dMatrix(byte[][][] array) {
        for(int j=0; j<array.length; j++) {
            for(int i=0; i<array[0].length; i++) {
                for(int k=0; k<array[0][0].length; k++) {
                    System.out.print("" + array[j][i][k]);
                    if(k<array[0][0].length - 1) {
                        System.out.print(",\t");
                    } else {
                        System.out.print("|\t");
                    }
                }
            }
            System.out.println("");
        }
    }

    public static void test1() {
        byte[][][] a = new byte[][][]{
                {{0,2,3,4},{1,2,3,4},{2,2,3,4},{3,2,3,4}},
                {{0,0,3,4},{0,1,3,4},{0,2,3,4},{0,3,3,4}},
                {{0,0,0,4},{0,0,1,4},{0,0,2,4},{0,0,3,4}},
                {{0,0,0,0},{0,0,1,1},{0,0,2,2},{0,0,3,3}}
        };
        print2dMatrix(a);
        DataStructures.rotateMatrix(a);
        System.out.println("");
        print2dMatrix(a);
    }

    public static void test2() {
        byte[][] a = new byte[][]{
                {1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,0},
                {1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,0,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,0,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1}
        };
        print2dMatrix(a);
        DataStructures.zeroMatrix(a);
        System.out.println("");
        print2dMatrix(a);
    }

    public static void testOneAway() {
        System.out.println(DataStructures.oneAway("abcd", "bbcd"));
        System.out.println(DataStructures.oneAway("acd", "acd"));
        System.out.println(DataStructures.oneAway("abcdd", "abcd"));
        System.out.println(DataStructures.oneAway("abcdd", "bcd"));
    }

}
