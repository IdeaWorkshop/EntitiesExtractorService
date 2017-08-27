package org.idea.analytics.rosette.controller;

import com.basistech.rosette.api.HttpRosetteAPIException;
import org.idea.analytics.rosette.model.Entity;
import org.idea.analytics.rosette.services.EntitiesExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = EntitiesExtractorController.ENTITIES)
public class EntitiesExtractorController {

    public static final String ENTITIES = "/entities/";
    public static final Logger logger = LoggerFactory.getLogger(EntitiesExtractorController.class);
    @Autowired
    EntitiesExtractor entitiesExtractor;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, List<Entity>>> getEntities(final WebRequest webRequest) {
        String url = webRequest.getParameter("url");
        if (url == null || url.trim().isEmpty()) return null;
        Map<String, List<Entity>> entityMap = new HashMap<>();
        try {
            entityMap = entitiesExtractor.getEntityMap(url);
        } catch (HttpRosetteAPIException exception) {
            logger.error("Exception occurred in Rosette Apis", exception);
            entityMap = new HashMap<>();
        }
        return new ResponseEntity<>(entityMap, HttpStatus.OK);
    }
}
