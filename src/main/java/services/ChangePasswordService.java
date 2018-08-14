package services;

import database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordService {
    @Autowired
   Database database;

    public ChangePasswordService(Database database) {
        this.database = database;
    }

}
