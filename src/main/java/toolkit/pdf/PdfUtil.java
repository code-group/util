package toolkit.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhull
 * @date 2018/6/25
 * <P>pdf生成工具</P>
 * 通过velocity模板生成html文件，再转为pdf文件
 */
public class PdfUtil {

    /**
     *
     * @param  templatePathName 模板路径+名称
     * @param  dataMap          数据
     * @param  pdfPathName      pdf路径+名称
     * @throws IOException
     */
    public static void createPdf(String templatePathName, Map<String, Object> dataMap, String pdfPathName) throws IOException {
        // 设置生成的HTML路径
        String htmlPath = pdfPathName.substring(0, templatePathName.lastIndexOf(".")) + ".html";

        // 初始化模板引擎
        VelocityEngine vEngine = new VelocityEngine();
        int tmpIndex = templatePathName.lastIndexOf("/");
        vEngine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, templatePathName.substring(0, tmpIndex));
        vEngine.init();

        // 生成html文件
        File f = new File(htmlPath);
        if (!f.exists()) {
            f.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
        writer.write(VelocityEngineUtils.mergeTemplateIntoString(vEngine, templatePathName.substring(tmpIndex + 1),
                "UTF-8", dataMap));
        writer.close();

        // 转换为PDF
        getPdfFromHtml(htmlPath, pdfPathName);

        // 删除html文件
        f.delete();
    }

    /**
     * 将html文件转化为pdf文件 A4 纸张为纵向
     * @param pdfFile  pdf文件名(包括路径)
     * @param htmlFile html文件名(包括路径)
     */
    public static void getPdfFromHtml(String htmlFile, String pdfFile){
        Document document = new Document(PageSize.A4,38, 38, 60, 65);
        PdfWriter pdfwriter;
        try {
            pdfwriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            Rectangle rect = new Rectangle(36, 54, 559, 788);
            rect.setBorderColor(BaseColor.BLACK);
            pdfwriter.setBoxSize("art", rect);
//            HeaderFooter header = new HeaderFooter();
//            pdfwriter.setPageEvent(header);
            pdfwriter.setPageEvent(new PdfHeaderFooter());
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document, new FileInputStream(htmlFile), Charset.forName("UTF-8"));
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        createPdf("/Users/zhull/Desktop/test.vm", new HashMap<>(1), "/Users/zhull/Desktop/test.pdf");
    }
}
