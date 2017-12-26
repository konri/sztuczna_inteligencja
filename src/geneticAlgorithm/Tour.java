package geneticAlgorithm;

import exceptions.GeneticAlgorithmExceptions;
import exceptions.GeneticAlgorithmExceptions.ErrorNumber;
import graph.Node;
import graph.NodeManager;
import hamiltonAlgorithm.HamiltonAlgorithm;

import java.util.ArrayList;
import java.util.Collections;


public class Tour {
	public enum MethodFitness{
		getAllConnect,
		getAfterAnotherConnect
	}
	private ArrayList<Node> tour = new ArrayList<Node>();
	private int fitness = 0;
	private MethodFitness method = MethodFitness.getAllConnect;

	public Tour() {
		/*
		 * we create tour of nulls because of crossword algorithm
		 */
		for (int i = 0; i < NodeManager.staticManager.getSizeOfNodes(); i++) {
			tour.add(null);
		}

	}

	public Tour(ArrayList<Node> tour) {
		this.tour = tour;
	}

	/*
	 * Generate randomize tour of nodes
	 */
	public void generateRandomize() {
		for (int i = 0; i < NodeManager.staticManager.getSizeOfNodes(); i++) {
			setNode(i, NodeManager.staticManager.getReferenceNodeByIndex(i));
		}
		Collections.shuffle(tour);
	}

	/*
	 * set Node for exactly position in tour.
	 */
	public void setNode(int index, Node node) {
		tour.set(index, node);
		fitness = 0;
		method = MethodFitness.getAllConnect;
	}

	/*
	 * get Note from indexTour
	 */
	public Node getNode(int indexTour) {
		return tour.get(indexTour);
	}


	public int getSize()
	{
		return tour.size();
	}

	public ArrayList<Node> getTour()
	{
		return tour;
	}

	public boolean containsNode(Node node)
	{
		return tour.contains(node);
	}

	/*
	 * Get number of all correct connection between two Nodes
	 * if no connection is return -1 
	 */
	public int getAllNumberConnections(){
		int tmp = HamiltonAlgorithm.getAllAmountGoodConnections(this); 
		return  tmp != 0 ? tmp : -1;
	}

	/*
	 * Get number of correct connection between two Nodes but it must be after another 
	 * if has not connection between Nodes function stop and return actual amount
	 * if no connection is return -1 
	 */
	public int getAfterAnotherNumberConnections(){
		int tmp = HamiltonAlgorithm.getAfterAnotherAmountGoodConnections(this);
		return  tmp != 0 ? tmp : -1;
	}

	public int getFittness(MethodFitness method){
		if(fitness == 0){
			if(method == MethodFitness.getAfterAnotherConnect){
				fitness = getAfterAnotherNumberConnections();
				this.method = method;
			}else{
				fitness = getAllNumberConnections();
				this.method = method;
			}
		}else{
			if(this.method != method){

				fitness = 0;
				fitness = getFittness(method);
			}
		}

	return fitness;
	}
	/*
	 * return true if tours the same
	 * return false if tours are different
	 */
	public boolean compareTours(Tour tour) throws GeneticAlgorithmExceptions{
		if(this.getSize() != tour.getSize())
			throw new GeneticAlgorithmExceptions(ErrorNumber.notMatchSizeOfTours);

		for(int i = 0; i < getSize(); i++)		{
			if(this.getNode(i) != tour.getNode(i))
				return false;
		}
		return true;
	}

	public String toString(){
		String tmp ="";
		for(int i= 0; i < getSize(); i++)
			if(i < getSize() - 1)
				tmp += NodeManager.staticManager.getIdNodeByRefernce(getNode(i)) + "=>";
			else
				tmp += NodeManager.staticManager.getIdNodeByRefernce(getNode(i)) + "\n";

	return tmp;
	}
	public int[] getCycle(){
		int[] tmp = new int[getSize()];
		for(int i = 0; i < getSize(); i ++) {
			tmp[i] = NodeManager.staticManager.getIdNodeByRefernce(getNode(i));
			System.out.print(tmp[i] + " : ");
		}
		return tmp;
		
	}

}