package collection.collectoinTest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @date 2018/11/4
 */
public class CollectionTest {
    private Map<String, Integer> map = new HashMap<>();
    private List<TreeNode> nodeList = new ArrayList<>();
    public static void main(String[] args) {
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) {
            return nodeList;
        }
        inorderTraversal(root);
        return nodeList;
    }

    private String inorderTraversal(TreeNode node) {
        String left = null;
        String root = String.valueOf(node.val);
        String right = null;
        if (node.left != null) {
            left = inorderTraversal(node.left);
        }
        if (node.right != null) {
            right = inorderTraversal(node.right);
        }
        String key = root + left + right;
        if (map.containsKey(key)) {
            if (map.get(key) == 1) {
                nodeList.add(node);
            }
            map.put(key, map.get(key)+1);
        } else {
            map.put(key, 1);
        }
        return key;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}