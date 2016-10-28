package com.luxshare.phoneinfo;

import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class LocationModel {

    private String countryName;

    private String locality;

    private List<String> addressLines;

    public List<String> getAddressLines() {
        return addressLines;
    }

    public void setAddressLines(List<String> addressLines) {
        this.addressLines = addressLines;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    @Override
    public String toString() {
        return countryName + "-----" + locality + "----" + addressLines;
    }
}
