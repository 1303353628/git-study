package zhan.wang;

import zhan.wang.util.FileUtil;

import java.io.File;

public class TestDemo1 {
//    private static Logger logger = LoggerFactory.getLogger(TestDemo1.class);

    public static void main(String[] args) {

        FileUtil fileUtil = new FileUtil();
        String sourcePath = "E:\\百度云下载\\test123\\75六百四十部";
        String destPath = "E:\\百度云下载\\test123\\temp";
        File file = new File(sourcePath);
        for (File tempFile : file.listFiles()) {
            if (tempFile.isFile()) {
                if (!tempFile.getName().contains("downloading")) {
                    fileUtil.moveFile(tempFile, destPath);
                }
            } else if (tempFile.isDirectory()) {
                for (File subFile : tempFile.listFiles()) {
                    if (!tempFile.getName().contains("downloading")) {
                        fileUtil.moveFile(subFile, destPath);
                    }

                }
            }
        }

    }
}
