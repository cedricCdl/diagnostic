package com.softway.diagnostic.model;

public enum Pathology {
    CARDIOLOGY("Cardiologie"),
    TRAUMATOLOGY("Traumatologie");
    
    private final String displayName;
    
    Pathology(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
