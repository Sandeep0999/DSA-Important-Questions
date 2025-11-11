import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class NoOfWaysToArriveAtDestination {
    class Pair{
    long first;
    int second;
    Pair(long first,int second){
        this.first=first;
        this.second=second;
    }
}
    public int countPaths(int n, int[][] roads) {
        ArrayList<ArrayList<Pair>> adj=new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        int m=roads.length;
        for(int i=0;i<m;i++){
            int u = roads[i][0];
            int v = roads[i][1];
            long wt = roads[i][2];
            adj.get(u).add(new Pair(wt, v));
            adj.get(v).add(new Pair(wt, u));
        }
        PriorityQueue<Pair> pq=new PriorityQueue<Pair>((x,y)-> Long.compare(x.first,y.first));
        int[] ways = new int[n];
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        pq.add(new Pair(0,0));
        ways[0] = 1;
        dist[0] = 0;
        int mod = 1000000007;
        while(!pq.isEmpty()){
            Pair it = pq.poll();
            long distance = it.first;
            int node = it.second;
            if(distance > dist[node]) continue;
            for(Pair p : adj.get(node)){
                int adjnode = p.second;
                long edw = p.first;
                long newdist = distance + edw;
                if(newdist < dist[adjnode]){
                    dist[adjnode] = newdist;
                    pq.add(new Pair(newdist, adjnode));
                    ways[adjnode] = ways[node];
                }
                else if(newdist == dist[adjnode]){
                    ways[adjnode] = (ways[adjnode] + ways[node]) % mod;
                }

            }
        }
        return (ways[n-1] % mod);
    }
}
