package app;

import java.io.IOException;
import java.util.List;

public class CrawlerApp {

    public static final String SERPER_API_KEY = "44ce8d5cf1c00ec8d070a952400b50d0926d09fa";

    private static final List<String> TOPICS = List.of("Agent Cooper", "Sherlock", "Poirot", "Detective Monk");


    public static void main(String[] args) throws IOException, InterruptedException {
        PhotoCrawler photoCrawler = new PhotoCrawler();
        photoCrawler.resetLibrary();
//        photoCrawler.downloadPhotoExamples();
        photoCrawler.downloadPhotosForQuery(TOPICS.getFirst());
        photoCrawler.downloadPhotosForMultipleQueries(TOPICS);
    }
}