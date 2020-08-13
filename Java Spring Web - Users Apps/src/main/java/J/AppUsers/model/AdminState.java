package J.AppUsers.model;

import lombok.Data;

@Data
public class AdminState {
    private boolean state;

    public AdminState() {
        this.state = false;
    }
}