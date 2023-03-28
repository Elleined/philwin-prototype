package com.philwinclone.philwinprototype.service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

import java.util.List;

public interface PhilWinService {
    Long getComboScore(int combo, long score);

    List<Integer> generateGameTiles(int bombCount);

    Long getScoreMultiplier(int selectedBombLevel, List<Integer> bombLevelList);

    void initializeGameContent(List<Integer> tiles, List<ImageView> imageViewList);

    void showAlert(Alert.AlertType alertType, String message);

    void showAlert(Alert.AlertType alertType, String message, ButtonType... buttonTypes);

    ButtonType showConfirmAlert(String message, ButtonType... buttonTypes);
}
