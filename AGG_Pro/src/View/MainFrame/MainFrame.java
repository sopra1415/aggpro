package View.MainFrame;

import Data.Database.DatabaseConnector;
import Data.Database.EventLoader;
import Data.LiveClasses.*;
import View.InputPanes.ManipulateTournament;
import View.MainFrame.OperatingPanes.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Heiko Geppert
 */
public class MainFrame extends javax.swing.JFrame {

    private Event actualEvent;
    private Administrate administrate;
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {

            
            lookAndFeel();
            initComponents();
            initOwnComponents();
            initListener();
            
            initDbConnection();
            this.tbMainFrame.lock();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("AGG Pro Tournament Manager"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void initDbConnection(){
        EventLoader ev = new EventLoader();
        if (ev.getEvents().isEmpty()){
            try {
                //TODO needs to be handled
                this.setActualEvent(new Event("neues Event", new GregorianCalendar(2015, 1,1), new GregorianCalendar(2015,1,1)));
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(AggToolBar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.setActualEvent(new Event(new DatabaseConnector(ev.getEvents().get(0))));
            } catch (ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form
     * with the manually added elements, such as layoutmanagers.
     */
    private void initOwnComponents() {        
        
        this.setLayout(new BorderLayout());        
        
        //initialize components
        initJComponents();
        initTable();
        setComponents();  
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    // own Variables declaration - do modify ;)
    private JPanel panelToolBar;
    private JPanel panelFrame;
    private AggToolBar tbMainFrame;
    private JSplitPane panelMainFrame;
    private JPanel panelTournamentListWithButton;
    private JScrollPane panelTournamentList;
    private JTabbedPane panelTabPane;
    private JButton btnNewTournament;
    
    private JTable tournamentList;
    private DefaultTableModel tournamentListModel;

    private void initTable() {
        tournamentList = new JTable();
        tournamentList.setModel(new DefaultTableModel(new Object[0][0],new String[]{"Turnier"}){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }            
        });
        tournamentListModel = ((DefaultTableModel)tournamentList.getModel());
        tournamentList.setFillsViewportHeight(true);
        //this.update();
    }
    
    private void initJComponents(){
        panelToolBar = new JPanel();
        panelFrame = new JPanel();
        panelFrame.setLayout(new BorderLayout());
        panelTournamentListWithButton = new JPanel();
        panelTournamentList = new JScrollPane(tournamentList);
        panelTabPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        panelMainFrame = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, panelTournamentListWithButton, panelTabPane);
        
        btnNewTournament = new JButton("+");
        tbMainFrame = new AggToolBar(this);
    }
    
    private void setComponents(){
        panelTournamentListWithButton.setLayout(new BorderLayout());
        panelTournamentListWithButton.add(panelTournamentList, BorderLayout.CENTER);
        panelTournamentListWithButton.add(btnNewTournament, BorderLayout.SOUTH);
        panelTabPane.add(new MainMenu(this, null));
        
        // customize components        
        // calculate the Position of the divider
        double dDivLoc = this.getSize().width *0.25d;
        panelMainFrame.setDividerLocation(((int)dDivLoc));        
        panelMainFrame.setDividerSize(3);          
        panelMainFrame.setEnabled(false);
        
        //build the frame, with the components
        panelToolBar.add(tbMainFrame);
        this.add(panelToolBar, BorderLayout.NORTH);
        this.add(panelFrame, BorderLayout.CENTER);
        this.panelFrame.add(panelMainFrame, BorderLayout.CENTER);
    }
    
    private void initListener(){
        tournamentList.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                int row = tournamentList.getSelectedRow();
                // TODO neues operatingpane mit dem entsprechenden turnier öffnen
            }
            
        });
        
        final MainFrame main = this;
        btnNewTournament.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {                
                main.setEnabled(false);
                ManipulateTournament f = new ManipulateTournament(main, ManipulateTournament.state.addTournament);
                f.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        main.setEnabled(true);
                    }
                });
            }
        });
    }
    
    private void lookAndFeel(){
        /* Set the Nimbus look and feel */
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public AggToolBar getAggToolBar(){
        return tbMainFrame;
    }
    
    public Event getActualEvent(){
        return actualEvent;
    }
    
    public void changeTab(JPanel jp){
        this.panelTabPane.remove(panelTabPane.getSelectedComponent());
        this.panelTabPane.add(jp);
        
    }
    
    public void setActualEvent(Event newActualEvent){
        this.panelTabPane.removeAll();
        this.actualEvent = newActualEvent;
        this.administrate = new Administrate(this);
        this.panelTabPane.add(administrate);
        this.tbMainFrame.update();
        update();
        //TODO alle Operating panes schließen
    }
    
    public void update(){
        tournamentList.removeAll();
        tournamentListModel.addRow(new Object[]{"Event-Teilnehmer"});
        ArrayList<Tournament> allTournaments = actualEvent.getTournaments();
        for (Tournament t:allTournaments){
            tournamentListModel.addRow(new Object[] {t.getName()});
        }
    }
    
    public Administrate getAdministrate(){
        return administrate;        
    }
}
