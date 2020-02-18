package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedHashMap;

import org.apache.tomcat.util.json.JSONParser;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AuthorizationServerApplicationTests {

	@Autowired
	private MockMvc mvc;

	@LocalServerPort
	int randomServerPort;

	@Test
	void invokeCreateAuthoken() throws Exception {
		mvc.perform(post("/oauth/token").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.with(user("db-client-password-credentials").password("test_secret"))
				.queryParam("grant_type", "password").queryParam("scope", "any").queryParam("username", "enduser")
				.queryParam("password", "test_secret")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void validateAuthoken() throws Exception {
		MvcResult result = mvc
				.perform(post("/oauth/token").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.with(user("db-client-password-credentials").password("test_secret"))
						.queryParam("grant_type", "password").queryParam("scope", "any")
						.queryParam("username", "enduser").queryParam("password", "test_secret"))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		JSONParser parser = new JSONParser(result.getResponse().getContentAsString());
		LinkedHashMap<String, Object> object = parser.object();
		String access_token = (String) object.get("access_token");
		assertNotNull(access_token);

		mvc.perform(post("/oauth/check_token").with(user("db-client-password-credentials").password("test_secret"))
				.queryParam("token", access_token)).andDo(print()).andExpect(status().isOk()).andReturn();
	}

	@Test
	void validateAuthCode() throws Exception {
		MvcResult authrize = mvc.perform(get("/oauth/authorize").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.queryParam("client_id", "db_client_auth_code").queryParam("grant_type", "authorization_code")
				.queryParam("state", "1234").queryParam("response_type", "code").queryParam("redirect_uri", "/testapp"))
				.andDo(print()).andExpect(status().is3xxRedirection()).andReturn();

		MockHttpSession session = (MockHttpSession) authrize.getRequest().getSession();
		String loginUrl = authrize.getResponse().getRedirectedUrl();

		MvcResult login = mvc.perform(post(loginUrl).param("username", "enduser").param("password", "test_secret")
				.session(session).with(csrf())).andDo(print()).andExpect(status().is3xxRedirection()).andReturn();

		authrize = mvc.perform(get(login.getResponse().getRedirectedUrl())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).param("client_id", "db_client_auth_code")
				.param("grant_type", "authorization_code").param("state", "1234").param("response_type", "code")
				.param("redirect_uri", "/testapp").session(session)).andDo(print()).andExpect(status().isOk())
				.andReturn();

		authrize = mvc
				.perform(get(authrize.getResponse().getForwardedUrl())
						.contentType(MediaType.APPLICATION_FORM_URLENCODED).session(session))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		session = (MockHttpSession) authrize.getRequest().getSession();

		authrize = mvc
				.perform(post("/oauth/authorize").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("user_oauth_approval", "true").param("scope.any", "true").param("authorize", "Authorize")
						.session(session).with(csrf()))
				.andDo(print()).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("/testapp?code=**&state=1234")).andReturn();
	}

}
