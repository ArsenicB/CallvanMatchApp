package com.example.mobileswpj7t;

public class Taxi {
    private String documentId;
    private String startP;
    private String endP;
    private int People;

    public Taxi(String documentId, String startP, String endP, int people) {
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

    public int getPeople() {
        return People;
    }

    public void setPeople(int people) {
        People = people;
    }
}
