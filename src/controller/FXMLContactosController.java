/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.pojos.Contacto;

/**
 * Controlador principal del la interfaz
 * @author Manolo
 * @since 03/18/2018
 * @version 0.6
 */
public class FXMLContactosController implements Initializable {
    @FXML
    private StackPane stackDialogPane;
    @FXML
    private JFXListView<Label> contactList;
    @FXML
    private JFXTextField txt_buscar;
    @FXML
    private JFXButton btn_addContact;
    @FXML
    private AnchorPane contactPane;
    @FXML
    private Label lbl_nombre;
    @FXML
    private Label lbl_cumple;
    @FXML
    private JFXTextField txt_nombre;
    @FXML
    private JFXTextField txt_apodo;
    @FXML
    private JFXTextField txt_telefono;
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXDatePicker txt_date;
    @FXML
    private JFXTextField txt_direccion;
    @FXML
    private ImageView imageStandBy;
    @FXML
    private JFXButton btn_aceptar;
    @FXML
    private JFXButton btn_cancelar;

    private JFXPopup popup;
    private JFXButton btn_eliminar;

    private List<Contacto> contactos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarContactos(null);
        initPopup();
        lbl_cumple.setVisible(false);
        limpiarCampos();
        contactPane.setVisible(false);
        imageStandBy.setVisible(true);
    }

    public void cargarContactos(String nombre) {
        contactList.getItems().clear();
        if (nombre == null) {
            contactos = ContactoDAO.getAllContactos();
        } else {
            contactos = ContactoDAO.buscarContacto(nombre);
        }
        if (contactos != null) {
            for (Contacto c : contactos) {
                try {
                    Label lbl = new Label(c.getNombre());
                    lbl.setGraphic(new ImageView(new Image(
                            getClass().getResourceAsStream("/resources/ic_account_circle_grey600_48dp(1).png"))));
                    contactList.getItems().add(lbl);
                } catch (Exception ex) {
                    System.err.println("Error: " + ex);
                }
            }
        }
    }

    public void eliminarContacto() {
        int id = contactos.get(contactList.getSelectionModel().getSelectedIndex()).getIdContacto();
        if (ContactoDAO.eliminar(id)) {
            showDialog("Eliminado", "Contacto eliminado con éxito");
            this.cargarContactos(null);
        } else {
            showDialog("Error", "No se pudo eliminar el contacto");
        }
        limpiarCampos();
    }

    public long calcularCumple(Date d) {
        Date current = new Date();
        long dias = (int) ((current.getTime() - d.getTime()) / 86400000);
        int bis = 1;
        while (dias > 365) {
            dias -= 365;
            bis++;
        }
        dias = dias - (bis / 4);
        if (dias > 0) {
            dias = 365 - dias;
        } else {
            dias *= -1;
        }
        return dias;
    }

    public boolean camposIncompletos() {
        return txt_nombre.getText().isEmpty() || txt_telefono.getText().isEmpty() || txt_email.getText().isEmpty()
                || txt_direccion.getText().isEmpty() || txt_date.getValue() == null;
    }

    public void limpiarCampos() {
        lbl_nombre.setText("Contacto");
        txt_nombre.setText("");
        txt_apodo.setText("");
        txt_telefono.setText("");
        txt_direccion.setText("");
        txt_email.setText("");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txt_date.setValue(LocalDate.parse(LocalDate.now().format(formatter), formatter));
    }

    public void initPopup() {
        btn_eliminar = new JFXButton("Eliminar");
        btn_eliminar.setPadding(new Insets(10));
        VBox vbox = new VBox(btn_eliminar);
        popup = new JFXPopup(contactList);
        popup.setPopupContent(vbox);
        btn_eliminar.setOnAction((ActionEvent e) -> {
            popup.hide();
            eliminarContacto();
        });
    }

    public void showDialog(String head, String body) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(head));
        content.setBody(new Text(body));
        JFXDialog dialog = new JFXDialog(stackDialogPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton aceptar = new JFXButton("ACEPTAR");
        aceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dialog.close();
            }
        });
        content.setActions(aceptar);
        dialog.show();
    }

    @FXML
    public void showPopup(MouseEvent event) {
        contactPane.setVisible(true);
        imageStandBy.setVisible(false);
        if (event.getButton() == MouseButton.SECONDARY) {
            popup.show(contactList, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(),
                    event.getY());
        } else {
            Contacto c = contactos.get(contactList.getSelectionModel().getSelectedIndex());
            txt_nombre.setText(c.getNombre());
            txt_apodo.setText(c.getApodo());
            txt_telefono.setText(c.getTelefono());
            txt_direccion.setText(c.getDireccion());
            txt_email.setText(c.getEmail());
            txt_date.setValue(c.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            lbl_nombre.setText(c.getNombre());
            lbl_cumple.setVisible(true);
            lbl_cumple.setText(calcularCumple(c.getFechaNacimiento()) + " días para su cumpleaños");
        }
    }

    @FXML
    public void busquedaActiva(KeyEvent e) {
        cargarContactos(txt_buscar.getText() + "%");
    }

    @FXML
    public void addNewContact(ActionEvent e) {
        limpiarCampos();
        contactPane.setVisible(true);
        imageStandBy.setVisible(false);
        lbl_cumple.setVisible(false);
    }

    //TODO: Actualizar
    @FXML
    public void aceptarOnClick(ActionEvent e) {
        if (!lbl_cumple.isVisible()) {
            if (!camposIncompletos()) {
                Contacto nc;
                nc = new Contacto(txt_nombre.getText(), txt_telefono.getText(), txt_email.getText(),
                        txt_direccion.getText(), txt_apodo.getText(),
                        Date.from(txt_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                if (ContactoDAO.registrar(nc)) {
                    showDialog("Guardado", "Contacto almacenado con éxito");
                    this.cargarContactos(null);
                } else {
                    showDialog("Error", "No se pudo almacenar el contacto");
                }
            } else {
                showDialog("Campos Incompletos", "Por favor llene todos los campos necesarios");
            }
        } else {
            if (!camposIncompletos()) {
                Contacto nc;
                nc = new Contacto(txt_nombre.getText(), txt_telefono.getText(), txt_email.getText(),
                        txt_direccion.getText(), txt_apodo.getText(),
                        Date.from(txt_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                if (ContactoDAO.actualizar(nc)) {
                    showDialog("Actualizado", "Contacto actualizado con éxito");
                    this.cargarContactos(null);
                } else {
                    showDialog("Error", "No se pudo almacenar el contacto");
                }
            } else {
                showDialog("Campos Incompletos", "Por favor llene todos los campos necesarios");
            }
        }
    }

    @FXML
    public void cancelarOnClick(ActionEvent e) {
        limpiarCampos();
        contactPane.setVisible(false);
        imageStandBy.setVisible(true);
    }
}
