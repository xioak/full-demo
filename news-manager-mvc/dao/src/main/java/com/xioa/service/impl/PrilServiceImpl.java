package com.xioa.service.impl;

import com.xioa.dao.PrilMapper;
import com.xioa.model.Pril;
import com.xioa.service.IPrilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrilServiceImpl implements IPrilService{

    @Autowired
    PrilMapper prilMapper;

    @Override
    public int addPril(Pril recode, int i) {


        try {
            int r= prilMapper.insert(recode);
            if(r==1 && i==1){
               // throw new RuntimeException("erro : user define erro");
                throw new Exception("new Exception");
            }else{
                return r;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("user define erro" +e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("user define erro 222" +e.getMessage());
        }


    }

    @Override
    public int updatePril(Pril recode) {
        return 0;
    }

    @Override
    public Pril findPril(Integer id) {
        return null;
    }

    @Override
    public void getByProc(int id) {

    }
}