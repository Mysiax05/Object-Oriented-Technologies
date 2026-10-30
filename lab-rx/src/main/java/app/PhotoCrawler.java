package app;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.Photo;
import model.PhotoSize;
import util.PhotoDownloader;
import util.PhotoProcessor;
import util.PhotoSerializer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhotoCrawler {

    private static final Logger log = Logger.getLogger(PhotoCrawler.class.getName());

    private final PhotoDownloader photoDownloader;

    private final PhotoSerializer photoSerializer;

    private final PhotoProcessor photoProcessor;

    public PhotoCrawler() throws IOException {
        this.photoDownloader = new PhotoDownloader();
        this.photoSerializer = new PhotoSerializer("./photos");
        this.photoProcessor = new PhotoProcessor();
    }

    public void resetLibrary() throws IOException {
        photoSerializer.deleteLibraryContents();
    }

    public void downloadPhotoExamples() {
        try {
            photoDownloader
                    .getPhotoExamples()
                    .subscribe(photoSerializer::savePhoto);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Downloading photo examples error", e);
        }
    }

    public void downloadPhotosForQuery(String query) {
            photoDownloader
                    .searchForPhotos(query)
                    .take(3)
                    .compose(this::processPhoto)
                    .subscribe(photoSerializer::savePhoto,
                            error -> System.err.println("Could not download a photo: " + error.getMessage()),
                                    () -> System.out.println("Download finished!"));
    }

    public void downloadPhotosForMultipleQueries(List<String> queries) {
            photoDownloader
                    .searchForPhotos(queries)
                    .compose(this::processPhoto)
                    .blockingSubscribe(photoSerializer::savePhoto,
                            error -> System.err.println("Could not download a photo: " + error.getMessage()),
                                    () -> System.out.println("Download finished!"));
    }

    public Observable<Photo> processPhoto(Observable<Photo> photoObservable) {
            return photoObservable
                    .groupBy(PhotoSize::resolve)
                    .flatMap(groupedObservable -> {
                        PhotoSize size = groupedObservable.getKey();

                        return switch (size) {
                            case SMALL -> Observable.empty();
                            case MEDIUM -> groupedObservable
                                    .buffer(5, TimeUnit.SECONDS)
                                    .flatMap(Observable::fromIterable);
                            case LARGE -> groupedObservable
                                    .observeOn(Schedulers.computation())
                                    .map(photoProcessor::convertToMiniature);

                        };
                    });
    }
}
