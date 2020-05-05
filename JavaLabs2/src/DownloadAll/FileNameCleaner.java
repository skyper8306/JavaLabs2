package DownloadAll;

import java.util.Arrays;

//https://stackoverflow.com/questions/1155107/is-there-a-cross-platform-java-method-to-remove-filename-special-chars
public class FileNameCleaner {
    final static int[] illegalChars = {34, 60, 62, 124, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 58, 42, 63, 92, 47};
    private static final String IMAGE__PATTERN =
            ".*.(?:jpg|svg|gif|png)$";

    static {
        Arrays.sort(illegalChars);
    }

    public static String cleanFileName(String badFileName) {
        if(!badFileName.matches(IMAGE__PATTERN)){
            badFileName+=".png";
        }

        StringBuilder cleanName = new StringBuilder();
        int len = badFileName.codePointCount(0, badFileName.length());
        for (int i = 0; i < len; i++) {
            int c = badFileName.codePointAt(i);
            if (Arrays.binarySearch(illegalChars, c) < 0) {
                cleanName.appendCodePoint(c);
            }
        }
        return cleanName.toString();
    }
}