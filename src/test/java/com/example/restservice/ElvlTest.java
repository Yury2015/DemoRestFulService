package com.example.restservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class ElvlTest {
    private Elvl app;
    private Quote quote1;
    private Quote quote2;
    private Quote quote3;
    private Quote quoteEmptyBid;

    @BeforeEach
    void setUp() {
        app = new Elvl();
        app.setHistory(new HashMap<>());
        app.setElvl(new HashMap<>());
        quote1 = new Quote("RU000A0JX0J2", String.valueOf(100.2f), String.valueOf(101.9f));
        quote2 = new Quote("RU000A0JX0J2", String.valueOf(100.5f), String.valueOf(101.9f));
        quote3 = new Quote("RU000A0JX0J2", String.valueOf(100.5f), String.valueOf(101.8f));
        quoteEmptyBid = new Quote("RU000A0JX0J2", "", String.valueOf(101.9f));
    }

    @Test
    void getElvl() {
        app.save(1L, quote1);
        Float result = app.getElvl("RU000A0JX0J2");

        assertEquals(100.2F, result);

        app.save(2L, quote2);

        assertEquals(100.5F, Elvl.getElvl("RU000A0JX0J2"));

    }

    @Test
    void getElvlAndBidBiggerElvl() {
        app.save(1L, quote1);

        assertEquals(100.2F, Elvl.getElvl("RU000A0JX0J2"));

        app.save(2L, quote2);

        assertEquals(100.5F, Elvl.getElvl("RU000A0JX0J2"));

    }

    @Test
    void getElvlAndElvlMissing() {
        app.save(1L, quote1);

        assertEquals(100.2F, Elvl.getElvl("RU000A0JX0J2"));

    }


    @Test
    void elvlEmpty() {

        assertEquals(null, Elvl.getElvl("RU000A0JX0J2"));

    }

    @Test
    void getElvlAndElvlMissingAndBidMissing() {
        app.save(1L, quoteEmptyBid);

        assertEquals(101.9F, Elvl.getElvl("RU000A0JX0J2"));

    }

    @Test
    void getElvlAndBidMissing() {

        app.save(1L, quoteEmptyBid);

        assertEquals(101.9F, Elvl.getElvl("RU000A0JX0J2"));

        app.save(2L, quote2);

        assertEquals(101.9F, Elvl.getElvl("RU000A0JX0J2"));

    }

    @Test
    void getElvlAndBidMissingAndAskLessElvl() {
        app.save(1L, quoteEmptyBid);

        assertEquals(101.9F, Elvl.getElvl("RU000A0JX0J2"));

        app.save(2L, quote3);

        assertEquals(101.8F, Elvl.getElvl("RU000A0JX0J2"));

    }


    @Test
    void save() {
        app.save(1L, quote1);
        app.save(2L, quote2);
        app.save(3L, quote3);
        app.save(4L, quoteEmptyBid);
        assertEquals(4, app.getHistory().size());
    }
}