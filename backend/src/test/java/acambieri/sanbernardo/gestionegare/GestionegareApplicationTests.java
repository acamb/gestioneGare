package acambieri.sanbernardo.gestionegare;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static acambieri.sanbernardo.gestionegare.JwtUtils.generateToken;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GestionegareApplicationTests {

	@Autowired
	private MockMvc mvc;
	@Value("${jwt.secret}")
	private String jwtSecret;
	@Value("${token.validity.seconds}")
	private Long tokenValidity;
	@Autowired
	@Qualifier("dummy")
	private UserDetailsService userDetailsService;

	@Test
	public void testNotAuthorized() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/test")).andExpect(status().isUnauthorized());
	}

	@Test
	public void authenticateAndAccessRoot() throws Exception{
		UserDetails userDetails=userDetailsService.loadUserByUsername("andrea");
		String token = "Bearer " + generateToken(userDetails,tokenValidity,jwtSecret);
		assertNotNull(token);
		mvc.perform(MockMvcRequestBuilders.get("/").header("Authorization", token)).andExpect(status().isOk());
	}


}
