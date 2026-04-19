package com.bootcamp.bootcamp17.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class BaseResponse<T> {
    private UUID reqId = UUID.randomUUID();
    private String status = "T";
    private String message = "Berhasil";
    private T data;
}
