package com.tqt.WebBasic.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
@Service
public class GeneraServiceImpl implements IGeneralService {
    @Override
    public String addImage(MultipartFile[] files, String models) {
        // Khởi tạo một danh sách để chứa các đường dẫn của các ảnh được upload
        List<String> imageUrls = new ArrayList<>();
        // Lặp qua từng file ảnh trong mảng các files được truyền vào
        for (MultipartFile file : files) {
            try {
                // Kiểm tra phần mở rộng của file để đảm bảo rằng nó có đúng định dạng (jpg, jpeg, png, gif)
                String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
                List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");
                if (!allowedExtensions.contains(fileExtension)) {
                    // Nếu file không đúng định dạng thì trả về một thông báo lỗi
                    return ResponseEntity.badRequest().body("Only files with .jpg, .jpeg, .png, .gif extensions are allowed").toString();
                }
                //Gọi hàm dateNow lấy ngày giờ hiện tại để ghép với tên file
                String dateNow = dateNow("yyyy-MM-dd_HH-mm-ss");
                // Tạo một chuỗi ngẫu nhiên với độ dài là 8 ký tự
                String randomString = RandomStringUtils.random(8, true, true);

                // Đặt tên file mới bằng cách ghép chuỗi ngẫu nhiên với tên file gốc
                String newFileName = dateNow+"__"+randomString +"__"+ file.getOriginalFilename();
                // Lưu tập tin vào hệ thống, cộng thêm chuỗi random để tránh trùng lặp file name
                Path path = Paths.get("uploads", models, newFileName);
                Files.write(path, file.getBytes());

                // Thêm đường dẫn của ảnh mới vào danh sách các đường dẫn
                imageUrls.add(newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Trả về danh sách các đường dẫn ảnh dưới dạng một chuỗi string
        return imageUrls.toString();
    }

    @Override
    public String dateNow(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = new Date();
        String formattedDate = formatter.format(date);
        return formattedDate;
    }
}
