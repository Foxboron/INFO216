package no.uib.info216;


import no.uib.info216.Misc.WeekDates;
import no.uib.info216.RDF.Queries.EventQueries;
import no.uib.info216.RDF.Queries.FacebookQueries;
import no.uib.info216.RDF.Queries.WeatherQuery;
import no.uib.info216.RDF.RDFHandler;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.ListIterator;


/**
 * Created 04.04.2016.
 * Class for setting up the GUI and making it all visible.
 *
 */
public class GUI extends JPanel{
    private int NUMBER_OF_DAYS = 7;

    private ImageIcon icon = createImageIcon("images/middle.gif");

    private ArrayList<JTextArea> tabList;
    private JTabbedPane tabbedPane = new JTabbedPane();

    private ArrayList<JPanel> panelList;

    private RDFHandler rdfHandler;

    // Query handler

    private EventQueries evtQueries;
    private WeatherQuery weatherQuery;
    private FacebookQueries facebookQueries;


    private JTextArea tab2 = new JTextArea("Tab 2");
    private JTextArea tab3 = new JTextArea("Tab 3");
    private JTextArea tab4 = new JTextArea("Tab 4");
    private JTextArea tab5 = new JTextArea("Tab 5");
    private JTextArea tab6 = new JTextArea("Tab 6");
    private JTextArea tab7 = new JTextArea("Tab 7");


    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();
    private JPanel panel6 = new JPanel();
    private JPanel panel7 = new JPanel();


    public GUI(RDFHandler rdfHandler) {
        super(new GridLayout(1, 3));

        this.rdfHandler = rdfHandler;
        this.evtQueries = new EventQueries(rdfHandler);
        this.weatherQuery = new WeatherQuery(rdfHandler);
        this.facebookQueries = new FacebookQueries(rdfHandler);

        //JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("images/middle.gif");
        tabbedPane.setPreferredSize(new Dimension(600, 300));

        // ------------- Tab 1 filled ----------------------------

        // --------------- Mouse Listeners -----------------------

        // -------------------------------------------------------



        // Generates the tab
        this.tabList = this.populateTappedPane();
        this.generateTabTextArea(this.tabList);

        //this.panelList = this.populatePanelList();


        //Add the tabbed pane to this panel.
        add(this.tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    private ArrayList<JTextArea> populateTappedPane() {
        ArrayList<JTextArea> retList = new ArrayList<JTextArea>();
        for(int i = this.NUMBER_OF_DAYS; i != 0; i--){
            retList.add(new JTextArea());
        }
        return retList;
    }

    private void generateTabTextArea(ArrayList<JTextArea> tabList) {
        ArrayList<ArrayList<String>> days = new WeekDates().getWeekDates(this.NUMBER_OF_DAYS);
        ListIterator<ArrayList<String>> daysItems = days.listIterator();


        for(JTextArea jtabText: tabList) {
            ArrayList<String> day = daysItems.next();

            jtabText.setText(day.get(0));
            //JComponent panel = makeTextPanel(day);
            JPanel panel = new JPanel();
            JLabel tab1 = new JLabel(" SUNNY + 'pic?' + 666*C ");
            JLabel tab11 = new JLabel(" RAVE PAR10 + 'klare kje skrifto di' + 'fuck skrifto di' ");
            JLabel tab12 = new JLabel(" RAVE PAR10 + 'klare kje skrifto di' + 'fuck skrifto di' ");
            JLabel tab13 = new JLabel(" RAVE PAR10 + 'klare kje skrifto di' + 'fuck skrifto di' ");
            JLabel suggested = new JLabel("<html><b><font size=+1> Suggested events: </font></b></html>");

            tab11.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    DetailsPanel dp = new DetailsPanel();
                }

                public void mousePressed(MouseEvent e) {

                }

                public void mouseReleased(MouseEvent e) {

                }

                public void mouseEntered(MouseEvent e) {

                }

                public void mouseExited(MouseEvent e) {

                }
            });

            tab12.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    DetailsPanel dp = new DetailsPanel();
                }

                public void mousePressed(MouseEvent e) {
                }

                public void mouseReleased(MouseEvent e) {
                }

                public void mouseEntered(MouseEvent e) {
                }

                public void mouseExited(MouseEvent e) {
                }
            });

            tab13.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    DetailsPanel dp = new DetailsPanel();
                }

                public void mousePressed(MouseEvent e) {

                }

                public void mouseReleased(MouseEvent e) {

                }

                public void mouseEntered(MouseEvent e) {

                }

                public void mouseExited(MouseEvent e) {

                }
            });

            panel.add(tab1, BorderLayout.NORTH);
            panel.add(suggested, BorderLayout.NORTH);
            panel.add(tab11, BorderLayout.CENTER);
            panel.add(tab12, BorderLayout.SOUTH);
            panel.add(tab13);

            tabbedPane.addTab(day.get(0), this.icon, panel, day.get(1));


            tab1.setPreferredSize(new Dimension(550, 40));
            tab11.setPreferredSize(new Dimension(550, 40));
            tab12.setPreferredSize(new Dimension(550, 40));
            tab13.setPreferredSize(new Dimension(550, 40));
            tab11.setBorder(BorderFactory.createTitledBorder(new TitledBorder("")));
            tab12.setBorder(BorderFactory.createTitledBorder(new TitledBorder("")));
            tab13.setBorder(BorderFactory.createTitledBorder(new TitledBorder("")));
        }
    }

    protected JComponent makeTextPanel(ArrayList<String> day) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(day.get(0));
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = GUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    public static void createAndShowGUI(RDFHandler rdfHandler) {
        //Create and set up the window.
        JFrame frame = new JFrame("216");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new GUI(rdfHandler), BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}

