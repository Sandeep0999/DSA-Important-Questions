import java.util.*;
public class ShortestPathInDAG {
    class Pair{
        int first, second;
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }

    public int[] shortestPath(int V, int E, int[][] edges) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            adj.get(edge[0]).add(new Pair(edge[1], edge[2]));
        }

        boolean vis[] = new boolean[V];
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < V; i++){
            if(!vis[i]) dfs(i, vis, st, adj);
        }

        int dist[] = new int[V];
        int INF = (int)1e9;

        for(int i = 0; i < V; i++){
            dist[i] = INF;
        }
        
        dist[0] = 0;
        
        while(!st.isEmpty()){
            int u = st.pop();
            if(dist[u] != INF){
                for(Pair p : adj.get(u)){
                    int v = p.first;
                    int wt = p.second;
                    if(dist[u] + wt < dist[v]){
                        dist[v] = dist[u] + wt;
                    }
                }
            }
        }

        for(int i = 0; i < V; i++){
            if(dist[i] == INF) dist[i] = -1;
        }

        return dist;
    }
    
    public void dfs(int node, boolean vis[], Stack<Integer> st, ArrayList<ArrayList<Pair>> adj){
        vis[node] = true;
        for(Pair p : adj.get(node)){
            if(!vis[p.first]) dfs(p.first, vis, st, adj);
        }
        st.push(node);
    }
}
