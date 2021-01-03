package com.rindus.handlers;

import lombok.SneakyThrows;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateErrorHandler
        implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {
        return httpResponse.getStatusCode().is5xxServerError();
    }

    @SneakyThrows
    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {
        if (httpResponse.getStatusCode().is5xxServerError()) {
            throw new Rindus5Exception("Some error happen");
        }
    }
}
