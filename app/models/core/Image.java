package models.core;

import play.Play;
import play.libs.Codec;

import java.io.File;

public class Image {

    public static final File DATA = Play.getFile("data");
    public static final File STATIC = new File(DATA, "static");
    public static final File IMAGES = new File(DATA, "images");

    private String url;
    private File file;

    private Image() {
    }

    public static Image of(final String url) {
        final Image image = new Image();
        image.url = url;
        image.file = new File(STATIC, image.url);
        return image;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
