package xyz.migoo.web.dao;

import org.apache.ibatis.annotations.Param;
import xyz.migoo.web.po.ScheduleDetailPO;
import xyz.migoo.web.vo.ScheduleDetailVO;

import java.util.List;

/**
 * @author xiaomi
 */
public interface ScheduleDetailDAO {

    /**
     * 通过 ScheduleDetailPO 对象 查找schedule
     * @param scheduleId  scheduleId
     * @return schedule list
     */
    List<ScheduleDetailVO> selectByScheduleId(@Param("scheduleId") Long scheduleId);

    int insert(ScheduleDetailPO schedule);

    int update(ScheduleDetailPO schedule);
}
