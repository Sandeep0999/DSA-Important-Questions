import java.util.ArrayList;
import java.util.List;

class FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int state[] = new int[n];
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(dfs(i, state, graph)) ans.add(i);
        }
        return ans;
    }
    private boolean dfs(int node, int state[], int graph[][]){
        if(state[node] != 0){
            return state[node] == 2;
        }
        state[node] = 1;
        for(int next : graph[node]){
            if(!dfs(next, state, graph)) return false;
        }
        state[node] = 2;
        return true;
    }
}
