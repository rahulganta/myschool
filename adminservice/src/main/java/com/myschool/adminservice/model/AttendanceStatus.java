package com.myschool.adminservice.model;

public enum AttendanceStatus {
    P("PRESENT"),
    E("EXECUSED_ABSENT"),
    U("UNEXECUSED_ABSENT"),
    L("LATE");
    private String label;
    AttendanceStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
