package com.softway.diagnostic.exception;

/**
 * Exception métier pour les index de santé invalides
 * Respecte le principe de gestion d'erreurs appropriée
 */
public class InvalidHealthIndexException extends RuntimeException {
    
    private final int invalidIndex;
    
    public InvalidHealthIndexException(int invalidIndex, String message) {
        super(message);
        this.invalidIndex = invalidIndex;
    }
    
    public InvalidHealthIndexException(int invalidIndex, String message, Throwable cause) {
        super(message, cause);
        this.invalidIndex = invalidIndex;
    }
    
    public int getInvalidIndex() {
        return invalidIndex;
    }
    
    @Override
    public String getMessage() {
        return String.format("Index de santé invalide [%d]: %s", invalidIndex, super.getMessage());
    }
}
