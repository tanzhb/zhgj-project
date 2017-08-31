package com.congmai.zhgj.web.activiti.processTask.taskCommand;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.TaskEntity;

/**
 * 
 * @ClassName DeleteActiveTaskCmd
 * @Description 删除未完成的任务
 * @author tanzb
 * @Date 2017年8月24日 上午9:38:22
 * @version 1.0.0
 */
public class DeleteActiveTaskCmd implements Command<java.lang.Void> {

	private TaskEntity _currentTaskEntity;
	private Boolean _cascade;
	private String _deleteReason;
	
	public DeleteActiveTaskCmd(TaskEntity currentTaskEntity, String deleteReason, Boolean cascade) {
		this._currentTaskEntity = currentTaskEntity;
		this._deleteReason = deleteReason;
		this._cascade = cascade;
	}
	
	@Override
	public Void execute(CommandContext commandContext) {
		Context.getCommandContext().getTaskEntityManager()
			.deleteTask(this._currentTaskEntity, this._deleteReason, this._cascade);
		return null;
	}

}
