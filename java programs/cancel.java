


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leeaa
 */
public class cancel extends javax.swing.JFrame {

    /**
     * Creates new form cancel
     */
    String username;
    public cancel(String para) {
       username=para;
        initComponents();
       
    }
   
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Enter the ticket no.: ");

        jButton3.setText("CANCEL THE TICKET");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jButton3)
                        .addGap(49, 49, 49)
                        .addComponent(jButton1)))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/railway","leeaa","Physics@science5");
            String querya="Drop trigger if exists trig_a";
            String queryb="Drop trigger if exists trig_b";
            String queryc="Drop trigger if exists trig_c";
            PreparedStatement psta=con.prepareStatement(querya);
            PreparedStatement pstb=con.prepareStatement(queryb);
            PreparedStatement pstc=con.prepareStatement(queryc);
            psta.execute();
            pstb.execute();
            pstc.execute();
            
            String query0="create trigger trig_a\n after \n delete on booking\n for each row\n update available\n set l_berth=l_berth+1\n where train_no=old.train_no and old.berth='L'\n ";
            PreparedStatement pst0=con.prepareStatement(query0);
            pst0.execute();
            
            String query1="create trigger trig_b\n after \n delete on booking\n for each row\n update available\n set m_berth=m_berth+1\n where train_no=old.train_no and old.berth='M'\n ";
            PreparedStatement pst1=con.prepareStatement(query1);
            pst1.execute();
            
            String query2="create trigger trig_c\n after \n delete on booking\n for each row\n update available\n set u_berth=u_berth+1\n where train_no=old.train_no and old.berth='U'\n ";
            PreparedStatement pst2=con.prepareStatement(query2);
            pst2.execute();
            
            String query3="Select * from booking where ticket= "+jTextField1.getText()+"\n and username='"+username+"'";
            PreparedStatement pst3=con.prepareStatement(query3);
            ResultSet rs= pst3.executeQuery();
            if(!rs.next())
            {
            JOptionPane.showMessageDialog(new JFrame(), "no such ticket!", "ERROR", JOptionPane.ERROR_MESSAGE);
             jTextField1.setText("");
            }
            else
            {
            String query4="Delete from booking where ticket="+jTextField1.getText();
            PreparedStatement pst4=con.prepareStatement(query4);
            pst4.execute();
            JOptionPane.showMessageDialog(null,"Successfully cancelled the ticket! "+jTextField1.getText());
            String query5="Drop trigger trig_a";
            String query6="Drop trigger trig_b";
            String query7="Drop trigger trig_c";
            PreparedStatement pst5=con.prepareStatement(query5);
            PreparedStatement pst6=con.prepareStatement(query6);
            PreparedStatement pst7=con.prepareStatement(query7);
            pst5.execute();
            pst6.execute();
            pst7.execute();
            new cancel(username).setVisible(true);
            this.setVisible(false);
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new AFTER_LOGIN(username).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(cancel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cancel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cancel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cancel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
        
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
