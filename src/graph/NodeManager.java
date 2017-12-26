package graph;

import hamiltonAlgorithm.MatrixPermission;

import java.util.ArrayList;

/*
 * this class include every nodes which we add to workspace. 
 * It return:
 *  index of Node's reference  --> it help to get information from matrix which contains information about possibility path. 
 *  reference of Node's index
 *  amount number of containsNode list.    ]
 *  we can set permission of tour between two nodes. 
 *  we can get permission.  
 * 
 */
public class NodeManager {
	public static NodeManager staticManager = null; 
	private  ArrayList<Node> containsNode = null; // hold our nodes
	private MatrixPermission matrix = null;


	public NodeManager(MatrixPermission matrix){
		containsNode = new ArrayList<Node>();
		this.matrix = matrix;
		staticManager = this;
	}


	/*
	 * add Node to main list
	 */
	public Node addNode(Node node) {
		containsNode.add(node);
		matrix.addNode(getSizeOfNodes());  // TODO: optimization we can add to check there if matrix can handle more permissions. 
		return node;
	}

	/*
	 * Get Node reference by index.
	 */
	public Node getReferenceNodeByIndex(int index) {
		return containsNode.get(index);
	}

	/*
	 * Get integer id of node.
	 */
	public int getIdNodeByRefernce(Node node) {
		return containsNode.indexOf(node);
	}

	/*
	 * Get size of list.
	 */
	public int getSizeOfNodes() {
		return containsNode.size();
	}


}