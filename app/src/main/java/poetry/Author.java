package poetry;

import java.io.Serializable;

/**
 * Created by zhangshijie on 2016/7/29.
 */
public class Author implements Serializable {

      private String  authorName;

    public Author() {
    }

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {

        return    authorName;

    }
}
