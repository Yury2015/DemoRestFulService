package com.example.restservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Elvl {
    private static Map<Long, Quote> history = new HashMap<>();
    private static Map<String, List<Quote>> historyQuoteByIsin = new HashMap<>();
    private static Map<String, Float> elvl = new HashMap<>();

    public static Float getElvl(String isin) {
        return Elvl.elvl.get(isin);
    }

    public static void save(Long id, Quote quote) {
        history.put(id, quote);
        elvl.put(quote.getIsin(), calculateElvl(quote));
    }

    private static Float calculateElvl(Quote quote) {
        String isin = quote.getIsin();
        Float elvl = Elvl.elvl.get(isin);
        Float bid = Float.valueOf(quote.getBid());
        Float ask = Float.valueOf(quote.getAsk());

        if (elvl != null) {
            if (bid > elvl) {
                return bid;
            }
            if (ask < elvl) {
                return ask;
            }
        } else {
            if (bid == null) {
                return ask;
            }
            return bid;
        }


        return null;
    }

    public static void main(String[] args) {
       Elvl app = new Elvl();
       Quote quote1 = new Quote( "RU000A0JX0J2", String.valueOf(100.2f), String.valueOf(101.9f));
       app.save(1L, quote1);
       Float result = app.getElvl("RU000A0JX0J2");
       System.out.println(result);

       Quote quote2 = new Quote("RU000A0JX0J2", String.valueOf(100.5f), String.valueOf(101.9f));
       app.save(2L, quote2);
       result = app.getElvl("RU000A0JX0J2");
       System.out.println(result);

    }


}
