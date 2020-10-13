package io.renren.modules.ren.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.modules.perf.service.PerfVoService;
import io.renren.modules.perf.vo.PerfVoEntity;
import io.renren.modules.ren.vo.RenKbiAuditVoEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.ren.entity.RenKbiAuditEntity;
import io.renren.modules.ren.service.RenKbiAuditService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 效能分审定表
 *
 * @author ygg
 * @date 2020-07-03 16:11:22
 */
@RestController
@RequestMapping("ren/kbiaudit")
public class RenKbiAuditController {
    @Autowired
    private RenKbiAuditService renKbiAuditService;

    @Autowired
    private PerfVoService perfVoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        List<RenKbiAuditEntity> list = renKbiAuditService.queryList(params);

        return R.ok().put("list", list);
    }

    /**
     * 保存
     */
    @SysLog("提交效能审定")
    @RequestMapping("/save")
    public R save(@RequestBody RenKbiAuditVoEntity voEntity){
        if (voEntity.getYear() == null || voEntity.getMonth() == null) {
            return R.error("审定日期为空！请刷新重试！");
        }
        renKbiAuditService.delete(
                new EntityWrapper<RenKbiAuditEntity>().eq("year",voEntity.getYear())
                .and().eq("month" , voEntity.getMonth())
        );
        for(RenKbiAuditEntity entity : voEntity.getKbiAuditList()) {
            entity.setYear(voEntity.getYear());
            entity.setUpdown(voEntity.getMonth());
        }
        if(voEntity.getKbiAuditList().size() > 0)
		renKbiAuditService.insertBatch(voEntity.getKbiAuditList());

        return R.ok();
    }

    /**
     * Excel 导出
     */
    @SysLog("导出Excel")
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response, @RequestParam Map<String, Object> params){
        try {
            List<PerfVoEntity> list = perfVoService.queryList(params);

            HSSFWorkbook workbook = perfVoService.exportExcel(list);
            //准备将Excel的输出流通过response输出到页面下载
            response.setContentType("application/vnd.ms-excel");
            //设置导出Excel的名称
            response.setHeader("Content-disposition", "attachment;filename=" + "1" + ".xls");
            //刷新缓冲
            response.flushBuffer();
            //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
            workbook.write(response.getOutputStream());
            //关闭workbook
            workbook.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("set:renkbiaudit:delete")
    public R delete(@RequestBody Long[] ids){
		renKbiAuditService.deleteBatch(ids);

        return R.ok();
    }

}
