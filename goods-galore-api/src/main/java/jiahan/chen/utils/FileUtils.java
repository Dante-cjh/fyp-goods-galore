package jiahan.chen.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Jiahan Chen
 * @ClassName FileUtils
 */
@Slf4j
public class FileUtils {

    @Value("${upload.path}")
    private static String uploadPath;

    public static List<String> getFileList() {
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        //获取文件夹下的文件列表
        File[] flist = dir.listFiles();
        List<String> sss = Arrays.stream(flist).filter(s -> s.isFile())
                .sorted((s1, s2) -> {
                    //按照文件修改时间倒序排列，后上传的文件，排在前面
                    return (int) (s2.lastModified() - s1.lastModified());
                })
                .map(s -> s.getName()).collect(Collectors.toList());
        return sss;
    }

    public static String upload(MultipartFile file) {

        //如果你想要上传的文件，使用一个随机的文件名称时，可以使用这些代码
        //原始文件名
        String originalName = file.getOriginalFilename();
        //获取后缀名
        String extName = "";
        int index = originalName.lastIndexOf(".");
        if (index > -1) {
            extName = originalName.substring(index);
        }

        String newName = UUID.randomUUID().toString() + extName;
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(uploadPath + newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newName;
    }

    public static void download(String name, HttpServletResponse response) throws UnsupportedEncodingException {

        //下载文件的响应类型，这里统一设置成了文件流
        //你可以根据自己所提供下载的文件类型，使用不同的响应 mime 类型
        response.setContentType("application/octet-stream;charset=utf-8");
        //设置下载弹出框中默认显示的文件名称，如果指定中文名称的话，需要转成 iso8859-1 编码，解决乱码问题
        String fileName = new String(name.getBytes(), "iso8859-1");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(uploadPath + fileName))) {
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] bArr = new byte[1024];
            int len;
            while ((len = bis.read(bArr)) != -1) {
                outputStream.write(bArr, 0, len);
            }
        } catch (Exception ex) {
            log.error("下载文件异常", ex);
        }
    }

    /**
     * 删除文件操作，0表示删除失败，1表示删除成功
     * @param name
     * @return
     */
    public static int delete(String name) {
        File file = new File(uploadPath + name);
        if (!file.exists()) {
            log.info("文件不存在，无法删除");
            return 0;
        }

        try {
            file.delete();
            log.info("删除文件成功");
            return 1;
        } catch (Exception ex) {
            log.error("删除文件异常", ex);
            return 0;
        }
    }

}
