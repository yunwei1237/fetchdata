package com.tcf.data.util;

import java.util.List;

/**
 * 执行一项特定的任务
 * @author Archer Tan
 */
public class AntThread extends Thread {
    private Executable exe;
    private List<Object> datas;
    /**
     * 是否开启重复模式，直到运行获得结果或者运行了Integer.MAX_VALUE次之后才进行下一个任务
     */
    private boolean untilSuccess = false;
    public AntThread(Executable exe,List<Object> datas) {
        this.exe = exe;
        this.datas = datas;
    }
    public AntThread(Executable exe,List<Object> datas,boolean untilSuccess) {
        this.exe = exe;
        this.datas = datas;
        this.untilSuccess = untilSuccess;
    }
    public AntThread(String name, Executable exe) {
        super(name);
        this.exe = exe;
    }

    public void setExe(Executable exe) {
        this.exe = exe;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    @Override
    public void run() {
        if(exe == null)
            throw new IllegalArgumentException("exe is null");
        for(Object data : datas){
            try {
                exe.setData(data);//添加数据
                if(untilSuccess) {//如果设置重复模式，就会运行很多次，直到获得结果为止
                    int times = 0;
                    boolean result = false;
                    do {
                        result = exe.execute();//执行并获得结果
                        times++;
                    } while (times <= Integer.MAX_VALUE && !result);
                }else{//如果没有设置，就执行一次，无论任务是否成功
                    exe.execute();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
