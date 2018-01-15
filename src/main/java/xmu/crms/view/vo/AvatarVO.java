package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class AvatarVO {

    public String url;

    public AvatarVO() {
    }

    public AvatarVO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "AvatarVO{" +
                "url='" + url + '\'' +
                '}';
    }
}
