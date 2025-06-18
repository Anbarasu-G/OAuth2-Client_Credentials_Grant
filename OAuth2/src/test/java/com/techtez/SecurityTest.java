package com.techtez;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

	
	@Autowired
	private MockMvc mockMvc;
	private static final String ACCESS_TOKEN_ENDPOINT = "/oauth2/token";

	@Test
	public void testAccessTokenFail() throws Exception {

		mockMvc.perform(post(ACCESS_TOKEN_ENDPOINT)
				
				.param("grant_type", "client_credentials")
				.param("client_id", "vfsf")
				.param("client_secret", "vdfvj")
				)
		.andDo(print())
		.andExpect(status().isUnauthorized())
		.andExpect(jsonPath("$.error", is("invalid_client")));
	}
	
	@Test
	public void testAccessTokenSuccess() throws Exception {

		mockMvc.perform(post(ACCESS_TOKEN_ENDPOINT)
				
				.param("grant_type", "client_credentials")
				.param("client_id", "client-2")
				.param("client_secret", "password-1")
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.access_token").isString())
		.andExpect(jsonPath("$.expires_in").isNumber())
		.andExpect(jsonPath("$.token_type", is("Bearer")));
	}
}
