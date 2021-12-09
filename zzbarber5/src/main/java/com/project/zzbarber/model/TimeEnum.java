package com.project.zzbarber.model;



public enum TimeEnum {

    TimeEnum10_00("10.00"),
    TimeEnum10_30("10.30"),
    TimeEnum11_00("11.00"),
    TimeEnum11_30("11.30"),
    TimeEnum12_00("12.00"),
    TimeEnum14_00("14.00"),
    TimeEnum14_30("14.30"),
    TimeEnum15_00("15.00"),
    TimeEnum15_30("15.30"),
    TimeEnum16_00("16.00"),
    TimeEnum16_30("16.30"),
    TimeEnum17_00("17.00"),
    TimeEnum17_30("17.30"),
    TimeEnum18_00("18.00"),
    TimeEnum18_30("18.30");


    private final String time;

     TimeEnum(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
