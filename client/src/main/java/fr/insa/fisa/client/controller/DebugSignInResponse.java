package fr.insa.fisa.client.controller;

public class DebugSignInResponse {
    private String message;
    private boolean successfulMatch;
    private String rawPasswordProvided;
    private String encodedPasswordStored;

    public DebugSignInResponse(String message, boolean successfulMatch, String rawPasswordProvided, String encodedPasswordStored) {
        this.message = message;
        this.successfulMatch = successfulMatch;
        this.rawPasswordProvided = rawPasswordProvided;
        this.encodedPasswordStored = encodedPasswordStored;
    }

    // Getters et Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessfulMatch() {
        return successfulMatch;
    }

    public void setSuccessfulMatch(boolean successfulMatch) {
        this.successfulMatch = successfulMatch;
    }

    public String getRawPasswordProvided() {
        return rawPasswordProvided;
    }

    public void setRawPasswordProvided(String rawPasswordProvided) {
        this.rawPasswordProvided = rawPasswordProvided;
    }

    public String getEncodedPasswordStored() {
        return encodedPasswordStored;
    }

    public void setEncodedPasswordStored(String encodedPasswordStored) {
        this.encodedPasswordStored = encodedPasswordStored;
    }
}

