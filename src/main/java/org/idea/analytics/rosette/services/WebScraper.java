package org.idea.analytics.rosette.services;


import org.idea.analytics.rosette.model.URL;

import java.util.List;

public interface WebScraper {
    List<URL> getUrls(String searchQuery);
}
