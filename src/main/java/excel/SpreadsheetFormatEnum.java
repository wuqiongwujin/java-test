package excel;

/**
 * @Description 电子表格格式枚举
 * @date 2020/2/17
 */
public enum SpreadsheetFormatEnum {
    /** excel2003扩展名 */
    EXCEL03_EXTENSION(".xls"),
    /** excel2007扩展名 */
    EXCEL07_EXTENSION(".xlsx"),
    /** 导出会员csv扩展名 */
    SV_EXTENSION(".csv");

    private String extension;

    SpreadsheetFormatEnum(String extension) {
        this.extension = extension;
    }

    public static SpreadsheetFormatEnum valueOfExtension(String extension) {
        for (SpreadsheetFormatEnum e : SpreadsheetFormatEnum.values()) {
            if (e.extension.equals(extension)) {
                return e;
            }
        }
        return null;
    }
}
