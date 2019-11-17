package Algorithm.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * 给定一个二叉树，返回它的中序 遍历。
 * @date 2019/11/16
 */
public class InorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        find(root, resultList);
        return resultList;
    }

    private void find(TreeNode node, List<Integer> resultList) {
        if (node == null) return;
        find(node.left, resultList);
        resultList.add(node.val);
        find(node.right, resultList);
    }
}
