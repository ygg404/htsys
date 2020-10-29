package io.renren.modules.perf.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.MapUtils;
import io.renren.modules.perf.dao.PerfExtraDao;
import io.renren.modules.perf.dao.PerfVoDao;
import io.renren.modules.perf.entity.PerfExtraEntity;
import io.renren.modules.perf.entity.PerfExtraScoringEntity;
import io.renren.modules.perf.service.PerfExtraScoringService;
import io.renren.modules.perf.service.PerfExtraService;
import io.renren.modules.perf.service.PerfVoService;
import io.renren.modules.perf.vo.PerfVoEntity;
import io.renren.modules.set.entity.BranchEntity;
import io.renren.modules.set.service.BranchService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("perfVoService")
public class PerfVoServiceImpl extends ServiceImpl<PerfVoDao, PerfVoEntity> implements PerfVoService {

    @Autowired
    private BranchService branchService;

    @Autowired
    private PerfExtraScoringService perfExtraScoringService;

    @Autowired
    private PerfExtraService perfExtraService;

    @Override
    public List<PerfVoEntity> queryList(Map<String, Object> params){
        List<BranchEntity> branchList = branchService.selectList(new EntityWrapper<BranchEntity>());
        List<PerfVoEntity> perfVoList = this.baseMapper.queryVoList(params);
        List<PerfExtraScoringEntity> scoringList = perfExtraScoringService.queryList(params);

        Long cUserId = 0L;
        List<PerfVoEntity> pVoList = new ArrayList<>();
        for (PerfVoEntity voEntity : perfVoList) {
            if (cUserId != voEntity.getCheckUserId()) {
                PerfVoEntity newEntity = new PerfVoEntity();
                // 被考核人 评分类 赋值
                newEntity.setCheckUserId(voEntity.getCheckUserId());
                newEntity.setCheckUserName(voEntity.getCheckUserName());
                newEntity.setDutyStandScore(voEntity.getDutyStandScore());
                newEntity.setTitleStandScore(voEntity.getTitleStandScore());
                newEntity.setCbranchId(voEntity.getCbranchId());
                newEntity.setCbranchName(voEntity.getCbranchName());
                newEntity.setKbiStandScore( (voEntity.getDutyStandScore() == null ? 0 : voEntity.getDutyStandScore()) >  (voEntity.getTitleStandScore() == null ? 0 : voEntity.getTitleStandScore())?
                        voEntity.getDutyStandScore() : voEntity.getTitleStandScore());
                newEntity.setRoleId(voEntity.getRoleId());
                newEntity.setRoleName(voEntity.getRoleName());
                newEntity.setPerfList( new ArrayList<>());
                pVoList.add(newEntity);
                cUserId = voEntity.getCheckUserId();
                // 加减分分数列表
                List<PerfExtraScoringEntity> sNewList = new ArrayList<>();
                for (PerfExtraScoringEntity scoringEntity : scoringList) {
                    if ( scoringEntity.getCheckUserId() == newEntity.getCheckUserId() ) {
                        sNewList.add(scoringEntity);
                    }
                }
                newEntity.setExtraList(sNewList);
            }
            PerfVoEntity nEntity = pVoList.get(pVoList.size()-1);
            List<PerfVoEntity> perfList = nEntity.getPerfList();
            perfList.add(voEntity);
            nEntity.setPerfList( perfList);

            voEntity.setIsGuider(setIsGuider(voEntity.getCbranchId(),voEntity.getUserId() ,branchList));
        }

        return pVoList;
    }

    // 查看是否为 其领导 (被考核人的工作组ID, 考核人Id, 工作组列表 )
    public Long setIsGuider(Long cbranchId, Long userId,List<BranchEntity> branchList){
        Long isGuider = 0L;
        List<BranchEntity> pBranchList = new ArrayList<>();
        this.findAllBranch(pBranchList,cbranchId,branchList);
        for (BranchEntity entity : pBranchList) {
            if(entity.getmdeputyId() == userId || entity.getsdeputyId() == userId ) {
                isGuider = 1L;
                break;
            }
        }
        return isGuider;
    }

