import java.util.*;
public class KruskalsAlgo {
    public class DisjointSet{
        int par[], rank[];
        public DisjointSet(int n){
            par = new int[n];
            rank = new int[n];
            for(int i=0; i<n; i++){
                par[i] = i;
                rank[i] = 0;
            }
        }
        public int find(int x){
            if(par[x] == x) return x;
            par[x] = find(par[x]);
            return par[x];
        }
        public void unionByRank(int u, int v){
            int pu = find(u);
            int pv = find(v);
            if(pu == pv) return;
            if(rank[pu] < rank[pv]){
                par[pu] = pv;
            }
            else if(rank[pv] < rank[pu]){
                par[pv] = pu;
            }
            else{
                par[pv] = pu;
                rank[pu]++;
            }
        }
    }
    class Edge implements Comparable<Edge>{
        int src, des, wt;
        public Edge(int src, int des, int wt){
            this.src = src;
            this.des = des;
            this.wt = wt;
        }
        public int compareTo(Edge that){
            return this.wt - that.wt;
        }
    }
    public int spanningTree(int V, List<List<List<Integer>>> adj) {
        List<Edge> edges = new ArrayList<>();
        for(int i=0; i<V; i++){
            for(List<Integer> it : adj.get(i)){
                int v = it.get(0), w = it.get(1);
                if( i < v ) edges.add(new Edge(i, v, w));
            }
        }
        Collections.sort(edges);
        DisjointSet ds = new DisjointSet(V);
        int mstW = 0;
        for(Edge e : edges){
            int u = e.src, v = e.des, w = e.wt;
            if(ds.find(u) != ds.find(v)){
                mstW += w;
                ds.unionByRank(u, v);
            }
        }
        return mstW;
    }
}
