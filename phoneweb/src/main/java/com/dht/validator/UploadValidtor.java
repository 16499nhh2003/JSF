package com.dht.validator;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Huy Hòa Nguyễn
 */
@FacesValidator("UploadValidtor")
public class UploadValidtor implements Validator{

    /**
     * Creates a new instance of UploadValidtor
     */
    public UploadValidtor() {
    }

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {
        Part p = (Part) value;
        //Ext
        if(!p.getContentType().equals("image/png") && !p.getContentType().equals("image/jpg")){
            FacesMessage msg = new FacesMessage("Allowed Type PNG JPG");
            throw new ValidatorException(msg);
        }
        
        //  Size 
        if(p.getSize() > 2097152){
            FacesMessage msg = new FacesMessage("Size <= 2MB");
            throw new ValidatorException(msg);
        }
    }
    
}
