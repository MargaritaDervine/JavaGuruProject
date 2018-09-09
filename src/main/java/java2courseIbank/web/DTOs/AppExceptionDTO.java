package java2courseIbank.web.DTOs;

import java2courseIbank.AppError;

import java.util.List;

public class AppExceptionDTO {
    private List<AppError> errors;

    public AppExceptionDTO() {
    }

    public AppExceptionDTO(List<AppError> errors) {
        this.errors = errors;
    }

    public List<AppError> getErrors() {
        return errors;
    }

    public void setErrors(List<AppError> errors) {
        this.errors = errors;
    }
}
