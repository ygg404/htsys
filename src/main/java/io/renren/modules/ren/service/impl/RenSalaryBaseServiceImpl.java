package io.renren.modules.ren.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.*;
import io.renren.modules.ren.vo.RenRecordVoEntity;
import io.renren.modules.ren.vo.RenSalaryVoEntity;
import io.renren.modules.set.entity.SetScoreHouseEntity;
import io.renren.modules.set.service.SetScoreHouseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import io.renren.modules.ren.dao.RenSalaryBaseDao;
import io.renren.modules.ren.entity.RenSalaryBaseEntity;
import io.renren.modules.ren.service.RenSalaryBaseService;


@Service("renSalaryBaseService")
public class RenSalaryBaseServiceImpl extends ServiceImpl<RenSalaryBaseDao, RenSalaryBaseEntity> implements RenSalaryBaseService {
    @Autowired
    private SetScoreHouseService setScoreHouseService;

    @Override
    public List<RenSalaryBaseEntity> queryList(Map<String, Object> params) {
        String salaryYear = (String)params.get("salaryYear");
        String salaryMonth = (String)params.get("salaryMonth");

        List<RenSalaryBaseEntity> list = this.selectList(
          new EntityWrapper<RenSalaryBaseEntity>().eq("salary_year",salaryYear)
          .eq("salary_month",salaryMonth)
        );

        return list;
    }

