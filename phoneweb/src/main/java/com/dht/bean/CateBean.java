/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.bean;

import com.dht.service.CategoryService;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import com.dht.pojo.Category;
import com.dht.pojo.Manufacturer;
import com.dht.service.ManufacturerService;

/**
 *
 * @author Huy Hòa Nguyễn
 */
@ManagedBean
@Named(value = "cateBean")
public class CateBean implements Serializable{
    private final static CategoryService cateService = new CategoryService();
    private final static ManufacturerService manuService = new ManufacturerService();
    public CateBean(){
    }
    public List<Category> getCategories() {
        return cateService.getCategories();
    }
    public List<Manufacturer> getManufacturers() {
        return manuService.getManufacturers();  
    }
}
