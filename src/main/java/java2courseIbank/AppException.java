package java2courseIbank;

import java.util.ArrayList;
import java.util.List;

public class AppException extends RuntimeException {

    private List<AppError> errors;

    public AppException(AppError error) {
        this.errors = new ArrayList<>();
        this.errors.add(error);
    }

    public AppException(List<AppError> errors) {
        this.errors = errors;
    }

    public List<AppError> getErrors() {
        return errors;
    }
}