package xyz.migoo.web.pojo;

/**
 * @author xiaomi
 */
public class ResponseBean {

    private int status;
    private Object data;

    public ResponseBean setStatus(int status) {
        this.status = status;
        return this;
    }

    public ResponseBean setData(Object data) {
        this.data = data;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}
