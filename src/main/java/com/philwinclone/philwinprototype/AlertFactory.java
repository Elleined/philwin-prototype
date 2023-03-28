package com.philwinclone.philwinprototype;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertFactory {

    public void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.show();
    }

    public void showAlert(Alert.AlertType alertType, String message, ButtonType... buttonTypes) {
        new Alert(alertType, message, buttonTypes).show();
    }

    public ButtonType showConfirmAlert(String message, ButtonType... buttonTypes) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, buttonTypes);
        return alert.showAndWait().orElse(ButtonType.CANCEL);
    }
}
