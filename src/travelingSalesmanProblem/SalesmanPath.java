package travelingSalesmanProblem;

import geneticAlgorithm.Population;
import geneticAlgorithm.Tour;
import graph.NodeManager;
import hamiltonAlgorithm.MatrixPermission;

import java.util.ArrayList;
import java.util.List;


public class SalesmanPath {
    private MatrixPermission matrixPermission;
    private Population population;
    private int toursAmount;

    public SalesmanPath(MatrixPermission matrixPermission, Population population, int toursAmount) {
        this.matrixPermission = matrixPermission;
        this.population = population;
        this.toursAmount = toursAmount;
    }

    public SalesmanPathReturn getToursSmallestDistance() {
        int[] distances = getDistances();
        int minDistance = 0;

        if (distances.length > 0) {
            minDistance = distances[0];
        }

        for (int i = 1; i < distances.length; i++) {
            if (distances[i] < minDistance) minDistance = distances[i];
        }

        List<Tour> tours = new ArrayList<Tour>();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < toursAmount;i++) {
            if (distances[i] <= minDistance) {
                tours.add(population.getTour(i));
                indexes.add(i);
            }

        }

        return new SalesmanPathReturn(tours, minDistance, indexes);
    }

    private int[] getDistances() {
        int[] distances = new int[toursAmount];

        for (int i = 0; i < toursAmount; i++) {
            distances[i] = this.calculateFullDistance(population.getTour(i));
            System.out.println("i" + i + " distance: " + distances[i]);
        }
        return distances;
    }

    private int calculateFullDistance(Tour tour) {
        int distance = 0;
        for (int i = 0; i < tour.getSize() - 1; i++) {
            int from = NodeManager.staticManager.getIdNodeByRefernce(tour.getNode(i));
            int to = NodeManager.staticManager.getIdNodeByRefernce(tour.getNode(i + 1));
            distance += matrixPermission.getDistance(from, to);
        }
        return distance;
    }

}
