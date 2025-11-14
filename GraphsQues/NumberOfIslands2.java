import java.util.*;
public class NumberOfIslands2 {
    class DisjointSet {
        int par[], size[];

        public DisjointSet(int n) {
            par = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                par[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (par[x] == x) return x;
            return par[x] = find(par[x]);
        }

        public void union(int u, int v) {
            int pu = find(u);
            int pv = find(v);
            if (pu == pv) return;

            if (size[pu] < size[pv]) {
                par[pu] = pv;
                size[pv] += size[pu];
            } else {
                par[pv] = pu;
                size[pu] += size[pv];
            }
        }
    }

    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {

        DisjointSet ds = new DisjointSet(rows * cols);
        boolean vis[][] = new boolean[rows][cols];

        int count = 0;
        int dir[][] = {{1,0}, {0,1}, {-1,0}, {0,-1}};
        List<Integer> ans = new ArrayList<>();

        for (int[] it : operators) {

            int u = it[0], v = it[1];

            if (vis[u][v]) {  
                ans.add(count);
                continue;
            }

            vis[u][v] = true;
            count++;
            int id = u * cols + v;

            for (int[] d : dir) {
                int r = u + d[0];
                int c = v + d[1];

                if (r >= 0 && r < rows && c >= 0 && c < cols) {
                    if (vis[r][c]) {
                        int newId = r * cols + c;

                        if (ds.find(id) != ds.find(newId)) {
                            ds.union(id, newId);
                            count--;
                        }
                    }
                }
            }

            ans.add(count);
        }
        return ans;
    }
}