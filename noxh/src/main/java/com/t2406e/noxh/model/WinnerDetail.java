package com.t2406e.noxh.model;

public class WinnerDetail {
    private int applicantId;
    private String name;
    private boolean status;

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

//Không dùng Entity Applicant cho JOIN, tránh lẫn nghiệp vụ
