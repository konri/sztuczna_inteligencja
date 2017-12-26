package geneticAlgorithm;

import exceptions.GeneticAlgorithmExceptions;
import geneticAlgorithm.Tour.MethodFitness;
import hamiltonAlgorithm.HamiltonAlgorithm;

import java.util.Random;


public class GeneticAlgorithm {

	private int amountGoodPath = 0;
	private int tournamentSize = 10;
	private MethodFitness methodFitness;
	private boolean isFullPopulation = false;
	private Population correctPathPopulation;
	private MutationAlgorithm mutation;

	/*
	 * Amount of population with correct paths
	 */
	public GeneticAlgorithm(int populationSizeCorrectPath, double mutationSeek, int tournamentSize, MethodFitness methodFitness){
		correctPathPopulation = new Population(populationSizeCorrectPath, false);
		mutation = new MutationAlgorithm(mutationSeek);
		this.tournamentSize = tournamentSize; 
		this.methodFitness = methodFitness;
	}

	public GeneticAlgorithm(){
		correctPathPopulation = new Population(10, false);
		mutation = new MutationAlgorithm();
		methodFitness = MethodFitness.getAllConnect;
	}

	public Population solvePopulation(Population population) throws GeneticAlgorithmExceptions {
		Population tmpPopulation = new Population(population.getSize(), false);

		/*
		 * add correct Hamilton path to correctPathPopulation 
		 * First we check if is Hamilton then checkIfExists such a path in population 
		 */

		for(int i = 0; i < population.getSize(); i++){
			if(HamiltonAlgorithm.checkHamilton(population.getTour(i))){
				if(!correctPathPopulation.checkIfExists(population.getTour(i),amountGoodPath)){
					correctPathPopulation.setTour(amountGoodPath, population.getTour(i));
					if(++amountGoodPath > correctPathPopulation.getSize() - 1){
						isFullPopulation = true;
						return tmpPopulation;
					}
				}
			}
		}

		/*
		 * Add the best tour from old population for the first place
		 **/
		tmpPopulation.setTour(0, population.getFittest(methodFitness));

		for (int k = 1; k < tmpPopulation.getSize(); k++) {
			// get randomly parent1 and parent 2 from old population
			Tour parent1 = getTournamentTour(population);
			Tour parent2 = getTournamentTour(population);
			// create child with crossover
			Tour child = CrossoverAlgorithm.crossOver(parent1, parent2);
			// set new tour to new population
			tmpPopulation.setTour(k, child);
		}
		for (int k = 1; k < tmpPopulation.getSize(); k++)
			mutation.mutation(tmpPopulation.getTour(k));

		return tmpPopulation;

	}

	/*
	 * Return population which contains correct Hamiltion's paths. 
	 */
	public Population getHamiltonPaths(){
		return correctPathPopulation;
	}

	/*
	 * Return number of amount good path which save in population 
	 */
	public int getAmountGoodPath(){
	return amountGoodPath;
	}
	/*
	 * Return boolean isFullPopulation of correct Hamiltion's paths.  
	 */
	public boolean isFull() {
		return isFullPopulation;
	}

	/*
	 * Get random tour from population 
	 */
	private Tour getTournamentTour(Population population) {
		Random rand = new Random();
		Population tournament = new Population(tournamentSize, false);
		for(int i = 0; i < tournament.getSize(); i++ ){
			tournament.setTour(i, population.getTour(rand.nextInt(population.getSize())));
		}
		return tournament.getFittest(methodFitness);

	}


}

