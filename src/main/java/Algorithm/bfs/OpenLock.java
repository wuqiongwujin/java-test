package Algorithm.bfs;

import java.util.*;

/**
 * @Description 广度优先算法-打开转盘锁
 * @date 2019/10/28
 */
public class OpenLock {

    private Queue<Node> queue1 = new ArrayDeque<>();
    private Queue<Node> queue2 = new ArrayDeque<>();
    private Set<Node> visited = new HashSet<>();
    private Set<String> deadends;
    private String target;

    private class Node{
        public int a;
        public int b;
        public int c;
        public int d;

        public Node(int a,int b,int c,int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return a == node.a &&
                    b == node.b &&
                    c == node.c &&
                    d == node.d;
        }

        @Override
        public int hashCode() {

            return Objects.hash(a, b, c, d);
        }

        @Override
        public String toString() {
            return ""+a+b+c+d;
        }
    }

    public int openLock(String[] deadends, String target) {
        this.deadends = new HashSet<>(Arrays.asList(deadends));
        this.target = target;
        Node root = new Node(0,0,0,0);
        if (this.deadends.contains(root.toString()) || this.deadends.contains(target)) {
            return -1;
        }
        int length = 0;
        queue1.offer(root);
        visited.add(root);
        while(!queue1.isEmpty()) {
            Node cur = queue1.poll();
            if (a(cur) || b(cur) || c(cur) || d(cur)) {
                return ++length;
            }
            if (queue1.isEmpty()) {
                length++;
                queue1 = queue2;
                queue2 = new ArrayDeque<>();
            }
        }
        return -1;
    }

    private boolean a(Node node) {
        Node up = new Node(node.a+1 > 9 ? 0 : node.a+1,node.b,node.c,node.d);
        Node down = new Node(node.a-1 < 0 ? 9 : node.a-1,node.b,node.c,node.d);
        return visit(up, down);
    }

    private boolean b(Node node) {
        Node up = new Node(node.a,node.b+1 > 9 ? 0 : node.b+1,node.c,node.d);
        Node down = new Node(node.a,node.b-1 < 0 ? 9 : node.b-1,node.c,node.d);
        return visit(up, down);
    }

    private boolean c(Node node) {
        Node up = new Node(node.a,node.b,node.c+1 > 9 ? 0 : node.c+1,node.d);
        Node down = new Node(node.a,node.b,node.c-1 < 0 ? 9 : node.c-1,node.d);
        return visit(up, down);
    }

    private boolean d(Node node) {
        Node up = new Node(node.a,node.b,node.c,node.d+1 > 9 ? 0 : node.d+1);
        Node down = new Node(node.a,node.b,node.c,node.d-1 < 0 ? 9 : node.d-1);
        return visit(up, down);
    }

    private boolean visit(Node up, Node down) {
        if (up.toString().equals(this.target)) {
            return true;
        }
        if (down.toString().equals(this.target)) {
            return true;
        }
        if (!visited.contains(up) && !deadends.contains(up.toString())) {
            queue2.offer(up);
            visited.add(up);
        }
        if (!visited.contains(down) && !deadends.contains(down.toString())) {
            queue2.offer(down);
            visited.add(down);
        }
        return false;
    }


    public static void main(String[] args) {
        String[] deadends = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target = "8888";
        OpenLock openLock = new OpenLock();
        System.out.println(openLock.openLock(deadends, target));
    }
}
