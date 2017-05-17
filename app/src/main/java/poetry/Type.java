package poetry;

import java.io.Serializable;

/**
 * Created by zhangshijie on 2016/7/29.
 */
public class Type implements Serializable {

      private String  typeName;

    public Type() {
    }

    public Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName ;
    }
}

