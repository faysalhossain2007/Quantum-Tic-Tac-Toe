package qtt;
// A Java Program to detect cycle in an undirected graph

import java.util.*;

// This class represents a directed graph using adjacency list
// representation
public class Graph {

    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; // Adjacency List Represntation
    public LinkedList<Integer> subscriptList[]; // Subscript to node mapping
    public List<Integer> cycleNode = new LinkedList<Integer>(); //which nodes make cycle

    // Constructor
    public Graph(int v) {  // subtract 10 from all four here
        V = v * v;
        adj = new LinkedList[v * v + 1];
        subscriptList = new LinkedList[v * v + 1];
        for (int i = 1; i <= v * v; i++) {
            adj[i] = new LinkedList();
        }
        for (int i = 1; i <= v * v; i++) {
            subscriptList[i] = new LinkedList();
        }

    }

    // Function to add an edge into the graph
    public void addEdge(int v, int w, int moveNum) {
        adj[v].add(w);
        adj[w].add(v);

        if (v < w) {
            //if (true) {
            subscriptList[moveNum].add(v);
            subscriptList[moveNum].add(w);
        } else {
            subscriptList[moveNum].add(w);
            subscriptList[moveNum].add(v);
        }
        //subscriptList[moveNum].sort(subscriptList[moveNum]);
    }

    public void removeEdge(int v, int w) { //my func
        //System.out.println("YO "+adj[v].get(adj[v].indexOf(w)));
        //System.out.println("YO "+adj[w].get(adj[w].indexOf(v)));
//        adj[v].remove(adj[v].get(adj[v].indexOf(w)));
//        adj[v].remove(adj[v].get(adj[v].indexOf(w)));
//        adj[w].remove(adj[w].get(adj[w].indexOf(v)));
//        adj[w].remove(adj[w].get(adj[w].indexOf(v)));
//        if (false) {
//
//            adj[v].remove(adj[v].indexOf(w));
//            adj[v].remove(adj[v].indexOf(w));
//            adj[w].remove(adj[w].indexOf(v));
//            adj[w].remove(adj[w].indexOf(v));
//        } else {
        if (adj[v].indexOf(w) != -1) {
            // System.out.println("removing -->> V index: " + adj[v].indexOf(w) + "  W index:  " + adj[w].indexOf(v));
            // System.out.println("removing -->> V: " + v + "  W:  " + w);
            adj[v].remove(adj[v].indexOf(w));
            adj[w].remove(adj[w].indexOf(v));
        }
        //}

    }

    // A recursive function that uses visited[] and parent to detect
    // cycle in subgraph reachable from vertex v.
    Boolean isCyclicUtil(int v, Boolean visited[], int parent) {
        // Mark the current node as visited
        visited[v] = true;
        Integer i;
        //System.out.println("v in isCyclicUtil "+v);

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();

            // If an adjacent is not visited, then recur for that
            // adjacent
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v)) {
                    //System.out.println("Item is adding in the queue: "+i);
                    if (!cycleNode.contains(i)) {

                        int temp = getBiggestInCycleNode();
                        if (i > temp && !cycleNode.isEmpty()) {
                            cycleNode.add(1, i);
                        } else {
                            cycleNode.add(i);
                        }
                    }
                    return true;
                }
            } // If an adjacent is visited and not parent of current
            // vertex, then there is a cycle.
            else if (i != parent) {
                if (!cycleNode.contains(i)) {

                    int temp = getBiggestInCycleNode();
                    if (i > temp && !cycleNode.isEmpty()) {
                        cycleNode.add(1, i);
                    } else {
                        cycleNode.add(i);
                    }
                }
                //System.out.println("Parent : "+parent+"  i  "+i);
                return true;
            } else {
                //System.out.println("Else Parent : "+parent+"  i  "+i);
                //queue.remove(i);
            }
        }
        //queue.remove(v);
        return false;
    }

    // Returns true if the graph contains a cycle, else false.
    public Boolean isCyclic() {
        // Mark all the vertices as not visited and not part of
        // recursion stack
        Boolean visited[] = new Boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            visited[i] = false;
        }

        // Call the recursive helper function to detect cycle in
        // different DFS trees
        for (int u = 1; u <= V; u++) {
            if (!visited[u]) // Don't recur for u if already visited                 
            {
                if (isCyclicUtil(u, visited, -1)) {
                    if (!cycleNode.contains(u)) {
                        int temp = getBiggestInCycleNode();
                        if (u > temp && !cycleNode.isEmpty()) {
                            cycleNode.add(1, u);
                        } else {
                            cycleNode.add(u);
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }

    public void printAdjList() {   //my func
        for (int i = 1; i <= V; i++) {

            Iterator<Integer> it = adj[i].iterator();
            System.out.println("Edges for node " + i + " is being printed: ");
            while (it.hasNext()) {
                //int j=it.next();

                System.out.println(it.next());
            }
        }
        System.out.println("----------------------------");
    }

    public void emptyCycleNode() {
        cycleNode.removeAll(cycleNode);
    }

    public void printCycleNode() {
        for (int i = 0; i < cycleNode.size(); i++) {
            System.out.println("Cycle Node Print -> " + cycleNode.get(i) + " ");

        }
    }

    public void printSubscriptionList() {
        for (int i = 1; i <= V; i++) {

            Iterator<Integer> it = subscriptList[i].iterator();
            System.out.println("Nodes for subscript " + i + " is being printed: ");
            while (it.hasNext()) {
                //int j=it.next();

                System.out.println(it.next());
            }
        }

    }

    public Boolean manualAdjCheckAllNodeInOne() {
        for (int i = 2; i <= V; i++) {

            if (adj[i].size() == V) {
                return true;
            }
            //Iterator<Integer> it = adj[i].iterator();
//            System.out.println("Edges for node " + i + " is being printed: ");
//            while (it.hasNext()) {
//                //int j=it.next();
//
//                System.out.println(it.next());
        }

        return false;
    }

    public int getBiggestInCycleNode() {
        int largest = 0;
        for (int i = 0; i < cycleNode.size(); i++) {
            //System.out.println("Cycle Node Print -> " + cycleNode.get(i) + " ");
            if (cycleNode.get(i) > largest) {
                largest = cycleNode.get(i);
            }
        }
        return largest;

    }
}

//    // Driver method to test above methods
//    public static void main(String args[])
//    {
//        // Create a graph given in the above diagram
//        Graph g1 = new Graph(5);
//        g1.addEdge(1, 0);
//        g1.addEdge(0, 2);
//        g1.addEdge(2, 0);
//        g1.addEdge(0, 3);
//        g1.addEdge(3, 4);
//        if (g1.isCyclic())
//            System.out.println("Graph contains cycle");
//        else
//            System.out.println("Graph doesn't contains cycle");
// 
//        Graph g2 = new Graph(3);
//        g2.addEdge(0, 1);
//        g2.addEdge(1, 2);
//        if (g2.isCyclic())
//            System.out.println("Graph contains cycle");
//        else
//            System.out.println("Graph doesn't contains cycle");
//    }
