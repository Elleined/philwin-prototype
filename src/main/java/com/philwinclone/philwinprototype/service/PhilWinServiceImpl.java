package com.philwinclone.philwinprototype.service;

import com.philwinclone.philwinprototype.AlertFactory;
import com.philwinclone.philwinprototype.GameInitializer;
import com.philwinclone.philwinprototype.algorithm.ComboHitAlgorithm;
import com.philwinclone.philwinprototype.algorithm.GameAlgorithm;
import com.philwinclone.philwinprototype.algorithm.ScoreMultiplierAlgorithm;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

import java.util.List;

public class PhilWinServiceImpl implements PhilWinService {
    private final AlertFactory alertFactory = new AlertFactory();
    private final GameAlgorithm gameAlgorithm = new GameAlgorithm();
    private final ScoreMultiplierAlgorithm scoreMultiplierAlgorithm = new ScoreMultiplierAlgorithm();
    private final ComboHitAlgorithm comboHitAlgorithm = new ComboHitAlgorithm();
    private final GameInitializer gameInitializer = new GameInitializer();

    @Override
    public Long getComboScore(int combo, long score) {
        return comboHitAlgorithm.getComboScore(combo, score);
    }

    @Override
    public List<Integer> generateGameTiles(int bombCount) {
        return gameAlgorithm.generateGameTiles( bombCount );
    }

    @Override
    public Long getScoreMultiplier(int selectedBombLevel, List<Integer> bombLevelList) {
        return scoreMultiplierAlgorithm.getScoreMultiplier(selectedBombLevel, bombLevelList);
    }

    @Override
    public void initializeGameContent(List<Integer> tiles, List<ImageView> imageViewList) {
        gameInitializer.initializeGameContent(tiles, imageViewList);
    }

    @Override
    public void showAlert(Alert.AlertType alertType, String message) {
        alertFactory.showAlert(alertType, message);
    }

    @Override
    public void showAlert(Alert.AlertType alertType, String message, ButtonType... buttonTypes) {
        alertFactory.showAlert(alertType, message, buttonTypes);
    }

    @Override
    public ButtonType showConfirmAlert(String message, ButtonType... buttonTypes) {
        return alertFactory.showConfirmAlert(message, buttonTypes);
    }
}
