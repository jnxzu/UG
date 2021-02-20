package J.AppUsers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import J.AppUsers.model.User;

@Service
public class UserListsService {
  @Autowired
  @Qualifier("userlist")
  List<User> userlist;

  List<User> getUsers() {
    return userlist;
  }
  void setUsers(List<User> usrl){
    this.userlist=usrl;
  }
}
