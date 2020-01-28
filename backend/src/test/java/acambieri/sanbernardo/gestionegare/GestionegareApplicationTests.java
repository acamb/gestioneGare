package acambieri.sanbernardo.gestionegare;

import acambieri.sanbernardo.gestionegare.config.SecurityConfig;
import acambieri.sanbernardo.gestionegare.config.WebConfig;
import acambieri.sanbernardo.gestionegare.controllers.GareController;
import acambieri.sanbernardo.gestionegare.services.DummyUserDetailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.print.attribute.standard.Media;
import javax.servlet.Filter;

import static acambieri.sanbernardo.gestionegare.JwtUtils.generateToken;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GestionegareApplicationTests {

	private MockMvc mvc;
	@Value("${jwt.secret}")
	private String jwtSecret;
	@Value("${token.validity.seconds}")
	private Long tokenValidity;
	@Autowired
	@Qualifier("dummy")
	private UserDetailsService userDetailsService;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private Filter springSecurityFilterChain;

	@Before
	public void setup(){
		this.mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.addFilter(springSecurityFilterChain)
				.build();
	}

	@Test
	public void testNotAuthorized() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/gare/getGare?anno=2020")).andExpect(status().isUnauthorized());
	}

	@Test
	public void authenticateAndAccessProtectedEndpoint() throws Exception{
		String token = initToken("andrea");
		assertNotNull(token);
		mvc.perform(MockMvcRequestBuilders.get("/gare/getGare?anno=2020").header("Authorization", token)).andExpect(status().isOk());
	}

	/*@Test
	public void viewRole_editEndpoint_notAuthorized()throws Exception{
		String token = initToken("view");
		assertNotNull(token);
		mvc.perform(MockMvcRequestBuilders.post("/gare/salva")
				.content("{\"nome\": \"aaa\",\"anno\":2020}")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void editRole_editEndpoint_authorized()throws Exception{
		String token = initToken("andrea");
		assertNotNull(token);
		mvc.perform(MockMvcRequestBuilders.post("/gare/salva")
				.content("{nome: 'aaa',anno:2020}")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token))
				.andExpect(status().isOk());
	}*/

	private String initToken(String username){
		UserDetails userDetails=userDetailsService.loadUserByUsername(username);
		return "Bearer " + generateToken(userDetails,tokenValidity,jwtSecret);
	}

}
