package geneticAlgorithm;

import graph.Node;

import java.util.Random;

public class MutationAlgorithm {
	private double mutationRate;

	public MutationAlgorithm(double mutationRate) {
		this.mutationRate = mutationRate;
	}
	public MutationAlgorithm(){
		this.mutationRate = 0.1;
	}
	/*
	 * Mutation tour swap two nodes if possibility will be lesser than
	 * mutationRate(10%).
	 */
	public void mutation(Tour tour) {
		Random rand = new Random();
		for (int i = 0; i < tour.getSize(); i++) {
			if (rand.nextDouble() < mutationRate) {
				int randomSecond = rand.nextInt(tour.getSize());// generate second index of swapping node.

				Node node1 = tour.getNode(i);
				Node node2 = tour.getNode(randomSecond);

				// swap between two nodes.
				tour.setNode(i, node2);
				tour.setNode(randomSecond, node1);
			}
		}
	}

}
