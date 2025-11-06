import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class SafeStatesUsingBFS {
    public ArrayList<Integer> safeNodes(int V, int[][] edges) {
        // Code here
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }
        for(int edge[] : edges){
            adj.get(edge[1]).add(edge[0]);
        }
        int in[] = new int[V];
        for(int i=0; i<V; i++){
            for(int it : adj.get(i)){
                in[it]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<V; i++){
            if(in[i] == 0) q.offer(i); 
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()){
            int node = q.poll();
            ans.add(node);
            for(int next : adj.get(node)){
                in[next]--;
                if(in[next] == 0) q.offer(next);
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
