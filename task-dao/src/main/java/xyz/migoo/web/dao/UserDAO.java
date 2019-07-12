package xyz.migoo.web.dao;

import xyz.migoo.web.po.UserPO;
import xyz.migoo.web.vo.UserVO;

import java.util.List;

/**
 * @author xiaomi
 */
public interface UserDAO {

    /**
     * 通过 ScheduleDTO 对象 查找schedule
     * @return UserVO list
     */
    List<UserVO> select();

    UserVO selectById(Long id);

    UserVO selectByName(String userName);

    int insert(UserPO user);

    int update(UserPO user);
}
