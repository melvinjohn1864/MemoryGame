package com.example.memorygame.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Image_ {

@SerializedName("id")
@Expose
private Double id;
@SerializedName("product_id")
@Expose
private Double productId;
@SerializedName("position")
@Expose
private Integer position;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("alt")
@Expose
private Object alt;
@SerializedName("width")
@Expose
private Integer width;
@SerializedName("height")
@Expose
private Integer height;
@SerializedName("src")
@Expose
private String src;
@SerializedName("variant_ids")
@Expose
private List<Object> variantIds = null;
@SerializedName("admin_graphql_api_id")
@Expose
private String adminGraphqlApiId;

private boolean clickStatus = false;

private boolean checkStatus = true;

public Double getId() {
return id;
}

public void setId(Double id) {
this.id = id;
}

public Double getProductId() {
return productId;
}

public void setProductId(Double productId) {
this.productId = productId;
}

public Integer getPosition() {
return position;
}

public void setPosition(Integer position) {
this.position = position;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

public Object getAlt() {
return alt;
}

public void setAlt(Object alt) {
this.alt = alt;
}

public Integer getWidth() {
return width;
}

public void setWidth(Integer width) {
this.width = width;
}

public Integer getHeight() {
return height;
}

public void setHeight(Integer height) {
this.height = height;
}

public String getSrc() {
return src;
}

public void setSrc(String src) {
this.src = src;
}

public List<Object> getVariantIds() {
return variantIds;
}

public void setVariantIds(List<Object> variantIds) {
this.variantIds = variantIds;
}

public String getAdminGraphqlApiId() {
return adminGraphqlApiId;
}

public void setAdminGraphqlApiId(String adminGraphqlApiId) {
this.adminGraphqlApiId = adminGraphqlApiId;
}

    public boolean isClickStatus() {
        return clickStatus;
    }

    public void setClickStatus(boolean clickStatus) {
        this.clickStatus = clickStatus;
    }

    public boolean isCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }
}