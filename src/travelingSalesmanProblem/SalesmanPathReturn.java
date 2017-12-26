package travelingSalesmanProblem;

import geneticAlgorithm.Tour;

import java.util.List;

public class SalesmanPathReturn {
    private List<Tour> tours;
    private int distance;
    private List<Integer> nodeIndex;

    public SalesmanPathReturn(List<Tour> tours, int distance, List<Integer> nodeIndex) {
        this.tours = tours;
        this.distance = distance;
        this.nodeIndex = nodeIndex;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public int getDistance() {
        return distance;
    }

    public String toString() {
        String context = "trasy: \n";
        for (Tour tour: tours){
            context += tour + "\n";
        }
        context += "dystans: " + this.distance + "\n";
        context += getNodeIndex();
        return context;
    }

    private String getNodeIndex() {
        String index = "index: ";
        for(int i = 0; i < nodeIndex.size();i++) {
            index += String.valueOf(nodeIndex.get(i));
            if (i < nodeIndex.size() -1) index += ", ";
        }
        return index;
    }
}
