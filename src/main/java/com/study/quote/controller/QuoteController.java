package com.study.quote.controller;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.study.quote.model.Quote;
import com.study.quote.service.QuoteService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class QuoteController {

    private QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public String showQuote(Model model) {
        Quote randomQuote=quoteService.getRandomQuote();
        model.addAttribute("quote", randomQuote);

        return "index";
    }
    @GetMapping("/search")
    public String searchQuote(@RequestParam String query, Model model) {
        List<Quote> quoteList=quoteService.searchQuotes(query);
        if (!quoteList.isEmpty()
                && quoteList.size() == 1
                && "No quotes found".equalsIgnoreCase(quoteList.get(0).getBody())) {

            model.addAttribute("quoteList", Collections.emptyList());
        } else {
            model.addAttribute("quoteList", quoteList);
        }

        return "search-results";
    }


}
