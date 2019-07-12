package xyz.migoo.web.dao;

import org.apache.ibatis.annotations.Param;
import xyz.migoo.web.po.SchedulePO;
import xyz.migoo.web.vo.ScheduleVO;

import java.util.List;

/**
 * @author xiaomi
 */
public interface ScheduleDAO {

    /**
     * 通过 ScheduleDTO 对象 查找schedule
     * @param schedule  ScheduleDTO
     * @return schedule list
     */
    List<ScheduleVO> selectByUserId(SchedulePO schedule);

    ScheduleVO selectById(@Param("id") Long id);

    int insert(SchedulePO schedule);

    int update(SchedulePO schedule);
}
