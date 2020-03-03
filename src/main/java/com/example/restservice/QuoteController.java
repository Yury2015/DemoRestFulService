package com.example.restservice;

import java.util.List;
import java.util.Map;
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
        if (!(bid.isEmpty() || ask.isEmpty())) {
            if (Float.valueOf(bid) >= Float.valueOf(ask)) {
                throw new RuntimeException("Bid must less ask!");
            }
        }

        Quote quote = new Quote(isin, bid, ask);
        Elvl.save(counter.incrementAndGet(), quote);
        return quote;
    }

    @GetMapping("/elvl")
    public Float getElvl(@RequestParam(value = "isin") String isin) {
        return Elvl.getElvl(isin);
    }

    @GetMapping("/elvlAll")
    public List<Map.Entry<String, Float>> getElvlAll() {
        return Elvl.getElvlAll();
    }

}
