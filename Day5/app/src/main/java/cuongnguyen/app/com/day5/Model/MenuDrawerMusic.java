package cuongnguyen.app.com.day5.Model;

/**
 * Created by CuongNguyen on 07/12/17.
 */

public class MenuDrawerMusic {
    private String name;
    private int image;
    private boolean isGroup;
    private int count;

    public MenuDrawerMusic(String name, boolean isGroup) {
        this.name = name;
        this.isGroup = isGroup;
    }

    public MenuDrawerMusic(String name, int image, int count) {
        this.name = name;
        this.image = image;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
