package J.AppUsers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import J.AppUsers.model.AdminState;

@Service
public class AdminStateService {
  @Autowired
  @Qualifier("admin")
  AdminState admin;

  AdminState getState() {
    return admin;
  }

  void setState(AdminState adm) {
    this.admin = adm;
  }
}
