package com.yimi.cn.service;

import com.yimi.cn.model.NewsContent;

public interface INewsContentService {
    int addNewsContent(NewsContent recode);

    int updateNewsContent(NewsContent recode);

    NewsContent findNewsContent(Integer id);
}