    /**
     * 根据档案资料设置基本工资
     */
    @Override
    public List<RenSalaryBaseEntity> setSalaryByRecord(List<RenRecordVoEntity> recordVoList, List<RenSalaryBaseEntity> salaryList,Map<String, Object> params){
        // 职级房补
        List<SetScoreHouseEntity> houseList = setScoreHouseService.queryList(new MapUtils());

        List<RenSalaryBaseEntity> salaryNewList = new ArrayList<>();
        for(RenRecordVoEntity recordVoEntity : recordVoList){
            boolean isInSalary = false;
            RenRecordVoEntity recordNewVo = recordVoEntity;
            RenSalaryBaseEntity salaryNewBase = new RenSalaryBaseEntity();
            for(RenSalaryBaseEntity salaryBase : salaryList) {
                // 判断当前月份 是否存在某个人的工资
                if (recordVoEntity.getuserId()  ==  salaryBase.getUserId()){
                    isInSalary = true;
                    salaryNewBase = salaryBase;
                    break;
                }

            }
            // 当前工资月份
            String curMonth = params.get("salaryYear") + "-" + String.format("%02d", Integer.parseInt(params.get("salaryMonth").toString())) + "-08";
            // 入职时间不能为空 并且 入职时间要小于当前时间
            if ( recordNewVo.getentryTime() != null && isMaxMonth(DateUtil.fomatDate(curMonth),recordNewVo.getentryTime())) {
                // 当前月份下的用户的工资有保存 则不通过档案设置
                if (isInSalary) {
                    ;
                } else{
                    salaryNewBase.setUserId(recordNewVo.getuserId());
                    salaryNewBase.setUsername(recordNewVo.getUsername());
                    salaryNewBase.setEntryTime(recordNewVo.getentryTime());
                    salaryNewBase.setEducationTime(recordNewVo.getEducationTime());
                    // 学历评分
                    salaryNewBase.setEdId(recordNewVo.geteducation());
                    salaryNewBase.setEdName(recordNewVo.getEdName());
                    salaryNewBase.setEdScore(recordNewVo.getEdScore());
                    salaryNewBase.setEdProid(recordNewVo.getProRatio());
                    salaryNewBase.setEdProname(recordNewVo.getProName());
                    salaryNewBase.setEdProscore(recordNewVo.getEdProscore());
                    salaryNewBase.setEdTypeid(recordNewVo.getEducationType());
                    salaryNewBase.setEdTypename(recordNewVo.getEdTypeName());
                    salaryNewBase.setEdTypescore(recordVoEntity.getEdTypescore());
                    salaryNewBase.setEdAllscore(
                            (long) Math.round((recordNewVo.getEdScore() == null ? 0f : recordNewVo.getEdScore()) *
                                    (recordNewVo.getEdProscore() == null ? 0f : recordNewVo.getEdProscore()) *
                                    (recordNewVo.getEdTypescore() == null ? 0f : recordNewVo.getEdTypescore()))
                    );
                    // 职称评分
                    salaryNewBase.setTitleId(recordNewVo.gettitleLever());
                    salaryNewBase.setTitleName(recordNewVo.getTitleName());
                    salaryNewBase.setTitleScore(recordNewVo.getTitleScore());
                    salaryNewBase.setTitleProid(recordNewVo.gettrialPeriod());
                    salaryNewBase.setTitleProname(recordNewVo.getTitleProname());
                    salaryNewBase.setTitleProscore(recordNewVo.getTitleProscore());
                    salaryNewBase.setTitleAllscore(
                            (long) Math.round((recordNewVo.getTitleProscore() == null ? 0f : recordNewVo.getTitleProscore()) *
                                    (recordNewVo.getTitleScore() == null ? 0f : recordNewVo.getTitleScore()))
                    );
                    // 工龄评分
                    // 其他公司工作年龄= 他公司工作年份/2 (他公司工作年份不超过6年)
                    if (recordNewVo.getEducationTime() == null || recordNewVo.getentryTime() == null) {
                        salaryNewBase.setOtherYear(0L);
                    } else {
                        Long otherYear = (long) DateUtil.getDiffYear(DateUtil.getFormatDay(recordNewVo.getEducationTime()), DateUtil.getFormatDay(recordNewVo.getentryTime()));
                        salaryNewBase.setOtherYear(otherYear >= 6 ? 3L : otherYear / 2);
                    }
                    // 司龄
                    if (recordNewVo.getentryTime() == null) {
                        salaryNewBase.setFirmYear(0L);
                    } else {
                        salaryNewBase.setFirmYear(
                                (long) DateUtil.getDiffYear(DateUtil.getFormatDay(recordNewVo.getentryTime()), curMonth)
                        );
                        // 欧阳这个仔比较特殊，扣除一年工龄
                        if (recordNewVo.getuserId() == 9){
                            salaryNewBase.setFirmYear(salaryNewBase.getFirmYear() - 1L );
                        }
                    }
                    //合计工龄
                    salaryNewBase.setWorkYear(salaryNewBase.getOtherYear() + salaryNewBase.getFirmYear());
                    salaryNewBase.setWorkScore(salaryNewBase.getWorkYear() * 3);
                    //职务评分
                    salaryNewBase.setDutyId(recordNewVo.getDutyId());
                    salaryNewBase.setDutyName(recordNewVo.getDutyName());
                    salaryNewBase.setDutyScore(recordNewVo.getDutyScore());
                    // 合计总分认定职级（学历分 + 职称评分 + 工龄评分 + 职务评分）
                    Long fixedScore = salaryNewBase.getEdAllscore() + salaryNewBase.getTitleAllscore()
                            + salaryNewBase.getWorkScore() + (salaryNewBase.getDutyScore() == null ? 0L : salaryNewBase.getDutyScore());
                    // 固定工资
                    salaryNewBase.setFixedSalary(fixedScore * 25f);
                    for (SetScoreHouseEntity houseEntity : houseList) {
                        if (houseEntity.getHighScore() > fixedScore && fixedScore >= houseEntity.getLowScore()) {
                            salaryNewBase.setJobRank(houseEntity.getJobRank());
                            salaryNewBase.setHousingSalary(houseEntity.getHouseAdd());
                            break;
                        }
                    }
                    // 是否为实习生 （实习生不算效能分）
                    salaryNewBase.setJobType(recordNewVo.getjobType());
                    if (recordNewVo.getjobType() != null && recordNewVo.getjobType() == 3) {
                        salaryNewBase.setJobRank("实习生");
                        salaryNewBase.setFixedSalary(1020f);
                        salaryNewBase.setKbiSalary(480f);
                        salaryNewBase.setHousingSalary(200L);
                    } else {
                        // 效能分数
                        salaryNewBase.setKbiAuditScore(recordNewVo.getKbiAuditScore());
                        // 没有审定的考核效能分数
                        Long dutyKbiScore = (long) (recordNewVo.getDutyStandScore() == null ? 0 : recordNewVo.getDutyStandScore());
                        Long titleKbiScore = (long) (recordNewVo.getTitleStandScore() == null ? 0 : recordNewVo.getTitleStandScore());
                        // 该成员的转正时间
                        Date fillDate = DateUtils.addDateMonths(recordNewVo.getentryTime(), recordNewVo.gettrialPeriod().intValue());
                        // 判断该成员是否在试用期
                        // 最后的试用期时间
                        String lastTrial = params.get("salaryYear") + "-" + String.format("%02d", Integer.parseInt(params.get("salaryMonth").toString())) + "-08";
                        if( isMaxMonth( DateUtils.addDateMonths(fillDate,-1) , DateUtil.fomatDate(lastTrial))  ){
                            dutyKbiScore = (long) (recordNewVo.getDutyTrialScore() == null ? 0 : recordNewVo.getDutyTrialScore());
                            titleKbiScore = (long) (recordNewVo.getTitleTrialScore() == null ? 0 : recordNewVo.getTitleTrialScore());
                        }
                        Long kbiScore = (dutyKbiScore >= titleKbiScore) ? dutyKbiScore : titleKbiScore;
                        salaryNewBase.setKbiScore(kbiScore);

                        // 最终认定的效能分 （有考核的选审定后的分数）
                        salaryNewBase.setKbiFinalScore(recordNewVo.getKbiAuditScore() == null ? kbiScore : recordNewVo.getKbiAuditScore());
                        // 效能工资 = 效能分* 80
                        salaryNewBase.setKbiSalary((salaryNewBase.getKbiFinalScore()) * 80f);
                    }
                    salaryNewList.add(salaryNewBase);
                }
            }
        }
        return salaryNewList;
    }

    // 判断两个日期的月份哪个大（date1 >= date2 为 true）
    public boolean isMaxMonth(Date dateOne, Date dateTwo) {
        //传入日期
        Calendar calder = Calendar.getInstance();
        calder.setTime(dateOne);//设置时间
        int year1 = calder.get(Calendar.YEAR);//获取年份
        int month1 =calder.get(Calendar.MONTH);//获取月份
        calder.setTime(dateTwo);//设置时间
        int year2 = calder.get(Calendar.YEAR);//获取年份
        int month2 =calder.get(Calendar.MONTH);//获取月份

        if(year1 < year2) {
            return false;
        } else if (year1 == year2) {
            if (month1 >= month2) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RenSalaryBaseEntity entity) {
        this.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RenSalaryBaseEntity entity) {
        this.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] Ids) {
        //删除
        this.deleteBatchIds(Arrays.asList(Ids));
    }

}