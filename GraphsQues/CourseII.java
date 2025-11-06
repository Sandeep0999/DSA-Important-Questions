import java.util.*;
public class CourseII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        boolean[] vis = new boolean[numCourses];
        boolean[] pathVis = new boolean[numCourses];
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
        }

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < numCourses; i++) {
            if (!vis[i]) {
                if (dfs(i, vis, pathVis, st, adj)) return new int[0]; // cycle found
            }
        }

        int idx = 0;
        while (!st.isEmpty()) {
            ans[idx++] = st.pop();
        }

        return ans;
    }

    public boolean dfs(int node, boolean[] vis, boolean[] pathVis, Stack<Integer> st, List<List<Integer>> adj) {
        vis[node] = true;
        pathVis[node] = true;

        for (int next : adj.get(node)) {
            if (!vis[next]) {
                if (dfs(next, vis, pathVis, st, adj)) return true;
            } 
            else if (pathVis[next]) { 
                return true; 
            }
        }

        pathVis[node] = false;
        st.push(node);
        return false;
    }
}
