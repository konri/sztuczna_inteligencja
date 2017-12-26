package geneticAlgorithm;

import exceptions.GeneticAlgorithmExceptions;
import exceptions.GeneticAlgorithmExceptions.ErrorNumber;
import geneticAlgorithm.Tour.MethodFitness;

public class Population {
	private Tour[] tours;
	private int amountPopulation;

	/*
	 * Create a population initialize: true -- if we want to create a begin
	 * population
	 */
	public Population(int amountPopulation, boolean initialize) {
		this.amountPopulation = amountPopulation;
		tours = new Tour[amountPopulation];

		if (initialize) {
			for (int i = 0; i < amountPopulation; i++) {
				tours[i] = new Tour();
				tours[i].generateRandomize();
			}
		}
	}

	public Tour getTour(int index) throws ArrayIndexOutOfBoundsException {
		if (index < 0 || index >= amountPopulation)
			throw new ArrayIndexOutOfBoundsException();

		return tours[index];
	}

	public void setTour(int index, Tour tour) throws ArrayIndexOutOfBoundsException {
		if (index < 0 || index > amountPopulation)
			throw new ArrayIndexOutOfBoundsException();

		tours[index] = tour;
	}

	public void deleteTour(int index) throws GeneticAlgorithmExceptions {
		if( index > (getSize() - 1) || index < 0)
				throw new GeneticAlgorithmExceptions(ErrorNumber.outOfBoundsPopulation);
		for (int i = index; i < (getSize() - 1); i++) {
			tours[i] = tours[i + 1];
		}
		tours[getSize() - 1] = null;
	}

	public int getSize() {
		return tours.length;
	}

	/*
	 * check if exist tour
	 * true if exist
	 * false no-exist
	 */
	public boolean checkIfExists(Tour tour, int amount) throws GeneticAlgorithmExceptions
	{
	for(int i = 0; i < amount; i++)	{
		if(getTour(i).compareTours(tour)){
		   return true;
		}
	}
	return false;
	}
	/*
	 * check if there any duplicates in actual population 
	 * return true -- exist duplicates 
	 * return false -- no exist duplcates. 
	 */
	public boolean checkPresenceDuplicates(int amount) throws GeneticAlgorithmExceptions{
		if(amount == 0)
			amount = getSize();
		for(int mainIndex = 0; mainIndex < amount - 1; mainIndex++){
			for(int insideIndex = mainIndex + 1; insideIndex < amount; insideIndex++){
				if( getTour(mainIndex).compareTours(getTour(insideIndex)) ){
					return true;
				}
			}
		}
	return false;	
	}

	public int removeDuplicates() throws GeneticAlgorithmExceptions{
		int howManyDuplicates = 0;
		for(int mainIndex = 0; mainIndex < getSize() - howManyDuplicates; mainIndex++){
			for(int insideIndex = mainIndex + 1; insideIndex < getSize() - howManyDuplicates; insideIndex++){
				if( getTour(mainIndex).compareTours(getTour(insideIndex)) ){
					deleteTour(insideIndex);
					howManyDuplicates++;
				}
			}
		}
	return howManyDuplicates;
	}
	public Tour getFittest(MethodFitness method){
		Tour tmp = tours[0];
		for(int i = 1; i < getSize(); i++){
			if(tmp.getFittness(method) <= getTour(i).getFittness(method))
				tmp = getTour(i);
		}
		return tmp;
	}
	public String toString() {
		String tmp = "";
		for (int i = 0; i < amountPopulation; i++)
			tmp += getTour(i);
		return tmp;
	}
}
