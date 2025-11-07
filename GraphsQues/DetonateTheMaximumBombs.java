import java.util.*;
public class DetonateTheMaximumBombs {
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<List<Integer>> adj = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            long x1 = bombs[i][0], y1 = bombs[i][1], r1 = bombs[i][2];
            long r1Sq = r1 * r1;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                long x2 = bombs[j][0], y2 = bombs[j][1];
                long dist = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
                
                if (dist <= r1Sq) adj.get(i).add(j);
            }
        }

        int max = 1;

        for (int i = 0; i < n; i++) {
            boolean[] vis = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            vis[i] = true;

            int count = 1;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int nxt : adj.get(cur)) {
                    if (!vis[nxt]) {
                        vis[nxt] = true;
                        queue.offer(nxt);
                        count++;
                    }
                }
            }
            max = Math.max(max, count);

            if (max == n) return n;
        }

        return max;
    }
}

