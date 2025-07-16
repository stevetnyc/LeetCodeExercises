import java.util.*;

public class Heap {


    static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int rtnVal = 0;

        for (int i = 0; i < nums.length; i++) {
            maxHeap.add(nums[i]);
        }

        for (int b = 0; b < k; b++) {
            rtnVal = maxHeap.poll();
        }

        return rtnVal;
    }

    static double distance(double[] point) {
        return Math.sqrt((point[0] * point[0]) + (point[1] * point[1]));
    }

    static void printArr(int[][] arr) {

        for (int i = 0; i <arr.length; i++) {
            for (int b = 0; b < arr[i].length; b++) {
                System.out.println("arr["+i+"][" + b + "] = " + arr[i][b]);
            }
        }
    }
    static void printHeap(PriorityQueue<double[]> minHeap) {
        int n = minHeap.size();
        for (int i = 0; i < n; i++) {
            double[] val = minHeap.poll();

            System.out.println("x: " + val[0] + "; y: " + val[1] + " | Distance: " + distance(val));
        }

    }


    static int[][] kClosest(int[][] points, int k) {

//        PriorityQueue<int[]> minHeap = new
//                PriorityQueue<>((a, b) -> (int)(Math.sqrt((a[0] * a[0]) + (a[1] * a[1])) - Math.sqrt((b[0] * b[0]) + (b[1] * b[1]))));

        PriorityQueue<double[]> minHeap = new
                PriorityQueue<>((a, b) -> Double.compare(distance(a), distance(b)));
        for (int i = 0; i < k; i++) {
            minHeap.add(new double[]{(double)points[i][0], (double)points[i][1]});
        }

        for (int b = k; b < points.length; b++) {

            double z = distance(new double[]{(double)points[b][0], (double)points[b][1]});
            minHeap.add(new double[]{(double)points[b][0], (double)points[b][1]});
        }

//        printHeap(minHeap);
        int[][] rtnVal = new int[k][2];
        for (int m = 0; m < k; m++) {

            double[] tmp  = minHeap.poll();

            rtnVal[m][0] = (int)tmp[0];
            rtnVal[m][1] = (int)tmp[1];

        }

        return rtnVal;

    }

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        Integer[] result = new Integer[k];
        PriorityQueue<Integer[]> diffResults =
                new PriorityQueue<>((a,b) -> {
                    if(a[0] != b[0]) {
                        return Integer.compare(b[0], a[0]);
                    }

                    if(a[1] != b[1]) {
                        return Integer.compare(b[1], a[1]);
                    }

                    return 0;
                });


        for (int i = 0; i < arr.length; i++) {

            Integer[] tmpRes = new Integer[2];
            tmpRes[0] = Math.abs(arr[i] - x);
            tmpRes[1] = arr[i];
            if (diffResults.size() < k) {
                diffResults.add(tmpRes);
            } else {
                Integer[] tmpPeek = diffResults.peek();
                if (tmpRes[0] < tmpPeek[0] || (tmpRes[0] == tmpPeek[0] && tmpRes[1] < tmpPeek[1])) {
                    diffResults.poll();
                    diffResults.add(tmpRes);
                }
            }
        }

        for (int i = 0; i < k; i++) {
            Integer[] tmpres = diffResults.poll();
            result[i] = tmpres[1];
        }
        Arrays.sort(result);

        List<Integer> resLIst = Arrays.asList(result);
        return resLIst;
    }

    public static void main(String[] args) {
//        int[] arr = new int[] {3,2,1,5,6,4};
//        int[] arr = new int[] {3,2,3,1,2,4,5,5,6};
//        int k = 4;
//
//        System.out.println(findKthLargest(arr, k));

//        int[][] arr = new int[][] {{3,3},{5,-1},{-2,4}};
//        int k = 2;

        //        int[][] arr = new int[][] {{3,3},{5,-1},{-2,4}};
//        int[][] arr = new int[][] {{2,10},{-9,-9},{0,8},{-2,-2},{8,9},{-10,-7},{-5,2},{-4,-9}};
//        int k = 7;
//
//       printArr(kClosest(arr, k));

        int[] arr = {0,0,1,2,3,3,4,7,7,8};
        int k = 3;
        int x = 5;

        Utils.printList(findClosestElements(arr, k, x));

    }
}
