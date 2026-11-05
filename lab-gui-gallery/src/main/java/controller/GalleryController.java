package controller;


import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.schedulers.IoScheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Gallery;
import model.Photo;
import org.pdfsam.rxjavafx.schedulers.JavaFxScheduler;
import util.PhotoDownloader;

public class GalleryController {

    private Gallery gallery;

    @FXML
    private Button button;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField imageNameField;

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<Photo> imagesListView;

    @FXML
    private void initialize() {
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
                        bindSelectedPhoto(newValue);
                    } else {
                        imageNameField.setText("");
                    }
                });
    }

    public void setModel(Gallery gallery) {
        this.gallery = gallery;
        imagesListView.setItems(gallery.getPhotos());
        imagesListView.getSelectionModel().select(0);
    }

    private void bindSelectedPhoto(Photo selectedPhoto) {
        imageView.imageProperty().bind(selectedPhoto.photoDataProperty());
        imageNameField.textProperty().bindBidirectional(selectedPhoto.nameProperty());
    }

    public void searchButtonClicked(ActionEvent actionEvent) {
        PhotoDownloader photoDownloader = new PhotoDownloader();
        String searchQuery = searchTextField.getText();

        gallery.clear();

        photoDownloader.searchForPhotos(searchQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .subscribe(
                        gallery::addPhoto,
                        error -> System.err.println(error.getMessage()));
    }
}

