package com.example.memorygame.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Option {

@SerializedName("id")
@Expose
private Double id;
@SerializedName("product_id")
@Expose
private Double productId;
@SerializedName("name")
@Expose
private String name;
@SerializedName("position")
@Expose
private Integer position;
@SerializedName("values")
@Expose
private List<String> values = null;

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

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Integer getPosition() {
return position;
}

public void setPosition(Integer position) {
this.position = position;
}

public List<String> getValues() {
return values;
}

public void setValues(List<String> values) {
this.values = values;
}

}