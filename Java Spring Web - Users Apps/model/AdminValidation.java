package J.AppUsers.model;

import J.AppUsers.validator.AdminPW;
import lombok.Data;

@Data
public class AdminValidation {
    @AdminPW
    private String pw;

    public AdminValidation(String pw) {
        this.pw = pw;
    }

    public AdminValidation() {
        this.pw = "";
    }
}