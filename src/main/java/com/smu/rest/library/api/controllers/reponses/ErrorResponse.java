package com.smu.rest.library.api.controllers.reponses;

import java.util.List;

public class ErrorResponse {

    private List<String> messages;

    public ErrorResponse(List<String> message) {
        this.messages = message;
    }

    public List<String> getMessages() {
        return messages;
    }
}
