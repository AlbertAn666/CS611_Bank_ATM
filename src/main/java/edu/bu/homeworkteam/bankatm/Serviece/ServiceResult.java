package edu.bu.homeworkteam.bankatm.Serviece;

import lombok.Data;

@Data
public class ServiceResult {
    private boolean successful;
    private String message;

    public ServiceResult(boolean successful, String message){
        this.successful=successful;
        this.message=message;
    }
}
