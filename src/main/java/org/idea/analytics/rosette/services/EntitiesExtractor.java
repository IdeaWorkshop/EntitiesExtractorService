package org.idea.analytics.rosette.services;


import org.idea.analytics.rosette.model.Entity;

import java.util.List;
import java.util.Map;

public interface EntitiesExtractor {
    Map<String, List<Entity>> getEntityMap(String uri);
}
