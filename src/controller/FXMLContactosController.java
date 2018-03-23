/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import model.pojos.Contacto;

/**
 *
 * @author Manolo
 */
public class FXMLContactosController implements Initializable {
    @FXML
    private AnchorPane contactPane;
    @FXML
    private JFXButton btn_addContact;
    @FXML
    private JFXButton btn_aceptar;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private JFXListView<Label> contactList;
    @FXML
    private JFXTextField txt_buscar;
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

    private JFXPopup popup;
    private JFXButton btn_editar;
    private JFXButton btn_eliminar;
    private List<Contacto> contactos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarContactos(null);
        initPopup();
        lbl_cumple.setVisible(false);
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

    public boolean camposIncompletos() {
        return txt_nombre.getText().isEmpty() || txt_telefono.getText().isEmpty() || txt_email.getText().isEmpty()
                || txt_direccion.getText().isEmpty() || txt_date.getValue() == null;
    }

    public void limpiarCampos() {
        txt_nombre.setText("");
        txt_apodo.setText("");
        txt_telefono.setText("");
        txt_direccion.setText("");
        txt_email.setText("");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        txt_date.setValue(LocalDate.parse(LocalDate.now().format(formatter), formatter));
    }

    @FXML
    public void busquedaActiva(KeyEvent e) {
        cargarContactos(txt_buscar.getText() + "%");
    }

    // TODO: Funcionalidad de Agregar Contacto
    @FXML
    public void addNewContact(ActionEvent e) {
        limpiarCampos();

    }

    public void initPopup() {
        btn_editar = new JFXButton("Editar   ");
        btn_eliminar = new JFXButton("Eliminar");
        btn_editar.setPadding(new Insets(10));
        btn_eliminar.setPadding(new Insets(10));
        VBox vbox = new VBox(btn_editar, btn_eliminar);
        popup = new JFXPopup(contactList);
        popup.setPopupContent(vbox);
    }

    public void camposVisibles(boolean bool) {

    }
    /**
     * TODO: 
     * Date input = new Date();
     * Instant instant = input.toInstant();
     * ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
     * LocalDate date = zdt.toLocalDate();
     */
    @FXML
    public void showPopup(MouseEvent event) {
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
            //txt_date.setValue(c.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            lbl_nombre.setText(c.getNombre());
        }
    }
    /* TODO: 
     * Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
     */
    @FXML
    public void aceptarOnClick(ActionEvent e) {
        limpiarCampos();
    }

    @FXML
    public void cancelarOnClick(ActionEvent e) {
        limpiarCampos();
    }
}
