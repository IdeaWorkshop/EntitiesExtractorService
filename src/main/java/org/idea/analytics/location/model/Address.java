package org.idea.analytics.location.model;



import java.util.List;

public class Address {
    private String latitude;
    private String longitude;
    private String fullAddress;
    private List<AddressComponent> components;
    private boolean valid;
    public Address() {
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
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

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public List<AddressComponent> getComponents() {
        return components;
    }

    public void setComponents(List<AddressComponent> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "Address[" + "latitude=" + latitude +   ", longitude=" + longitude
                +", fullAddress= " + fullAddress
                +", components=" + components
                +", valid=" + valid +"]";

    }
}
