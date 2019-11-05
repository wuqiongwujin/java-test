package Algorithm.bfs;


import java.util.*;

/**
 * @Description 广度优先算法,完全平方数问题
 * 算法思想：分割和数，效率低
 * @date 2019/10/29
 */
public class PerfectSquareNumber {

    private Queue<Node> queue1 = new ArrayDeque<>();
    private Queue<Node> queue2 = new ArrayDeque<>();
    private Set<Node> visited = new HashSet<>();

    private class Node {
        public List<Integer> numList;

        public Node(Integer ...elements) {
            numList = new LinkedList<>(Arrays.asList(elements));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            if (this.numList.size() != node.numList.size()) return false;
            for (Iterator<Integer> iterator = node.numList.iterator();iterator.hasNext(); ){
                if (!this.numList.contains(iterator.next())) return false;
            }
            return true;
        }

        @Override
        public int hashCode() {

            return Objects.hash(numList);
        }
    }

    private class DatePair{
        public int a;
        public int b;
        public DatePair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public int numSquares(int n) {
        if (n < 0) return 0;
        if (isPerfectSquareNumber(n)) return 1;
        Node root = new Node(n);
        queue1.offer(root);
        visited.add(root);
        while(!queue1.isEmpty()) {
            Node cur = queue1.poll();
            if (isPerfectSquareNumbers(cur)) {
                return cur.numList.size();
            }
            Node t = split(cur);
            if (t != null) {
                return t.numList.size();
            }
            if (queue1.isEmpty()) {
                queue1 = queue2;
                queue2 = new ArrayDeque<>();
            }
        }
        return 0;
    }

    /**
     * 寻找node节点的所有临近节点
     * @param node
     */
    private Node split(Node node) {
        int e;
        for (int i=0; i<node.numList.size();i++) {
            e = node.numList.get(i);
            if (e <= 1) {
                continue;
            }
            List<Integer> newNumList = new LinkedList<>(node.numList);
            newNumList.remove(i);
            List<DatePair> ds = split(e);
            for (DatePair d : ds) {
                Node newNode = new Node(newNumList.toArray(new Integer[]{}));
                newNode.numList.add(d.a);
                newNode.numList.add(d.b);
                if (isPerfectSquareNumbers(newNode)) return newNode;
                if (!visited.contains(newNode)) {
                    queue2.offer(newNode);
                    visited.add(newNode);
                }
            }
        }
        return null;
    }

    /**
     * 寻找所有a+b=n的集合,a>0,b>0
     * @param n
     * @return
     */
    private List<DatePair> split(int n) {
        List<DatePair> ds = new ArrayList<>();
        int a=1;
        while (a <= n/2) {
            if (!isPerfectSquareNumber(a)) {
                a++;
                continue;
            } else {
                DatePair d = new DatePair(a,n-a);
                ds.add(d);
                a++;
            }
        }
        return ds;
    }

    private boolean isPerfectSquareNumbers(Node node) {
        if (node == null || node.numList == null || node.numList.isEmpty()) {
            return false;
        }
        for (int n : node.numList) {
            if (!isPerfectSquareNumber(n)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPerfectSquareNumber(int n) {
        return Math.sqrt(n) % 1 == 0;
    }

    public static void main(String[] args) {
        PerfectSquareNumber p = new PerfectSquareNumber();
        System.out.println(p.numSquares(7168));
    }
}
