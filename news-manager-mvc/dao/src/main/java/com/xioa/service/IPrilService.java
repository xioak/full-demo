package com.xioa.service;

import com.xioa.model.Pril;

public interface IPrilService {
    int addPril(Pril recode, int i);

    int updatePril(Pril recode);

    Pril findPril(Integer id);

    void getByProc(int id);
}