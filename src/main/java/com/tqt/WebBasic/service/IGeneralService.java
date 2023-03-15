package com.tqt.WebBasic.service;

import org.springframework.web.multipart.MultipartFile;

public interface IGeneralService {
    public String addImage(MultipartFile[] files, String models);
    public String dateNow(String format);

}
