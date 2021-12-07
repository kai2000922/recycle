/**
 * Copyright 2021 bejson.com
 */
package com.ruoyi.recycle.domain.express;

/**
 * Auto-generated: 2021-11-01 17:9:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class PackageInfo {

    private String cargoName;
    private String deliveryType;
    private int totalNumber;
    private double totalVolume;
    private Long totalWeight;
    private String packageService;

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalVolume(double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalWeight(Long totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Long getTotalWeight() {
        return totalWeight;
    }

    public void setPackageService(String packageService) {
        this.packageService = packageService;
    }

    public String getPackageService() {
        return packageService;
    }

}