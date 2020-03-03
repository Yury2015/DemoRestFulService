package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/quote")
    public Quote createQuote(@RequestParam(value = "isin") String isin,
                             @RequestParam(value = "ask") String ask,
                             @RequestParam(value = "bid") String bid) {
        if (isin.length() != 12) {
            throw new RuntimeException("isin length is not 12 chars");
        }
        if (bid.length() > 0 && ask.length() > 0) {

        }

        Quote quote = new Quote(isin, bid, ask);
        Elvl.save(counter.incrementAndGet(), quote);
        return quote;
    }

    @GetMapping("/elvl")
    public Float getElvl(@RequestParam(value = "isin") String isin) {
        return Elvl.getElvl(isin);
    }

}
