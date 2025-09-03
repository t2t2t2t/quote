package com.study.quote.service;

import com.study.quote.model.Quote;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service

public class QuoteService {

    private final RestTemplate restTemplate;

    public QuoteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Quote getRandomQuote() {
        QuoteResponse result = restTemplate.getForObject("https://favqs.com/api/qotd", QuoteResponse.class);
        return result.getQuote();
    }

    public List<Quote> searchQuotes(String keyword) {
        String url="https://favqs.com/api/quotes?filter="+keyword;
        QuoteResponseSearch result = restTemplate.getForObject(url, QuoteResponseSearch.class);
        return result.getQuotes();
    }


    @Getter
    @Setter
    private static class QuoteResponse {
       private Quote quote;
    }

    @Getter
    @Setter
    private static class QuoteResponseSearch {
       private List<Quote> quotes;
    }

}
