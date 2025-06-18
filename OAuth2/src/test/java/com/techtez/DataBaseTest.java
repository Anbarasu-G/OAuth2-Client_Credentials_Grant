package com.techtez;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.techtez.entity.Client;
import com.techtez.repository.ClientRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class DataBaseTest {

	@Autowired
	private ClientRepository repository;
	
	@Test
	public void testAddClient() {

		Client client = new Client();
		
		client.setClientId("client-2");
		client.setName("Vasanth");
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = "password-1";
		
		client.setClientSecret(encoder.encode(password));
		client.setScope("read");
		
		Client savedClient = repository.save(client);
		
		assertThat(savedClient.getId()).isNotNull();
		
		
	}
	
	@Test
	public void testFindByClientId() {

		Optional<Client> byClientId = repository.findByClientId("client-2");
		assertThat(byClientId).isPresent();
		
		
	}
}
