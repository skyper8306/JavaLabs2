package DownloadAll;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Downloader {
    public static Runnable downloadFiles(String urlImg, String dirToSave, int minimalSizeBytes) {
        try { //как использовать try с ресурсами, если in зависит от URL connection, который не может быть вне try?
            URL connection = new URL(urlImg);
            HttpURLConnection urlconn = (HttpURLConnection) connection.openConnection();
            urlconn.setRequestMethod("GET");
            urlconn.connect();
            if (urlconn.getContentLength() < minimalSizeBytes) {
                return null;
            }

            InputStream in = new BufferedInputStream(urlconn.getInputStream());
            dirToSave.length();
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(dirToSave));
            byte buffer[] = new byte[1];
            int c = in.read(buffer);
            while (c > 0) {
                writer.write(buffer, 0, c);
                c = in.read(buffer);
            }

            System.out.println('\n' + urlImg + '\n' +
                    "Saved as " + dirToSave);
            HTMLParser.countImg.getAndIncrement();

            in.close();
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e){

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Runnable downloadBase64Image(String urlImg, String dirToSave, int minimalSizeBytes) {
        try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(dirToSave))) {
            String[] splitStr = urlImg.split(",");
            String base64Image = splitStr[1];
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            if (imageBytes.length < minimalSizeBytes) {
                return null;
            }

            writer.write(imageBytes, 0, imageBytes.length);
            writer.flush();

            System.out.println('\n' + urlImg + '\n' +
                    "Saved as " + dirToSave);
            HTMLParser.countImg.getAndIncrement();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}