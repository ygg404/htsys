package io.renren.modules.project.controller;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.FileUtil;
import io.renren.common.utils.MapUtils;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.project.entity.ProjectContractEntity;
import io.renren.modules.project.entity.ProjectEntity;
import io.renren.modules.project.service.ProjectContractService;
import io.renren.modules.project.service.ProjectService;
import io.renren.modules.sys.service.SysUserService;
import io.renren.modules.sys.vo.UserVoEntity;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 合同表
 *
 * @author ygg
 * @date 2019-10-30 15:40:10
 */
@RestController
@RequestMapping("project/contract")
public class ContractController {

    @Value("${spring.file.upContractFolder}")
    private String upContractFolder;

    @Autowired
    private ProjectContractService projectContractService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
//    @SysLog("查看合同")
    @RequestMapping("/list")
    @RequiresPermissions("project:contract:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = projectContractService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 获取业务负责人列表
     * @return
     */
    @RequestMapping("/getBusinessList")
    public R getBusinessList(){
        // 角色Id 为12是 业务员
        List<UserVoEntity> businessList = sysUserService.getUserList(12L);
        return R.ok().put("list", businessList);
    }

    /**
     * 获取最大的合同编号
     */
    @RequestMapping("/getMaxContractNo")
    public R getMaxContractNo() {
        String contractNo = projectContractService.getMaxContractNo();
        return R.ok().put("contractNo", contractNo);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        ProjectContractEntity projectContract = projectContractService.selectById(id);

        return R.ok().put("projectContract", projectContract);
    }

    /**
     * 保存
     */
    @SysLog("保存合同")
    @RequestMapping("/save")
    @RequiresPermissions("project:contract:save")
    public R save(@RequestBody ProjectContractEntity projectContract) {
        //保存合同时候，判断是否有存在的合同编号
        ProjectContractEntity temp = projectContractService.getByContractNo(projectContract.getcontractNo());
        if(temp != null){
            return R.error("合同编号重复,请刷新后重试！");
        }

        projectContractService.save(projectContract);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改合同")
    @RequestMapping("/update")
    @RequiresPermissions("project:contract:update")
    public R update(@RequestBody ProjectContractEntity projectContract) {
        projectContractService.updateById(projectContract);
        //获取 该合同下所有项目信息
        List<ProjectEntity> projectList = projectService.queryList(new MapUtils().put("contractNo",projectContract.getcontractNo()));
        for( ProjectEntity project : projectList){
            project.setprojectType(projectContract.getprojectType());
            project.setprojectAuthorize(projectContract.getcontractAuthorize());
            project.setprojectBusiness(projectContract.getcontractBusiness());
            project.setprojectNote(projectContract.getcontractNote());
        }
        if(projectList.size() > 0) {
            projectService.insertOrUpdateBatch(projectList);
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除合同")
    @RequestMapping("/delete")
    @RequiresPermissions("project:contract:delete")
    public R delete(@RequestBody Long[] ids) {
        //删除合同时 所有项目也一起删除
        for(Long id : ids){
            ProjectContractEntity contractEntity = projectContractService.selectById(id);
            // 获取合同号
            String contractNo = contractEntity.getcontractNo();
            if(Strings.isNotBlank(contractNo)){
                Map<String, Object> params = new HashMap<>();
                params.put("contractNo" , contractNo);
                for(ProjectEntity project : projectService.queryList(params)){
                    // 获取项目编号 将项目放入回收站
                    String projectNo = project.getprojectNo();
                    if(Strings.isNotBlank(projectNo)){
                        project.setpStage("2");
                    }
                    projectService.updateById(project);
                }
            }
        }

        projectContractService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @SysLog("上传合同文件")
    @RequestMapping("/upContractFile")
    public R uploadFile(@PathVariable MultipartFile file, @RequestParam("contractNo") String contractNo) {
        String fileName = "";
        try {
            String filePath = FileUtil.setFilePath(upContractFolder, file.getOriginalFilename() , true);
            File dest = new File(filePath);
            fileName = dest.getName();
            file.transferTo(dest);
        }catch (Exception ex){
            return R.error(ex.getMessage());
        }
        return R.ok().put("fileName", fileName);
    }

    /**
     * 下载合同文件
     * @param contractNo
     * @param response
     * @return
     */
    @SysLog("下载合同文件")
    @RequestMapping("/download")
    public R downloadFile(@RequestParam("contractNo") String contractNo, HttpServletResponse response) {
        ProjectContractEntity pcEntity = projectContractService.selectOne(
                new EntityWrapper<ProjectContractEntity>().eq("contract_no", contractNo)
        );
        try{
            FileUtil.downloadFiles(response , upContractFolder + pcEntity.getfilename());
        }catch (Exception ex){
            return R.error("下载失败");
        }
        return R.ok("下载成功");
    }
}
