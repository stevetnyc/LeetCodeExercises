

import java.util.*;

public class GraphExercises {


    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int courseTaken = 0;

        Queue<Integer> courses = new ArrayDeque<>();
        int[] inDegrees = new int[numCourses];
        HashMap<Integer, List<Integer>> preqMap = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            int course1 = prerequisites[i][0];
            int course2 = prerequisites[i][1];
            inDegrees[course1] += 1;
            List<Integer> tmpList = new ArrayList<>();
            if (preqMap.containsKey(course2)) {
                tmpList = preqMap.get(course2);
                tmpList.add(course1);
            } else {
                tmpList.add(course1);
            }
            preqMap.put(course2, tmpList);
        }


        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) courses.add(i);
        }

        while (!courses.isEmpty()) {
            int currCourse = courses.poll();
            courseTaken++;
            List<Integer> tmpList = preqMap.getOrDefault(currCourse, null);
            if (tmpList != null) {
                for (int i: tmpList) {
                    inDegrees[i] -= 1;
                    if (inDegrees[i] == 0) {
                        courses.add(i);
                    }
                }
            }

        }

        return courseTaken == numCourses;
    }
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0 && numCourses == 1) return new int[1];

        int[] result = new int[numCourses];
        int[] numPreqs = new int[numCourses];
        HashMap<Integer, List<Integer>> preqMap = new HashMap<>();
        Queue<Integer> courseAvailable = new ArrayDeque<>();

        for (int i = 0; i < prerequisites.length; i++) {
            int course1 = prerequisites[i][0];
            int course2 = prerequisites[i][1];

            numPreqs[course1]++;
            List<Integer> tmpList = new ArrayList<>();
            if (preqMap.containsKey(course2)) {
                tmpList = preqMap.get(course2);
                tmpList.add(course1);
            } else {
                tmpList.add(course1);
            }
            preqMap.put(course2, tmpList);
        }

        for (int i = 0; i < numCourses; i++){
            if (numPreqs[i] == 0) {
                courseAvailable.add(i);
            }
        }

        int courseTaken = 0;
        while (!courseAvailable.isEmpty()) {
            int tmpCourse = courseAvailable.poll();

            result[courseTaken] = tmpCourse;
            courseTaken++;

            if (preqMap.containsKey(tmpCourse)) {
                for (int reqCourse : preqMap.get(tmpCourse)) {
                    numPreqs[reqCourse]--;
                    if (numPreqs[reqCourse] == 0) courseAvailable.add(reqCourse);
                }
            }
        }

       if (courseTaken == numCourses) {
           return result;
       } else {
           return new int[0];
       }
    }

    public static void cg_rcrs(Node orig, Node copy, Set<Node> visited) {
        if (visited.contains(orig)) return;

        visited.add(orig);
        copy.val = orig.val;

        for (Node neighbor: orig.neighbors) {
            copy.neighbors.add(neighbor);
            Node neigborCopy = new Node();
            neigborCopy.val = neighbor.val;
            cg_rcrs(neighbor, neigborCopy, visited);
        }
    }

    public static Node cloneGraph(Node node) {
        if (node == null) return node;
        Node result = new Node();
        Set<Node> visited = new HashSet<>();

        cg_rcrs(node, result, visited);

        return result;
    }

    public static void main(String[] args) {

//        [[2,4],[1,3],[2,4],[1,3]]
//        List<List<Integer>> nodeList = new ArrayList<>();
//        List<Integer> tmp1 = Arrays.asList(new Integer[] {2, 4});
//        nodeList.add(tmp1);
//        List<Integer> tmp2 = Arrays.asList(new Integer[] {1, 3});
//        nodeList.add(tmp2);
//        List<Integer> tmp3 = Arrays.asList(new Integer[] {2, 4});
//        nodeList.add(tmp3);
//        List<Integer> tmp4 = Arrays.asList(new Integer[] {1, 3});
//        nodeList.add(tmp4);




//        int[][] preReqs = {{1,0}, {0, 1}};
//        int[][] preReqs = {{1,0}, {2,0}, {3,1}, {3,2}};
//        int numCourses = 4;
//
//        Utils.printArr(findOrder(numCourses, preReqs));
    }
}
