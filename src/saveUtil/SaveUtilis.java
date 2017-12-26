package saveUtil;

import geneticAlgorithm.Population;
import geneticAlgorithm.Tour;
import graph.Node;
import graph.NodeManager;
import hamiltonAlgorithm.MatrixPermission;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Konrad on 23.01.2016.
 */
public class SaveUtilis {
    private final static String PROTECTION = "FILE_IS_CREATED_BY_GENETIC_ALGORITHM";
    private MatrixPermission matrixPermission;
    private Node tabNodes[];
    private Population population;
    private NodeManager nodeManager;


    public SaveUtilis(MatrixPermission matrixPermission, Node tabNodes[], Population population) {
        this.matrixPermission = matrixPermission;
        this.tabNodes = tabNodes;
        this.population = population;
    }

    public SaveUtilis() {

    }

    public boolean write(String path) {
        if (matrixPermission == null || tabNodes == null || population == null)
            return false;
        try {
            File file = new File(path + ".dat");
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(PROTECTION);

            String perrmisionMatrixString = "";

            printWriter.println(matrixPermission.getAmountNodes());
            for (int i = 0; i < matrixPermission.getAmountNodes(); i++) {
                for (int j = 0; j < matrixPermission.getAmountNodes(); j++) {
                    if (matrixPermission.getPermission(i, j))
                        perrmisionMatrixString += i + "," + j + ";";
                }
            }
            printWriter.println(perrmisionMatrixString);

            String nodesCoordinates = "";
            for (Node node : tabNodes) {
                nodesCoordinates += node.getX() + "," + node.getY() + ";";
            }
            printWriter.println(nodesCoordinates);

            printWriter.println(population.getSize());
            String tours = "";
            for (int i = 0; i < population.getSize(); i++) {
                tours += population.getTour(i).toString();
            }

            printWriter.println(tours);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
        return true;

    }

    public boolean read(String path) {
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            List<String> stringList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                stringList.add(line);
            }
            if (!stringList.get(0).equals(PROTECTION))
                return false;

            System.out.println(stringList.get(2));
            String [] splitPermision = stringList.get(2).split("\\;");
            int amoutnMatrixPermission = Integer.parseInt(stringList.get(1));
            System.out.println(amoutnMatrixPermission);
            matrixPermission = new MatrixPermission(amoutnMatrixPermission);

            String[] splitNodesCoordinates = stringList.get(3).split("\\;");
            tabNodes = new Node[amoutnMatrixPermission];
            nodeManager = new NodeManager(matrixPermission);
            for (int i = 0; i < amoutnMatrixPermission; i++) {
//                matrixPermission.addNode(i);

                String s = splitNodesCoordinates[i];
                int x = Integer.parseInt(s.split(",")[0]);
                int y = Integer.parseInt(s.split(",")[1]);
                tabNodes[i] = new Node(x,y);
                nodeManager.addNode(tabNodes[i]);
            }

            for (int i = 0; i < splitPermision.length - 1; i++) {
                String s = splitPermision[i];
                int x = Integer.parseInt(s.split(",")[0]);
                int y = Integer.parseInt(s.split(",")[1]);
                matrixPermission.addPermission(x, y);
            }

            int populationAmount = Integer.parseInt(stringList.get(4));
            population = new Population(populationAmount,false);

            for (int i = 5; i < 5 + populationAmount; i++) {
                String tab[] = stringList.get(i).split("=>");
                ArrayList<Node> tmpArray = new ArrayList<>();
                for (int j = 0; j < tab.length; j++) {
                    tmpArray.add(tabNodes[Integer.parseInt(tab[j])]);
                }
                Tour tour = new Tour(tmpArray);
                population.setTour(i - 5, tour);
            }
                for(int i = 0; i < populationAmount; i++)
                    System.out.println(population.getTour(i).toString());


            System.out.println(matrixPermission);

            fileReader.close();

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean writeToFile(String path) {
        if (matrixPermission == null || tabNodes == null || population == null)
            return false;
        try {
            File file = new File(path + ".txt");
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            String tours = "";
            for (int i = 0; i < population.getSize(); i++) {
                tours += population.getTour(i).toString();
            }

            printWriter.println(tours);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
        return true;
    }

    public MatrixPermission getMatrixPermission() {
        return matrixPermission;
    }

    public Node[] getTabNodes() {
        return tabNodes;
    }

    public Population getPopulation() {
        return population;
    }


    public NodeManager getNodeManager() {
        return nodeManager;
    }
}
