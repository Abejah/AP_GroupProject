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

public class StudentDashboard extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private JPanel leftSidePanel;
    private String[] serviceTypeChoices = {"Compliant", "Query"};
    private JList<String> list = new JList<String>(serviceTypeChoices);


    public StudentDashboard()
    {
         frame = new JFrame();
        //ImageIcon icon= new ImageIcon("icon/utech.png");

        //left sidebar panel
        leftSidePanel = new JPanelGradient();
        leftSidePanel.setBounds(0, 0, 240, 750);
        

        //left sidebar label title and customization
        JLabel title =new JLabel();
        title.setText("University of Technology");
        //title.setIcon(new ImageIcon("./icon/utech.png"));
        
        JLabel title1 =new JLabel("Compliant and Query System");    
        JLabel leftSidePanelTitle = new JLabel("Service Type");
        JLabel leftSidePanelTitle1 = new JLabel("Search List");
        JLabel leftSidePanelTitle2 = new JLabel("View List");
        JLabel leftSidePanelTitle3 = new JLabel("Settings");
        JLabel leftSidePanelTitle4 = new JLabel("Logout");
        
        
        ImageIcon ii=new ImageIcon("src/icon/utech.png");
        JLabel icon=new JLabel(ii);
        
        //icon.setIcon(new ImageIcon("../icon/));
        

        title.setBounds(25,5,200, 70);
        title.setFont(new Font("Times New Roman", Font.BOLD, 15));
        title.setForeground(Color.WHITE);
        
        title1.setBounds(19,25,200, 70);
        title1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        title1.setForeground(Color.WHITE);
        
        icon.setBounds(58,60,100,100);
        
        leftSidePanelTitle.setBounds(20, 180, 150, 30);
        leftSidePanelTitle.setFont(new Font("Times New Roman", Font.BOLD, 15));
        leftSidePanelTitle.setForeground(Color.WHITE);
        leftSidePanelTitle.setIcon(new ImageIcon("src/icon/2.png"));

        list.setBounds(20, 220, 100, 45);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setFont(new Font("Serif", Font.BOLD, 15));

        leftSidePanelTitle1.setBounds(20, 280, 150, 30);
        leftSidePanelTitle1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        leftSidePanelTitle1.setForeground(Color.WHITE);
        leftSidePanelTitle1.setIcon(new ImageIcon("src/icon/4.png"));

        leftSidePanelTitle2.setBounds(20, 380, 150, 30);
        leftSidePanelTitle2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        leftSidePanelTitle2.setForeground(Color.WHITE);
        leftSidePanelTitle2.setIcon(new ImageIcon("src/icon/3.png"));
        
        leftSidePanelTitle3.setBounds(20, 480, 150, 30);
        leftSidePanelTitle3.setFont(new Font("Times New Roman", Font.BOLD, 15));
        leftSidePanelTitle3.setForeground(Color.WHITE);
        leftSidePanelTitle3.setIcon(new ImageIcon("src/icon/5.png"));
        
        leftSidePanelTitle4.setBounds(20, 580, 150, 30);
        leftSidePanelTitle4.setFont(new Font("Times New Roman", Font.BOLD, 15));
        leftSidePanelTitle4.setForeground(Color.WHITE);
        leftSidePanelTitle4.setIcon(new ImageIcon("src/icon/6.png"));
        

        frame.add(title);
        frame.add(title1);
        frame.add(icon);
        frame.add(leftSidePanelTitle);
        frame.add(list);
        frame.add(leftSidePanelTitle1);
        frame.add(leftSidePanelTitle2);
        frame.add(leftSidePanelTitle3);
        frame.add(leftSidePanelTitle4);

        list.setCellRenderer(new DefaultListCellRenderer() {
         	private static final long serialVersionUID = 1L;

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
        leftSidePanelTitle3.setOpaque(false);
        leftSidePanelTitle3.addMouseListener(adapter);
        leftSidePanelTitle4.setOpaque(false);
        leftSidePanelTitle4.addMouseListener(adapter);

        leftSidePanelTitle1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                SearchForm searchForm =new SearchForm();
                searchForm.setVisible(true);

            }
        });


        frame.setSize(1040, 700);
        frame.setTitle("Dashboard");
        frame.add(leftSidePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public static void main(String args[])
    {
        new StudentDashboard();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}