import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class bookings extends javax.swing.JFrame {
    public Connection cn=null;
    public PreparedStatement st=null;
    public ResultSet rss=null;
    public add_hbooking ah;
    public edit_hbooking eh;
    public add_tbooking at;
    public edit_tbooking et;
    public int t;
    public boolean bq;
    public bookings() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(150, 130, 90, 30);

        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(410, 130, 90, 30);

        jLabel2.setFont(new java.awt.Font("Andalus", 1, 16)); // NOI18N
        jLabel2.setText("Hotel Bookings");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(140, 30, 120, 30);

        jLabel3.setFont(new java.awt.Font("Andalus", 1, 16)); // NOI18N
        jLabel3.setText("Travel Bookings");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(400, 30, 130, 30);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "View Booking", "Add Booking", "Delete Booking", "Edit Booking" }));
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(130, 80, 140, 30);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "View Booking", "Add Booking", "Delete Booking", "Edit Booking" }));
        jPanel1.add(jComboBox2);
        jComboBox2.setBounds(390, 80, 140, 30);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(50, 220, 570, 120);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\dell\\Downloads\\11.jpg")); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 670, 380);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void passdata(int a)
    {
        t=a;
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String text = (String)jComboBox2.getSelectedItem();
        if (text.equals("View Booking"))
                showTableData2();
        else if (text.equals("Add Booking"))
        {
                at = new add_tbooking();
                at.passdata(t);
                at.setVisible(true);
        }
        else if (text.equals("Delete Booking"))
        {
            String hi = JOptionPane.showInputDialog(null, "Enter Vehicle_No");
            int x = Integer.parseInt(hi);
            try{
            String sql = "DELETE FROM travel_booking WHERE C_Id = "+t+" AND V_No = "+x+"" ;
            cn = DriverManager.getConnection("jdbc:mysql://localhost/trip_planner","root","");
            st = cn.prepareStatement(sql);
            int y = st.executeUpdate();
            if(y>0)
                JOptionPane.showMessageDialog(null, "Deleted  Successfully.");
            else
                JOptionPane.showMessageDialog(null, "No Such Entry!");
            }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            }
        }
        else
        {
            try
            {
            cn = DriverManager.getConnection("jdbc:mysql://localhost/trip_planner","root","");
            String sql = "SET GLOBAL FOREIGN_KEY_CHECKS=0";
            st = cn.prepareStatement(sql);
            bq=st.execute();
            et = new edit_tbooking();
            et.passdata(t);
            et.setVisible(true);  
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String text = (String)jComboBox1.getSelectedItem();
        if (text.equals("View Booking"))
        {
            try
            {
            cn = DriverManager.getConnection("jdbc:mysql://localhost/trip_planner","root","");
            String sql = "CALL update_expense()";
            st = cn.prepareStatement(sql);
            bq=st.execute();
            showTableData(); 
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        else if (text.equals("Add Booking"))
        {
                ah = new add_hbooking();
                ah.passdata(t);
                ah.setVisible(true);
        }
        else if (text.equals("Delete Booking"))
        {
            String hi = JOptionPane.showInputDialog(null, "Enter Hotel_Id");
            int x = Integer.parseInt(hi);
            try{
            String sql = "DELETE FROM hotel_booking WHERE C_Id = "+t+" AND H_Id = "+x+"" ;
            cn = DriverManager.getConnection("jdbc:mysql://localhost/trip_planner","root","");
            st = cn.prepareStatement(sql);
            int y = st.executeUpdate();
            if(y>0)
                JOptionPane.showMessageDialog(null, "Deleted  Successfully.");
            else
                JOptionPane.showMessageDialog(null, "No Such Entry!");
            }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            }
        }
        else
        {
            try
            {
            cn = DriverManager.getConnection("jdbc:mysql://localhost/trip_planner","root","");
            String sql = "SET GLOBAL FOREIGN_KEY_CHECKS=0";
            st = cn.prepareStatement(sql);
            bq=st.execute();
            eh = new edit_hbooking();
            eh.passdata(t);
            eh.jFrame1.setVisible(true);  
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void showTableData(){
     try{
         cn = DriverManager.getConnection("jdbc:mysql://localhost/trip_planner","root","");
         String sql = "select h.Hotel_Id, h.Name, h.City, hb.Check_In, hb.Check_Out, hb.Expense, hb.Review from hotel h, hotel_booking hb where hb.C_Id = "+t+" AND hb.H_Id = h.Hotel_Id";
         st = cn.prepareStatement(sql);
         rss=st.executeQuery();
         jTable1.setModel(DbUtils.resultSetToTableModel(rss));
        }
     catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);
         }
    }
    
    public void showTableData2(){
     try{
         cn = DriverManager.getConnection("jdbc:mysql://localhost/trip_planner","root","");
         String sql = "select t.Vehicle_No, t.Type, t.Name, t.From_City, t.To_City, tb.Date, t.Fare AS Expense from transport t, travel_booking tb where t.Vehicle_No=tb.V_no AND C_Id="+t+"";
         st = cn.prepareStatement(sql);
         rss=st.executeQuery();
         jTable1.setModel(DbUtils.resultSetToTableModel(rss));
        }
     catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);
         }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bookings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
