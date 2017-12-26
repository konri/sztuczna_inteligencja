package GUI;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import exceptions.GeneticAlgorithmExceptions;
import geneticAlgorithm.GeneticAlgorithm;
import geneticAlgorithm.Population;
import geneticAlgorithm.Tour;
import geneticAlgorithm.Tour.MethodFitness;
import graph.Node;
import graph.NodeManager;
import hamiltonAlgorithm.HamiltonAlgorithm;
import hamiltonAlgorithm.MatrixPermission;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;
import saveUtil.SaveUtilis;
import travelingSalesmanProblem.SalesmanPath;
import travelingSalesmanProblem.SalesmanPathReturn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class InterfaceHamilton extends JFrame {

    private static final long serialVersionUID = 1L;
    private LinkedList<Integer> nodeList = new LinkedList<Integer>();
    private SparseGraph<Integer, Integer> g;
    private SparseGraph<Integer, Integer> graph_generator;

    private JPanel contentPane;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    //up panel:
    private JDesktopPane upPanel;
    private JPanel algorytmPanel;
    private JPanel nodePanel; // nodePanel
    private JButton btnNodeGenerate; //button for generate nodes.
    private JSpinner nodeSpinner; // spinner from nodePanel
    private JPanel permissionPanel;
    private JButton btnShowArray;
    private JButton btnCycle;
    private JButton btnCyclePrev;
    private JButton btnCycle_generator;
    private JButton btnCyclePrev_generator;
    private JPanel geneticAlgorithPanel;
    private JSpinner populationSpinner;
    private JSpinner muttationSpinner;
    private JSpinner tournamentSpinner;
    private JSpinner iterationSpinner;
    private JPanel hamiltonPanel_1;
    private JSpinner hamiltonSpinner;
    private JPanel eliminacjaPanel;
    private JRadioButton rdbtnWszystkieMiasta;
    private JRadioButton rdbtnMiastaLiczoneZ;
    private JButton btnUruchom;

    /*
     * elements of algorithm
     */
    private NodeManager nodeManager = null;
    private MatrixPermission matrix = null;
    private MatrixPermission matrix_generator = null;
    private Node tabNodes[];
    private Node tabNodes_generator[];
    private GeneticAlgorithm geneticAlgorithm = null;
    private int[][] tab;
    private int[][] tab_generator;

    private int tours;
    private SaveUtilis saveUtilis;
    JFrame frame;
    private JLabel text;
    private JPanel panel;
    Factory<Integer> edgeFactory;
    Layout<Integer, Integer> layout;
    private int iloscRazy = 0;
    private int it = -1;
    private int it_generator = -1;
    private JPanel CyclePane;

    JLabel text_generator;
    Factory<Integer> edgeFactory_generator;
    Layout<Integer, Integer> layout_generator;


//	private XYSeries[] series = new XYSeries[3];

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfaceHamilton frame = new InterfaceHamilton();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public InterfaceHamilton() {

        /*
         * default information about GUI view
         */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 768);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        /*
         * Up Panel
         */
        upPanel = new JDesktopPane();
        upPanel.setBackground(Color.DARK_GRAY);
        upPanel.setBounds(800, 0, 224, 768);
        contentPane.add(upPanel);

        algorytmPanel = new JPanel();
        algorytmPanel.setLayout(null);
        algorytmPanel.setBackground(Color.DARK_GRAY);
        algorytmPanel.setBounds(0, 0, 800, 40);
        contentPane.add(algorytmPanel);

        btnCycle = new JButton(">");
        btnCyclePrev = new JButton("<");
        int padding = 150;
        btnCycle.setBounds(300 + padding, 5, 50, 33);
        btnCyclePrev.setBounds(200 + padding, 5, 50, 33);
        text = new JLabel("-/-");
        text.setBounds(263 + padding, 5, 70, 33);
        final JLabel uruchomAglorytm = new JLabel("Proszę wygenerować graf, a następnie uruchomić algorytm!");
        uruchomAglorytm.setForeground(Color.white);
        text.setForeground(Color.white);
        uruchomAglorytm.setBounds(200, 5, 500, 33);

        algorytmPanel.add(btnCycle);
        algorytmPanel.add(btnCyclePrev);
        algorytmPanel.add(text);
        algorytmPanel.add(uruchomAglorytm);
        btnCycle.setVisible(false);
        btnCyclePrev.setVisible(false);
        text.setVisible(false);


        /*
         * Generate node panel
         */
        nodePanel = new JPanel();
        nodePanel.setBackground(Color.DARK_GRAY);
        nodePanel.setBorder(new TitledBorder(null, "Generator węzłów", TitledBorder.CENTER, TitledBorder.TOP, null, Color.white));
        nodePanel.setForeground(Color.LIGHT_GRAY);
        nodePanel.setBounds(9, 10, 200, 106);
        upPanel.add(nodePanel);
        nodePanel.setLayout(null);

        nodeSpinner = new JSpinner();
        nodeSpinner.setModel(new SpinnerNumberModel(new Integer(3), new Integer(3), null, new Integer(1)));
        nodeSpinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
        nodeSpinner.setBounds(57, 23, 76, 28);
        nodePanel.add(nodeSpinner);


        btnNodeGenerate = new JButton("Generuj");
        btnNodeGenerate.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNodeGenerate.addActionListener(new ActionListener() {

            private JPanel CyclePane;


            public void actionPerformed(ActionEvent arg0) {
                int amountNodes = (Integer) nodeSpinner.getValue();
                if (amountNodes < 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Ilość węzłów nie może być ujemna",
                            "ErrorException -- nie ma mozliwosci wygenerowania",
                            JOptionPane.ERROR_MESSAGE);

                } else {
                    edgeFactory = new Factory<Integer>() {
                        int i = 0;

                        public Integer create() {
                            return i++;
                        }

                    };
                    it = -1;
                    System.out.print(amountNodes);
                    tabNodes = new Node[amountNodes];
                    matrix = new MatrixPermission();
                    nodeManager = new NodeManager(matrix);
                    double probability = 0.5;
                    g = new SparseGraph<Integer, Integer>();
                    for (int i = 0; i < amountNodes; i++) {
                        g.addVertex(i);
                        tabNodes[i] = new Node();
                        nodeManager.addNode(tabNodes[i]);
                    }



                    for (int i = 0; i < amountNodes; i++) {
                        for (int j = i + 1; j < amountNodes; j++) {
                            if (Math.random() < probability) {
                                g.addEdge(edgeFactory.create(), i, j);
                                matrix.addDistance(i, j);
                            }
                        }

                    }

                    btnCycle.setVisible(true);
                    btnCyclePrev.setVisible(true);
                    text.setVisible(true);
                    uruchomAglorytm.setVisible(false);


                    printGraph();
                }
            }

            //funkcja odpowiedzialna za rysowanie grafu
            private void printGraph() {

                layout = new CircleLayout<Integer, Integer>(g);
                layout.setSize(new Dimension(600, 600));
                final BasicVisualizationServer<Integer, Integer> visualisation =
                        new BasicVisualizationServer<Integer, Integer>(layout);
                visualisation.setBounds(80, 70, 720, 668);
                visualisation.getRenderContext().setVertexFillPaintTransformer(new Transformer<Integer, Paint>() {
                    @Override
                    public Paint transform(Integer integer) {
                        return Color.WHITE;
                    }
                });
                visualisation.getRenderContext().setVertexShapeTransformer(new Transformer<Integer, Shape>() {
                    @Override
                    public Shape transform(Integer integer) {
                        return new Ellipse2D.Double(-20, -20, 40, 40);
                    }
                });
                visualisation.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Integer>());
                visualisation.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
                iloscRazy = 0;
                btnCycle.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        if (tab != null) {
                            //reset ustawien do grafu nieskierowanego
                            Collection<Integer> tmp_e = g.getEdges();

                            for (int i : tmp_e) {

                                int v1 = g.getEndpoints(i).getFirst();
                                int v2 = g.getEndpoints(i).getSecond();
                                g.removeEdge(i);
                                g.addEdge(i, v1, v2, EdgeType.UNDIRECTED);
                            }

                            it++;
                            System.out.print(it);
                            //zaznaczenie pierwszego wierzcholka w cyklu
                            if (it >= tours) {
                                it = 0;
                            }
                            Transformer<Integer, Paint> vertexColor = new Transformer<Integer, Paint>() {
                                public Paint transform(Integer i) {
                                    if (i == tab[it][0]) return Color.GREEN;
                                    return Color.WHITE;
                                }
                            };

                            //ustawienie strzalek w grafie bedacymi przedstawieniem cyklu w grafie
                            for (int i = 0; i < g.getVertexCount() - 1; i++) {
                                if ((g.findEdge(tab[it][i], tab[it][i + 1])) != null) {
                                    int e = g.findEdge(tab[it][i], tab[it][i + 1]);
                                    g.removeEdge(e);
                                    g.addEdge(e, tab[it][i], tab[it][i + 1], EdgeType.DIRECTED);
                                    if (i == (g.getVertexCount() - 2) && (g.findEdge(tab[it][i + 1], tab[it][0])) != null) {
                                        int e1 = g.findEdge(tab[it][i + 1], tab[it][0]);
                                        g.removeEdge(e1);
                                        g.addEdge(e1, tab[it][i + 1], tab[it][0], EdgeType.DIRECTED);
                                    }
                                }

                            }

                            //kolorowanie krawędzi cyklu
                            Transformer<Integer, Paint> edgesPaint = new Transformer<Integer, Paint>() {
                                public Paint transform(Integer i) {
                                    Collection<Integer> edges = g.getEdges(EdgeType.DIRECTED);
                                    for (int e : edges) {
                                        if (i == e) return Color.GREEN;

                                    }
                                    return Color.BLACK;
                                }
                            };


                            layout = new CircleLayout<Integer, Integer>(g);
                            int k = it;
                            text.setText(++k + "/" + tours);
                            visualisation.setGraphLayout(layout);
                            visualisation.getRenderContext().setEdgeDrawPaintTransformer(edgesPaint);
                            visualisation.getRenderContext().setVertexFillPaintTransformer(vertexColor);
                            repaint();

                        } else {
                            if (iloscRazy < 1) {
                                iloscRazy++;
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Musisz najpierw uruchomic algorytm!",
                                        "",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }

                    }
                });
                btnCyclePrev.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {

                        if (tab != null) {
                            //reset ustawien do grafu nieskierowanego
                            Collection<Integer> tmp_e = g.getEdges();

                            for (int i : tmp_e) {

                                int v1 = g.getEndpoints(i).getFirst();
                                int v2 = g.getEndpoints(i).getSecond();
                                g.removeEdge(i);
                                g.addEdge(i, v1, v2, EdgeType.UNDIRECTED);
                            }

                            it--;
                            //zaznaczenie pierwszego wierzcholka w cyklu
                            if (it < 0) {
                                it = tours - 1;
                            }
                            Transformer<Integer, Paint> vertexColor = new Transformer<Integer, Paint>() {
                                public Paint transform(Integer i) {
                                    if (i == tab[it][0]) return Color.GREEN;
                                    return Color.WHITE;
                                }
                            };

                            //ustawienie strzalek w grafie bedacymi przedstawieniem cyklu w grafie
                            for (int i = 0; i < g.getVertexCount() - 1; i++) {
                                if ((g.findEdge(tab[it][i], tab[it][i + 1])) != null) {
                                    int e = g.findEdge(tab[it][i], tab[it][i + 1]);
                                    g.removeEdge(e);
                                    g.addEdge(e, tab[it][i], tab[it][i + 1], EdgeType.DIRECTED);
                                    if (i == (g.getVertexCount() - 2) && (g.findEdge(tab[it][i + 1], tab[it][0])) != null) {
                                        int e1 = g.findEdge(tab[it][i + 1], tab[it][0]);
                                        g.removeEdge(e1);
                                        g.addEdge(e1, tab[it][i + 1], tab[it][0], EdgeType.DIRECTED);
                                    }
                                }

                            }

                            //kolorowanie krawędzi cyklu
                            Transformer<Integer, Paint> edgesPaint = new Transformer<Integer, Paint>() {
                                public Paint transform(Integer i) {
                                    Collection<Integer> edges = g.getEdges(EdgeType.DIRECTED);
                                    for (int e : edges) {
                                        if (i == e) return Color.GREEN;

                                    }
                                    return Color.BLACK;
                                }
                            };


                            layout = new CircleLayout<Integer, Integer>(g);
                            int k = it;
                            text.setText(++k + "/" + tours);
                            visualisation.setGraphLayout(layout);
                            visualisation.getRenderContext().setEdgeDrawPaintTransformer(edgesPaint);
                            visualisation.getRenderContext().setVertexFillPaintTransformer(vertexColor);
                            repaint();

                        } else {
                            if (iloscRazy < 1) {
                                iloscRazy++;
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Musisz najpierw uruchomic algorytm!",
                                        "",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }

                    }
                });


                tab = null;
                contentPane.add(visualisation);
            }

        });

        btnNodeGenerate.setBounds(44, 62, 99, 33);
        nodePanel.add(btnNodeGenerate);

        /*
         * Permission Panel
         */
        permissionPanel = new JPanel();
        permissionPanel.setBorder(new TitledBorder(null, "Uprawnienia", TitledBorder.CENTER, TitledBorder.TOP, null, Color.white));
        permissionPanel.setBackground(Color.DARK_GRAY);
        permissionPanel.setBounds(9, 116, 200, 80);
        upPanel.add(permissionPanel);
        permissionPanel.setLayout(null);

        btnShowArray = new JButton("Poka\u017C \r\ntablice");
        btnShowArray.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                ArrayList<String> columnNames = new ArrayList<String>();
                columnNames.add("");
                for (int i = 0; i < matrix.getAmountNodes(); i++) {
                    columnNames.add("miasto " + i);
                }
                String[] stockArr = new String[columnNames.size()];
                stockArr = columnNames.toArray(stockArr);
                System.out.println(matrix);


                Object[][] firstColumn = new Object[matrix.getAmountNodes()][matrix.getAmountNodes() + 1];
                for (int i = 0; i < matrix.getAmountNodes(); i++) {
                    Object[] addingColumn = new Object[matrix.getAmountNodes() + 1];
                    for (int j = 0; j < matrix.getAmountNodes(); j++) {
                        if (j == 0) {
                            addingColumn[j] = "miasto " + (i + 1);
                        }
                        if (matrix.getPermission(i, j)) {
                            System.out.println("matrix: " + String.valueOf(matrix.getDistance(i, j)));
                            addingColumn[j + 1] = String.valueOf(matrix.getDistance(i, j));
                        } else {
                            addingColumn[j + 1] = "---";
                        }
                    }
                    firstColumn[i] = addingColumn;
                }

                DefaultTableModel model = new DefaultTableModel(firstColumn, stockArr);
                JTable table = new JTable(model) {
                    //  Returning the Class of each column will allow different
                    //  renderers to be used based on Class
                    public Class getColumnClass(int column) {
                        return getValueAt(0, column).getClass();
                    }
                };
                table.setPreferredScrollableViewportSize(table.getPreferredSize());
                Color color = UIManager.getColor("Table.gridColor");
                MatteBorder border = new MatteBorder(1, 1, 0, 0, color);
                table.setShowGrid(true);
                table.setGridColor(Color.black);
                table.setEnabled(false);
                JScrollPane scrollPane = new JScrollPane(table);
                frame.add(scrollPane, BorderLayout.CENTER);
                int width = table.getPreferredSize().width;

                frame.setSize(width, width / 2);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });

        btnShowArray.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnShowArray.setBounds(43, 27, 101, 33);
        permissionPanel.add(btnShowArray);

        /*
         * Genetic Algorithm parameters panel.
         */

        geneticAlgorithPanel = new JPanel();
        geneticAlgorithPanel.setBackground(Color.DARK_GRAY);
        geneticAlgorithPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Algorytm genetyczny", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));
        geneticAlgorithPanel.setBounds(9, 200, 200, 220);
        upPanel.add(geneticAlgorithPanel);
        geneticAlgorithPanel.setLayout(null);


        populationSpinner = new JSpinner();
        populationSpinner.setModel(new SpinnerNumberModel(new Integer(20), new Integer(10), null, new Integer(5)));
        populationSpinner.setBounds(70, 34, 70, 28);
        geneticAlgorithPanel.add(populationSpinner);

        JLabel lblWielkoPopulacji = new JLabel("<html><center>Wielkość populacji</center></html>");
        lblWielkoPopulacji.setForeground(Color.white);
        lblWielkoPopulacji.setBounds(40, 18, 200, 14);
        geneticAlgorithPanel.add(lblWielkoPopulacji);


        muttationSpinner = new JSpinner();
        muttationSpinner.setModel(new SpinnerNumberModel(new Double(0.1), new Double(0.05), new Double(1), new Double(0.05)));

        muttationSpinner.setBounds(70, 80, 70, 28);
        geneticAlgorithPanel.add(muttationSpinner);

        JLabel lblMuttationSeek = new JLabel("<html><center>Współczynnik mutacji</center></html>");
        lblMuttationSeek.setForeground(Color.white);
        lblMuttationSeek.setBounds(30, 63, 200, 14);
        geneticAlgorithPanel.add(lblMuttationSeek);

        tournamentSpinner = new JSpinner();
        tournamentSpinner.setModel(new SpinnerNumberModel(new Integer(10), new Integer(2), null, new Integer(5)));
        tournamentSpinner.setBounds(70, 138, 70, 28);
        geneticAlgorithPanel.add(tournamentSpinner);

        JLabel lblTournamentSize = new JLabel("<html><center>Krzyżowanie <br> (wielkość turnieju)</center></html>");
        lblTournamentSize.setForeground(Color.white);
        lblTournamentSize.setBounds(40, 104, 200, 34);
        geneticAlgorithPanel.add(lblTournamentSize);

        iterationSpinner = new JSpinner();
        iterationSpinner.setModel(new SpinnerNumberModel(new Integer(100), new Integer(0), null, new Integer(100)));
        iterationSpinner.setBounds(70, 180, 70, 28);
        geneticAlgorithPanel.add(iterationSpinner);


        JLabel lblIloIteracji = new JLabel("<html><center>Ilość iteracji</center></html>");
        lblIloIteracji.setForeground(Color.white);
        lblIloIteracji.setBounds(50, 165, 200, 14);
        geneticAlgorithPanel.add(lblIloIteracji);

        hamiltonPanel_1 = new JPanel();
        hamiltonPanel_1.setBounds(9, 420, 200, 80);
        upPanel.add(hamiltonPanel_1);
        hamiltonPanel_1.setBorder(new TitledBorder(null, "Problem komiwojazera", TitledBorder.CENTER, TitledBorder.TOP, null, Color.white));
        hamiltonPanel_1.setBackground(Color.DARK_GRAY);
        hamiltonPanel_1.setLayout(null);

        rdbtnWszystkieMiasta = new JRadioButton("wszystkie miasta \r\n(bez przeskok\u00F3w)");
        rdbtnWszystkieMiasta.setSelected(true);
        rdbtnWszystkieMiasta.setBackground(Color.LIGHT_GRAY);
        rdbtnWszystkieMiasta.setActionCommand("allCity");
        buttonGroup.add(rdbtnWszystkieMiasta);

        btnUruchom = new JButton("Uruchom");
        btnUruchom.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnUruchom.setBounds(25, 30, 150, 34);
        btnUruchom.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {

                int populationSize = (Integer) populationSpinner.getValue();
                int populationSizeCorrectPaths = 50;
                double muttationSeek = (Double) muttationSpinner.getValue();
                int tournamentSize = (Integer) tournamentSpinner.getValue();
                int amountIteration = (Integer) iterationSpinner.getValue();

                MethodFitness method;
                matrix.setReady();
                System.out.print(matrix.toString());

                HamiltonAlgorithm.setMatrixPermission(matrix);
                method = MethodFitness.getAllConnect;

                if (populationSize <= 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Populacja musi byc wieksza od 0!!",
                            "",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Population testPopulacji = new Population(populationSize, true);

                    geneticAlgorithm = new GeneticAlgorithm(populationSizeCorrectPaths, muttationSeek, tournamentSize, method);
                    System.out.println();
                    for (int i = 0; i < amountIteration; i++)
                        if (!geneticAlgorithm.isFull()) {
                            try {
                                testPopulacji = geneticAlgorithm.solvePopulation(testPopulacji);
                            } catch (GeneticAlgorithmExceptions err) {
                                err.printStackTrace();
                            }

                        }

                    tours = geneticAlgorithm.getAmountGoodPath();
                    tab = new int[tours][g.getVertexCount()];
                    Population hamiltonPaths = geneticAlgorithm.getHamiltonPaths(); // population of correct non-duplicated paths.

                    SalesmanPath travelingSalesman = new SalesmanPath(matrix, hamiltonPaths, tours);

                    SalesmanPathReturn salesmanPathReturn = travelingSalesman.getToursSmallestDistance();

                    System.out.println("Correct paths: " + geneticAlgorithm.getAmountGoodPath());

                    System.out.println("komiwojazer: " + salesmanPathReturn);
                    JOptionPane.showMessageDialog(
                            null,
                            "Algorytm zakończony, wygenerowano tras hamiltowskich: " + geneticAlgorithm.getAmountGoodPath() + " \n"
                            + "Komiwojazer: " + salesmanPathReturn,
                            "",
                            JOptionPane.INFORMATION_MESSAGE);

                    for (int i = 0; i < geneticAlgorithm.getAmountGoodPath(); i++) {
                        System.out.println(HamiltonAlgorithm.checkHamilton(hamiltonPaths
                                .getTour(i)) + " : " + hamiltonPaths.getTour(i));
                        tab[i] = hamiltonPaths.getTour(i).getCycle();
                    }
                }

            }
        });

        hamiltonPanel_1.add(btnUruchom);


    }

}
            

