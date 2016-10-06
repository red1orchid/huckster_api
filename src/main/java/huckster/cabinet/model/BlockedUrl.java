package huckster.cabinet.model;

/**
 * Created by PerevalovaMA on 17.08.2016.
 */
public class BlockedUrl {
    private int id;
    private String url;
    private int isTrash;
    private String createTime;

    public BlockedUrl(int ind, String url, int isTrash, String createTime) {
        this.id = ind;
        this.url = url;
        this.isTrash = isTrash;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public int getIsTrash() {
        return isTrash;
    }

    public String getCreateTime() {
        return createTime;
    }
}
