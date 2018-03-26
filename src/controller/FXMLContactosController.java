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
 * Controlador principal del la Intefaz Gráfica de Usuario
 * @author Manolo
 * @since 03/18/2018
 * @version 1.0
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

    /**
     * Inicialización de los componentes principales de la Interfaz 
     * Gráfica de Usuario
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarContactos(null);
        initPopup();
        lbl_cumple.setVisible(false);
        limpiarCampos();
        contactPane.setVisible(false);
        imageStandBy.setVisible(true);
    }

    /**
     * Función para buscar en la Base de Datos los Contactos y mostrarlos en la ListView
     * @param nombre En el caso que se busque un contacto con un nombre en específico
     */
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

    /**
     * Se hace la eliminación de un contacto a partir del id del Contacto 
     * seleccionado en la JFXListView
     */
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

    /**
     * Se calcula los días restantes para el cumpleaños del Contacto seleccionado
     * @param cumple Fecha de cumpleaños del Contacto seleccionado
     * @return Días restantes para el cumpleaños
     */
    public long calcularCumple(Date cumple) {
        Date current = new Date();
        long dias = (int) ((current.getTime() - cumple.getTime()) / 86400000);
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

    /**
     * Verificación de que todos los campos necesarios esten llenos
     * @return Confirmación si los campos no están vacíos
     */
    public boolean camposIncompletos() {
        return txt_nombre.getText().isEmpty() || txt_telefono.getText().isEmpty() || txt_email.getText().isEmpty()
                || txt_direccion.getText().isEmpty() || txt_date.getValue() == null;
    }

    /**
     * Se limpian los campos de texto para el llenado de un Contacto
     */
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

    /**
     * Inicialización del JFXPopup y llenado con la opción de "Eliminar",
     * los cuales se muestran dentro de la lista. Se inicialización del 
     * evento por la activación del botón Eliminar
     */
    public void initPopup() {
        btn_eliminar = new JFXButton("Eliminar");
        btn_eliminar.setPadding(new Insets(10));
        VBox vbox = new VBox(btn_eliminar);
        popup = new JFXPopup(contactList);
        popup.setPopupContent(vbox);
        btn_eliminar.setOnAction((ActionEvent e) -> {
            popup.hide();
            eliminarContacto();
            lbl_cumple.setVisible(false);
        });
    }

    /**
     * Inicialización y muestra de un JFXDialog al centro de la pantalla, 
     * mandando una advertencia a alguna operación
     * @param head Título del dialog
     * @param body Texto principal del dialog
     */
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

    /**
     * Respuesta al evento de Mouse generado en la JFXListView contactList, en 
     * el que un click derecho muestra el JFXPopup para eliminar, y un click
     * derecho muestra los datos del contacto seleccionado en la lista.
     * @param event Evento de mouse, correspondiente a un click derecho o izquierdo
     */
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

    /**
     * Respuesta activa al ingresar una tecla. Se hace una búsqueda de Contactos por 
     * nombre conforme se ingresa una tecla y se muestra en la JFXListView
     * @param e Evento activado por presionar una tecla en el JFXTextFiel txt_buscar
     */
    @FXML
    public void busquedaActiva(KeyEvent e) {
        cargarContactos(txt_buscar.getText() + "%");
    }

    /**
     * Respuesta al precionar el botón de Agregar, que prepara los campos para rellenar 
     * los datos de un nuevo Contacto
     * @param e Evento activado por presionar el botón Aceptar
     */
    @FXML
    public void addNewContact(ActionEvent e) {
        limpiarCampos();
        contactPane.setVisible(true);
        imageStandBy.setVisible(false);
        lbl_cumple.setVisible(false);
    }

    /**
     * Respuesta al presional el botón Aceptar, en la que se guarda o actualiza o registra 
     * un contacto, dependiendo del estado de la etiqueta lbl_cumple (que sólo se muestra 
     * para Contactos ya registrados antes)
     * @param e Evento activado por presionar el botón Aceptar
     */
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
                nc = new Contacto(contactos.get(contactList.getSelectionModel().getSelectedIndex()).getIdContacto(), txt_nombre.getText(),
                        txt_telefono.getText(), txt_email.getText(), txt_direccion.getText(), txt_apodo.getText(),
                        Date.from(txt_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                if (ContactoDAO.actualizar(nc)) {
                    showDialog("Actualizado", "Contacto actualizado con éxito");
                    this.cargarContactos(null);
                } else {
                    showDialog("Error", "No se pudo actualizar el contacto");
                }
            } else {
                showDialog("Campos Incompletos", "Por favor llene todos los campos necesarios");
            }
        }
    }

    /**
     * Respuesta al presionar el botón Cancelar en un registro de Contacto
     * @param e Evento activado por presionar el botón Cancelar
     */
    @FXML
    public void cancelarOnClick(ActionEvent e) {
        limpiarCampos();
        contactPane.setVisible(false);
        imageStandBy.setVisible(true);
    }
}
