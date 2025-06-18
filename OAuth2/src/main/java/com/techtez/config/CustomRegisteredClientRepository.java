package com.techtez.config;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

import com.techtez.entity.Client;
import com.techtez.repository.ClientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class CustomRegisteredClientRepository implements RegisteredClientRepository {

    private final ClientRepository repo;

    @Override
    public void save(RegisteredClient registeredClient) {
        throw new UnsupportedOperationException("Saving is not supported.");
    }

    @Override
    public RegisteredClient findById(String id) {
        // Optional: implement lookup by ID from DB
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
       
    	Optional<Client> optional = repo.findByClientId(clientId);
        if (optional.isEmpty()) return null;

        Client client = optional.get();

        return RegisteredClient.withId(UUID.randomUUID().toString()) // Ensure this is a unique and consistent value
            .clientId(client.getClientId())
            .clientName(client.getName())
            .clientSecret(client.getClientSecret()) // should be hashed already
            .scope(client.getScope()) // assumes single string; split if needed
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .build();
    }
}
