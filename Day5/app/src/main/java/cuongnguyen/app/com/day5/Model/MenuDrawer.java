package cuongnguyen.app.com.day5.Model;

/**
 * Created by CuongNguyen on 07/11/17.
 */

public class MenuDrawer {
    private String name;
    private int image;
    private boolean isGroup;
    public MenuDrawer(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public MenuDrawer(String name, boolean isGroup) {
        this.name = name;
        this.isGroup = isGroup;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
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
}
