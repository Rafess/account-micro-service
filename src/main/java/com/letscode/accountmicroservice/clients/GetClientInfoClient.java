package com.letscode.accountmicroservice.clients;

import com.letscode.accountmicroservice.entities.client.Client;
import com.letscode.accountmicroservice.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetClientInfoClient {
    @Value("${micro.client}")
    private String url;

    public Client execute(String cpf) {

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            ResponseEntity<Client> clientResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Client.class, cpf);
            if (clientResponseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ClientNotFoundException();
            }
            clientResponseEntity.getStatusCode();
            return clientResponseEntity.getBody();


    }
}
