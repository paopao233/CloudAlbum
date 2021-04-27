package com.controller;

import com.pojo.DeleteResults;
import com.pojo.Images;
import com.pojo.UploadResults;
import com.service.ImageService;
import com.service.impl.ImageServiceImpl;
import com.utils.GetImageClassify;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 控制图片的上传,下载,删除以及查询
 * @author x1yyy
 */
@Controller
public class ImageController {

    private final static String DELETE_IMAGES = "images";

    ImageService imageService = new ImageServiceImpl();

    @ResponseBody
    @RequestMapping("/uploadImage")
    public Map<Integer, List<UploadResults>> uploadImage(MultipartFile[] img, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int userKey = Integer.parseInt(request.getHeader("userKey"));

        List<UploadResults> list = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(img.length);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (MultipartFile file : img) {
            String name = new String(Objects.requireNonNull(file.getOriginalFilename()).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

            String newName = imageService.checkRepeat(name, userKey);

            String imgUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/image/"+ userKey + "/" + newName;
            String thumbUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/image/"+ userKey + "/thumb/" + newName;
            String path = "/www/CloudAlbum/" + userKey + "/";
            String thumbPath = path + "thumb/";
            double size = Double.parseDouble(String.valueOf(file.getSize()));

            File folder = new File(path);
            if (!folder.exists()) {
                Boolean bool = folder.mkdirs();
            }

            File thumbFolder = new File(thumbPath);
            if (!thumbFolder.exists()) {
                Boolean bool = thumbFolder.mkdirs();
            }

            Runnable task = () -> {
                try {
                    file.transferTo(new File(folder, Objects.requireNonNull(newName)));
                    Thumbnails.of(path + newName).scale(1F).outputQuality(0.3F).toFile(thumbPath + newName);

                    // 腾讯云Api解析,要放在写入文件之后
                    String classify = GetImageClassify.getClassify(imgUrl);

                   // 存入数据库
                    int classifyKey = imageService.queryClassifyKey(classify);

                    if (!imageService.uploadImage(newName, imgUrl, classify, thumbUrl, classifyKey, size, userKey)) {
                        // 存入数据库失败
                        list.add(new UploadResults(-1,name,"上传失败"));
                        // 删除写入的文件

                    } else {
                        assert name != null;
                        if (name.equals(newName)) {
                            list.add(new UploadResults(200,name,"上传成功", classify));
                        } else {
                            list.add(new UploadResults(0,name,"存在相同名称的图片,已重命名为" + newName, classify));
                        }
                    }
                } catch (IOException e) {
                    // 写入文件失败
                    list.add(new UploadResults(-2,newName,"上传失败"));
                } finally {
                    countDownLatch.countDown();
                }
            };
            executorService.execute(task);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        return list.stream().collect(Collectors.groupingBy(UploadResults :: getStatus));
    }

    @ResponseBody
    @RequestMapping("/initPage")
    public List<Images> initPage(HttpServletRequest request) {
        int userKey = -1;
        try {
            userKey = Integer.parseInt(request.getHeader("userKey"));
        } catch (NumberFormatException ignored) {
        }
        return imageService.initPage(userKey);
    }

    @ResponseBody
    @RequestMapping("/deleteImages")
    public List<DeleteResults> deleteImage(@RequestBody Map<String,List<String>> images, HttpServletRequest request) {
        int userKey = Integer.parseInt(request.getHeader("userKey"));

        List<DeleteResults> results = new ArrayList<>();

        CountDownLatch countDownLatch = new CountDownLatch(images.get(DELETE_IMAGES).size());

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (String s : images.get(DELETE_IMAGES)) {

            Runnable task = new Runnable() {
                @Override
                public void run() {
                    // 删除数据库中的记录
                    Boolean isSuccess1 = imageService.deleteImage(s, userKey);

                    // 删除本地存储的图片
                    String path = "/www/CloudAlbum/" + userKey + "/" + s;
                    File file = new File(path);

                    String thumbPath = "/www/CloudAlbum/" + userKey + "/thumb/" + s;
                    File thumbFile = new File(thumbPath);

                    // file 是否是一个文件夹
                    if (!file.isDirectory() && !thumbFile.isDirectory()) {
                        Boolean isSuccess2 = file.delete();
                        Boolean isSuccess3 = thumbFile.delete();

                        String result = isSuccess1 && isSuccess2 && isSuccess3 ? "success" : "failed";
                        results.add(new DeleteResults(s, result));
                    }

                    countDownLatch.countDown();
                }
            };

            executorService.execute(task);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        return results;
    }

    @ResponseBody
    @RequestMapping("/selectClassify")
    public List<Images> selectImageInClassify(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        int userKey = Integer.parseInt(request.getHeader("userKey"));
        int classifyKey = Integer.parseInt(request.getParameter("classify"));

        return imageService.selectClassify(classifyKey, userKey);
    }

    @ResponseBody
    @RequestMapping("/search")
    public List<Images> selectImages(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");

        int userKey = Integer.parseInt(request.getHeader("userKey"));
        String imageName = request.getParameter("imageName");

        return imageService.searchImages(imageName ,userKey);
    }
}
