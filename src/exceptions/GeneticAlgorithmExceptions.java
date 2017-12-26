package exceptions;

public class GeneticAlgorithmExceptions extends Exception {
	public enum ErrorNumber{
		outOfBoundsPopulation,
		notMatchSizeOfTours		
	}
	/**
	 * Default serialVesion
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	private ErrorNumber error;
	
	public GeneticAlgorithmExceptions(ErrorNumber error){
		this.error = error; 
		
	}

}