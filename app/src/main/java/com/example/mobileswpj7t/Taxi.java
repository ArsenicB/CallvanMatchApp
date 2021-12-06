package com.example.mobileswpj7t;

public class Taxi {
    private String documentId;
    private String startP;
    private String endP;
    private String People;

    public Taxi(){

    }

    public Taxi(String documentId, String startP, String endP, String people) {
        this.documentId = documentId;
        this.startP = startP;
        this.endP = endP;
        People = people;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getStartP() {
        return startP;
    }

    public void setStartP(String startP) {
        this.startP = startP;
    }

    public String getEndP() {
        return endP;
    }

    public void setEndP(String endP) {
        this.endP = endP;
    }

    public String getPeople() {
        return People;
    }

    public void setPeople(String people) {
        People = people;
    }

    @Override
    public String toString() {
        return "Taxi{" +
                "documentId='" + documentId + '\'' +
                ", startP='" + startP + '\'' +
                ", endP='" + endP + '\'' +
                ", People=" + People +
                '}';
    }
}
