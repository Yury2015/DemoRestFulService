/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class QuoteControllerTests {


	@Autowired
	private MockMvc mockMvc;

	@Test
	public void noParamQuoteShouldReturnError() throws Exception {
		this.mockMvc.perform(get("/quote")).andDo(print()).andExpect(status().is(400));
	}

	@Test
	public void paramQuoteShouldExceptionBidMustLessAsk() {
		try {
			this.mockMvc.perform(get("/quote")
					.param("isin", "RU000A0JX0J2")
					.param("bid", "105.2")
					.param("ask", "101.9"));
			fail("Exception \"Bid must less ask!\" is not thrown");
		} catch (Exception e) {
			String errorMessage = "Bid must less ask!";
			String message = e.getMessage().substring(e.getMessage().length() - errorMessage.length());
			assertEquals(errorMessage, message);
		}
	}

	@Test
	public void paramQuoteShouldExceptionIsinLengthIsNot12Chars() {
		try {
			this.mockMvc.perform(get("/quote")
					.param("isin", "RU000A0JX0J")
					.param("bid", "100.2")
					.param("ask", "101.9"));
			fail("Exception \"isin length is not 12 chars\" is not thrown");
		} catch (Exception e) {
			String errorMessage = "isin length is not 12 chars";
			String message = e.getMessage().substring(e.getMessage().length() - errorMessage.length());
			assertEquals(errorMessage, message);
		}

	}

	@Test
	public void paramQuoteShouldReturnOk() throws Exception {
		this.mockMvc.perform(get("/quote")
				.param("isin", "RU000A0JX0J2")
				.param("bid", "100.2")
				.param("ask", "101.9"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("{\"isin\":\"RU000A0JX0J2\",\"bid\":\"100.2\",\"ask\":\"101.9\"}"));
	}

	@Test
	public void paramElvlShouldReturnOk() throws Exception {
		this.mockMvc.perform(get("/elvl")
				.param("isin", "RU000A0JX0J2"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("100.2"));
	}

	@Test
	public void paramElvlAllShouldReturnOk() throws Exception {
		this.mockMvc.perform(get("/elvlAll"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("[{\"RU000A0JX0J2\":100.2}]"));
	}

}
