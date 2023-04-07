package view;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DashboardView extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private JPanel leftSidePanel;
    private String[] serviceTypeChoices = {"Compliant", "Query"};
    private JList<String> list = new JList<String>(serviceTypeChoices);


    public DashboardView()
    {
        JPanelGradient jPanelGradient = new JPanelGradient();
        frame = new JFrame();


        //left sidebar panel
        leftSidePanel = new JPanelGradient();
        leftSidePanel.setBounds(0, 0, 240, 680);


        //left sidebar label title and customization
        JLabel leftSidePanelTitle = new JLabel("Service Type");
        JLabel leftSidePanelTitle1 = new JLabel("View List");
        JLabel leftSidePanelTitle2 = new JLabel("Search Complaints");


        leftSidePanelTitle.setBounds(20, 50, 100, 30);
        leftSidePanelTitle.setFont(new Font("Times New Roman", Font.BOLD, 15));
        leftSidePanelTitle.setForeground(Color.WHITE);

        list.setBounds(20, 80, 100, 45);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setFont(new Font("Serif", Font.BOLD, 15));

        leftSidePanelTitle1.setBounds(20, 180, 100, 30);
        leftSidePanelTitle1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        leftSidePanelTitle1.setForeground(Color.WHITE);

        leftSidePanelTitle2.setBounds(20, 280, 100, 30);
        leftSidePanelTitle2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        leftSidePanelTitle2.setForeground(Color.WHITE);


        frame.add(leftSidePanelTitle);
        frame.add(list);
        frame.add(leftSidePanelTitle1);
        frame.add(leftSidePanelTitle2);

        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected,
                        cellHasFocus);
                c.setBackground(new Color(128, 145, 53));
                return c;
            }
        });

        list.setVisible(false);
        // Add an action listener to show or hide the list when clicking on sidebar title label
        leftSidePanelTitle.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (list.isVisible()) {
                    list.setVisible(false);
                } else {
                    list.setVisible(true);
                }

            }
        });
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ((JLabel) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JLabel) e.getSource()).setBorder(null);
            }
        };

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                String Com="Compliant";
                String Que="Query";
                if (!e.getValueIsAdjusting()) {
                    String selectedValue = list.getSelectedValue();
                    if(selectedValue.equals(Com))
                    {
                        StudentForm studentForm =new StudentForm();
                        studentForm.setVisible(true);
                    }
                    else
                    {
                        if (selectedValue.equals(Que)) ;
                        {
                            StudentForm studentForm = new StudentForm();
                            studentForm.setVisible(true);
                        }
                    }

                    //JOptionPane.showMessageDialog(null, "You selected: " + selectedValue);
                }
            }
        });

        leftSidePanelTitle.setOpaque(false);
        leftSidePanelTitle.addMouseListener(adapter);
        leftSidePanelTitle1.setOpaque(false);
        leftSidePanelTitle1.addMouseListener(adapter);
        leftSidePanelTitle2.setOpaque(false);
        leftSidePanelTitle2.addMouseListener(adapter);

        leftSidePanelTitle1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                SearchForm searchForm =new SearchForm();
                searchForm.setVisible(true);

            }
        });


        frame.setSize(1040, 680);
        frame.setTitle("Dashboard");
        frame.add(leftSidePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public static void main(String args[])
    {
        new DashboardView();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}