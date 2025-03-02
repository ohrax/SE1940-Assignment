/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author admin
 */
public class AbsentRequest {

    private int requestId;
    private String title;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private int createdBy;
    private String status; //approve, in progress, reject
    private int processedBy;
    private String reason;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public enum StatusEnum {
        APPROVED("Approved"), IN_PROGRESS("In Progress"), REJECTED("Rejected");
        private final String value;
        StatusEnum(String value) { this.value = value; }
        public String getValue() { return value; }
    }

    public AbsentRequest(int requestId, String title, LocalDateTime fromDate, LocalDateTime toDate, int createdBy, String status, int processedBy, String reason, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.requestId = requestId;
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdBy = createdBy;
        this.status = status;
        setStatus(status);
        this.processedBy = processedBy;
        this.reason = reason;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    

    public AbsentRequest() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if ("Approved".equals(status) || "In Progress".equals(status) || "Rejected".equals(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status must be 'Approved', 'In Progress', or 'Rejected'");
        }
    }

    public int getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(int processedBy) {
        this.processedBy = processedBy;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

}
