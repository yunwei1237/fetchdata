package com.tcf.data.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 分配任务
 * @author Archer Tan
 */
public class TaskDistributor {
    //任务数量
    private int size = 10;
    //任务执行对象
    private Executable exe;
    //任务执行需要的数据
    private List<Object> datas;

    public TaskDistributor(Executable exe,List<Object> datas) {
        this.exe = exe;
        this.datas = datas;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void distribute(){
        if(exe == null)
            throw new IllegalArgumentException("exe is null");
        if(size <=0){
            throw new IllegalArgumentException("size is negative or zero");
        }
        //拆分数据，尽量平均分给每一个线程
        List<List<Object>> dataList = splitData(datas,size);
        //启动全部的线程
        for(int i = 0;i<size;i++){
            new AntThread(exe,dataList.get(i)).start();
        }
    }

    /**
     * 将数据分成指定的份数
     * @param datas
     * @param parts
     * @return
     */
    public List<List<Object>> splitData(List<Object> datas,int parts){
        List<List<Object>> dataList = new ArrayList<>();
        int size = datas.size()/parts;
        //为每一个部分创建集合对象
        for(int i = 0;i<parts;i++){
            dataList.add(new ArrayList<Object>());
        }
        if(datas.size()%parts != 0){
            dataList.add(new ArrayList<Object>());
        }
        //将数据填充到每一个部分
        int p = 0;
        for(int i = 1;i<=datas.size();i++){
            if(i%(size+1) == 0){
                p++;
            }
            dataList.get(p).add(datas.get(i-1));
        }
        return dataList;
    }
}
