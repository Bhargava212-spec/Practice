package temp;

import com.test.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BSTIterator {
    List<Integer> list;
    int index;

    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
        getInorderTraversal(root);
        index = 0;
    }

    private void getInorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        getInorderTraversal(root.left);
        list.add(root.val);
        getInorderTraversal(root.right);
    }

    public int next() {
        return list.get(index++);
    }

    public boolean hasNext() {
        return index < list.size();
    }
}
