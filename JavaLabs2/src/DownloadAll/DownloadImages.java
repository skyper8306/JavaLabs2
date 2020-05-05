package DownloadAll;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class HTMLParser {
    private static String baseURL;
    private static String dirToSave;
    private static int minimalSizeBytes;
    private static HashSet<String> uniqueURL = new HashSet<>(); //Предотвращение скачивания одной картинки несколько раз
    static AtomicInteger countImg = new AtomicInteger(0); //считаем сами, потому что не все картинки будут скачаны из-за minimalSizeBytes
    private static ExecutorService es = Executors.newCachedThreadPool();


    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        FileInputStream fileInputStream;
        Properties prop = new Properties();

        try {
            //Читаем config.properties
            fileInputStream = new FileInputStream("src\\DownloadAll\\config.properties");
            prop.load(fileInputStream);
            baseURL = prop.getProperty("baseURL");
            dirToSave = prop.getProperty("dirToSave");
            minimalSizeBytes = Integer.parseInt(prop.getProperty("minimalSizeBytes"));
            fileInputStream.close();

            System.out.println("Downloading images from: " + baseURL);
            getImgbyTag(baseURL); //Скачивание <img> по аттрибуту 'src'

            //Достаем ссылки из html и для каждой вызываем getImgByTag
            Elements links;
            String curHref;
            String host;
            Document document = Jsoup.connect(baseURL).userAgent("Mozilla").get();
            links = document.getElementsByTag("a");
            for (Element elem : links) {
                curHref = elem.attr("href");
                if (curHref.startsWith("//")) {
                    getImgbyTag("https://" + curHref.substring(2));
                    continue;
                }
                if (curHref.startsWith("/")) { //добавить if '//', переосмыслить downloadImageWithCorrectURL()
                    host = new URL(baseURL).getHost();
                    getImgbyTag("https://" + host + curHref);
                } else {
                    getImgbyTag(curHref);
                }
            }
            es.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Images were downloaded: " + countImg);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Time spent: " + (System.currentTimeMillis() - time) + "ms");
    }

    static void getImgbyTag(String baseURL) {
        Elements elems;
        Document doc;
        String curImgSrc;
        try {
            doc = Jsoup.connect(baseURL).userAgent("Mozilla").get();
            elems = doc.getElementsByTag("img");

            for (Element elem : elems) {
                curImgSrc = elem.attr("src");
                if (uniqueURL.add(curImgSrc)) { //если ссылка добавилась (еще не было), то скачать
                    String finalCurImgSrc = curImgSrc;
                    es.submit(() -> {
                        downloadImageWithCorrectURL(baseURL, finalCurImgSrc);
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Runnable downloadImageWithCorrectURL(String curURL, String curImgSrc) {
        String correctCurImgSrcToSave = FileNameCleaner.cleanFileName(curImgSrc);

        if (curImgSrc.startsWith("/[^/]")) {
            Downloader.downloadFiles(curURL + curImgSrc, dirToSave + correctCurImgSrcToSave, minimalSizeBytes);
            return null;
        }

        if (curImgSrc.startsWith("//")) {
            Downloader.downloadFiles("https:" + curImgSrc, dirToSave + correctCurImgSrcToSave, minimalSizeBytes);
            return null;
        }

        if (curImgSrc.startsWith("data:image/")) { //base64 images
            Downloader.downloadBase64Image(curImgSrc, dirToSave + correctCurImgSrcToSave, minimalSizeBytes);
            return null;
        }

        if (curImgSrc.startsWith("http")) { //http+https
            Downloader.downloadFiles(curImgSrc, dirToSave + correctCurImgSrcToSave, minimalSizeBytes);
            return null;
        }
        return null;
    }
}