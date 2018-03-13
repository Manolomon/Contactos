/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ContactoDAO;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.pojos.Contacto;

/**
 *
 * @author Manolo
 */
public class MainContactos extends javax.swing.JFrame {

    private List<Contacto> contactos;
    private DefaultListModel modelo;

    /**
     * Creates new form MainContactos
     */
    public MainContactos() {
        initComponents();
        cargarContactos(null);
        this.PanelDatos.setVisible(false);
    }

    private void cargarContactos(String nombre) {
        if (nombre == null) {
            contactos = ContactoDAO.getAllContactos();
        } else {
            contactos = ContactoDAO.buscarContacto(nombre);
        }
        modelo = new DefaultListModel();
        if (contactos != null) {
            for (Contacto c : contactos) {
                modelo.addElement(c.getNombre());
            }
            lista.setModel(modelo);
        }
    }
    
    private boolean camposIncompletos(){
        return txt_Nombre.getText().isEmpty() ||txt_Telefono.getText().isEmpty() || txt_Correo.getText().isEmpty() || txt_Direccion.getText().isEmpty();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelContactos = new javax.swing.JPanel();
        PanelBuscar = new javax.swing.JPanel();
        txt_Buscar = new javax.swing.JTextField();
        btn_Buscar = new javax.swing.JButton();
        PanelLista = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList<>();
        PanelAgregar = new javax.swing.JPanel();
        btn_Agregar = new javax.swing.JButton();
        PanelDatos = new javax.swing.JPanel();
        PanelTag = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbl_header = new javax.swing.JLabel();
        lbl_cumple = new javax.swing.JLabel();
        btn_Borrar = new javax.swing.JButton();
        PanelTexts = new javax.swing.JPanel();
        lbl_Nombre = new javax.swing.JLabel();
        txt_Nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_Apodo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_Telefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_Direccion = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        txt_Correo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jdc_dob = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        btn_Cancelar = new javax.swing.JButton();
        btn_Guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        PanelContactos.setBackground(new java.awt.Color(255, 255, 255));
        PanelContactos.setLayout(new java.awt.BorderLayout());

        PanelBuscar.setBackground(new java.awt.Color(255, 255, 255));
        PanelBuscar.setLayout(new java.awt.BorderLayout());

        txt_Buscar.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_Buscar.setMinimumSize(new java.awt.Dimension(20, 40));
        txt_Buscar.setPreferredSize(new java.awt.Dimension(150, 22));
        txt_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BuscarActionPerformed(evt);
            }
        });
        PanelBuscar.add(txt_Buscar, java.awt.BorderLayout.CENTER);

        btn_Buscar.setBackground(new java.awt.Color(203, 203, 203));
        btn_Buscar.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_Buscar.setText("Buscar");
        btn_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BuscarActionPerformed(evt);
            }
        });
        PanelBuscar.add(btn_Buscar, java.awt.BorderLayout.EAST);

        PanelContactos.add(PanelBuscar, java.awt.BorderLayout.PAGE_START);

        PanelLista.setBackground(new java.awt.Color(255, 255, 255));
        PanelLista.setLayout(new javax.swing.BoxLayout(PanelLista, javax.swing.BoxLayout.Y_AXIS));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        lista.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(lista);

        PanelLista.add(jScrollPane1);

        PanelContactos.add(PanelLista, java.awt.BorderLayout.CENTER);

        PanelAgregar.setBackground(new java.awt.Color(255, 255, 255));

        btn_Agregar.setBackground(new java.awt.Color(203, 203, 203));
        btn_Agregar.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_Agregar.setText("Agregar");
        btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarActionPerformed(evt);
            }
        });
        PanelAgregar.add(btn_Agregar);

        PanelContactos.add(PanelAgregar, java.awt.BorderLayout.SOUTH);

        getContentPane().add(PanelContactos, java.awt.BorderLayout.CENTER);

        PanelDatos.setLayout(new java.awt.BorderLayout());

        PanelTag.setBackground(new java.awt.Color(217, 217, 217));
        PanelTag.setLayout(new java.awt.BorderLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ic_account_circle_grey600_48dp.png"))); // NOI18N
        PanelTag.add(jLabel1, java.awt.BorderLayout.WEST);

        jPanel1.setBackground(new java.awt.Color(217, 217, 217));
        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        lbl_header.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        lbl_header.setText("Nombre de Contacto");
        jPanel1.add(lbl_header);

        lbl_cumple.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lbl_cumple.setForeground(new java.awt.Color(103, 103, 103));
        lbl_cumple.setText("No. días para cumpleaños");
        jPanel1.add(lbl_cumple);

        PanelTag.add(jPanel1, java.awt.BorderLayout.CENTER);

        btn_Borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ic_delete_circle_grey600_24dp.png"))); // NOI18N
        btn_Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BorrarActionPerformed(evt);
            }
        });
        PanelTag.add(btn_Borrar, java.awt.BorderLayout.EAST);

        PanelDatos.add(PanelTag, java.awt.BorderLayout.NORTH);

        PanelTexts.setBackground(new java.awt.Color(255, 255, 255));
        PanelTexts.setLayout(new javax.swing.BoxLayout(PanelTexts, javax.swing.BoxLayout.Y_AXIS));

        lbl_Nombre.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_Nombre.setText("Nombre");
        PanelTexts.add(lbl_Nombre);

        txt_Nombre.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_Nombre.setPreferredSize(new java.awt.Dimension(150, 22));
        txt_Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NombreActionPerformed(evt);
            }
        });
        PanelTexts.add(txt_Nombre);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Apodo (Opcional)");
        PanelTexts.add(jLabel5);

        txt_Apodo.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_Apodo.setPreferredSize(new java.awt.Dimension(150, 22));
        PanelTexts.add(txt_Apodo);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Teléfono");
        PanelTexts.add(jLabel6);

        txt_Telefono.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_Telefono.setPreferredSize(new java.awt.Dimension(150, 22));
        PanelTexts.add(txt_Telefono);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setText("Dirección");
        PanelTexts.add(jLabel7);

        txt_Direccion.setColumns(20);
        txt_Direccion.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_Direccion.setRows(5);
        txt_Direccion.setPreferredSize(new java.awt.Dimension(150, 22));
        jScrollPane2.setViewportView(txt_Direccion);

        PanelTexts.add(jScrollPane2);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setText("Correo Electrónico");
        PanelTexts.add(jLabel8);

        txt_Correo.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txt_Correo.setPreferredSize(new java.awt.Dimension(150, 22));
        PanelTexts.add(txt_Correo);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setText("Fecha de Nacimiento");
        PanelTexts.add(jLabel9);

        jdc_dob.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jdc_dob.setPreferredSize(new java.awt.Dimension(150, 22));
        PanelTexts.add(jdc_dob);

        PanelDatos.add(PanelTexts, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btn_Cancelar.setBackground(new java.awt.Color(203, 203, 203));
        btn_Cancelar.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_Cancelar.setText("Cancelar");
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Cancelar);

        btn_Guardar.setBackground(new java.awt.Color(203, 203, 203));
        btn_Guardar.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btn_Guardar.setText("Guardar");
        btn_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Guardar);

        PanelDatos.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(PanelDatos, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BuscarActionPerformed
        if (this.txt_Buscar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Búsqueda Vacía");
            return;
        }
        String nombre = this.txt_Buscar.getText();
        this.cargarContactos(nombre);
    }//GEN-LAST:event_btn_BuscarActionPerformed

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
        txt_Nombre.setText("");
        txt_Apodo.setText("");
        txt_Telefono.setText("");
        txt_Direccion.setText("");
        txt_Correo.setText("");
        jdc_dob.setDate(new Date());
        this.PanelDatos.setVisible(true);
        this.btn_Borrar.setVisible(false);
        lbl_header.setText("");
        lbl_cumple.setVisible(false);
        txt_Nombre.requestFocus();
    }//GEN-LAST:event_btn_AgregarActionPerformed

    private void txt_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NombreActionPerformed

    private void txt_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BuscarActionPerformed

    private void listaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMousePressed
        try {
            Contacto c = contactos.get(lista.getSelectedIndex());
            txt_Nombre.setText(c.getNombre());
            txt_Apodo.setText(c.getApodo());
            txt_Telefono.setText(c.getTelefono());
            txt_Direccion.setText(c.getDireccion());
            txt_Correo.setText(c.getEmail());
            jdc_dob.setDate(c.getFechaNacimiento());
            lbl_header.setText(c.getNombre());
            this.PanelDatos.setVisible(true);
            btn_Borrar.setVisible(true);
            lbl_cumple.setVisible(true);
            Date current = new Date();
            long dias=(int) ((current.getTime() - c.getFechaNacimiento().getTime())/86400000);
            int bis = 1;
            while(dias > 365){
                dias -= 365;
                bis++;
            }
            dias = dias - (bis/4) ;
            lbl_cumple.setText("Faltan " + dias+ " días para su cumpleaños");
        } catch (Exception ev) {
            System.out.println("Fijar mouse antes de hacer click derecho");
        }
    }//GEN-LAST:event_listaMousePressed

    private void btn_BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BorrarActionPerformed
        int resultado = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar el contacto seleccionado?", "Eliminar Contacto",
                JOptionPane.YES_NO_OPTION);
        Contacto c = contactos.get(lista.getSelectedIndex());
        if (resultado == 0) {
            int id = (contactos.get(lista.getSelectedIndex()).getIdContacto());
            if (ContactoDAO.eliminar(id)) {
                JOptionPane.showMessageDialog(this, "Contacto eliminado correctamente");
                this.cargarContactos(null);
            } else {
                JOptionPane.showMessageDialog(this, "No se puedo eliminar el contacto");
            }
        }
    }//GEN-LAST:event_btn_BorrarActionPerformed
    
    private void btn_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarActionPerformed
        if (btn_Borrar.isVisible()) {
            if (!camposIncompletos()){  
                int resultado = JOptionPane.showConfirmDialog(this, "¿Desea modificar los datos del contacto?",
                        "Modificar Contacto", JOptionPane.YES_NO_OPTION);
                Contacto c = contactos.get(lista.getSelectedIndex());
                if (resultado == 0) {
                    int id = (contactos.get(lista.getSelectedIndex()).getIdContacto());
                    Contacto nc;
                    nc = new Contacto(c.getIdContacto(), txt_Nombre.getText(), txt_Telefono.getText(), txt_Correo.getText(), txt_Direccion.getText(), txt_Apodo.getText(), jdc_dob.getDate());
                    if (ContactoDAO.actualizar(nc)) {
                        JOptionPane.showMessageDialog(this, "Contacto modificado correctamente");
                        this.cargarContactos(null);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se puedo modificar el contacto");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Campos Incompletos");
            }
        } else {
            if(!camposIncompletos()){
                Contacto nc;
                nc = new Contacto(txt_Nombre.getText(), txt_Telefono.getText(), txt_Correo.getText(), txt_Direccion.getText(), txt_Apodo.getText(), jdc_dob.getDate());
                if (ContactoDAO.registrar(nc)) {
                    JOptionPane.showMessageDialog(this, "Contacto guardado correctamente");
                    this.cargarContactos(null);
                } else {
                    JOptionPane.showMessageDialog(this, "No se puedo guardar el contacto");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Campos Incompletos");
            }
        }
    }//GEN-LAST:event_btn_GuardarActionPerformed

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        txt_Nombre.setText("");
        txt_Apodo.setText("");
        txt_Telefono.setText("");
        txt_Direccion.setText("");
        txt_Correo.setText("");
        jdc_dob.setDate(new Date());
        this.PanelDatos.setVisible(false);
        this.btn_Borrar.setVisible(false);
        lbl_cumple.setVisible(false);
    }//GEN-LAST:event_btn_CancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (InstantiationException ex) {
            Logger.getLogger(MainContactos.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainContactos.class.getName()).log(Level.SEVERE, null, ex);

        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainContactos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainContactos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelAgregar;
    private javax.swing.JPanel PanelBuscar;
    private javax.swing.JPanel PanelContactos;
    private javax.swing.JPanel PanelDatos;
    private javax.swing.JPanel PanelLista;
    private javax.swing.JPanel PanelTag;
    private javax.swing.JPanel PanelTexts;
    private javax.swing.JButton btn_Agregar;
    private javax.swing.JButton btn_Borrar;
    private javax.swing.JButton btn_Buscar;
    private javax.swing.JButton btn_Cancelar;
    private javax.swing.JButton btn_Guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdc_dob;
    private javax.swing.JLabel lbl_Nombre;
    private javax.swing.JLabel lbl_cumple;
    private javax.swing.JLabel lbl_header;
    private javax.swing.JList<String> lista;
    private javax.swing.JTextField txt_Apodo;
    private javax.swing.JTextField txt_Buscar;
    private javax.swing.JTextField txt_Correo;
    private javax.swing.JTextArea txt_Direccion;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JTextField txt_Telefono;
    // End of variables declaration//GEN-END:variables
}
