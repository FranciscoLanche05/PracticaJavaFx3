package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    private String username = "admin";
    private String password = "admin123";

    @FXML private TextField TxtUser;
    @FXML private PasswordField pstPassword;
    @FXML private Button btnIngresar;
    @FXML private Button btnSalir;
    @FXML private ComboBox<String> ComRol;
    @FXML private Label lblMensaje;

    @FXML
    public void initialize() {
        ComRol.getItems().addAll("Administrador", "Cajero");
    }

    public void abrirVentana(String nombreArchivo) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(FormulariosApplication.class.getResource(nombreArchivo));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            mostrarError("Error al abrir ventana: " + e.getMessage());
        }
    }

    @FXML
    public void ingresar() {
        lblMensaje.setText("");
        String usuario = TxtUser.getText();
        String pass = pstPassword.getText();
        String rol = ComRol.getValue();

        try {
            validar(usuario, pass, rol);
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
            return;
        }

        if (usuario.equals(username) && pass.equals(this.password)) {
            Stage stage = (Stage) btnIngresar.getScene().getWindow();
            stage.close();
            if (rol.equals("Administrador")) {
                abrirVentana("Administrador.fxml");
            } else if (rol.equals("Cajero")) {
                abrirVentana("Cajero.fxml");
            }
        } else {
            mostrarError("❌ Usuario o contraseña incorrectos");
        }
    }

    private void validar(String usuario, String password, String rol) {
        if (rol == null) throw new IllegalArgumentException("⚠ Seleccione un rol");
        if (usuario.isBlank()) throw new IllegalArgumentException("⚠ Ingrese su usuario");
        if (password.isBlank()) throw new IllegalArgumentException("⚠ Ingrese su contraseña");
    }

    private void mostrarError(String mensaje) {
        lblMensaje.setText(mensaje);
    }

    @FXML
    public void salir() {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
}