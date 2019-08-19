package com.mooc.mx.service;

import com.mooc.mx.beans.Bean;

/**
 * @ClassName SalaryService
 * @Description TODO
 * @Author miaoxu
 * @Date 2019/8/19 14:44
 * @Version 1.0
 **/
@Bean
public class SalaryService {

    public Integer calSalary(int experience){
        return 1000 * experience;
    }

}
