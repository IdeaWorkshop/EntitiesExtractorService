package org.idea.analytics.rosette.services;


import org.idea.analytics.rosette.model.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebScraperImpl implements WebScraper {
    @Override
    public List<URL> getUrls(String searchQuery) {
        List<URL> urls = new ArrayList<>();
        String search = "http://www.bing.com/search?q=" + searchQuery;

        try {
            Document document = Jsoup.connect(search).get();
            Elements elements = document.select("li.b_algo h2 a");

            for (int i = 0; i < 20 && i < elements.size(); i++) {
                String attr = elements.get(i).attr("href");
                String text = elements.get(i).text();
                URL url = new URL(text, attr);
                urls.add(url);
            }
            return urls;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
