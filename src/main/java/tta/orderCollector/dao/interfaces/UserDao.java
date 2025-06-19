package tta.orderCollector.dao.interfaces;

import java.util.List;

import tta.orderCollector.dto.model.User;

public interface UserDao {
   void save(User user);
   List<User> list();
}
