package app;

import java.io.IOException;
import java.util.List;

public class CrawlerApp {

    public static final String SERPER_API_KEY = "*** Enter your https://serper.dev API key HERE ***";

    private static final List<String> TOPICS = List.of("Agent Cooper", "Sherlock", "Poirot", "Detective Monk");


    public static void main(String[] args) throws IOException {
        PhotoCrawler photoCrawler = new PhotoCrawler();
        photoCrawler.resetLibrary();
        photoCrawler.downloadPhotoExamples();
//        photoCrawler.downloadPhotosForQuery(TOPICS.get(0));
//        photoCrawler.downloadPhotosForMultipleQueries(TOPICS);
    }
}