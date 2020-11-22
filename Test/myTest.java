package ex1.Test;


import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.weighted_graph;
import ex1.src.weighted_graph_algorithms;

public abstract class myTest {

    public static void main(String[] args) {
        //HashMap<Integer, node_info> test = new HashMap<Integer, node_info>();
        WGraph_DS test = new WGraph_DS();
        weighted_graph_algorithms Avi;
        test.addNode(0);
        test.addNode(1);
        test.addNode(2);
        test.addNode(3);
        test.addNode(4);
        System.out.println("num of nodes =" + test.nodeSize());
        test.removeNode(3);
        System.out.println("num of nodes agter =" + test.nodeSize());
        System.out.println("num of edges =" + test.edgeSize());
        test.connect(0, 1, 5);
        test.connect(0, 2, 4);
        test.connect(1, 2, 5);
        System.out.println("num of edges =" + test.edgeSize());
        test.removeEdge(0, 1);
        System.out.println("num of edges after =" + test.edgeSize());
        System.out.println("num of nodes agter =" + test.nodeSize());
        System.out.println("isConnect" );


//System.out.println("number " + test.nodeSize());
//System.out.println(test.edgeSize());
//System.out.println(test.getMC());    
        //test.connect(1, 2);
        // System.out.println(test.edgeSize());
//   test.connect(4, 3);
//    System.out.println( "edge" + test.edgeSize());
//    System.out.println(test.getMC());
//System.out.println(d.getKey());
//test.connect(0, 1);
//test.connect(0, 2);
//test.connect(2, 3);
//test.connect(1, 3);
//System.out.println( "edgebefor" + test.edgeSize());
////test.removeNode(0);
//test.removeEdge(0, 2);
//System.out.println("number " + test.nodeSize());
//System.out.println( "edgeafter" + test.edgeSize());
//////
        WGraph_DS graph = new WGraph_DS();
        weighted_graph_algorithms algo = new WGraph_Algo();

        for (int i = 0; i <= 6; i++) {
            graph.addNode(i);
        }
        graph.connect(0,1,3);
        graph.connect(0,2,3);
        graph.connect(1,3,3);
        graph.connect(3,4,3);
        graph.connect(4,5,3);
        graph.connect(5,3,3);
        algo.init(graph);
        System.out.println("grapgedge" +graph.edgeSize());
        weighted_graph gr = algo.copy();
        //graph.connect(0, 1, 4);
        System.out.println("dd");
        System.out.println("graph edge size "+graph.edgeSize());
        System.out.println("gr edge size "+gr.edgeSize());
       // System.out.println("gr edge size "+graph.is);
        System.out.println(algo.isConnected());
        System.out.println("dd");
//System.out.println("create a graph");
//for (node_data n : graph.getV()){
//  System.out.println("key "+n.getKey()+" Ni --> " + n.getNi());
//}
//graph.connect(0,2);
//graph.connect(0,1);
//graph.connect(1,3);
//graph.connect(2,3);
//graph.connect(3,4);
//graph.connect(4,5);
//graph.connect(5,0);
//
//
//
//System.out.println("after connent the nodes in the graph");
//for (node_data n : graph.getV()){
//  System.out.println("key "+n.getKey()+" Ni --> " + n.getNi());
//}
////System.out.println(graph.hasEdge(0, 2));
//
//algo = new Graph_Algo ();
//algo.init(graph);
//System.out.println( "Shortest way 0 -> 2 should be 1 =  " + "the answer is : " + algo.shortestPathDist(0,4));
//System.err.println("MTV");
//System.out.println(algo.isConnected());
////algo.isConnected();
//System.err.println("hello");
//System.out.println((algo.shortestPath(0, 4)));


//System.out.println( "Shortest way 0 -> 3 should be 3 =  " + "the answer is : " +algo.shortestPathDist(0,3));
//System.out.println( "Shortest way 9 -> 6 should be 4 =  " + "the answer is : " +algo.shortestPathDist(9,6));
//System.out.println( "Shortest way 1 -> 0 should be -1 =  " + "the answer is : " +algo.shortestPathDist(1,0));
//System.out.println( "Shortest way 0 -> 0 should be 0 =  " + "the answer is : " +algo.shortestPathDist(0,0));
//System.out.println( "Shortest way 4 -> 6 should be 3 =  " + "the answer is : " +algo.shortestPathDist(4,6));
//System.out.println( "Shortest way 4 -> 7 should be 3 =  " + "the answer is : " +algo.shortestPathDist(4,7));
//System.out.println( "Shortest way 8 -> 1 should be 2 =  " + "the answer is : " +algo.shortestPathDist(8,1));
//System.out.println( "Shortest way 1 -> 5 should be 2 =  " + "the answer is : " +algo.shortestPathDist(1,5));
//System.out.println( "Shortest way 1 -> 20 should be -1 =  " + "the answer is : " +algo.shortestPathDist(1,20));

    }

}