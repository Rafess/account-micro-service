package com.letscode.accountmicroservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HelloResponse {
    private String text;
}