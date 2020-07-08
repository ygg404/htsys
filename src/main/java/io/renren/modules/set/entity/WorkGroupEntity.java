package io.renren.modules.set.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 工作组表
 * 
 * @author ygg
 * @date 2020-06-02 09:38:14
 */
@TableName("work_group")
public class WorkGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 工作组Id
	 */
	@TableId
	private Long id;
	/**
	 * 工作组名称
	 */
	private String name;
	/**
	 * 序列号
	 */
	private Long orderNum;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 队长名称
	 */
	private String headMan;
	/**
	 * 副队长名称
	 */
	private String deputyLeader;
	/**
	 * 队长Id
	 */
	private Long headId;
	/**
	 * 副队长Id
	 */
	private Long deputyId;
	/**
	 * 工作组父Id
	 */
	private Long pId;

	/**
	* 获取：工作组Id
	*/
	public Long getId (){return this.id;};
	/**
	 * 设置：工作组Id
	 */
	public void setId (Long id){this.id = id;};
	/**
	* 获取：工作组名称
	*/
	public String getName (){return this.name;};
	/**
	 * 设置：工作组名称
	 */
	public void setName (String name){this.name = name;};
	/**
	* 获取：序列号
	*/
	public Long getOrderNum (){return this.orderNum;};
	/**
	 * 设置：序列号
	 */
	public void setOrderNum (Long orderNum){this.orderNum = orderNum;};
	/**
	* 获取：
	*/
	public Date getCreateTime (){return this.createTime;};
	/**
	 * 设置：
	 */
	public void setCreateTime (Date createTime){this.createTime = createTime;};
	/**
	* 获取：队长名称
	*/
	public String getHeadMan (){return this.headMan;};
	/**
	 * 设置：队长名称
	 */
	public void setHeadMan (String headMan){this.headMan = headMan;};
	/**
	* 获取：副队长名称
	*/
	public String getDeputyLeader (){return this.deputyLeader;};
	/**
	 * 设置：副队长名称
	 */
	public void setDeputyLeader (String deputyLeader){this.deputyLeader = deputyLeader;};
	/**
	* 获取：队长Id
	*/
	public Long getHeadId (){return this.headId;};
	/**
	 * 设置：队长Id
	 */
	public void setHeadId (Long headId){this.headId = headId;};
	/**
	* 获取：副队长Id
	*/
	public Long getDeputyId (){return this.deputyId;};
	/**
	 * 设置：副队长Id
	 */
	public void setDeputyId (Long deputyId){this.deputyId = deputyId;};
	/**
	* 获取：工作组父Id
	*/
	public Long getPId (){return this.pId;};
	/**
	 * 设置：工作组父Id
	 */
	public void setPId (Long pId){this.pId = pId;};
}
