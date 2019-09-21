package bfs.topological_sort.course_schedule;

import java.util.*;

public class Solution {
    // <course, list<prerequisites>
    private Map<Integer, Set<Integer>> prerequisitesMap;

    // <course, indegree>
    private Map<Integer, Integer> indegreeMap;

    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 ) {
            return true;
        }
        // <prerequisity, set<course>
        prerequisitesMap = new HashMap<>();

        // <course, indegree>
        indegreeMap = new HashMap<>();

        buildMaps(numCourses, prerequisites);

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegreeMap.get(i) == 0) {
                queue.offer(i);
            }
        }

        List<Integer> finished = new ArrayList<>();

        while (!queue.isEmpty()) {
            Integer course = queue.poll();
            finished.add(course);

            for (Integer nextCourse : prerequisitesMap.get(course)) {
                int curIndegree = indegreeMap.get(nextCourse);
                curIndegree--;
                indegreeMap.put(nextCourse, curIndegree);

                if (curIndegree == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        return finished.size() == numCourses;
    }



    private void buildMaps(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < numCourses; i++) {
            indegreeMap.put(i, 0);
            prerequisitesMap.put(i, new HashSet<>());
        }

        for (int i = 0 ; i < prerequisites.length ; i++) {
            int course = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if (!prerequisitesMap.get(pre).contains(course)) {
                indegreeMap.put(course, indegreeMap.get(course) + 1);
                prerequisitesMap.get(pre).add(course);
            }
        }

    }

//[[5,8],[3,5],[1,9],[4,5],[0,2],[1,9],[7,8],[4,9]]
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] prerequisites = new int[8][2];
        prerequisites[0] = new int[]{5,8};
        prerequisites[1] = new int[]{3,5};
        prerequisites[2] = new int[]{1,9};
        prerequisites[3] = new int[]{4,5};
        prerequisites[4] = new int[]{0,2};
        prerequisites[5] = new int[]{1,9};
        prerequisites[6] = new int[]{7,8};
        prerequisites[7] = new int[]{4,9};

        s.canFinish(10, prerequisites);
    }
}