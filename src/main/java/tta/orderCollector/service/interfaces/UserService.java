package tta.orderCollector.service.interfaces;

import java.util.List;

import tta.orderCollector.dto.model.User;

public interface UserService {
   void save(User user);
   List<User> list();
}
