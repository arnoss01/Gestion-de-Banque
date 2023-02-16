package vue.palette;
import dao.daoFiles.ClientDao;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TablePanel extends JPanel {

    private JTable table;
    private TableModel tableModel;
    private JScrollPane scrollPane;
    private SearchPanel searchPanel;


    private void initTable(){

        tableModel  = new TableModel();
        tableModel.initColumns("Id", "Nom", "Prénom",
                "Login", "Pass", "Cin", "Email", "Tel", "Sexe");
        tableModel.initClientsData(new ClientDao().findAll());

        table       = new JTable(tableModel);
        table.setFont(new Font("Optima", Font.BOLD, 17));
        table.setForeground(new Color(16, 44, 114));
        table.setRowHeight(35);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Optima", Font.BOLD, 20));
        header.setForeground(new Color(198, 113, 34));
        header.setBackground(Color.WHITE);

        ((DefaultTableCellRenderer)header.getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);

        JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);

        scrollPane = new JScrollPane(table);

        searchPanel = new SearchPanel(Color.white);

        initActions();
    }

    private void initActions(){


        searchPanel.getCrudPanel().deleteBtn().addActionListener(e->{

            int row = table.getSelectedRow();
            if(row == -1){

                JOptionPane.showMessageDialog(this,
                        "Veuillez choisir un client d'abord !!!",
                        "A L E R T",
                        JOptionPane.ERROR_MESSAGE);
            }
            else{

                long id           =  (long)tableModel.getValueAt(row, 0);
                String  nom      =  (String)tableModel.getValueAt(row, 1);
                String  prenom   =  (String)tableModel.getValueAt(row, 2);
                String nomComplet = nom + " " + prenom;


                new ClientDao().deleteById(id);
                var list = new ClientDao().findAll();
                tableModel.initClientsData(list);

                JOptionPane.showMessageDialog(this,
                        "Le Client "+nomComplet+ " a été supprimé avec succès",
                        "I N F O",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        });

        searchPanel.getTxt_search().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    searchPanel.getBtn_search().doClick();
                }
            }
        });
        searchPanel.getBtn_search().addActionListener(e->{
            String keyword = searchPanel.getTxt_search().getText();

            var clients =  new ClientDao().findByKeywordLike(keyword);

            tableModel.initClientsData(clients);

        });
    }

    public TablePanel(){

        initTable();
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);
    }
}
