WGraph_DS
This class implements a mathematical weighted graph by implements two classes
NodeInfo which implements the basic information and methods each of node.
EdgeData which stores help us to keep utils information.
Functions:
•	WGraph_DS()  Default constructor
•	WGraph_DS(WGraph_DS)  Copy constructor
•	getNode(int Key)  Returns a node by the Key
•	hasEdge(int node1, int node2)  Checks if there is an edge between node1 and node2  O(1)
•	getEdge(int node1, int nide2)  Returns an wheight by the Edge  O(1)
•	addNode(int key)  Adds a new node to the graph with the given key ID.  O(1)
•	connect(int node1, int node2, double w)  Connects two nodes in the graph, If the edge already exist its just updates the weight.  O(1)
•	getV()  Returns a collection of node_info of the graph  O(1)
•	getV(int node_id)  Returns a collection view of the graph  O(1)
•	removeNode(itn key)  Removed a node from the graph and remove all edges which are connect to this node  O(n)
•	removeEdge(int node1, int node2)  Remove the edge between node1 and node2 in the graph (without deleting the nodes)  O(1)
•	nodeSize()  Returns the number of nodes in the graph  O(1)
•	edgeSize()  Returns the number of edges in the graph  O(1)
•	getMC()  Returns the number of modifications we made in the graph  O(1)


WGraph_Algo
•	init () – initialize the graph
•	 copy() - Create a deep copy of the graph
•	isConnected() - Checks if the graph is connected, if there is a path between each pair of vertices in he graph.
•	shortestPathDist(int src, int dest) - Returns the length of te shortest path between src and dest.
•	shortestPath(int src, int dest)  - Returns a List<node_data> of the shortest path between two nodes.
•	Save (String file)  Saves a graph to a file.
•	Load(string file) - Loads a graph from a file.
