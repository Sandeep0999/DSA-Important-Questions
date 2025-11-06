import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AlienDictionary {
    public String findOrder(String[] words) {
        int n = words.length;
        
        boolean[] present = new boolean[26];
        for(String w : words){
            for(char c : w.toCharArray()){
                present[c - 'a'] = true;
            }
        }
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int i = 1; i < n; i++){
            if(!buildEdge(words[i-1], words[i], adj)) return ""; 
        }
        
        int[] indegree = new int[26];
        for(int i = 0; i < 26; i++){
            for(int v : adj.get(i)){
                indegree[v]++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < 26; i++){
            if(present[i] && indegree[i] == 0){
                q.offer(i);
            }
        }
        
        StringBuilder ans = new StringBuilder();
        while(!q.isEmpty()){
            int u = q.poll();
            ans.append((char)(u + 'a'));
            for(int v : adj.get(u)){
                indegree[v]--;
                if(indegree[v] == 0){
                    q.offer(v);
                }
            }
        }
        
        if(ans.length() != count(present)) return "";
        
        return ans.toString();
    }

    boolean buildEdge(String a, String b, ArrayList<ArrayList<Integer>> adj){
        int n = Math.min(a.length(), b.length());
        for(int i = 0; i < n; i++){
            if(a.charAt(i) != b.charAt(i)){
                adj.get(a.charAt(i)-'a').add(b.charAt(i)-'a');
                return true;
            }
        }
        return b.length() >= a.length(); 
    }
    
    int count(boolean[] present){
        int c = 0;
        for(boolean x : present) if(x) c++;
        return c;
    }
}
