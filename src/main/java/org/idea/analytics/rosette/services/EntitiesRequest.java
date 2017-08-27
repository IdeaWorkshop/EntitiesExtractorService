package org.idea.analytics.rosette.services;

import com.basistech.rosette.api.HttpRosetteAPI;
import com.basistech.rosette.api.common.AbstractRosetteAPI;
import com.basistech.rosette.apimodel.DocumentRequest;
import com.basistech.rosette.apimodel.EntitiesOptions;
import com.basistech.rosette.apimodel.EntitiesResponse;
import com.basistech.util.LanguageCode;

public class EntitiesRequest {
    private String contentUri;
    private LanguageCode languageCode;
    private HttpRosetteAPI api;

    private EntitiesRequest(RequestBuilder builder) {
        contentUri = builder.getContentUri();
        languageCode = builder.getLanguageCode();
        api = new HttpRosetteAPI.Builder().key(System.getenv("API_KEY")).build();
    }

    private String getContentUri() {
        return contentUri;
    }

    private HttpRosetteAPI getApi() {
        return api;
    }

    private LanguageCode getLanguageCode() {
        return languageCode;
    }

    public EntitiesResponse send() {
        DocumentRequest<EntitiesOptions> request = new DocumentRequest.Builder<EntitiesOptions>()
                .language(getLanguageCode())
                .contentUri(getContentUri())
                .build();
        EntitiesResponse response = getApi().perform(AbstractRosetteAPI.ENTITIES_SERVICE_PATH, request, EntitiesResponse.class);
        return response;
    }

    public static class RequestBuilder {
        private String contentUri;
        private LanguageCode languageCode;

        private String getContentUri() {
            return contentUri;
        }

        public RequestBuilder setContentUri(String contentUri) {
            this.contentUri = contentUri;
            return this;
        }

        private LanguageCode getLanguageCode() {
            return languageCode;
        }

        public RequestBuilder setLanguageCode(LanguageCode languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public EntitiesRequest build() {
            return new EntitiesRequest(this);
        }
    }
}
