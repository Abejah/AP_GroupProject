package controller;

import model.Student;

public class test {

    public static void main(String[] args) {

        Controller controller = new Controller();

        Student student1 = controller.findIssue("Missing Grades", "1001");

        System.out.println(student1);


        /*JTextField idField = new JTextField();
        JTextField infoField = new JTextField();

        idField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String info = getInfoFromDatabase(id);
                infoField.setText(info);
            }
        });

        private String getInfoFromDatabase (String id){
            String info = "";
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = DriverManager.getConnection("jdbc:mysql://hostname:port/databaseName", "username", "password");
                stmt = conn.prepareStatement("SELECT info FROM tableName WHERE id = ?");
                stmt.setString(1, id);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    info = rs.getString("info");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (rs != null) try {
                    rs.close();
                } catch (SQLException ex) {
                }
                if (stmt != null) try {
                    stmt.close();
                } catch (SQLException ex) {
                }
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }

            return info;
        }*/
    }
}