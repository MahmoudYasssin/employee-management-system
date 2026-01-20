package com.example.employeemanagementsystem.exception;

public class ErrorResponse {

    private int status;
    private String message;
    private long timetamp;

    public ErrorResponse(int status, String message, long timetamp) {
        this.status = status;
        this.message = message;
        this.timetamp = timetamp;
    }

    public ErrorResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimetamp() {
        return timetamp;
    }

    public void setTimetamp(long timetamp) {
        this.timetamp = timetamp;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", timetamp=" + timetamp +
                '}';
    }
}
