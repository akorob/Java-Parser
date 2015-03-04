package com.spring.model;

import com.spring.persistence.domain.Owner;

import java.io.Serializable;

/**
 * Created by Andrey on 03.03.2015.
 */
public class OwnerDto implements Serializable {
    private int id;
    private String name;
    private String region;
    private String address;
    private String phone;
    private boolean enable;

    public  OwnerDto(){}

    public OwnerDto(Owner owner){
        this.id = owner.getId();
        this.name = owner.getName();
        this.region = owner.getRegion();
        this.address = owner.getAddress();
        this.phone = owner.getPhone();
        this.enable = owner.isEnable();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
