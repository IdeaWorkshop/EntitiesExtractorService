package org.idea.analytics.location.service;


import org.idea.analytics.location.model.Address;

public interface LocationService {
    Address getAddress(String location);
}
