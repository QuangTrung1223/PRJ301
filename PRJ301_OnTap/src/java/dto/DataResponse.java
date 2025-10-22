package dto;

public class DataResponse<E> {
    private E info; // result info
    private String msg;

    public DataResponse() {
    }

    public DataResponse(E info, String msg) {
        this.info = info;
        this.msg = msg;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "DataResponse{" +
                "info=" + info +
                ", msg='" + msg + '\'' +
                '}';
    }
}
