package com.congmai.zhgj.core.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName ErrorResult
 * @Description 用于响应错误的请求的对象
 * @author tanzb
 * @Date 2017年7月26日 下午2:09:35
 * @version 1.0.0
 */
public class ErrorResult extends Result {
    private static final long serialVersionUID = 8567221653356186674L;

    /**
     * 封装多个 错误信息
     */
    private Map<String, Object> errors = new HashMap<String, Object>();

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }

    public ErrorResult() {

    }
}
