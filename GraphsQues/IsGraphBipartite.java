import java.util.*;
class IsGraphBipartite {
    public boolean isGraphBipartite(int graph[][]){
        int n = graph.length;
        int color[] = new int[n];
        Arrays.fill(color, -1);
        for(int i=0; i<n; i++){
            if(color[i] == -1){
                if(!dfs(i, 0, color, graph)) return false;
            }
        }
        return true;
    }
    public boolean dfs(int node, int c, int color[], int graph[][]){
        color[node] = c;
        for(int next : graph[node]){
            if(color[next] == -1){
                if(!dfs(next, 1 - c, color, graph)) return false;
            }
            else if(color[next] == color[node]) return false;
        }
        return true;
    }
}
