package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class AdministradorController {

    @FXML private AnchorPane panelAdmin;
    @FXML private ToggleGroup grupoQ1;
    @FXML private ToggleGroup grupoQ2;
    @FXML private ToggleGroup grupoQ3;
    @FXML private ToggleGroup grupoQ4;
    @FXML private Label lblResultados;
    @FXML private Label lblError;
    @FXML private Button btnLogout;

    @FXML
    private void cerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            mostrarError("Error al regresar al login: " + e.getMessage());
        }
    }

    @FXML
    public void evaluarEncuesta() {
        lblError.setText("");
        lblResultados.setText("");

        RadioButton r1 = (RadioButton) grupoQ1.getSelectedToggle();
        RadioButton r2 = (RadioButton) grupoQ2.getSelectedToggle();
        RadioButton r3 = (RadioButton) grupoQ3.getSelectedToggle();
        RadioButton r4 = (RadioButton) grupoQ4.getSelectedToggle();

        if (r1 == null || r2 == null || r3 == null || r4 == null) {
            mostrarError("⚠ Debe responder todas las preguntas antes de enviar");
            return;
        }

        int correctas = 0;
        if (r1.getText().equals("Encapsulamiento")) correctas++;
        if (r2.getText().equals("FXMLLoader")) correctas++;
        if (r3.getText().equals("start()")) correctas++;
        if (r4.getText().equals("AnchorPane")) correctas++;

        int incorrectas = 4 - correctas;
        int puntaje = correctas * 5;

        lblResultados.setText(String.format(
                "✔ Puntaje: %d / 20  |  Correctas: %d  |  Incorrectas: %d",
                puntaje, correctas, incorrectas));

        grupoQ1.getToggles().forEach(t -> ((RadioButton) t).setDisable(true));
        grupoQ2.getToggles().forEach(t -> ((RadioButton) t).setDisable(true));
        grupoQ3.getToggles().forEach(t -> ((RadioButton) t).setDisable(true));
        grupoQ4.getToggles().forEach(t -> ((RadioButton) t).setDisable(true));
    }

    private void mostrarError(String mensaje) {
        lblError.setText(mensaje);
    }
}