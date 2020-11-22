package ex1.Test;

import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSMyTest {
    private static Random _rnd = null;

    @Test
    void nodeSize() {
        weighted_graph g = new WGraph_DS();
        int random = (int)(Math.random() * 500 + 1);
        for(int i=0; i<= random; i++)
        {
            g.addNode(i);
        }
        int randomRemove = (int)(Math.random() * random + 1);
        for (int j=0; j<=randomRemove; j++)
        {
            g.removeNode(j);
        }
        int s = g.nodeSize();
        assertEquals(random-randomRemove,s);

    }

    @Test
    void edgeSize() {
        weighted_graph g = new WGraph_DS();
        int random = (int)(Math.random() * 500 + 1);
        for(int i=0; i<= random; i++)
        {
            g.addNode(i);
        }
        int randomConnect = (int)(Math.random() * random + 1);
        for(int j=0; j<= randomConnect; j++)
        {
            g.connect(0,j,1);
        }

        int e_size =  g.edgeSize();
        assertEquals(randomConnect, e_size);
    }

    @Test
    void getV() {
        weighted_graph g = new WGraph_DS();
        int random = (int)(Math.random() * 500 + 1);
        for(int i=0; i<= random; i++)
        {
            g.addNode(i);
        }
        int randomConnect = (int)(Math.random() * random + 1);
        for(int j=0; j<= randomConnect; j++)
        {
            g.connect(0,j,1);
        }
        Collection<node_info> v = g.getV();
        Iterator<node_info> iter = v.iterator();
        while (iter.hasNext()) {
            node_info n = iter.next();
            assertNotNull(n);
        }
    }

    @Test
    void hasEdge() {
        int v = 10, e = v*(v-1)/2;
        weighted_graph g = graph_creator(v,e,1);
        for(int i=0;i<v;i++) {
            for(int j=i+1;j<v;j++) {
                boolean b = g.hasEdge(i,j);
                assertTrue(b);
                assertTrue(g.hasEdge(j,i));
            }
        }
    }

    @Test
    void connect() {
        weighted_graph g = new WGraph_DS();
        int random = (int)(Math.random() * 500 + 1);
        for(int i=0; i<= random; i++)
        {
            g.addNode(i);
        }
        int randomConnect = (int)(Math.random() * random + 1);
        for(int j=0; j<= randomConnect; j++)
        {
            g.connect(0,j,1);
        }
        g.removeEdge(0,1);
        assertFalse(g.hasEdge(1,0));
        g.removeEdge(2,1);
        g.connect(0,1,1);
        double w = g.getEdge(1,0);
        assertEquals(w,1);
    }


    @Test
    void removeNode() {
        weighted_graph g = new WGraph_DS();
        int random = (int) (Math.random() * 500 + 1);
        for (int i = 0; i < random; i++) {
            g.addNode(i);
        }
        int n = g.nodeSize();
        assertEquals(random, n);
        int randomConnect = (int) (Math.random() * random + 1);
        for (int j = 0; j < randomConnect; j++) {
            g.connect(j, j+1, 1);
        }
        int c = g.edgeSize();
        assertEquals(randomConnect, c);
        int randomRemove = (int) (Math.random() * randomConnect + 1);
        for (int k = 0; k < randomRemove; k++) {
            g.removeNode(k);
        }
        c = g.edgeSize();
        assertEquals(randomConnect-randomRemove,c);
        int deletedEdge = (int) (Math.random() * randomRemove + 1);
        g.removeEdge(0, deletedEdge);
        assertFalse(g.hasEdge(0,deletedEdge));
        int e = g.edgeSize();
        //assertEquals(c-1,e);
    }

    @Test
    void removeEdge() {
        weighted_graph g = new WGraph_DS();
        int random = (int) (Math.random() * 500 + 1);
        for (int i = 0; i < random; i++) {
            g.addNode(i);
        }
        int n = g.nodeSize();
        assertEquals(random, n);
        int randomConnect = (int) (Math.random() * random + 1);
        for (int j = 0; j < randomConnect; j++) {
            g.connect(j, j+1, 1);
        }
        int c = g.edgeSize();
        assertEquals(randomConnect, c);
        int randomRemove = (int) (Math.random() * randomConnect + 1);
        for (int k = 0; k < randomRemove; k++) {
            g.removeNode(k);
        }
        c = g.edgeSize();
        assertEquals(randomConnect-randomRemove,c);
        int deletedEdge = (int) (Math.random() * randomRemove + 1);
        g.removeEdge(0, deletedEdge);
        assertFalse(g.hasEdge(0,deletedEdge));
        int e = g.edgeSize();
        assertEquals(c-1,e);
        g.removeEdge(0,3);
        double w = g.getEdge(0,3);
        assertEquals(w,-1);
    }


    ///////////////////////////////////
    /**
     * Generate a random graph with v_size nodes and e_size edges
     * @param v_size
     * @param e_size
     * @param seed
     * @return
     */
    public static weighted_graph graph_creator(int v_size, int e_size, int seed) {
        weighted_graph g = new WGraph_DS();
        _rnd = new Random(seed);
        for(int i=0;i<v_size;i++) {
            g.addNode(i);
        }
        // Iterator<node_data> itr = V.iterator(); // Iterator is a more elegant and generic way, but KIS is more important
        int[] nodes = nodes(g);
        while(g.edgeSize() < e_size) {
            int a = nextRnd(0,v_size);
            int b = nextRnd(0,v_size);
            int i = nodes[a];
            int j = nodes[b];
            double w = _rnd.nextDouble();
            g.connect(i,j, w);
        }
        return g;
    }
    private static int nextRnd(int min, int max) {
        double v = nextRnd(0.0+min, (double)max);
        int ans = (int)v;
        return ans;
    }
    private static double nextRnd(double min, double max) {
        double d = _rnd.nextDouble();
        double dx = max-min;
        double ans = d*dx+min;
        return ans;
    }
    /**
     * Simple method for returning an array with all the node_data of the graph,
     * Note: this should be using an Iterator<node_edge> to be fixed in Ex1
     * @param g
     * @return
     */
    private static int[] nodes(weighted_graph g) {
        int size = g.nodeSize();
        Collection<node_info> V = g.getV();
        node_info[] nodes = new node_info[size];
        V.toArray(nodes); // O(n) operation
        int[] ans = new int[size];
        for(int i=0;i<size;i++) {ans[i] = nodes[i].getKey();}
        Arrays.sort(ans);
        return ans;
    }
}