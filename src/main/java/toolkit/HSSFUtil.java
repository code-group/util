package toolkit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * @author zhull
 * @date 2018/6/25
 * <P>操作xlsx文件</P>
 * apache poi
 */
public class HSSFUtil {

    /**
     * <P>date: 2018/6/25</P>
     * <P>创建新文件</P>
     *
     * @param filePathName 输出文件路径+名称
     */
    public static void write(String filePathName) throws IOException {
        File file = createFile(filePathName);
        try (OutputStream os = new FileOutputStream(file)) {
            // 创建xls
//            Workbook workbook = new HSSFWorkbook();
////            Sheet sheet = workbook.createSheet("a");
//            Sheet sheet = workbook.createSheet();
//            Row row = sheet.createRow(1);
//            row.createCell(0).setCellStyle(setCellStyle(workbook));
//            row.createCell(0).setCellValue("test");
////             ...
//            workbook.write(os);
            // 创建xlsx
            XSSFWorkbook xWorkBook = new XSSFWorkbook();
            XSSFSheet sheet = xWorkBook.createSheet();
            // 创建合并单元格
            sheet.addMergedRegion(new CellRangeAddress(1, 3, 1, 3));
            XSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellStyle(setCellStyle(xWorkBook));
            row.createCell(0).setCellValue("test");
            row = sheet.createRow(1);
            row.createCell(1).setCellValue("aaa");
            xWorkBook.write(os);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * <P>date: 2018/6/25</P>
     * <P>在模板基础上编辑</P>
     *
     * @param filePathName     输出文件路径+名称
     * @param templatePathName 模板文件路径+名称
     */
    public static void writeBaseTemplate(String filePathName, String templatePathName) throws IOException, InvalidFormatException {
        File file = createFile(filePathName);
        try (InputStream is = getInputStream(templatePathName);
             OutputStream os = new FileOutputStream(file)) {
            // 从模板创建workbook
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(1);
            row.createCell(0).setCellStyle(setCellStyle(workbook));
            row.createCell(0).setCellValue("test");
            // ...

            // 写入目标文件
            workbook.write(os);
        } catch (IOException | InvalidFormatException e) {
            throw e;
        }
    }

    public static void read(String filePathName) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(getInputStream(filePathName));
        XSSFSheet sheet;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheet = workbook.getSheetAt(i);
            for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                XSSFRow row = sheet.getRow(j);
                if (row != null) {
                    for (int k = 0; k < row.getLastCellNum(); k++) {
                        if (row.getCell(k) != null) {
                            System.out.print(row.getCell(k) + "\t");
                        } else {
                            System.out.print("\t");
                        }
                    }
                }
                System.out.println();
            }
            System.out.println("读取sheet表：" + workbook.getSheetName(i) + " 完成");
        }
    }

    private static InputStream getInputStream(String templatePathName) throws FileNotFoundException {
        return new FileInputStream(new File(templatePathName));
    }

    private static File createFile(String filePathName) throws IOException {
        File f = new File(filePathName.substring(0, filePathName.lastIndexOf("/")));
        // 创建文件夹
        if (!f.exists()) {
            if (!f.mkdirs()) {
                System.err.println("createFile() 无法创建文件夹");
            }
        }
        f = new File(filePathName);
        // 创建文件
        if (!f.exists()) {
            f.createNewFile();
        }
        return f;
    }

    private static CellStyle setCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

    public static void main(String[] args) throws IOException {
//        writeBaseTemplate("/Users/zhull/Desktop/h/xxx1.xlsx", "/Users/zhull/Desktop/xxx.xlsx");
        write("/Users/zhull/Desktop/xxx1.xlsx");
        read("/Users/zhull/Desktop/xxx1.xlsx");
    }
}
