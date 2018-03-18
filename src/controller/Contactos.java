/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Manolo
 */
public class Contactos extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLContactos.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());
        stage.setTitle("Contactos");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/ic_account_card_details_white_36dp.png")));
        //stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
