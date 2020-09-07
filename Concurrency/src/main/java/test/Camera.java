package test;

/**
 *
 */

public class Camera {
    private int id;
    private String sourceDataUrl;
    private String tokenDataUrl;

    public Camera(int id, String sourceDataUrl, String tokenDataUrl) {
        this.id = id;
        this.sourceDataUrl = sourceDataUrl;
        this.tokenDataUrl = tokenDataUrl;
    }

    @Override
    public String toString() {
        return "Camera{" + "id=" + id + ", sourceDataUrl='" + sourceDataUrl + '\'' + ", tokenDataUrl='" + tokenDataUrl + '\'' + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceDataUrl() {
        return sourceDataUrl;
    }

    public void setSourceDataUrl(String sourceDataUrl) {
        this.sourceDataUrl = sourceDataUrl;
    }

    public String getTokenDataUrl() {
        return tokenDataUrl;
    }

    public void setTokenDataUrl(String tokenDataUrl) {
        this.tokenDataUrl = tokenDataUrl;
    }
}
