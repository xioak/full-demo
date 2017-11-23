package com.yimi.cn.service.impl;

import com.yimi.cn.mapper.NewsContentMapper;
import com.yimi.cn.model.NewsContent;
import com.yimi.cn.service.INewsContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsContentServiceImpl implements INewsContentService{

    @Autowired
    NewsContentMapper newsContentMapper;

    @Override
    public int addNewsContent(NewsContent recode) {
        return newsContentMapper.insert(recode);
    }

    @Override
    public int updateNewsContent(NewsContent recode) {
        return newsContentMapper.updateByPrimaryKeySelective(recode);
    }

    @Override
    public NewsContent findNewsContent(Integer id) {
        return null;
    }

    @Override
    public List<NewsContent> findAllNewContent(int type) {
        return newsContentMapper.findAllNewContent(type);
    }

    @Override
    public int deletContent(NewsContent content) {

        if(content!=null && content.getType()!=null && content.getType()==5){

            return newsContentMapper.deleteByPrimaryKeyType(content.getId(),content.getType());
        }else{

            return newsContentMapper.deleteByPrimaryKey(content.getId());
        }
    }

    @Override
    public List<NewsContent> findAllNewContentByPage(int type, String page, String pageSize) {
        int inpage = Integer.parseInt(page);
        int inpagesize = Integer.parseInt(pageSize);
        int start = (inpage-1)*inpagesize;
        int end =inpage*inpagesize;
        return newsContentMapper.findAllNewContentByPage(type,start,end);
    }

    @Override
    public NewsContent findNewContent(Integer id) {
        return newsContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int getaTotalPageByType(int type, String pageSize) {
        int total = newsContentMapper.getTotalRows(type);
        int size = Integer.parseInt(pageSize);
        if(total%size!=0){
            return  total/size+1;
        }else{
            return total/size;
        }
    }

    @Override
    public List getContentNameOnly(NewsContent content) {
        return newsContentMapper.getContentNameOnly(content.getType());
    }

    @Override
    public NewsContent findNewContentByType(Integer id, Integer type) {
        return newsContentMapper.findNewContentByType(id,type);
    }
}