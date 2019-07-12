package xyz.migoo.web.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.migoo.web.dao.ScheduleDetailDAO;
import xyz.migoo.web.po.ScheduleDetailPO;
import xyz.migoo.web.vo.ScheduleDetailVO;

import java.util.Date;
import java.util.List;

/**
 * @author xiaomi
 */
@Service
public class ScheduleDetailService {
    private final ScheduleDetailDAO detailDAO;

    @Autowired
    public ScheduleDetailService(ScheduleDetailDAO detailDAO) {
        this.detailDAO = detailDAO;
    }

    public void insert(ScheduleDetailPO detailPO){
        detailPO.setId(System.currentTimeMillis());
        detailPO.setCreateAt(new Date());
        detailPO.setModifyAt(new Date());
        detailDAO.insert(detailPO);
    }

    public void update(ScheduleDetailPO detailPO){
        detailPO.setModifyAt(new Date());
        detailDAO.update(detailPO);
    }

    public List<ScheduleDetailVO> selectByScheduleId(Long scheduleId){
        return detailDAO.selectByScheduleId(scheduleId);
    }
}
