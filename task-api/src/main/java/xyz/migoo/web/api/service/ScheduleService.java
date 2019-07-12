package xyz.migoo.web.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.migoo.web.dao.ScheduleDAO;
import xyz.migoo.web.po.SchedulePO;
import xyz.migoo.web.vo.ScheduleVO;

import java.util.Date;
import java.util.List;

/**
 * @author xiaomi
 */
@Service
public class ScheduleService {
    private final ScheduleDAO scheduleDAO;

    @Autowired
    public ScheduleService(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }

    public void insert(SchedulePO schedulePO){
        schedulePO.setId(System.currentTimeMillis());
        schedulePO.setCreateAt(new Date());
        schedulePO.setModifyAt(new Date());
        scheduleDAO.insert(schedulePO);
    }

    public void update(SchedulePO schedulePO){
        schedulePO.setModifyAt(new Date());
        scheduleDAO.update(schedulePO);
    }

    public List<ScheduleVO> selectByUserId(SchedulePO schedulePO){
        return scheduleDAO.selectByUserId(schedulePO);
    }

    public ScheduleVO selectById(Long id){
        return scheduleDAO.selectById(id);
    }
}
