package ex1.src;

import java.io.Serializable;
import java.util.*;


public class WGraph_DS implements weighted_graph, Serializable {
    /**
     * A hash map that contains all the nodes in this Graph_DS as values,
     * and their key's are the map keys.
     */
    private HashMap<Integer, node_info> graphNode = new HashMap<>();
    private HashMap<Integer, EdgeData> graphEdge = new HashMap<>();
    private int countEdge = 0;
    private int mc = 0;

    /////////Default constructor//////////
    public WGraph_DS() {
        this.mc = 0;
        this.countEdge = 0;
        graphNode = new HashMap<>();
        graphEdge = new HashMap<>();
    }

    ////////////////////Copy Constructor///////////////////////////////
    public WGraph_DS(WGraph_DS gr) {
        weighted_graph graphCopy = new WGraph_DS();
        for (node_info n : this.getV()) {
            graphCopy.addNode(n.getKey());
            for (node_info e : this.getV(n.getKey())) {
                graphCopy.connect(n.getKey(), e.getKey(), this.getEdge(n.getKey(), e.getKey()));
            }
        }
    }


    private class nodeInfo implements node_info, Serializable //////////////////NODEINFO//////////////////////
    {
        private int id = 0;
        private int key;
        private double tag;
        private String info;
        // create ArrayList list
        private HashMap<Integer, node_info> NInfo;
        private double weight;


        public nodeInfo() {
            this.key = id++;
            this.tag = 0;
            this.weight = 0;
            this.info = "";
            NInfo = new HashMap<Integer, node_info>();
        }

        public nodeInfo(int k) {
            this.key = k;
            this.tag = 0;
            this.info = null;
        }

        @Override
        public int getKey() {
            return this.key;
        }

        public boolean hasNi(int key) {
            if (this.NInfo.containsKey(key))
                return true;
            else
                return false;
        }

        /**
         * @return the remark (meta data) associated with this edge
         */
        @Override
        public String getInfo() {
            return this.info;
        }

        public void addNi(node_info n) {
            if (n == null)
                return;
            else
                graphNode.put(n.getKey(), n);
        }

        @Override
        public void setInfo(String s) {
            this.info = s;
        }

        @Override
        public double getTag() {
            return tag;
        }

        @Override
        public void setTag(double t) {
            this.tag = tag;
        }

        /**
         * @return weight of the edge
         */
        public double getWeight() {
            return weight;
        }

        public void removeNode(node_info node) {
            NInfo.remove(node.getKey());
        }

        /**
         * @return string of the edge
         */
        public String toString() {
            return "Src: " + "\n" + "Dest: " + "\n" + "Weight: " + weight + "\n" + "Tag: " + tag;
        }

    }

    public class EdgeData implements Serializable { ///////////////////////////////EdgeData////////////////
        private HashMap<Integer, Double> edge_data;
        //<key, weight>

        private EdgeData() {
            this.edge_data = new HashMap<>();
        }

        /**
         * Sets the weight of a specific node between this->destination
         *
         * @param key - The destination node
         * @param w   - The weight to be set
         */
        private void setWeight(int key, double w) {
            this.edge_data.put(key, w);
        }

        /**
         * Checks if this node contains a neighbor with the received ID
         *
         * @param n - The received node to check
         * @return Boolean - True the the connections exists, False otherwise
         */
        private boolean hasNi(int n) {
            return this.edge_data.containsKey(n);
        }

        /**
         * @return a collection with all the Neighbor nodes of this node_info
         */
        private Collection<node_info> getNi() {
            Collection<node_info> col = new ArrayList<>();
            for (Integer k : this.edge_data.keySet()) {
                col.add(graphNode.get(k));
            }
            return col;
        }

        /**
         * connect this to e destanation and set the weight
         *
         * @param e - The node to connect
         * @param w - The weight to set
         */
        private void connectEdge(int e, double w) {
            this.edge_data.put(e, w);
        }

        /**
         * Returns the weight of the edge between this and -> dest node
         *
         * @param key - The destination node ID
         * @return double - The edge weight value
         */
        private double getWeight(int key) {
            return this.edge_data.get(key);
        }

        private void removeSrc() {
            this.edge_data.clear();
        }

        /**
         * Removed the edge between this and -> dest node
         * This is a one way change and is being used twice in the super classes methods
         * in order to provide a bidirectional operation
         *
         * @param e - The received node ID to connect the edge to
         */
        private void removeEdge(int e) {
            this.edge_data.remove(e);
        }

        /**
         * @return int - The number of nodes connected to this node
         */
        private int getSize() {
            return this.edge_data.size();
        }

    }

    /**
     * Returns a pointer to the specific the node_info by the node ID
     *
     * @param key - the node_id
     * @return node_info - The specified node info object
     */
    @Override
    public node_info getNode(int key) {
        if (graphNode.get(key) == null) {
            return null;
        }
        return this.graphNode.get(key);
    }

    public EdgeData getEdge(int key) {
        if (graphEdge.get(key) == null) {
            return null;
        }
        return this.graphEdge.get(key);
    }

