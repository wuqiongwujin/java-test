package extendsTest;

import java.util.List;

/**
 * @Description
 * @Author Cain
 * @date 2022/2/7
 */
public abstract class AbstractAction {

    protected List<String> idList;

    public AbstractAction setIdList(List<String> idList) {
        this.idList = idList;
        return this;
    }

    protected abstract void deal();
}
