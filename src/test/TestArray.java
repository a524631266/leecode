package test;

public class TestArray {
    static class Graph{
        // 只分配空间，不分配数据，因此所有元素为null
        public Node[] nodes = new Node[100];
        class Node {
            private int id;
            Node() {

            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        for (int i = 0; is < graph.nodes.length; i++) {
            Graph.Node node = graph.nodes[i];
            System.out.println(node); // null
        }
    }
}
