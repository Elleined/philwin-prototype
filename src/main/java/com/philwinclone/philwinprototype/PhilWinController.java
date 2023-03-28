package com.philwinclone.philwinprototype;

import com.philwinclone.philwinprototype.service.PhilWinService;
import com.philwinclone.philwinprototype.service.PhilWinServiceImpl;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PhilWinController implements Initializable {
    private Long score;
    private int combo = 0;
    private List<Integer> tiles;
    private final PhilWinService philWinService = new PhilWinServiceImpl();
    @FXML
    private ImageView imageView00;
    @FXML
    private ImageView imageView10;
    @FXML
    private ImageView imageView20;
    @FXML
    private ImageView imageView30;
    @FXML
    private ImageView imageView40;
    @FXML
    private ImageView imageView01;
    @FXML
    private ImageView imageView11;
    @FXML
    private ImageView imageView21;
    @FXML
    private ImageView imageView31;
    @FXML
    private ImageView imageView41;
    @FXML
    private ImageView imageView02;
    @FXML
    private ImageView imageView12;
    @FXML
    private ImageView imageView22;
    @FXML
    private ImageView imageView32;
    @FXML
    private ImageView imageView42;
    @FXML
    private ImageView imageView03;
    @FXML
    private ImageView imageView13;
    @FXML
    private ImageView imageView23;
    @FXML
    private ImageView imageView33;
    @FXML
    private ImageView imageView43;
    @FXML
    private ImageView imageView04;
    @FXML
    private ImageView imageView14;
    @FXML
    private ImageView imageView24;
    @FXML
    private ImageView imageView34;
    @FXML
    private ImageView imageView44;
    @FXML
    private GridPane mainFrame;
    @FXML
    private ComboBox<Integer> cbBombPicker;
    @FXML
    private Label lblCoinCount;
    @FXML
    private Label lblBombCount;
    @FXML
    private Label lblScore;
    @FXML
    private Label lblCombo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Integer> bombLevels = cbBombPicker.getItems();
        bombLevels.addAll( getBombLevelList() );
        cbBombPicker.setItems( bombLevels );
        cbBombPicker.valueProperty().addListener(this::comboBoxItemPressed);

        mainFrame.setVisible(false);
    }

    private void comboBoxItemPressed(Observable observable) {
        int selectedBombLevel = cbBombPicker.getSelectionModel().getSelectedItem();
        lblBombCount.setText( String.valueOf(selectedBombLevel) );
        int selectedCoinCount = 25 - selectedBombLevel;
        lblCoinCount.setText( String.valueOf(selectedCoinCount) );

        mainFrame.setVisible(true);
        this.tiles = philWinService.generateGameTiles( selectedBombLevel );
        // Set the score multiplier based on selectedBombLevel
        this.score = philWinService.getScoreMultiplier(selectedBombLevel, getBombLevelList());
        philWinService.initializeGameContent(tiles, imageViewList());
        cbBombPicker.setDisable(true);
    }

    private List<ImageView> imageViewList() {
        return Arrays.asList(
                // Row 1
                imageView00,
                imageView10,
                imageView20,
                imageView30,
                imageView40,
                // Row 2
                imageView01,
                imageView11,
                imageView21,
                imageView31,
                imageView41,
                // Row 3
                imageView02,
                imageView12,
                imageView22,
                imageView32,
                imageView42,
                // Row 4
                imageView03,
                imageView13,
                imageView23,
                imageView33,
                imageView43,
                // Row 5
                imageView04,
                imageView14,
                imageView24,
                imageView34,
                imageView44
        );
    }

    private List<Integer> getBombLevelList() {
        return Arrays.asList(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
    }

    private void addScore() {
        lblScore.setText( String.valueOf(score) );
        score *= 2;
    }

    private void deleteScore() {
        lblScore.setText( "0" );
    }

    private Long addComboScore() {
        long comboHitScore = 0;
        combo++;
        if (combo >= 3) comboHitScore = philWinService.getComboScore(combo, score);
        lblCombo.setText( String.valueOf(combo) );
        return comboHitScore;
    }

    private void deleteCombo() {
        combo = 0;
        lblCombo.setText( "0" );
    }

    private void tilePressed(int allocatedContainedElement, ImageView allocatedImageView) {
        if (allocatedImageView.isVisible()) {
            philWinService.showAlert(Alert.AlertType.WARNING, "This tile is already open!");
            return;
        }
        allocatedImageView.setVisible(true);
        if (allocatedContainedElement != Content.BOMB) {
            this.score += addComboScore();
            addScore();
            return;
        }
        imageViewList().forEach(imageView -> imageView.setVisible(true));
        deleteScore();
        deleteCombo();
        lblCoinCount.setText( String.valueOf(0) );
        lblBombCount.setText( String.valueOf(0) );

        philWinService.showConfirmAlert("This tile contains BOMB! You Lose!", ButtonType.OK);

        ButtonType yesOrNo = philWinService.showConfirmAlert("Do you want to play again?", ButtonType.YES, ButtonType.NO);
        if (yesOrNo != ButtonType.YES) {
            philWinService.showConfirmAlert("Thanks for Playing!", ButtonType.OK);
            Platform.exit();
            return;
        }
        mainFrame.setVisible(false);
        cbBombPicker.setDisable(false);
    }

    @FXML
    public void anchorPane00Clicked() {
        int allocatedContainingElement = tiles.get( 0 );
        ImageView allocatedImageView = imageViewList().get(0);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane10Clicked() {
        int allocatedContainingElement = tiles.get( 1 );
        ImageView allocatedImageView = imageViewList().get(1);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane20Clicked() {
        int allocatedContainingElement = tiles.get( 2 );
        ImageView allocatedImageView = imageViewList().get(2);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane30Clicked() {
        int allocatedContainingElement = tiles.get( 3 );
        ImageView allocatedImageView = imageViewList().get(3);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane40Clicked() {
        int allocatedContainingElement = tiles.get( 4 );
        ImageView allocatedImageView = imageViewList().get(4);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane01Clicked() {
        int allocatedContainingElement = tiles.get( 5 );
        ImageView allocatedImageView = imageViewList().get(5);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane11Clicked() {
        int allocatedContainingElement = tiles.get( 6 );
        ImageView allocatedImageView = imageViewList().get(6);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane21Clicked() {
        int allocatedContainingElement = tiles.get( 7 );
        ImageView allocatedImageView = imageViewList().get(7);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane31Clicked() {
        int allocatedContainingElement = tiles.get( 8 );
        ImageView allocatedImageView = imageViewList().get(8);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane41Clicked() {
        int allocatedContainingElement = tiles.get( 9 );
        ImageView allocatedImageView = imageViewList().get(9);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane02Clicked() {
        int allocatedContainingElement = tiles.get( 10 );
        ImageView allocatedImageView = imageViewList().get(10);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane12Clicked() {
        int allocatedContainingElement = tiles.get( 11 );
        ImageView allocatedImageView = imageViewList().get(11);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane22Clicked() {
        int allocatedContainingElement = tiles.get( 12 );
        ImageView allocatedImageView = imageViewList().get(12);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane32Clicked() {
        int allocatedContainingElement = tiles.get( 13 );
        ImageView allocatedImageView = imageViewList().get(13);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane42Clicked() {
        int allocatedContainingElement = tiles.get( 14 );
        ImageView allocatedImageView = imageViewList().get(14);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane03Clicked() {
        int allocatedContainingElement = tiles.get( 15 );
        ImageView allocatedImageView = imageViewList().get(15);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane13Clicked() {
        int allocatedContainingElement = tiles.get( 16 );
        ImageView allocatedImageView = imageViewList().get(16);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane23Clicked() {
        int allocatedContainingElement = tiles.get( 17 );
        ImageView allocatedImageView = imageViewList().get(17);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane33Clicked() {
        int allocatedContainingElement = tiles.get( 18 );
        ImageView allocatedImageView = imageViewList().get(18);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane43Clicked() {
        int allocatedContainingElement = tiles.get( 19 );
        ImageView allocatedImageView = imageViewList().get(19);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane04Clicked() {
        int allocatedContainingElement = tiles.get( 20 );
        ImageView allocatedImageView = imageViewList().get(20);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane14Clicked() {
        int allocatedContainingElement = tiles.get( 21 );
        ImageView allocatedImageView = imageViewList().get(21);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane24Clicked() {
        int allocatedContainingElement = tiles.get( 22 );
        ImageView allocatedImageView = imageViewList().get(22);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane34Clicked() {
        int allocatedContainingElement = tiles.get( 23 );
        ImageView allocatedImageView = imageViewList().get(23);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }

    @FXML
    public void anchorPane44Clicked() {
        int allocatedContainingElement = tiles.get( 24 );
        ImageView allocatedImageView = imageViewList().get(24);

        this.tilePressed(allocatedContainingElement, allocatedImageView);
    }
}
