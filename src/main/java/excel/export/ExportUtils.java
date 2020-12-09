package excel.export;

import jxl.CellView;
import jxl.Workbook;
import jxl.write.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author Cain
 * @date 2020/12/8
 */
public class ExportUtils {

    public static void main(String[] args) throws FileNotFoundException {
        String filePatch = "/Users/wuqiong/Downloads/BuilExcel.xls";
        File file = new File(filePatch);
        OutputStream stream = new FileOutputStream(file);
        try {
            List<String> titles = new ArrayList<>(Arrays.asList("第一列","第二列","第三列"));
            List<List<String>> dataList = new ArrayList<>();
            {
                List<String> data = new ArrayList<>(Arrays.asList("1","2","3"));
                dataList.add(data);
            }
            createExcel(stream, titles, dataList);
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
    }

    public static void createExcel(OutputStream os, List<String> titles, List<List<String>> dataList) throws WriteException,IOException {
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页  ---sheet页命名 ； sheet页号
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        sheet.setRowView(0, 600, false);
        //构造表头
        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i);
            Label label = new Label(i,0,title);
            sheet.addCell(label);
        }
        CellView cellView = new CellView();
        //设置自动大小
        cellView.setAutosize(true);
        for (int r = 0; r < dataList.size(); r++) {
            List<String> row = dataList.get(r);
            for (int j=0; j < row.size(); j++) {
                //根据内容自动设置列宽
                sheet.setColumnView(r, cellView);
                String data = row.get(j);
                Label demo = new Label(j, r+1, data);
                sheet.addCell(demo);
            }
        }
        workbook.write();
        workbook.close();
        os.close();
        System.out.println("创建完成！");
    }

}
