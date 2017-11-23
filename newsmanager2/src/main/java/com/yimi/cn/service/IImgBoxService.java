package com.yimi.cn.service;

import com.yimi.cn.model.ImgBox;

public interface IImgBoxService {
    int addImgBox(ImgBox recode);

    int updateImgBox(ImgBox recode);

    ImgBox findImgBox(Integer id);
}