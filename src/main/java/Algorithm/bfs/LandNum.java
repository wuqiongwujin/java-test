package Algorithm.bfs;


import java.util.*;

/**
 * @Description 广度优先算法-计算二维方格中岛屿数量
 * @date 2019/10/27
 */
public class LandNum {

    private Queue<Node> land = new ArrayDeque<Node>();
    private List<Node> used = new ArrayList<Node>();

    private class Node {
        private int i;
        private int j;
        public Node (int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return i == node.i &&
                    j == node.j;
        }

        @Override
        public int hashCode() {

            return Objects.hash(i, j);
        }
    }

    public int numIslands(char[][] grid) {
        int landNums = 0;
        if (grid == null || grid.length <= 0) {
            return 0;
        }
        for (int i=0; i<grid.length; i++) {
            for (int j=0;j<grid[i].length; j++) {
                if (used.contains(new Node(i,j))) {
                    continue;
                }
                if (grid[i][j] == '1') {
                    used.add(new Node(i,j));
                    land.offer(new Node(i,j));
                    findOneLand(grid);
                    landNums ++;
                } else {
                    continue;
                }
            }
        }
        return landNums;
    }

    private void findOneLand(char[][] grid) {
        while(!land.isEmpty()) {
            Node node = land.poll();
            up(grid, node);
            left(grid, node);
            right(grid, node);
            down(grid, node);
        }
    }

    private void left(char[][] grid, Node node) {
        int j = node.j - 1;
        if (j>=0) {
            Node newNode = new Node(node.i, j);
            if (grid[node.i][j] == '1' && !used.contains(newNode)) {
                used.add(newNode);
                land.offer(newNode);
            }
        }
    }

    private void up(char[][] grid, Node node) {
        int i = node.i - 1;
        if (i >= 0) {
            Node newNode = new Node(i, node.j);
            if (grid[i][node.j] == '1' && !used.contains(newNode)) {
                used.add(newNode);
                land.offer(newNode);
            }
        }
    }

    private void down(char[][] grid, Node node) {
        int i = node.i + 1;
        if (i < grid.length) {
            Node newNode = new Node(i,node.j);
            if (grid[i][node.j] == '1' && !used.contains(newNode)) {
                used.add(newNode);
                land.offer(newNode);
            }
        }
    }

    private void right(char[][] grid, Node node) {
        int j = node.j + 1;
        if (j < grid[node.i].length) {
            Node newNode = new Node(node.i, j);
            if (grid[node.i][j] == '1' && !used.contains(newNode)) {
                used.add(newNode);
                land.offer(newNode);
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {{'1','1','0','0','0'},{'1','1','0','1','1'},{'0','1','1','0','1'},{'0','0','1','1','1'}};
        LandNum landNum = new LandNum();
        System.out.println(landNum.numIslands(grid));
    }
}
