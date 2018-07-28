package com.tcf.data.util;

/**
 * 要执行的任务
 * @author Archer Tan
 */
public interface Executable {
    /**
     * 执行任务
     * @return 执行结果，true:成功，false：失败
     */
    boolean execute();

    /**
     * 设置执行时需要的数据
     * @param obj
     */
    void setData(Object obj);
}
