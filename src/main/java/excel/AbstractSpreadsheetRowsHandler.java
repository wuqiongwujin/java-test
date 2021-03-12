package excel;

import org.dommons.core.string.Stringure;

import java.util.List;
import java.util.Map;

/**
 * @Description 抽象电子表格行处理器
 * @date 2020/2/16
 */
public abstract class AbstractSpreadsheetRowsHandler implements SpreadsheetRowsHandler {

    /**
     * 处理表格内容行集合
     * @param rowsMap key(电子表格的行下标);values(该行单元格集合)
     */
    @Override
    public abstract void handleRows(Map<Integer, List<String>> rowsMap);

    /**
     * 计算数据表分区
     * @return 分区
     */
    protected String part(String companyID) {
        return part(endIndex(companyID));
    }

    /**
     * 计算分区值
     * @param index 序号
     * @return 分区值
     */
    protected String part(int index) {
        StringBuilder buf = new StringBuilder(2).append('_');
        buf.append(Math.abs(index) % parts());
        return buf.toString();
    }

    protected int parts() {
        return 10;
    }

    /**
     * 计算尾数
     * @param companyID 公司ID
     * @return 数值
     */
    private int endIndex(String companyID) {
        companyID = Stringure.trim(companyID);
        // 取最后一位数字
        int len = companyID.length();
        if (len == 0) return -1;
        Character let = null;
        for (int i = len; i > 0; i--) {
            char c = companyID.charAt(i - 1);
            if (c >= '0' && c <= '9') return c - '0';
            else if (let == null && Character.isLetter(c)) let = c;
        }
        // 无数字取最后一位字母36进制换算
        if (let != null) return Character.toLowerCase(let) - 'a' + 11;
        // 无字母取最后一位字符asc码值
        return Math.abs(companyID.charAt(len - 1));
    }
}
