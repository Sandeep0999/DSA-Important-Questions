import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInUndirectedGraph {
    public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
        // code here
        int n = adj.size();
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        int dist[] = new  int[n];
        int INF = (int)1e9;
        for(int i=0; i<dist.length; i++){
            dist[i] = INF;
        }
        dist[src] = 0;
        while(!q.isEmpty()){
            int node = q.poll();
            for(int it : adj.get(node)){
                if(dist[it] == INF){
                    dist[it] = dist[node] + 1;
                    q.add(it);
                }
            }
        }
        for(int i=0; i<dist.length; i++){
            if(dist[i] == INF) dist[i] = -1;
        }
        return dist;
    }
}
