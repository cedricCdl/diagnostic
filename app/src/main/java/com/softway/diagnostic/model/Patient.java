package com.softway.diagnostic.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    
    private String id;
    private int healthIndex;
    private List<Pathology> pathologies;
    private LocalDateTime arrivalTime;
    
    public Patient(String id, int healthIndex) {
        this.id = id;
        this.healthIndex = healthIndex;
        this.arrivalTime = LocalDateTime.now();
        this.pathologies = new ArrayList<>(); // Initialiser la liste vide
    }
    
    public Patient(String id, int healthIndex, List<Pathology> pathologies) {
        this.id = id;
        this.healthIndex = healthIndex;
        this.arrivalTime = LocalDateTime.now();
        this.pathologies = pathologies != null ? new ArrayList<>(pathologies) : new ArrayList<>();
    }
    
    public Patient() {
    }
    
    // Getters et Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public int getHealthIndex() {
        return healthIndex;
    }
    
    public void setHealthIndex(int healthIndex) {
        this.healthIndex = healthIndex;
    }
    
    public List<Pathology> getPathologies() {
        return pathologies;
    }
    
    public void setPathologies(List<Pathology> pathologies) {
        this.pathologies = pathologies;
    }
    
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
    
    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
