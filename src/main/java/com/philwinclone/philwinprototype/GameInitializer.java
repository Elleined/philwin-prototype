package com.philwinclone.philwinprototype;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Iterator;
import java.util.List;

public class GameInitializer {

    public void initializeGameContent(List<Integer> tiles, List<ImageView> imageViewList) {
        Iterator<Integer> tilesIterator = tiles.iterator();
        Iterator<ImageView> iteratorImageView = imageViewList.iterator();
        imageViewList.forEach(imageView -> imageView.setVisible(false));

        // Initialize the images
        while (tilesIterator.hasNext() && iteratorImageView.hasNext()) {
            setImageView(tilesIterator.next(), iteratorImageView.next());
        }
    }

    private void setImageView(int tileContentElement, ImageView imageView) {
        Image image = tileContentElement == Content.COIN ?
                new Image("/icons/coin.png") :
                new Image("/icons/bomb.png");
        imageView.setImage(image);
    }
}
