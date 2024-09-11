package custom;

import com.test.TreeNode;

import java.util.*;

public class BST {

    TreeNode root;
    Set<Integer> set;

    public BST() {
        root = null;
        set = new HashSet<>();
    }

    public void put(int value) {
        root = insert(root, value);
        set.add(value);
    }

    private TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (root.val == value) {
            return root;
        }
        if (value < root.val) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        return root;
    }

    public boolean contains(int value) {
        return set.contains(value);
    }

    public List<Integer> inOrderTraversal() {
        List<Integer> acc = new ArrayList<>();
        inOrderTraversal(root, acc);
        return acc;
    }

    private void inOrderTraversal(TreeNode node, List<Integer> acc) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, acc);
        acc.add(node.val);
        inOrderTraversal(node.right, acc);
    }


}
