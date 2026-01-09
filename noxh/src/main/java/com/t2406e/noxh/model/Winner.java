package com.t2406e.noxh.model;

public class Winner {
    private int id;
    private int applicantId;

    public Winner() {}

    public Winner(int id, int applicantId) {
        this.id = id;
        this.applicantId = applicantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }
}
