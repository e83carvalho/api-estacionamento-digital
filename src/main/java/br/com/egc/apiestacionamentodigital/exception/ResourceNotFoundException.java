package br.com.egc.apiestacionamentodigital.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final String MESSAGE = "recurso %s não encontrado.";
    private static final String MESSAGE_ID = "recurso %s com identificador %s não encontrado.";

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String entityName, Throwable causa) {
        super(String.format(MESSAGE, entityName), causa);
    }

    public ResourceNotFoundException(String entityName, String id) {
        super(String.format(MESSAGE_ID, entityName, id));
    }

    public ResourceNotFoundException(String entityName, String id, Throwable causa) {
        super(String.format(MESSAGE_ID, entityName, id), causa);
    }
}
