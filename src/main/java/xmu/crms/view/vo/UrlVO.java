package xmu.crms.view.vo;

public class UrlVO {

    /**
     * @author: LUWEIW
     */

    private String url;

    public UrlVO() {
    }

    public UrlVO(String url) {
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
        return "UrlVO{" +
                "url='" + url + '\'' +
                '}';
    }
}
