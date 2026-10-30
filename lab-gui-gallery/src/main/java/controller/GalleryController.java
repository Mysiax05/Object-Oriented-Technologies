package controller;


import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Gallery;
import model.Photo;

public class GalleryController {

    @FXML
    public ImageView imageView;

    @FXML
    public TextField imageNameField;

    @FXML
    private ListView imagesListView;

    private Gallery galleryModel;

    @FXML
    public void initialize() {
        // TODO additional FX controls initialization
    }

    public void setModel(Gallery gallery) {
        this.galleryModel = gallery;
        bindSelectedPhoto(gallery.getPhotos().get(0));
    }

    private void bindSelectedPhoto(Photo selectedPhoto) {
        imageView.imageProperty().bind(selectedPhoto.photoDataProperty());
        imageNameField.textProperty().bind(selectedPhoto.nameProperty());
    }
}

