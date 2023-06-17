/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.bean;

import com.dht.pojo.Category;
import com.dht.pojo.Manufacturer;
import com.dht.pojo.Product;
import com.dht.service.ProductService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Huy Hòa Nguyễn
 */
@ManagedBean
@Named(value = "productBean")
@RequestScoped
public class ProductBean {

    private int productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private Set<Manufacturer> manufacturers;
    private Part imgFile;

    private static final ProductService proService = new ProductService();

    /**
     * Creates a new instance of ProductBean
     */
    public ProductBean() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            String proId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("product_id");
            if (proId != null && !proId.isEmpty()) {
                Product p = proService.getProductById(Integer.parseInt(proId));
                this.productId = p.getId();
                this.name = p.getName();
                this.description = p.getDescription();
                this.price = p.getPrice();
                this.category = p.getCategory();
                this.manufacturers = p.getManufacturers();
            }
        }
    }

    public List<Product> getProducts() {
        return proService.getProducts(null);
    }

    public String addProduct() throws IOException {
        Product p;
        if (this.productId > 0) {
            p = proService.getProductById(this.productId);
        } else {
            p = new Product();
        }
        p.setName(this.name);
        p.setDescription(this.description);
        p.setPrice(this.price);
        p.setCategory(this.category);
        p.setManufacturers(this.manufacturers);
        try {
            if (this.imgFile != null) {
                this.uploadFile();
                p.setImage("upload/" + this.imgFile.getSubmittedFileName());
            }
            if (proService.addOrSaveProduct(p) == true) {
                return "product-list?faces-redirect=true";
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "product";
    }

    public String deleteProduct(Product p) throws Exception {
        if (proService.deleteProduct(p)) {
            return "product-list?faces-redirect=true";
        }
        throw new Exception("Something wrong!!!");
    }

    private void uploadFile() throws IOException {
        String path1 = FacesContext.getCurrentInstance()
                .getExternalContext().getRealPath("/resources/images/upload")
                + "/" + this.imgFile.getSubmittedFileName();
        String path = FacesContext.getCurrentInstance()
                .getExternalContext().getInitParameter("com.dht.uploadPath") + this.imgFile.getSubmittedFileName();
        try ( InputStream in = this.imgFile.getInputStream();  FileOutputStream out = new FileOutputStream(path)) {
            byte b[] = new byte[1024];
            int byteRead;
            while ((byteRead = in.read(b)) != -1) {
                out.write(b, 0, byteRead);
            }
        }
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the manufacturers
     */
    public Set<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    /**
     * @param manufacturers the manufacturers to set
     */
    public void setManufacturers(Set<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    /**
     * @return the imgFile
     */
    public Part getImgFile() {
        return imgFile;
    }

    /**
     * @param imgFile the imgFile to set
     */
    public void setImgFile(Part imgFile) {
        this.imgFile = imgFile;
    }
}
