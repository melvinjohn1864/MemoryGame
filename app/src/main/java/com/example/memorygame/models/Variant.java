package com.example.memorygame.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variant {

@SerializedName("id")
@Expose
private Double id;
@SerializedName("product_id")
@Expose
private Double productId;
@SerializedName("title")
@Expose
private String title;
@SerializedName("price")
@Expose
private String price;
@SerializedName("sku")
@Expose
private String sku;
@SerializedName("position")
@Expose
private Integer position;
@SerializedName("inventory_policy")
@Expose
private String inventoryPolicy;
@SerializedName("compare_at_price")
@Expose
private Object compareAtPrice;
@SerializedName("fulfillment_service")
@Expose
private String fulfillmentService;
@SerializedName("inventory_management")
@Expose
private Object inventoryManagement;
@SerializedName("option1")
@Expose
private String option1;
@SerializedName("option2")
@Expose
private Object option2;
@SerializedName("option3")
@Expose
private Object option3;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("taxable")
@Expose
private Boolean taxable;
@SerializedName("barcode")
@Expose
private Object barcode;
@SerializedName("grams")
@Expose
private Integer grams;
@SerializedName("image_id")
@Expose
private Object imageId;
@SerializedName("weight")
@Expose
private Double weight;
@SerializedName("weight_unit")
@Expose
private String weightUnit;
@SerializedName("inventory_item_id")
@Expose
private Double inventoryItemId;
@SerializedName("inventory_quantity")
@Expose
private Integer inventoryQuantity;
@SerializedName("old_inventory_quantity")
@Expose
private Integer oldInventoryQuantity;
@SerializedName("requires_shipping")
@Expose
private Boolean requiresShipping;
@SerializedName("admin_graphql_api_id")
@Expose
private String adminGraphqlApiId;

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

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getPrice() {
return price;
}

public void setPrice(String price) {
this.price = price;
}

public String getSku() {
return sku;
}

public void setSku(String sku) {
this.sku = sku;
}

public Integer getPosition() {
return position;
}

public void setPosition(Integer position) {
this.position = position;
}

public String getInventoryPolicy() {
return inventoryPolicy;
}

public void setInventoryPolicy(String inventoryPolicy) {
this.inventoryPolicy = inventoryPolicy;
}

public Object getCompareAtPrice() {
return compareAtPrice;
}

public void setCompareAtPrice(Object compareAtPrice) {
this.compareAtPrice = compareAtPrice;
}

public String getFulfillmentService() {
return fulfillmentService;
}

public void setFulfillmentService(String fulfillmentService) {
this.fulfillmentService = fulfillmentService;
}

public Object getInventoryManagement() {
return inventoryManagement;
}

public void setInventoryManagement(Object inventoryManagement) {
this.inventoryManagement = inventoryManagement;
}

public String getOption1() {
return option1;
}

public void setOption1(String option1) {
this.option1 = option1;
}

public Object getOption2() {
return option2;
}

public void setOption2(Object option2) {
this.option2 = option2;
}

public Object getOption3() {
return option3;
}

public void setOption3(Object option3) {
this.option3 = option3;
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

public Boolean getTaxable() {
return taxable;
}

public void setTaxable(Boolean taxable) {
this.taxable = taxable;
}

public Object getBarcode() {
return barcode;
}

public void setBarcode(Object barcode) {
this.barcode = barcode;
}

public Integer getGrams() {
return grams;
}

public void setGrams(Integer grams) {
this.grams = grams;
}

public Object getImageId() {
return imageId;
}

public void setImageId(Object imageId) {
this.imageId = imageId;
}

public Double getWeight() {
return weight;
}

public void setWeight(Double weight) {
this.weight = weight;
}

public String getWeightUnit() {
return weightUnit;
}

public void setWeightUnit(String weightUnit) {
this.weightUnit = weightUnit;
}

public Double getInventoryItemId() {
return inventoryItemId;
}

public void setInventoryItemId(Double inventoryItemId) {
this.inventoryItemId = inventoryItemId;
}

public Integer getInventoryQuantity() {
return inventoryQuantity;
}

public void setInventoryQuantity(Integer inventoryQuantity) {
this.inventoryQuantity = inventoryQuantity;
}

public Integer getOldInventoryQuantity() {
return oldInventoryQuantity;
}

public void setOldInventoryQuantity(Integer oldInventoryQuantity) {
this.oldInventoryQuantity = oldInventoryQuantity;
}

public Boolean getRequiresShipping() {
return requiresShipping;
}

public void setRequiresShipping(Boolean requiresShipping) {
this.requiresShipping = requiresShipping;
}

public String getAdminGraphqlApiId() {
return adminGraphqlApiId;
}

public void setAdminGraphqlApiId(String adminGraphqlApiId) {
this.adminGraphqlApiId = adminGraphqlApiId;
}

}