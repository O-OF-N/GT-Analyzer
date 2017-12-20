package org.gtd.properties.model;

import java.io.Serializable;

/**
 * Created by vm033450 on 12/12/17.
 */
public final class ExtractConfig implements Serializable{

    private String split;
    private String city;
    private String country;
    private String region;
    private String state;
    private String latitude;
    private String longitude;
    private String year;
    private String month;
    private String date;
    private String id;
    private String type;
    private String subtype;
    private String corp;
    private String target1;
    private String nationality1;
    private String target2;
    private String nationality2;
    private String target3;
    private String nationality3;
    private String suicide;

    public ExtractConfig() {
    }

    public String getSplit() {
        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getTarget1() {
        return target1;
    }

    public void setTarget1(String target1) {
        this.target1 = target1;
    }

    public String getNationality1() {
        return nationality1;
    }

    public void setNationality1(String nationality1) {
        this.nationality1 = nationality1;
    }

    public String getTarget2() {
        return target2;
    }

    public void setTarget2(String target2) {
        this.target2 = target2;
    }

    public String getNationality2() {
        return nationality2;
    }

    public void setNationality2(String nationality2) {
        this.nationality2 = nationality2;
    }

    public String getTarget3() {
        return target3;
    }

    public void setTarget3(String target3) {
        this.target3 = target3;
    }

    public String getNationality3() {
        return nationality3;
    }

    public void setNationality3(String nationality3) {
        this.nationality3 = nationality3;
    }

    public String getSuicide() {
        return suicide;
    }

    public void setSuicide(String suicide) {
        this.suicide = suicide;
    }
}
