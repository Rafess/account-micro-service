package com.letscode.accountmicroservice.dto.clients;

import lombok.Data;

@Data
public class CPFInfo {
    private String name;
    private CPFStatus cpfStatus;
}
