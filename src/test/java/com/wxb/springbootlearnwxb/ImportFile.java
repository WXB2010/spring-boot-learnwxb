package com.wxb.springbootlearnwxb;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxianbing
 * @date 2021-04-11 10:24
 */
@SpringBootTest
public class ImportFile {
    /**
     * 每行数据是List<String>无表头
     *
     * @throws IOException
     */
    @Test
    public void writeWithoutHead() throws IOException {
        try (OutputStream out = new FileOutputStream("C:\\cvte\\withoutHead.xlsx");) {
            //ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
            // 添加表头
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("第一个sheet1");
            List<List<String>> data = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                List<String> item = new ArrayList<>();
                item.add("item0" + i);
                item.add("item1" + i);
                item.add("item2" + i);
                data.add(item);
            }
            // 构造表头
            List<List<String>> head = new ArrayList<>();
            List<String> headCoulumn1 = new ArrayList<String>();
            List<String> headCoulumn2 = new ArrayList<String>();
            List<String> headCoulumn3 = new ArrayList<String>();
            headCoulumn1.add("第一列");
            headCoulumn2.add("第二列");
            headCoulumn3.add("第三列");
            head.add(headCoulumn1);
            head.add(headCoulumn2);
            head.add(headCoulumn3);
            Table table = new Table(1);
            table.setHead(head);


            //writer.write0(data, sheet1);
            writer.write0(data, sheet1,table);
            writer.finish();
            System.out.println("导入完成...");
        }
    }

    /**
     * 每行数据是List<String>有表头
     *
     * @throws IOException
     */
    @Test
    public void writeWithHead() throws IOException {
        try (OutputStream out = new FileOutputStream("C:\\cvte\\withHead.xlsx");) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("sheet1");
            List<List<String>> data = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                List<String> item = new ArrayList<>();
                item.add("item0" + i);
                item.add("item1" + i);
                item.add("item2" + i);
                data.add(item);
            }
            List<List<String>> head = new ArrayList<List<String>>();
            List<String> headCoulumn1 = new ArrayList<String>();
            List<String> headCoulumn2 = new ArrayList<String>();
            List<String> headCoulumn3 = new ArrayList<String>();
            headCoulumn1.add("第一列");
            headCoulumn2.add("第二列");
            headCoulumn3.add("第三列");
            head.add(headCoulumn1);
            head.add(headCoulumn2);
            head.add(headCoulumn3);
            Table table = new Table(1);
            table.setHead(head);
            writer.write0(data, sheet1, table);
            writer.finish();
            System.out.println("导入完成...");
        }
    }

    @Test
    public void writeWithHeadByExcelProp() throws IOException {
        try (OutputStream out = new FileOutputStream("C:\\cvte\\withHeadByExcelProp.xlsx");) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet1 = new Sheet(1, 0, ExcelPropertyIndexModel.class);
            sheet1.setSheetName("sheet1");
            List<ExcelPropertyIndexModel> data = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                ExcelPropertyIndexModel item = new ExcelPropertyIndexModel();
                item.setName("name" + i);
                item.setAge("age" + i);
                item.setEmail("email" + i);
                item.setAddress("address" + i);
                item.setSax("sax" + i);
                item.setHeigh("heigh" + i);
                item.setLast("last" + i);
                data.add(item);
            }
            writer.write(data, sheet1);
            writer.finish();
            System.out.println("导入完成...");
        }
    }

    /**
     * 复杂的多表头
     * @throws IOException
     */
    @Test
    public void writeWithMultiHead() throws IOException {
        try (OutputStream out = new FileOutputStream("C:\\cvte\\withMultiHead.xlsx");) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            Sheet sheet1 = new Sheet(1, 0, MultiLineHeadExcelModel.class);
            sheet1.setSheetName("sheet1");
            List<MultiLineHeadExcelModel> data = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                MultiLineHeadExcelModel item = new MultiLineHeadExcelModel();
                item.setP1("p1" + i);
                item.setP2("p2" + i);
                item.setP3("p3" + i);
                item.setP4("p4" + i);
                item.setP5("p5" + i);
                item.setP6("p6" + i);
                item.setP7("p7" + i);
                item.setP8("p8" + i);
                item.setP9("p9" + i);
                data.add(item);
            }
            writer.write(data, sheet1);
            writer.finish();
            System.out.println("导入完成...");
        }
    }
    @Test
    public void read() throws Exception {
        try (InputStream in = new FileInputStream("C:\\cvte\\withHead.xlsx");) {
            AnalysisEventListener<ExcelPropertyIndexModel> listener = new AnalysisEventListener<ExcelPropertyIndexModel>() {

                @Override
                public void invoke(ExcelPropertyIndexModel object, AnalysisContext context) {
                    System.err.println("Row:" + context.getCurrentRowNum() + " Data:" + object);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    System.err.println("doAfterAllAnalysed...");
                }
            };
            ExcelReader excelReader = ExcelReaderFactory.getExcelReader(in, null, listener);
            // 第二个参数为表头行数，按照实际设置
            excelReader.read(new Sheet(1, 1, ExcelPropertyIndexModel.class));
        }
    }

}
