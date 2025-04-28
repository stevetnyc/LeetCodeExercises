import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;

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

    public static void main(String[] args) {
//        int[][] preReqs = {{1,0}, {0, 1}};
        int[][] preReqs = {{1,0}, {2,0}, {3,1}, {3,2}};
        int numCourses = 4;

        Utils.printArr(findOrder(numCourses, preReqs));
    }
}
