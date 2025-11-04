import java.util.ArrayList;

public class NoOfConnectedCompoInUnDirectedGraph {
    public int countComponents(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        boolean vis[] = new boolean[n];
        for(int i=0; i<n; i++){
            adj.add(new ArrayList<>());
        }
        for(int i[] : edges){
            adj.get(i[0]).add(i[1]);
            adj.get(i[1]).add(i[0]);
        }
        int count = 0;
        for(int i=0; i<n; i++){
            if(!vis[i]){
                count++;
                dfs(i, vis, adj);
            }
        }
        return count;
    }
    public void dfs(int node, boolean vis[], ArrayList<ArrayList<Integer>> adj){
        vis[node] = true;
        for(int next : adj.get(node)) {
            if(!vis[next]) dfs(next, vis, adj);
        }
    }
}
