package com.letscode.accountmicroservice.clients;

import com.letscode.accountmicroservice.dto.clients.CPFInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class GetCPFInfoClient {
    @Value("${example.client.cpf.validator}")
    private String url;

    public CPFInfo execute(String cpf) {


            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            ResponseEntity<CPFInfo> cpfInfoResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, CPFInfo.class, cpf);
        // TODO LANÇAR ERRO
        cpfInfoResponseEntity.getStatusCode();
        return cpfInfoResponseEntity.getBody();

    }
}
