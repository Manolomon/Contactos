/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;

import java.net.URL;
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
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Manolo
 */
public class FXMLContactosController implements Initializable {
    @FXML
    private JFXButton addContact;
    @FXML
    private JFXListView<Label> contactList;

    private JFXPopup popup;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < 15; i++) {
            agregarContacto();
        }
        initPopup();
    }

    public void agregarContacto(){
        try {
            Label lbl = new Label("Contacto:");
            lbl.setGraphic(new ImageView(
                    new Image(getClass().getResourceAsStream("/resources/ic_account_circle_grey600_48dp(1).png"))));
            contactList.getItems().add(lbl);
        } catch (Exception ex) {
            System.err.println("Error: " + ex);
        }
    }

    @FXML
    public void addNewContact(ActionEvent e) {
        agregarContacto();
    }

    public void initPopup() {
        
        JFXButton b1 = new JFXButton("Editar   ");
        JFXButton b2 = new JFXButton("Eliminar");
        b1.setPadding(new Insets(10));
        b2.setPadding(new Insets(10));
        VBox vbox = new VBox(b1, b2);
        popup = new JFXPopup(contactList);
        popup.setPopupContent(vbox);
        /*popup.setContent(vbox);
        popup.setSource(contactList);*/
    }

    @FXML
    public void showPopup(MouseEvent event){
        if(event.getButton() == MouseButton.SECONDARY){
            popup.show(contactList,JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
        }
    }
}
