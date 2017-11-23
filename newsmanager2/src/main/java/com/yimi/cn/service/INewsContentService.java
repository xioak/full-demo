package com.yimi.cn.service;

import com.yimi.cn.model.NewsContent;

import java.util.List;

public interface INewsContentService {
    int addNewsContent(NewsContent recode);

    int updateNewsContent(NewsContent recode);

    NewsContent findNewsContent(Integer id);

    List<NewsContent> findAllNewContent(int i);

    int deletContent(NewsContent content);

    List<NewsContent> findAllNewContentByPage(int type, String page, String pageSize);

    NewsContent findNewContent(Integer id);

    int getaTotalPageByType(int type, String pageSize);

    List getContentNameOnly(NewsContent content);

    NewsContent findNewContentByType(Integer id, Integer type);
}