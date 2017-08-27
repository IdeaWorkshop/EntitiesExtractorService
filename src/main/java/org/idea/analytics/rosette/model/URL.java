package org.idea.analytics.rosette.model;

public class URL {
    private String url;
    private String text;

    public URL(String text, String url) {
        setText(text);
        setUrl(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

