/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_de_scolarité.PL.AdminAccount;

import gestion_de_scolarité.BL.Administrateur;
import gestion_de_scolarité.DAL.DatabaseConnection;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author slimane
 */
public class ElevesListeDetail extends javax.swing.JFrame {

    /**
     * Creates new form ElevesListeDetail
     */
    public ElevesListeDetail() {
       
        initComponents();
        updateTable2();
        this.setLocationRelativeTo(null);
        
        jTable1.getTableHeader().setForeground(new Color(60,60,60));
        jTable1.getTableHeader().setFont(new Font("segoe UI", Font.BOLD, 12));
        
        //populate combobox of the classes by data from DB
        ArrayList<String> classes = new ArrayList<>();
         String niveau = jComboNiveau.getSelectedItem().toString();
        String annee = anneeCombo.getSelectedItem().toString();
        classes = selectClasse(annee, niveau);
        if (!classes.isEmpty()) {
            jComboClasse.setEnabled(true);
            jComboClasse.setModel(new DefaultComboBoxModel(classes.toArray()));
        }
        //populate combobox by the years that is in the databases
        anneeCombo.setModel(new DefaultComboBoxModel(selectAnnee().toArray()));
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jComboNiveau = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboClasse = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        anneeCombo = new javax.swing.JComboBox<>();
        questionCheckbox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));

        jTextField1.setBackground(new java.awt.Color(254, 254, 254));
        jTextField1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(185, 185, 185));
        jTextField1.setText("Search");
        jTextField1.setToolTipText("");
        jTextField1.setBorder(null);
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestion_de_scolarité/PL/EnseignantAccount/VérifierLaPresence/search.png"))); // NOI18N
        jLabel25.setText("jLabel13");

        jSeparator1.setBackground(new java.awt.Color(206, 202, 198));

        jButton1.setBackground(new java.awt.Color(89, 89, 89));
        jButton1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jButton1.setForeground(new java.awt.Color(254, 254, 254));
        jButton1.setText("Search");

        jComboNiveau.setBackground(new java.awt.Color(254, 254, 254));
        jComboNiveau.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jComboNiveau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1ere année", "2eme année", "3eme année", "4eme année" }));
        jComboNiveau.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboNiveauItemStateChanged(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(60, 60, 60));
        jLabel23.setText("Niveau:");

        jTable1.setBackground(new java.awt.Color(254, 254, 254));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Prénom", "Date né", "Lieu né", "Adress", "Niveau", "Info Detail"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(88, 124, 245));
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jComboClasse.setBackground(new java.awt.Color(254, 254, 254));
        jComboClasse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N1C1", "N1C2", "N1C3", "N1C4" }));
        jComboClasse.setEnabled(false);
        jComboClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboClasseActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(60, 60, 60));
        jLabel24.setText("Classe:");
        jLabel24.setEnabled(false);

        jButton3.setBackground(new java.awt.Color(66, 76, 247));
        jButton3.setForeground(new java.awt.Color(254, 254, 254));
        jButton3.setText("Imprimer");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(66, 76, 247));
        jButton4.setForeground(new java.awt.Color(254, 254, 254));
        jButton4.setText("Diviser");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(66, 76, 247));
        jButton5.setForeground(new java.awt.Color(254, 254, 254));
        jButton5.setText("Annuler");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(60, 60, 60));
        jLabel26.setText("L'année:");
        jLabel26.setEnabled(false);

        anneeCombo.setBackground(new java.awt.Color(254, 254, 254));
        anneeCombo.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        anneeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "all", "2015", "2016", "2017" }));
        anneeCombo.setEnabled(false);
        anneeCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                anneeComboItemStateChanged(evt);
            }
        });

        questionCheckbox.setText("Vous voulez choisé l'année precedent?");
        questionCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                questionCheckboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(questionCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addGap(47, 47, 47))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(anneeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(18, 18, 18)
                                .addComponent(jComboNiveau, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(77, 77, 77)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anneeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboNiveau, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton3)
                        .addComponent(jButton5))
                    .addComponent(questionCheckbox, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboClasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboClasseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboClasseActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Diviseure diviseur = new Diviseure();
        diviseur.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboNiveauItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboNiveauItemStateChanged
        updateTable2();
    }//GEN-LAST:event_jComboNiveauItemStateChanged

    private void anneeComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_anneeComboItemStateChanged
        updateTable2();
    }//GEN-LAST:event_anneeComboItemStateChanged

    private void questionCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_questionCheckboxActionPerformed
        
        if(questionCheckbox.isSelected()){
            anneeCombo.setEnabled(true);
            jLabel26.setEnabled(true);
        }else{
            anneeCombo.setEnabled(false);
            jLabel26.setEnabled(false);
        }
    }//GEN-LAST:event_questionCheckboxActionPerformed
    
    /**
     * this method to fill the table with the data
     */
    public void updateTable2(){
       
          String query = null;
          int idNiveau = 40;
        String niveau = jComboNiveau.getSelectedItem().toString();
        String classe = jComboClasse.getSelectedItem().toString();
        String annee = anneeCombo.getSelectedItem().toString();
        switch(niveau){
            case "1ere année": idNiveau = 40; break;
            case "2eme année": idNiveau = 41; break;
            case "3eme année": idNiveau = 42; break;
            case "4eme année": idNiveau = 43; break;
        }
        if (!jComboClasse.isEnabled() && !anneeCombo.isEnabled()) {
            query = "select nom, prenom,  dateDeNaissance, lieuDeNaissance, adress, niveau " +
                      "from Person as pe right join Eleve as el on pe.idPerson = el.idEleve right join Eleve_Ni_An as ena on " +
                      "ena.idEleve = el.idEleve join Niveau as n on ena.idNiveau = n.idNiveau left join Annee as a " +
                      "on a.idAnnee = ena.idAnnee where n.idNiveau ="+ idNiveau + ";";

        } else { if (!jComboClasse.isEnabled() && anneeCombo.isEnabled()) {
                query = "select nom, prenom,  dateDeNaissance, lieuDeNaissance, adress, niveau " +
                      "from Person as pe right join Eleve as el on pe.idPerson = el.idEleve right join Eleve_Ni_An as ena on " +
                      "ena.idEleve = el.idEleve join Niveau as n on ena.idNiveau = n.idNiveau left join Annee as a " +
                      "on a.idAnnee = ena.idAnnee where n.idNiveau ="+ idNiveau + " and LEFT(a.annee, 4) = '"+ annee +"';";
            } else { if (jComboClasse.isEnabled() && !anneeCombo.isEnabled()) {
                query = "select nom, prenom,  dateDeNaissance, lieuDeNaissance, adress, niveau " +
                      "from Person as pe right join Eleve as el on pe.idPerson = el.idEleve right join Eleve_Ni_An as ena on " +
                      "ena.idEleve = el.idEleve join Niveau as n on ena.idNiveau = n.idNiveau left join Annee as a " +
                      "on a.idAnnee = ena.idAnnee join Classe as c on c.idClasse = ena.idClasse where n.idNiveau ="+ idNiveau + " and c.Classe = '"+ classe +"';";
                
            } else { if (jComboClasse.isEnabled() && anneeCombo.isEnabled()) {
                query = "select nom, prenom,  dateDeNaissance, lieuDeNaissance, adress, niveau " +
                      "from Person as pe right join Eleve as el on pe.idPerson = el.idEleve right join Eleve_Ni_An as ena on " +
                      "ena.idEleve = el.idEleve join Niveau as n on ena.idNiveau = n.idNiveau left join Annee as a " +
                      "on a.idAnnee = ena.idAnnee join Classe as c on c.idClasse = ena.idClasse where n.idNiveau ="+ idNiveau + " and c.Classe = '"+ classe +"' and LEFT(a.annee, 4) = '"+ annee +"';";
            }
            }
            }
            
        }
                   
       
       DatabaseConnection dc = new DatabaseConnection();
        try {
            dc.stmt= dc.conn.createStatement();
            System.out.println("create the statement in getEleve");
            dc.rs= dc.stmt.executeQuery(query);
        jTable1.setModel(DbUtils.resultSetToTableModel(dc.rs));
    }catch (SQLException ex) {
            System.out.println("message from : "+ex);
        }
    }
    /**
     * this method get all the years that found in the table Annee in DB
     * @return 
     */
    public ArrayList<String> selectAnnee(){
        String query = "select Left(annee, 4) from Annee;";
        DatabaseConnection dc = new DatabaseConnection();
        ArrayList<String> annee = new ArrayList<>();
        
        try {
            dc.stmt= dc.conn.createStatement();
            dc.rs= dc.stmt.executeQuery(query);
          
           while(dc.rs.next()){
               annee.add(dc.rs.getString(1));
              }
        } catch (SQLException ex) {
            System.out.println("error message : " +ex);
        }
            System.out.println("create the statement in getEleve");
            
            return annee;
    }
    
    
    public ArrayList<String> selectClasse(String annee, String niveau){
        
        ArrayList<String> classes = new ArrayList<>();
        DatabaseConnection dc = new DatabaseConnection();
        niveau = jComboNiveau.getSelectedItem().toString();
        annee = anneeCombo.getSelectedItem().toString();
        int idNiveau = 40;
        switch(niveau){
            case "1ere année": idNiveau = 40; break;
            case "2eme année": idNiveau = 41; break;
            case "3eme année": idNiveau = 42; break;
            case "4eme année": idNiveau = 43; break;
        }
        String query = "select c.classe from Classe as c right join Eleve_Ni_An as ena on ena.idClasse = c.idClasse left join Niveau as n on n.idNiveau = ena.idNiveau left join Annee as a on a.idAnnee = ena.idAnnee "
                + " where n.idNiveau = " + idNiveau + " and a.annee = '" + annee +"';";
        
        try {
            dc.stmt= dc.conn.createStatement();
            dc.rs= dc.stmt.executeQuery(query);
          
           while(dc.rs.next()){
               classes.add(dc.rs.getString(1));
              }
        } catch (Exception e) {
            System.out.println("error message : " +e);
        }
        
        return classes;
    }
    
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
            java.util.logging.Logger.getLogger(ElevesListeDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ElevesListeDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ElevesListeDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ElevesListeDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ElevesListeDetail().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> anneeCombo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboClasse;
    private javax.swing.JComboBox<String> jComboNiveau;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox questionCheckbox;
    // End of variables declaration//GEN-END:variables
}