    public void findAllBranch(List<BranchEntity> pBranchList,Long cbranchId,List<BranchEntity> branchList) {
        for(BranchEntity entity : branchList) {
            if (entity.getid() == cbranchId) {
                pBranchList.add(entity);
                if(entity.getparentId() == 0) {
                    break;
                } else {
                    findAllBranch(pBranchList,entity.getparentId(),branchList);
                    break;
                }
            }
        }
    }

    @Override
    public HSSFWorkbook exportExcel(List<PerfVoEntity> pList){
        List<PerfExtraEntity> extraList = perfExtraService.queryList(new MapUtils());
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 计算每个成员的加减得分总分
        Long extraAllScore;
        for(PerfVoEntity voEntity : pList) {
            extraAllScore = 0L;
            for ( PerfExtraScoringEntity extraEntity : voEntity.getExtraList()) {
                extraAllScore += extraEntity.getExtraNum();
            }
            voEntity.setExtraAllScore(extraAllScore);
        }
        // 每个被考核人 设置一个表格
        for(PerfVoEntity voEntity : pList) {
            // 统计加减分
            Long extraScore = 0L;
            HSSFSheet sheet = workbook.createSheet(voEntity.getCheckUserName());
            int rowIndex = 0;
            //设置表格列宽度和列数
            int colNum = voEntity.getPerfList().size() + 3;
            for ( int i = 0;i< colNum; i++) {
                sheet.setColumnWidth(i, 50 * 100);
            }
            // 标题字体设置
            HSSFFont hfont = workbook.createFont();
            hfont.setFontName("黑体");
            hfont.setFontHeightInPoints((short) 16);
            hfont.setBold(true);   //字体加粗
            // 标题表格样式
            HSSFCellStyle hstyle = workbook.createCellStyle();
            hstyle.setFont(hfont);
            hstyle.setAlignment(HorizontalAlignment.CENTER);       // 水平居中

            // 标题行
            HSSFRow hRow = sheet.createRow(rowIndex++);
            hRow.setHeight((short) 540);
            HSSFCell hcell = hRow.createCell(0);
            hcell.setCellValue(new HSSFRichTextString(voEntity.getCheckUserName() + "  考核表"));
            hcell.setCellStyle(hstyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));

            // 字段字体设置
            HSSFFont tfont = workbook.createFont();
            tfont.setFontName("黑体");
            tfont.setFontHeightInPoints((short) 12);
            tfont.setBold(true);   //字体加粗
            // 字段表格样式
            HSSFCellStyle tstyle = workbook.createCellStyle();
            tstyle.setFont(tfont);
            tstyle.setVerticalAlignment(VerticalAlignment.CENTER);  // 垂直居中

            // 考核标题
            Long userId = 0L;
            boolean isTitle = false;
            rowIndex++;
            HSSFRow tRow = sheet.createRow(rowIndex++);
            int colIndex = 0;
            for ( PerfVoEntity pEntity: voEntity.getPerfList() ) {
                if ( userId != pEntity.getUserId() && isTitle) {
                    HSSFCell tcell;
                    tcell = tRow.createCell(colIndex ++);
                    tcell.setCellValue(new HSSFRichTextString("个人效能评分"));
                    tcell.setCellStyle(tstyle);
                    tcell = tRow.createCell(colIndex ++);
                    tcell.setCellValue(new HSSFRichTextString("是否为其领导"));
                    tcell.setCellStyle(tstyle);
                    tcell = tRow.createCell(colIndex ++);
                    tcell.setCellValue(new HSSFRichTextString("是否为同一部门"));
                    tcell.setCellStyle(tstyle);
                    break;
                } else {
                    userId = pEntity.getUserId();
                    isTitle = true;
                    tRow.setHeight((short) 420);
                    HSSFCell tcell;
                    tcell = tRow.createCell(colIndex ++);
                    tcell.setCellValue(new HSSFRichTextString( pEntity.getKbiName() + "(" + pEntity.getKbiRatio().toString() + "%)"));
                    tcell.setCellStyle(tstyle);
                }
            }
            // 每个考核人 对 被考核人的评分明细
            userId = 0L;
            HSSFRow eRow = sheet.createRow(rowIndex);
            float kbiScore = 0; // 效能总分
            boolean isGudier = false;
            boolean isSameBranch = false;
            List<ScoreItem> scoreItemlist = new ArrayList<>();
            for ( PerfVoEntity pEntity: voEntity.getPerfList() ) {
                if ( userId != pEntity.getUserId() ) {
                    ScoreItem sItem = new ScoreItem();
                    sItem.setScore(kbiScore);
                    sItem.setIsGuider(isGudier);
                    sItem.setIsSameBranch(isSameBranch);
                    scoreItemlist.add(sItem);

                    colIndex = 0;
                    kbiScore = 0;
                    eRow = sheet.createRow(rowIndex++);
                    userId = pEntity.getUserId();
                    isGudier = (pEntity.getIsGuider() == 1 ? true : false);
                    isSameBranch = (pEntity.getIsSameBranch() == 1 ? true : false);
                    HSSFCell cell = eRow.createCell(11);
                    cell.setCellValue(new HSSFRichTextString(pEntity.getIsGuider() == 1 ? "是":"否"));
                    cell = eRow.createCell(12);
                    cell.setCellValue(new HSSFRichTextString(pEntity.getIsSameBranch() == 1 ? "是":"否"));
                }
                HSSFCell ecell;
                ecell = eRow.createCell(colIndex ++);
                ecell.setCellValue(new HSSFRichTextString( pEntity.getKbiScore() == null? "" : pEntity.getKbiScore().toString()));

                kbiScore += (pEntity.getKbiScore() == null? 0f : (float)pEntity.getKbiScore()) * (float)(pEntity.getKbiRatio()) / 100f;
                ecell = eRow.createCell(10);
                ecell.setCellValue(new HSSFRichTextString( String.valueOf(kbiScore) ));
            }
            rowIndex++;
            // 加减分扣分
            Long extraType = -1L;
            for ( PerfExtraEntity extraEntity : extraList) {
                // 加减分标题
                if (extraType != extraEntity.getExtraType()) {
                    eRow = sheet.createRow(rowIndex++);
                    HSSFCell tcell = eRow.createCell(0);
                    tcell.setCellValue( new HSSFRichTextString(   "加减分统计 / " + ( extraEntity.getExtraType() == 0L ?"加分项" : "减分项") ));
                    tcell.setCellStyle(tstyle);
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 4));
                    extraType = extraEntity.getExtraType();
                }
                eRow = sheet.createRow(rowIndex++);
                HSSFCell cell = eRow.createCell(0);
                cell.setCellValue(new HSSFRichTextString(extraEntity.getExtraItem()));
                sheet.addMergedRegion(new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 2));
                cell = eRow.createCell(3);
                cell.setCellValue(new HSSFRichTextString(extraEntity.getStandard()));
                sheet.addMergedRegion(new CellRangeAddress(rowIndex - 1, rowIndex - 1, 3, 4));
                // 默认 加减分的列表 分数为0
                HSSFCellStyle fontStyle = workbook.createCellStyle();
                HSSFFont ffont = workbook.createFont();
                ffont.setColor(HSSFColor.RED.index);
                fontStyle.setFont(ffont);
                cell = eRow.createCell(5);
                cell.setCellValue(new HSSFRichTextString("0"));
                cell.setCellStyle(fontStyle);

                for(PerfExtraScoringEntity score : voEntity.getExtraList()) {
                    if (score.getExtraId() == extraEntity.getId()) {
                        extraScore += score.getExtraNum();
                        voEntity.setExtraAllScore( extraScore);
                        cell = eRow.createCell(5);
                        cell.setCellValue(new HSSFRichTextString(score.getExtraNum().toString()));
                        cell.setCellStyle(fontStyle);
                        break;
                    }
                }
                voEntity.setExtraAllScore(extraScore);
            }
            // 计算最终的加减分
            // 获取同一部门的最高加减分数
            Long maxExtra = voEntity.getExtraAllScore();
            for (PerfVoEntity vEntity : pList) {
                if (vEntity.getCbranchId() == voEntity.getCbranchId() && vEntity.getExtraAllScore() > maxExtra) {
                    maxExtra = vEntity.getExtraAllScore();
                }
            }
            //个人加减分=10+个人加减分之和 （用此公式的目的是避免出现负分情况）
            // 加减得分 = 个人加减分 / 部门加减最高分 * 10 * 0.9
            Float finalExtra = (float)(voEntity.getExtraAllScore() + 10) / (float)(maxExtra + 10) * 10f * 0.9f;
            voEntity.setExtraFinalScore(finalExtra);
            // 统计 最终效能分
            Float kbiScoreOther = 0f ;  // 其他成员总分数
            int kbiOhterNum = 0;
            Float kbiScoreGuider = 0f;  // 领导成员总分数
            int kbiGuiderNum = 0;
            Float kbiScoreBranch = 0f; // 同部门成员总分数
            int kbiBranchNum = 0;
            int num = 0;
            for (ScoreItem scoreItem: scoreItemlist) {
                if (scoreItem.getIsGuider()) {
                    kbiScoreGuider += scoreItem.getScore();
                    kbiGuiderNum ++;
                }
                if (scoreItem.getIsSameBranch()) {
                    kbiScoreBranch += scoreItem.getScore();
                    kbiBranchNum ++;
                }
                if ( !(scoreItem.getIsGuider() && scoreItem.getIsSameBranch()) && num ++ > 0) {
                    kbiScoreOther += scoreItem.getScore();
                    kbiOhterNum ++;
                }
            }
            kbiScore = (kbiOhterNum > 0 ? kbiScoreOther * 0.2f / kbiOhterNum : 0f)
                                +  (kbiGuiderNum > 0 ? kbiScoreGuider * 0.4f / kbiGuiderNum : 0f)
                                + (kbiBranchNum > 0 ? kbiScoreBranch * 0.4f / kbiBranchNum : 0f) ;
            voEntity.setKbiScore((long)kbiScore);
            // 计算最终的效能分数
            Long finalKbiScore = (long) Math.round( (int)( ( 1 + (voEntity.getKbiScore() * 0.9f + voEntity.getExtraFinalScore() - 75f ) *0.6f / 75f) * 100 ) * voEntity.getKbiStandScore() / 100f );
            voEntity.setKbiFinalScore(finalKbiScore);
            // 合计项
            rowIndex += 1;
            HSSFRow fRow = sheet.createRow(rowIndex++);
            fRow.setHeight((short) 400);
            hcell = fRow.createCell(0);
            hcell.setCellValue(new HSSFRichTextString("合计项"));
            hcell.setCellStyle(tstyle);
            fRow = sheet.createRow(rowIndex++);
            HSSFCell cell = fRow.createCell(0);
            cell.setCellValue(new HSSFRichTextString("效能评分"));
            cell.setCellStyle(tstyle);
            cell = fRow.createCell(1);
            cell.setCellValue(new HSSFRichTextString("加减得分"));
            cell.setCellStyle(tstyle);
            cell = fRow.createCell(2);
            cell.setCellValue(new HSSFRichTextString("效能基准分"));
            cell.setCellStyle(tstyle);
            cell = fRow.createCell(3);
            cell.setCellValue(new HSSFRichTextString("最终效能得分"));
            cell.setCellStyle(tstyle);
            fRow = sheet.createRow(rowIndex++);
            cell = fRow.createCell(0);
            cell.setCellValue(new HSSFRichTextString(voEntity.getKbiScore().toString()));
            cell = fRow.createCell(1);
            cell.setCellValue(new HSSFRichTextString(voEntity.getExtraFinalScore().toString()));
            cell = fRow.createCell(2);
            cell.setCellValue(new HSSFRichTextString(voEntity.getKbiStandScore().toString()));
            cell = fRow.createCell(3);
            cell.setCellValue(new HSSFRichTextString(voEntity.getKbiFinalScore().toString()));
        }


        return workbook;

    }

    // 定义分数项的类
    public class ScoreItem {
        /**
         * 分数
         */
        private float score;
        /**
         * 是否领导
         */
        private boolean isGuider;
        /**
         *是否同部门
         */
        private boolean isSameBranch;

        public void setScore(float score) {this.score = score;}

        public float getScore() {return this.score;}

        public void setIsGuider(boolean isGuider) {this.isGuider = isGuider;}

        public boolean getIsGuider() {return this.isGuider;}

        public void setIsSameBranch(boolean isSameBranch) {this.isSameBranch = isSameBranch;}

        public boolean getIsSameBranch() {return this.isSameBranch;}
    }

}
