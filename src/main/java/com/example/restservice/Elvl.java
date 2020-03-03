package com.example.restservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Elvl {
    private static Map<Long, Quote> history = new HashMap<>();
    private static Map<String, Float> elvl = new HashMap<>();

    public static void setHistory(Map<Long, Quote> history) {
        Elvl.history = history;
    }

    public static void setElvl(Map<String, Float> elvl) {
        Elvl.elvl = elvl;
    }

    public static Float getElvl(String isin) {
        return Elvl.elvl.get(isin);
    }

    public static void save(Long id, Quote quote) {
        history.put(id, quote);
        elvl.put(quote.getIsin(), calculateElvl(quote));
    }

    private static Float calculateElvl(Quote quote) {
        String isin;
        Float elvl = null;
        Float bid = null;
        Float ask = null;
        try {
            isin = quote.getIsin();
            elvl = Elvl.elvl.get(isin);
            bid = Float.valueOf(quote.getBid());
            ask = Float.valueOf(quote.getAsk());

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
        } catch (NumberFormatException e) {
            if (quote.getBid().isEmpty()) {
                return Float.valueOf(quote.getAsk());
            }
            e.printStackTrace();
        }

        return elvl;
    }


    public static List<Map.Entry<String, Float>> getElvlAll() {
        List<Map.Entry<String, Float>> result = new ArrayList<>();
        for ( Map.Entry<String, Float> x : elvl.entrySet()) {
            result.add(x);
        }
        return result;
    }

}
