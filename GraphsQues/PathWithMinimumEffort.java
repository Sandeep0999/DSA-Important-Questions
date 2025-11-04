import java.util.Arrays;
import java.util.PriorityQueue;

public class PathWithMinimumEffort {
    class Node {
        int data;
        int row;
        int col;
        public Node(int data, int row, int col){
            this.data = data;
            this.row = row;
            this.col = col;
        }
    }
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length, m = heights[0].length;
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)-> a.data - b.data);
        int dist[][] = new int[n][m]; 
        for(int i[] : dist){
            Arrays.fill(i,Integer.MAX_VALUE);
        }
        int dir[][] = {{1,0},{0,-1},{-1,0},{0,1}};
        pq.offer(new Node(0,0,0));
        dist[0][0] = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int val = node.data;
            int x = node.row , y = node.col;
            if(x == n-1 && y == m-1) return val;
            for(int it[] : dir){
                int nr = x + it[0];
                int nc = y + it[1];
                if(nr >= 0 && nc >= 0 && nr < n && nc < m){
                    int diff = Math.abs(heights[nr][nc] - heights[x][y]);
                    int maxDiff = Math.max(diff,val);
                    if(maxDiff < dist[nr][nc]){
                        dist[nr][nc] = maxDiff;
                        pq.offer(new Node(maxDiff, nr, nc));
                    }
                }
            }
        }
        return 0;
    }
}
