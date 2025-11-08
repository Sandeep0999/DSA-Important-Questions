package TreesQues;
import java.util.*;
public class RecoverBinaryTree {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {};
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    TreeNode first = null, second = null , prev = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root){
        inOrder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    public void inOrder(TreeNode node){
        if(node == null) return;
        inOrder(node.left);
        if(node.val < prev.val){
            if(first == null) first = prev;
            second = node;
        }
        prev = node;
        inOrder(node.right);
    }
}