    /**
     * This function chek if there is an Edge between 2 Nodes.
     *
     * @param node1 - the source of the edge.
     * @param node2 - the destination of the edge.
     * @return true or false.
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if (node1 == node2)
            return false;
        if (!graphNode.containsKey(node1) || !graphNode.containsKey(node2))
            return false;
        return graphEdge.get(node1).edge_data.containsKey(node2);
    }

    /**
     * Returns the weight value between two nodes.
     * This method runs in a constant O(1) time.
     *
     * @param node1 - First node
     * @param node2 - Second node
     * @return double - The value of the edge between the two node, -1 if the edge doesn't exist
     */
    @Override
    public double getEdge(int node1, int node2) {
        if (node1 == node2)
            return -1;
        if (!graphNode.containsKey(node1) || !graphNode.containsKey(node2))
            return -1;
        if (graphEdge.get(node1).edge_data.containsKey(node2)) {
            return graphEdge.get(node1).getWeight(node2);
        } else {
            return -1;
        }
    }

    /**
     * Adds a new node to the graph by the key.
     * @param key - The key of the new node
     */
    @Override
    public void addNode(int key) {
        if (this.graphNode.containsKey(key))
            return;
        else {
            mc++;
            nodeInfo n = new nodeInfo(key);
            graphNode.put(key, n);
            EdgeData e = new EdgeData();
            graphEdge.put(key, e);
        }

    }

    /**
     * Connects an edge between node1 and node2 with the given weight.
     * If the edge already exist it's just updates the weight.
     *
     * @param node1 - First node to connect
     * @param node2 - Second node to connect
     * @param w     - Given weight to be set between them
     */
    @Override
    public void connect(int node1, int node2, double w) {
        // the weight can't be negative
        if (w < 0)
            return;
        if (!graphNode.containsKey(node1) && !graphNode.containsKey(node2))
            return;
        if (hasEdge(node1, node2)) {
            graphEdge.get(node1).setWeight(node2, w);
            graphEdge.get(node2).setWeight(node1, w);//????
            mc++;
        }
        //chek if the node are the same and if node1 isn't already connect to node2
        if ((node1 == node2) || (hasEdge(node1, node2)))
            return;
        //Checking that they are not null
        if ((graphNode.get(node1) == null) || (graphNode.get(node2) == null))
            return;
            //else, connect them to each other
        else {
            this.graphEdge.get(node1).connectEdge(node2, w);
            this.graphEdge.get(node2).connectEdge(node1, w);
            countEdge++;
            mc++;
            return;
        }
    }

    /**
     * Return a pointer to a collection representing all the nodes in the graph.
     * This method runs in a constant O(1) time by using the values() method implemented in HashMap.
     *
     * @return Collection<node_info> - shallow copy to the nodes Map
     */
    @Override
    public Collection<node_info> getV() {
        if (!graphNode.isEmpty())
            return graphNode.values();
        else return new ArrayList<node_info>();
    }

    /**
     * This function return a collection by node id.
     * @param node_id -the Node of the collection we want.
     * @return The collection.
     */
    @Override
    public Collection<node_info> getV(int node_id) {
        return this.graphEdge.get(node_id).getNi();
    }

    /**
     * Delete the node from the graph -
     * and removes all edges which starts or ends at this node.
     * @param key - Key of the node to be deleted
     * @return the deleted node, null if none exists.
     */
    @Override
    public node_info removeNode(int key) {
        if (this.graphNode.containsKey(key)) {
            // TODO Auto-generated method stub
            Iterator<node_info> itr = this.getV(key).iterator();
            while (itr.hasNext()) {
                node_info temp = itr.next();
                this.removeEdge(temp.getKey(), key);
            }
            node_info gk = graphNode.get(key);
            countEdge = countEdge - graphEdge.get(key).getSize();
            mc = mc + graphEdge.get(key).getSize();
            graphEdge.remove(gk);

            return this.graphNode.remove(key);
        } else return null;
    }


    /**
     * This function remove an Edge from the graph.
     * @param node1 the Node id that is the Edge source.
     * @param node2 the Node id that is the Edge Destenation.
     */
    @Override
    public void removeEdge(int node1, int node2) {
        //check the node1 is connect to node2 and check if the graph contains node1 and node2
        if (this.graphEdge.get(node1).hasNi(node2) || this.graphNode.containsKey(node2) && this.graphNode.containsKey(node1)) {
            mc++;
            this.graphEdge.get(node1).removeEdge(node2);
            this.graphEdge.get(node2).removeEdge(node1);
            countEdge--;
        } else return;
    }

    /**
     * @return the size of Nodes in this graph.
     */
    @Override
    public int nodeSize() {
        return graphNode.size();
    }

    /**
     * @return the size of Edges in this graph.
     */
    @Override
    public int edgeSize() {
        return this.countEdge;
    }

    /**
     * @param edge - set the size of Edges in this graph.
     */
    public int setEdgeSize(int edge) {
        return this.countEdge = edge;
    }

    /**
     * @param mc - set the number of Changes that happened in the graph.
     */
    public void setmc(int mc) {
        this.countEdge = mc;
    }


    /**
     * @return the number of Changes that happened in the graph.
     */
    @Override
    public int getMC() {
        return mc;
    }

    public void setW(int w, double d) {
        graphEdge.get(w).setWeight(w, d);
    }
}
