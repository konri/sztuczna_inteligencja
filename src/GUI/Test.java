//package GUI;
//
//import exceptions.GeneticAlgorithmExeptions;
//import geneticAlgorithm.GeneticAlgorithm;
//import geneticAlgorithm.Population;
//import geneticAlgorithm.Tour;
//import graph.Node;
//import graph.NodeManager;
//import hamiltonAlgorithm.HamiltonAlgorithm;
//import hamiltonAlgorithm.MatrixPermission;
//
//import java.util.Random;
//
//public class Test {
//
//	public static void main(String[] args)throws GeneticAlgorithmExeptions
//	{
//		Node node0 = new Node();
//		NodeManager.addNode(node0);
//		Node node1 = new Node();
//		NodeManager.addNode(node1);
//		Node node2 = new Node();
//		NodeManager.addNode(node2);
//		Node node3 = new Node();
//		NodeManager.addNode(node3);
//		Node node4 = new Node();
//		NodeManager.addNode(node4);
//		Node node5 = new Node();
//		NodeManager.addNode(node5);
//		Node node6 = new Node();
//		NodeManager.addNode(node6);
//		Node node7 = new Node();
//		NodeManager.addNode(node7);
//
//
//
//
//		MatrixPermission.addPermission(0,1);
//		MatrixPermission.addPermission(0,4);
//		MatrixPermission.addPermission(1,2 );
//		MatrixPermission.addPermission(2,4 );
//		MatrixPermission.addPermission(2,3 );
//		MatrixPermission.addPermission(4,3 );
//		MatrixPermission.addPermission(4,6);
//		MatrixPermission.addPermission(3,6 );
//		MatrixPermission.addPermission(3,5 );
//		MatrixPermission.addPermission(5,7 );
//		MatrixPermission.addPermission(7,4 );
//		MatrixPermission.addPermission(5,2 );
//		MatrixPermission.addPermission(6,7 );
//		MatrixPermission.addPermission(4,3 );
////		MatrixPermission.addPermission(1,3 );
////		MatrixPermission.addPermission(2,8 );
////		MatrixPermission.addPermission(6,4 );
////		MatrixPermission.addPermission(8,9 );
//		//MatrixPermission.allPermission();
//
//		//System.out.print(MatrixPermission.toPrint());
//
//		//Tour newTour = new Tour();
//
////		newTour.setNode(0, node3);
////		newTour.setNode(1, node5);
////		newTour.setNode(2, node7);
////		newTour.setNode(3, node6);
////		newTour.setNode(4, node4);
////		newTour.setNode(5, node2);
////		newTour.setNode(6, node1);
////		newTour.setNode(7, node0);
//
//		MatrixPermission.setReady();
//
////		newTour.generateRandomize();
////
////		while(!HamiltonAlgorithm.checkHamilton(newTour))
////		{
////			newTour.generateRandomize();
////		}
////		System.out.println(newTour + ": " + HamiltonAlgorithm.checkHamilton(newTour));
//
//
////
////		System.out.print("permission:" + 		NodeManager.getPermission(1, 1));
////
////		//System.out.print(MatrixPermission.toPrint());
////
////		System.out.println();
////
//
//		Population testPopulacji = new Population(100, true);
//
////		Random rand = new Random();
//
////
////		System.out.println("Rodzic1: " + testPopulacji.getTour(3));
////		System.out.println("Rodzic2: " + testPopulacji.getTour(5));
////		System.out.println("Child:   " + GeneticAlgorithm.crossOver(testPopulacji.getTour(3), testPopulacji.getTour(5)));
////		GeneticAlgorithm.mutation(testPopulacji.getTour(5));
////		System.out.println("muttation: " + testPopulacji.getTour(5));
////
////		System.out.print(testPopulacji);
////
//
///******************* Test population *************************/
////
////		System.out.println();
////		for(int i =0; i < 10000; i++)
////			if(!GeneticAlgorithm.isFull()){
////				try {
////
////					testPopulacji  = GeneticAlgorithm.solvePopulation(testPopulacji);
////
////
////				} catch (GeneticAlgorithmExeptions e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
////
////			}
//////
////		Population hamiltonPaths = GeneticAlgorithm.getHamiltonPaths();
////
////		System.out.println(hamiltonPaths);
////		System.out.println();
//////
////		for(int i=0; i < GeneticAlgorithm.amountGoodPath; i++)
////		{
////			System.out.println(HamiltonAlgorithm.checkHamilton(hamiltonPaths.getTour(i))
////								+ " : " +
////								hamiltonPaths.getTour(i)
////							   );
////		}
////
//
//
//
///******************* Test tour comparator *************************/
//		Population testExist = new Population(5, true);
//		Tour tour1 = new Tour();
//		tour1.generateRandomize();
//		Tour tour2 = new Tour(tour1.getTour());
//		Tour tour3 = new Tour();
//		tour3.generateRandomize();
//		System.out.println(testExist);
//		testExist.setTour(0, tour3);
//
////		System.out.println("Cos: " + testExist.checkIfExists(tour2));
//		System.out.println("Tour1 : " + tour1);
//		System.out.println("Tour2 : " + tour2);
//		System.out.println("Tour3 : " + tour3);
//		System.out.println("Tour1 to Tour2: " + tour1.compareTours(tour2));
//		System.out.println("Tour1 to Tour3: " + tour1.compareTours(tour3));
////
//
///******************* Test delete tour from population*************************/
////		Population testPopulation = new Population(10, true);
////		System.out.println(testPopulation);
////
////		try {
////			testPopulation.deleteTour(5);
////		} catch (GeneticAlgorithmExeptions e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		System.out.println();
////		System.out.println(testPopulation);
//
//		/******************* Test checkDuplicate*************************/
//		Population testPopulationDuplicate = new Population(20, true);
//
////		Tour tour1 = new Tour();
////		tour1.generateRandomize();
////		Tour tour2 = new Tour(tour1.getTour());
////		Tour tour3 = new Tour();
////		tour3.generateRandomize();
//////		testPopulationDuplicate.setTour(0, tour1);
//////		testPopulationDuplicate.setTour(5, tour2);
//////		testPopulationDuplicate.setTour(7, tour2);
////		testPopulationDuplicate.setTour(2, tour3);
////		testPopulationDuplicate.setTour(9, tour3);
////	System.out.println(testPopulationDuplicate);
////
////		boolean duplicate = false;
////		try {
////			duplicate = testPopulationDuplicate.checkPresenceDuplicates();
////		} catch (GeneticAlgorithmExeptions e1) {
////			// TODO Auto-generated catch block
////			e1.printStackTrace();
////		}
////		if(duplicate){
////			System.out.println("is duplicates");
////			try {
////			System.out.println("How many: "+testPopulationDuplicate.removeDuplicates());
////			} catch (GeneticAlgorithmExeptions e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////		}
////		System.out.println(testPopulationDuplicate);
//		}//main
//} // class
//
//
