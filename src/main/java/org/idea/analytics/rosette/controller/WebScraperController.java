package org.idea.analytics.rosette.controller;


import org.idea.analytics.rosette.model.URL;
import org.idea.analytics.rosette.services.WebScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping(WebScraperController.SEARCH)
public class WebScraperController {
    public final static String SEARCH = "/search/";

    @Autowired
    WebScraper scraperService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<URL>> getUrls(final WebRequest request) {
        String searchQuery = request.getParameter("q");
        List<URL> urls = null;
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            new ResponseEntity<>(null, HttpStatus.OK);
        }
        urls = scraperService.getUrls(searchQuery);
        ResponseEntity<List<URL>> response = new ResponseEntity<List<URL>>(urls, HttpStatus.OK);
        return response;
    }
}
