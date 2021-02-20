package J.AppUsers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import J.AppUsers.model.App;

@Service
public class AppListsService {
  @Autowired
  @Qualifier("applist")
  List<App> applist;

  List<App> getApps() {
    return applist;
  }
  void setApps(List<App> apl){
    this.applist=apl;
  }
}
