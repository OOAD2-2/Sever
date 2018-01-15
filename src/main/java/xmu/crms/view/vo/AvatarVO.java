package xmu.crms.view.vo;

public class AvatarVO {

    /**
     * @author: LUWEIW
     */

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
