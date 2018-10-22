package toolkit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author zhull
 * @date 2018/10/19
 * <P>文件操作工具</P>
 */
public class FileUtil {

    /**
     * <P>description: 下载文件</P>
     **/
    public static void downloadFile(final HttpServletRequest request, HttpServletResponse resp, byte[] fileBytes, String fileName) throws IOException {
        resp.setHeader("content-type", "application/octet-stream");
        resp.setContentType("application/octet-stream");
        final String filenameToBrowser;
        if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            filenameToBrowser = URLEncoder.encode(fileName, "UTF-8");
        } else {
            filenameToBrowser = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
        resp.setHeader("Content-Disposition", "attachment;filename=" + filenameToBrowser);
        resp.addHeader("Content-Length", String.valueOf(fileBytes.length));
        byte[] buff = new byte[1024];

        // try with resource
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(fileBytes), fileBytes.length);
             OutputStream outputStream = resp.getOutputStream()){
            int num = bufferedInputStream.read(buff);
            while (num != -1) {
                outputStream.write(buff, 0, num);
                outputStream.flush();
                num = bufferedInputStream.read(buff);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
