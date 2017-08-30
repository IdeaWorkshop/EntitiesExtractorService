package org.idea.analytics.location.service;


import org.idea.analytics.location.model.Address;
import org.idea.analytics.location.model.AddressComponent;
import org.idea.analytics.location.model.google.Address_components;
import org.idea.analytics.location.model.google.GoogleMapAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private RestTemplate restTemplate;

    private static Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    @Override
    public Address getAddress(String location) {

        Address address = new Address();
        address.setValid(false);

        String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + location;
        GoogleMapAddress googleAddress = restTemplate.getForObject(url, GoogleMapAddress.class);

        if ("OK".equals(googleAddress.getStatus())) {

            address.setValid(true);
            address.setFullAddress(googleAddress.getResults()[0].getFormatted_address());
            address.setComponents(getComponnets(googleAddress));
            address.setLongitude(googleAddress.getResults()[0].getGeometry().getLocation().getLng());
            address.setLatitude(googleAddress.getResults()[0].getGeometry().getLocation().getLat());

        }
        logger.info("Address " + address);
        return address;
    }


    private List<AddressComponent> getComponnets(GoogleMapAddress address) {
        List<AddressComponent> components = new ArrayList<>();
        for (Address_components googleComponent : address.getResults()[0].getAddress_components()) {
            AddressComponent component = new AddressComponent();
            component.setType(googleComponent.getTypes()[0]);
            component.setName(googleComponent.getLong_name());
            components.add(component);
        }
        return components;
    }
}
