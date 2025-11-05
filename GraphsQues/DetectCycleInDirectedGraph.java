import java.util.*;

public class DetectCycleInDirectedGraph {
    public boolean isCyclic(int V, int[][] edges) {

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
        }

        boolean[] vis = new boolean[V];
        boolean[] pathVis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (dfs(i, vis, pathVis, adj)) return true;
            }
        }
        return false;
    }
    private boolean dfs(int node, boolean[] vis, boolean[] pathVis, List<List<Integer>> adj) {
        vis[node] = true;
        pathVis[node] = true;

        for (int nei : adj.get(node)) {
            if (!vis[nei]) {
                if (dfs(nei, vis, pathVis, adj)) return true;
            } else if (pathVis[nei]) {
                return true;
            }
        }
        pathVis[node] = false;
        return false;
    }
}