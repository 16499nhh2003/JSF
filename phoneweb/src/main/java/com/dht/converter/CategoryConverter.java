/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.converter;

import com.dht.service.CategoryService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
/**
 *
 * @author Huy Hòa Nguyễn
 */
@FacesConverter("CategoryConverter")
public class CategoryConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        return new  CategoryService().getCategoryById(Integer.parseInt(value));
   }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return o.toString();
    }   
}
