package controller;


import javafx.fxml.FXML;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Gallery;
import model.Photo;

public class GalleryController {

    @FXML
    public ImageView imageView;

    @FXML
    public TextField imageNameField;

    @FXML
    private ListView<Photo> imagesListView;

    @FXML
    public void initialize() {
        imagesListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Photo item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView photoIcon = new ImageView(item.getPhotoData());
                    photoIcon.setPreserveRatio(true);
                    photoIcon.setFitHeight(50);
                    setGraphic(photoIcon);
                }
            }
        });

        var property = imagesListView.getSelectionModel().selectedItemProperty();
        property.addListener(
                (observable, oldValue, newValue) -> {
                    if (oldValue != null) {
                        imageNameField.textProperty().unbindBidirectional(oldValue.nameProperty());
                    }
                    if (newValue != null) {
                        imageNameField.textProperty().bindBidirectional(newValue.nameProperty());
                        imageView.setImage(newValue.getPhotoData());
                    } else {
                        imageNameField.setText("");
                    }
                });
    }

    public void setModel(Gallery gallery) {
        imagesListView.setItems(gallery.getPhotos());
        imagesListView.getSelectionModel().select(0);
    }

    private void bindSelectedPhoto(Photo selectedPhoto) {
        imageView.imageProperty().bind(selectedPhoto.photoDataProperty());
        imageNameField.textProperty().bindBidirectional(selectedPhoto.nameProperty());
    }
}

