/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.net.URL;
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
import model.Contacto;

/**
 * Controlador principal del la Intefaz Gráfica de Usuario
 *
 * @author Manolo
 * @since 03/18/2018
 * @version 2.0
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
    private ImageView imageStandBy;

    @FXML
    private Label lbl_nombre;

    @FXML
    private AnchorPane contactPane;

    @FXML
    private JFXTextField txt_nombre;

    @FXML
    private JFXTextField txt_apellido;

    @FXML
    private JFXTextField txt_telefono;

    @FXML
    private JFXTextField txt_email;

    @FXML
    private JFXButton btn_agregar;

    @FXML
    private JFXButton btn_cancelar;

    private JFXPopup popup;
    private JFXButton btn_eliminar;

    private boolean agregando = false;

    private List<Contacto> contactos;

    /**
     * Inicialización de los componentes principales de la Interfaz Gráfica de
     * Usuario
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarContactos(null);
        initPopup();
        limpiarCampos();
        contactPane.setVisible(false);
        imageStandBy.setVisible(true);
    }

    /**
     * Función para buscar en la Base de Datos los Contactos y mostrarlos en la
     * ListView
     *
     * @param nombre En el caso que se busque un contacto con un nombre en
     * específico
     */
    public void cargarContactos(String nombre) {
        contactList.getItems().clear();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ContactosPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        if (nombre == null) {
            contactos = em.createNamedQuery("Contacto.findAll").getResultList();
        } else {
            contactos = em.createNamedQuery("Contacto.findByNombreLike").setParameter("nombre", nombre).getResultList();
        }
        if (contactos != null) {
            for (Contacto c : contactos) {
                try {
                    Label lbl = new Label(c.getNombre() + " " + c.getApellido());
                    lbl.setGraphic(new ImageView(new Image(
                            getClass().getResourceAsStream("/resources/ic_account_circle_grey600_48dp(1).png"))));
                    contactList.getItems().add(lbl);
                } catch (Exception ex) {
                    System.err.println("Error: " + ex);
                }
            }
        }
        em.close();
        emf.close();
    }

    /**
     * Se hace la eliminación de un contacto a partir del id del Contacto
     * seleccionado en la JFXListView
     */
    public void eliminarContacto() {
        Contacto contacto = contactos.get(contactList.getSelectionModel().getSelectedIndex());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ContactosPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(contacto);
            em.getTransaction().commit();
            showDialog("Eliminado", "Contacto eliminado con éxito");
            this.cargarContactos(null);
        } catch (Exception e) {
            showDialog("Error", "No se pudo eliminar el contacto");
        } finally {
            em.close();
            emf.close();
        }
        limpiarCampos();
    }

    /**
     * Verificación de que todos los campos necesarios esten llenos
     *
     * @return Confirmación si los campos no están vacíos
     */
    public boolean camposIncompletos() {
        return txt_nombre.getText().isEmpty() || txt_telefono.getText().isEmpty() || txt_email.getText().isEmpty()
                || txt_apellido.getText().isEmpty();
    }

    /**
     * Se limpian los campos de texto para el llenado de un Contacto
     */
    public void limpiarCampos() {
        lbl_nombre.setText("Contacto");
        txt_nombre.setText("");
        txt_apellido.setText("");
        txt_telefono.setText("");
        txt_email.setText("");
    }

    /**
     * Inicialización del JFXPopup y llenado con la opción de "Eliminar", los
     * cuales se muestran dentro de la lista. Se inicialización del evento por
     * la activación del botón Eliminar
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
        });
    }

    /**
     * Inicialización y muestra de un JFXDialog al centro de la pantalla,
     * mandando una advertencia a alguna operación
     *
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
     *
     * @param event Evento de mouse, correspondiente a un click derecho o
     * izquierdo
     */
    @FXML
    public void showPopup(MouseEvent event) {
        contactPane.setVisible(true);
        imageStandBy.setVisible(false);
        if (event.getButton() == MouseButton.SECONDARY) {
            popup.show(contactList, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(),
                    event.getY());
        } else {
            try {
                Contacto c = contactos.get(contactList.getSelectionModel().getSelectedIndex());
                txt_nombre.setText(c.getNombre());
                txt_apellido.setText(c.getApellido());
                txt_telefono.setText(c.getTelefono());
                txt_email.setText(c.getEmail());
                lbl_nombre.setText(c.getNombre());
            } catch (ArrayIndexOutOfBoundsException e) {
                txt_nombre.setText("");
                txt_apellido.setText("");
                txt_telefono.setText("");
                txt_email.setText("");
                lbl_nombre.setText("Contactos");
            } finally {
                agregando = false;
            }
        }
    }

    /**
     * Respuesta activa al ingresar una tecla. Se hace una búsqueda de Contactos
     * por nombre conforme se ingresa una tecla y se muestra en la JFXListView
     *
     * @param e Evento activado por presionar una tecla en el JFXTextFiel
     * txt_buscar
     */
    @FXML
    public void busquedaActiva(KeyEvent e) {
        cargarContactos(txt_buscar.getText());
    }

    /**
     * Respuesta al precionar el botón de Agregar, que prepara los campos para
     * rellenar los datos de un nuevo Contacto
     *
     * @param e Evento activado por presionar el botón Aceptar
     */
    @FXML
    public void addNewContact(ActionEvent e) {
        limpiarCampos();
        contactPane.setVisible(true);
        imageStandBy.setVisible(false);
        agregando = true;
    }

    /**
     * Respuesta al presional el botón Aceptar, en la que se guarda o actualiza
     * o registra un contacto, dependiendo del estado de la etiqueta lbl_cumple
     * (que sólo se muestra para Contactos ya registrados antes)
     *
     * @param e Evento activado por presionar el botón Aceptar
     */
    @FXML
    public void aceptarOnClick(ActionEvent e) {
        if (agregando) {
            if (!camposIncompletos()) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("ContactosPU");
                EntityManager em = emf.createEntityManager();
                Contacto nuevoContacto = new Contacto(null, txt_nombre.getText(), txt_apellido.getText(), txt_telefono.getText(), txt_email.getText());
                try {
                    em.getTransaction().begin();
                    em.persist(nuevoContacto);
                    em.getTransaction().commit();
                    showDialog("Guardado", "Contacto almacenado con éxito");
                    this.cargarContactos(null);
                } catch (Exception ex) {
                    showDialog("Error", "No se pudo almacenar el contacto");
                } finally {
                    em.close();
                    emf.close();
                }
                agregando = false;
            } else {
                showDialog("Campos Incompletos", "Por favor llene todos los campos necesarios");
            }
        } else {
            if (!camposIncompletos()) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("ContactosPU");
                EntityManager em = emf.createEntityManager();
                Contacto contacto = new Contacto(contactos.get(contactList.getSelectionModel().getSelectedIndex()).getIdContacto(),
                        txt_nombre.getText(), txt_apellido.getText(), txt_telefono.getText(), txt_email.getText());
                try {
                    em.getTransaction().begin();
                    em.merge(contacto);
                    em.getTransaction().commit();
                    showDialog("Actualizado", "Contacto actualizado con éxito");
                    this.cargarContactos(null);
                } catch (Exception ex) {
                    showDialog("Error", "No se pudo almacenar el contacto");
                } finally {
                    em.close();
                    emf.close();
                }
                agregando = false;
            } else {
                showDialog("Campos Incompletos", "Por favor llene todos los campos necesarios");
            }
        }
    }

    /**
     * Respuesta al presionar el botón Cancelar en un registro de Contacto
     *
     * @param e Evento activado por presionar el botón Cancelar
     */
    @FXML
    public void cancelarOnClick(ActionEvent e) {
        limpiarCampos();
        contactPane.setVisible(false);
        imageStandBy.setVisible(true);
    }
}
