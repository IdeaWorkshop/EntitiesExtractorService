package org.idea.analytics.rosette.services;


import com.basistech.rosette.apimodel.EntitiesResponse;
import com.basistech.rosette.apimodel.EntityMention;
import com.basistech.util.LanguageCode;
import org.idea.analytics.rosette.model.Entity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EntitiesExtractorImpl implements EntitiesExtractor {
    @Override
    public Map<String, List<Entity>> getEntityMap(String uri) {
        EntitiesRequest request = new EntitiesRequest.RequestBuilder()
                .setContentUri(uri)
                .setLanguageCode(LanguageCode.ENGLISH)
                .build();
        EntitiesResponse response = request.send();
        List<EntityMention> mentionList = response.getEntities();
        List<Entity> persons = new ArrayList<>();
        List<Entity> products = new ArrayList<>();
        List<Entity> locations = new ArrayList<>();

        mentionList.forEach((entity) -> {
            if ("PERSON".equals(entity.getType())) addEntity(entity, persons);
            else if ("ORGANIZATION".equals(entity.getType())) addEntity(entity, products);
            else if ("LOCATION".equals(entity.getType())) addEntity(entity, locations);
            else if ("PRODUCT".equals(entity.getType())) addEntity(entity, products);
        });
        Map<String, List<Entity>> entityMap = new HashMap<>();
        entityMap.put("persons", persons);
        entityMap.put("locations", locations);
        entityMap.put("products", products);
        return entityMap;
    }

    private void addEntity(EntityMention entityMention, List<Entity> entities) {
        Entity entity = new Entity();
        entity.setMention(entityMention.getMention());
        entity.setCount(entityMention.getCount());
        entity.setType(entityMention.getType());
        entities.add(entity);
    }

}
