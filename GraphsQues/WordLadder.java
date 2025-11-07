import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
    class Pair{
        String name;
        int steps;
        public Pair(String name, int steps){
            this.name = name;
            this.steps = steps;
        }
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord,1));
        Set<String> set = new HashSet<>();
        for(String word : wordList){
            set.add(word);
        }
        set.remove(beginWord);
        while(!q.isEmpty()){
            Pair p = q.poll();
            String node = p.name;
            int steps = p.steps;
            if(node.equals(endWord)) return steps;
            for(int i=0; i<node.length(); i++){
                for(char ch='a'; ch <='z'; ch++){
                    char replacedChar[] = node.toCharArray();
                    replacedChar[i] = ch;
                    String newString = new String(replacedChar);
                    if(set.contains(newString)){
                        set.remove(newString);
                        q.offer(new Pair(newString,steps+1));
                    }
                }
            }
        }
        return 0;
    }
}
