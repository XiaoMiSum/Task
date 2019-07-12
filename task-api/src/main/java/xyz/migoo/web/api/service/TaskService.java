package xyz.migoo.web.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.migoo.web.dto.ScheduleDTO;
import xyz.migoo.web.dto.ScheduleDetailDTO;
import xyz.migoo.web.po.ScheduleDetailPO;
import xyz.migoo.web.po.SchedulePO;
import xyz.migoo.web.pojo.ResponseBean;
import xyz.migoo.web.vo.ScheduleDetailVO;
import xyz.migoo.web.vo.ScheduleVO;

import java.util.List;

/**
 * @author xiaomi
 */
@Service
public class TaskService {

    private final ScheduleService scheduleService;

    private final ScheduleDetailService detailService;

    @Autowired
    public TaskService(ScheduleService scheduleService, ScheduleDetailService detailService) {
        this.scheduleService = scheduleService;
        this.detailService = detailService;
    }

    public ResponseBean getSchedule(ScheduleDTO scheduleDTO){
        ResponseBean responseBean = new ResponseBean();
        try {
            SchedulePO schedulePO = new SchedulePO();
            schedulePO.setUserId(scheduleDTO.getUserId());
            schedulePO.setId(scheduleDTO.getId());
            List<ScheduleVO> scheduleVOList = scheduleService.selectByUserId(schedulePO);
            responseBean.setStatus(200);
            responseBean.setData(scheduleVOList);
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setStatus(999);
        }
        return responseBean;
    }

    public ResponseBean getScheduleDetail(ScheduleDetailDTO scheduleDetailDTO){
        ResponseBean responseBean = new ResponseBean();
        try {
            List<ScheduleDetailVO> detailVOList = detailService.selectByScheduleId(scheduleDetailDTO.getScheduleId());
            ScheduleVO scheduleVO = scheduleService.selectById(scheduleDetailDTO.getScheduleId());
            scheduleVO.setDetail(detailVOList);
            responseBean.setStatus(200);
            responseBean.setData(scheduleVO);
        }catch (Exception e){
            e.printStackTrace();
            responseBean.setStatus(999);
        }
        return responseBean;
    }

    public void addSchedule(ScheduleDTO scheduleDTO){
        SchedulePO schedulePO = new SchedulePO();
        schedulePO.setUserId(scheduleDTO.getUserId());
        schedulePO.setTitle(scheduleDTO.getTitle());
        schedulePO.setCount(0);
        schedulePO.setLocked(0);
        schedulePO.setLevle(0);
        scheduleService.insert(schedulePO);
    }

    public void editSchedule(ScheduleDTO scheduleDTO){
        SchedulePO schedulePO = new SchedulePO();
        schedulePO.setId(scheduleDTO.getId());
        schedulePO.setIsDelete(scheduleDTO.getIsDelete());
        schedulePO.setLevle(scheduleDTO.getLevel());
        schedulePO.setLocked(scheduleDTO.getLocked());
        schedulePO.setCount(scheduleDTO.getCount());
        schedulePO.setTitle(scheduleDTO.getTitle());
        scheduleService.update(schedulePO);
    }

    public void addScheduleDetail(ScheduleDetailDTO detailDTO){
        ScheduleDetailPO detailPO = new ScheduleDetailPO();
        detailPO.setScheduleId(detailDTO.getScheduleId());
        detailPO.setIsDelete(0);
        detailPO.setStatus(1);
        detailPO.setLevle(0);
        detailPO.setTitle(detailDTO.getTitle());
        detailService.insert(detailPO);

        // 更新 主表 count
        ScheduleVO scheduleVO = scheduleService.selectById(detailDTO.getScheduleId());
        SchedulePO schedulePO = new SchedulePO();
        schedulePO.setId(detailDTO.getScheduleId());
        schedulePO.setCount(scheduleVO.getCount() + 1);
        scheduleService.update(schedulePO);
    }

    public void editScheduleDetail(ScheduleDetailDTO detailDTO){
        ScheduleDetailPO detailPO = new ScheduleDetailPO();
        detailPO.setId(detailDTO.getId());
        detailPO.setScheduleId(detailDTO.getScheduleId());
        detailPO.setIsDelete(detailDTO.getIsDelete());
        detailPO.setStatus(detailDTO.getStatus());
        detailPO.setLevle(detailDTO.getLevel());
        detailPO.setTitle(detailDTO.getTitle());
        detailService.update(detailPO);

        // 更新 主表 count
        ScheduleVO scheduleVO = scheduleService.selectById(detailDTO.getScheduleId());
        SchedulePO schedulePO = new SchedulePO();
        schedulePO.setId(detailDTO.getScheduleId());
        schedulePO.setCount(scheduleVO.getCount() + detailDTO.getCount());
        scheduleService.update(schedulePO);
    }
}
