package com.example.mobileswpj7t.Adapters;

import com.google.firebase.firestore.ServerTimestamp;
import java.util.Date;

public class Board {
    private String documentId;
    private String nicname;
    private String title;
    private String contents;
    @ServerTimestamp
    private Date data;

    public Board() {
    }

//    public Board(String documentId, String title, String contents) {
//        this.documentId = documentId;
//        this.title = title;
//        this.contents = contents;
//    }
    public Board(String documentId, String nicname, String title,  String contents) {
        this.documentId = documentId;
        this.nicname = nicname;
        this.title = title;
        this.contents = contents;
    }

    public String getNicname() {
        return nicname;
    }

    public void setNicname(String nicname) {
        this.nicname = nicname;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

//    @Override
//    public String toString() {
//        return "Board{" +
//                "documentId='" + documentId + '\'' +
//                ", title='" + title + '\'' +
//                ", contents='" + contents + '\'' +
//                ", data=" + data +
//                '}';
//    }
        @Override
    public String toString() {
        return "Board{" +
                "documentId='" + documentId + '\'' +
                ", nicname='" + nicname + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", data=" + data +
                '}';
    }
}
