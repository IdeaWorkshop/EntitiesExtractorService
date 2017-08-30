package org.idea.analytics.location.model;


public class AddressComponent {
    private String type;
    private String name;
    public AddressComponent() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AddressComponent [" + "type= " + type + ", name= " + name + "]";

    }
}
