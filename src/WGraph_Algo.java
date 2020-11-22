package ex1.src;

import java.io.*;
import java.util.*;

import java.io.Serializable;

/**
 * This package represents an Undirected (positive) Weighted Graph Theory algorithms including:
 * 0. copy();
 * 1. init(graph);
 * 2. isConnected();
 * 3. double shortestPathDist(int src, int dest);
 * 4. List<node_data> shortestPath(int src, int dest);
 * 5. Save(file);
 * 6. Load(file);
 */

public class WGraph_Algo implements weighted_graph_algorithms, Serializable {
    weighted_graph graph = new WGraph_DS();

    /**
     * ** Init this set of algorithms on the parameter - graph.
     *
     * @param g - graph
     */
    @Override
    public void init(weighted_graph g) {
        this.graph = g;
    }

    /**
     * @return a graph
     */
    @Override
    public weighted_graph getGraph() {
        return this.graph;
    }

    /**
     * Compute a deep copy of this graph bu using copy constructor in WGraph_DS
     *
     * @return copied graph
     */
    @Override
    public weighted_graph copy() {
        weighted_graph graphCopy = new WGraph_DS((WGraph_DS) graph);
        return graphCopy;
    }

    /**
     * This function checks if a graph is connected
     * A graph is called connected if there is a path
     * between each pair of vertices of the graph.
     *
     * @return - if all vertexes been visited it returns true;
     */
    @Override
    public boolean isConnected() {
        if (this.graph == null || this.graph.nodeSize() <= 1 || graph.edgeSize() == 0)
            return true;
        Queue<node_info> ezer = new LinkedList<>();
        HashMap<Integer, node_info> count = new HashMap<Integer, node_info>();
        node_info node = this.graph.getV().iterator().next();
        ezer.add(node);
        count.put(node.getKey(), node);
        while (!ezer.isEmpty()) {
            for (node_info tmp : this.graph.getV(ezer.poll().getKey())) {
                if (!count.containsKey(tmp.getKey())) {
                    count.put(tmp.getKey(), tmp);
                    ezer.add(tmp);
                }
            }
        }
        if (count.size() == this.graph.nodeSize())
            return true;
        else return false;
    }

    /**
     * * @param src  - start node
     * * @param dest - end (target) node
     * * @return -  the length of the shortest path between src to dest
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (src == dest || this.graph == null || this.graph.getNode(src) == null || this.graph.getNode(dest) == null)
            return -1;
        double dbl;
        PriorityQueue<node_info> ezer = new PriorityQueue<>();
        //Iterator<node_info> iter = ezer.iterator();
        node_info node = this.graph.getNode(src);
        ezer.add(node);
        node.setTag(0);
        while (!ezer.isEmpty()) {
            node = ezer.poll();
            if (!Objects.equals(node.getInfo(), "pass")) {
                node.setInfo("pass");
                if (node.getKey() != dest) {
                    for (node_info n : this.graph.getV(node.getKey())) {
                        if (n.getTag() != Double.MAX_VALUE) {
                            n.setTag(Double.MAX_VALUE);
                        }
                        double tag = node.getTag() + this.graph.getEdge(node.getKey(), n.getKey());
                        if (tag < n.getTag()) {
                            n.setTag(tag);
                            ezer.add(n);
                        }
                    }

                }
            }
        }

        dbl = this.graph.getNode(dest).getTag();
        if (!Objects.equals(this.graph.getNode(dest).getInfo(), "pass"))
            return -1;
        this.setTag();
        return dbl;
    }

    /**
     * helper function used to compare.
     * @param obj
     * @return true or false if the objects are the same.
     */
    public boolean equals(Object obj) {
        weighted_graph gr = (WGraph_DS) obj;
        if (graph.edgeSize() != gr.edgeSize() || graph.nodeSize() != gr.nodeSize()) return false;
        return Objects.equals(graph.toString(), gr.toString());
    }

    /**
     * this function sets all nodes(Tag) to zero
     */
    private void setTag() {
        Collection<node_info> temp = graph.getV();
        for (node_info node : temp) {
            node.setTag(0);
            node.setInfo(null);
        }
    }

    /**
     * Using a Dijkstra method
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return the cost value of the shortest path src --> dest.
     */

    @Override
    public List<node_info> shortestPath(int src, int dest) {
        List<node_info> visited = new ArrayList<>();
        if (shortestPathDist(src, dest) == Double.POSITIVE_INFINITY) {
            return null;
        }
        String str = graph.getNode(dest).getInfo();
        str = str.substring(2);
        String[] splitArray = str.split("->");
        for (int i = 0; i < splitArray.length; i++) {
            visited.add(graph.getNode(Integer.parseInt(splitArray[i])));
        }
        visited.add(graph.getNode(dest));
        return visited;
    }


    /**
     * Saves the graph to a file.
     *
     * @param file
     */
    @Override
    public boolean save(String file) {
        boolean flag;
        try {
            FileOutputStream fileSave = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileSave);
            out.writeObject(this.graph);
            out.close();
            fileSave.close();
            flag = true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return flag;
    }

    /**
     * load graph from file
     */
    @Override
    public boolean load(String file) {
        boolean flag;
        try {
            FileInputStream fileLoad = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileLoad);
            this.graph = (weighted_graph) in.readObject();
            in.close();
            flag = true;
        } catch (IOException e) {
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
        return flag;
    }

}