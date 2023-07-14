package util.data_structures;

public class BinarySearchTree {
    private TreeNode root;

    private int size;

    private class TreeNode {
        private int data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    public BinarySearchTree() {
        this.size = 0;
        this.root = null;
    }

    public void insert(int data) {
        insert(root, data);
    }

    public TreeNode insert(TreeNode root, int data) {
        if(root == null) {
            root = new TreeNode(data);
            return root;
        }
        if(data < root.data){
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }
        return root;
    }

    public TreeNode search(int key) {
        return search(root, key);
    }

    public TreeNode search(TreeNode root, int key) {
        if(root == null || root.data == key) {
            return root;
        }
        if(root.data > key){
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    public boolean validate(TreeNode root, long min, long max) {
        if(root == null) {
            return true;
        }

        if(root.data < min || root.data > max) {
            return false;
        }

        boolean left = validate(root.left, min, root.data);

        if(left) {
            boolean right = validate(root.right, root.data, max);
            return right;
        }
        return false;

    }
}









