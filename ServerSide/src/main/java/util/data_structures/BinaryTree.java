package util.data_structures;

import java.util.Stack;

public class BinaryTree {
    private TreeNode root;
    private int length;

    private class TreeNode {
        private int data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public BinaryTree() {
        this.root = null;
        this.length = 0;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int length() {
        return length;
    }

    public void preOrderPrint(TreeNode root) {
        if(root == null) {
            return;
        }
        TreeNode temp = root;
        System.out.print(temp.data + " ");
        preOrderPrint(temp.left);
        preOrderPrint(temp.right);
    }

    public void inOrderPrint(TreeNode root) {
        if(root == null) {
            return;
        }
        TreeNode temp = root;
        inOrderPrint(temp.left);
        System.out.print(temp.data + " ");
        inOrderPrint(temp.right);
    }

    public void iterativeInOrderPrint(TreeNode root) {
        if(root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        while(!stack.isEmpty() || temp != null){
            if(temp != null){
                stack.push(temp);
                temp = temp.left;
            } else {
                temp = stack.pop();
                System.out.print(temp.data + " ");
                temp = temp.right;
            }
        }
    }

    public void postOrderPrint(TreeNode root) {
        if(root == null){
            return;
        }
        postOrderPrint(root.left);
        postOrderPrint(root.right);
        System.out.print(root.data + " ");
    }

    public void iterativePostOderPrint() {
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        while(current != null || !stack.isEmpty()) {
            if(current != null) {
                stack.push(current);
                current = current.left;
            } else {
                TreeNode temp = stack.peek().right;
                if(temp == null){
                    temp = stack.pop();
                    System.out.println(temp.data + " ");
                    while(!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        System.out.print(temp.data + " ");
                    }
                } else {
                    current = temp;
                }
            }
        }
    }

    public int findMax(){
        return findMax(root);
    }

    public int findMax(TreeNode root) {
        if(root == null) {
            return Integer.MIN_VALUE;
        }
        int result = root.data;
        int left = findMax(root.left);
        int right = findMax(root.right);

        if(left > result) {
            result = left;
        }

        if(right > result){
            result = right;
        }

        return result;
    }
}
