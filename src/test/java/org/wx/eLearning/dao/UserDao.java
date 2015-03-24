package org.wx.eLearning.dao;

import org.springframework.stereotype.Repository;
import org.wx.eLearning.model.User;
//Repository是Spring基于持久层（dao）的注解注入；而相应的业务层为（@service）；控制层为@Controller 
@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao{

}
