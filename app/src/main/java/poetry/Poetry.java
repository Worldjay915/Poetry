package poetry;

import java.io.Serializable;

/**
 * Created by zhangshijie on 2016/7/28.
 */
public class Poetry implements Serializable {

    private  String title;
    private  String auth;
    private  String type;
    private  String content;
    private  String desc;

    public Poetry() {
    }

    public Poetry(String title, String auth, String type, String content, String desc) {
        this.title = title;
        this.auth = auth;
        this.type = type;
        this.content = content;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Poetry{" +
                "atitle='" + title + '\'' +
                ", auth='" + auth + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

}
