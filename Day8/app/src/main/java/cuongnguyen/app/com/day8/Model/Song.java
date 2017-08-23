package cuongnguyen.app.com.day8.Model;

/**
 * Created by CuongNguyen on 07/14/17.
 */

public class Song {
    private String name;
    private String url;
    private boolean isFavorite;

    public Song(String name, String url, boolean isFavorite) {
        this.name = name;
        this.url = url;
        this.isFavorite = isFavorite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
