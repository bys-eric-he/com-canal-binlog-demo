package com.canal.application;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.*;

import java.util.List;

/**
 * 监听类
 */
@CanalEventListener
public class CanalServerListener {

    @InsertListenPoint
    public void onInsertEvent(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.out.println(String.format("================接收到 binlog 操作类型 [ %s ]", eventType));
        System.out.println("-----------------InsertListenPoint监听------------------");
        System.out.println("-----------------新增的数据------------------");
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            System.out.println("列-  >" + column.getName() + " 值->" + column.getValue() + "    update=" + column.getUpdated());
        }
    }

    @UpdateListenPoint
    public void onUpdateEvent(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.out.println(String.format("================接收到 binlog 操作类型 [ %s ]", eventType));
        System.out.println("-----------------UpdateListenPoint监听------------------");
        //获取修改前数据
        System.out.println("-----------------修改前数据------------------");
        for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
            System.out.println("列->" + column.getName() + " 值->" + column.getValue() + "    update=" + column.getUpdated());
        }
        //获取修改后数据
        System.out.println("-----------------修改后数据------------------");
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            System.out.println("列->" + column.getName() + " 值->" + column.getValue() + "    update=" + column.getUpdated());
        }
    }

    @DeleteListenPoint
    public void onDeleteEvent3(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.out.println(String.format("================接收到 binlog 操作类型 [ %s ]", eventType));
        System.out.println("-----------------DeleteListenPoint监听------------------");
        for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
            System.out.println("列->" + column.getName() + " 值->" + column.getValue() + "    update=" + column.getUpdated());
        }
    }

    /**
     * @param eventType
     * @param rowData
     */
    @ListenPoint(destination = "example", schema = "springboot-jdbc", table = {"sys_user", "sys_department"}, eventType = CanalEntry.EventType.UPDATE)
    public void onEvent4(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.out.println(String.format("================接收到 binlog 操作类型 [ %s ]", eventType));
        System.out.println("-----------------ListenPoint监听------------------");
        //判断操作类型
        if (eventType == CanalEntry.EventType.DELETE) {
            System.out.println("-----------------删除前数据------------------");
            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
            for (CanalEntry.Column column : beforeColumnsList) {
                System.out.println("列->" + column.getName() + " 值->" + column.getValue() + "    update=" + column.getUpdated());
            }

        } else if (eventType == CanalEntry.EventType.UPDATE) {
            //获取修改前数据
            System.out.println("-----------------修改前数据------------------");
            for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
                System.out.println("列->" + column.getName() + " 值->" + column.getValue() + "    update=" + column.getUpdated());
            }
            //获取修改后数据
            System.out.println("-----------------修改后数据------------------");
            for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
                System.out.println("列->" + column.getName() + " 值->" + column.getValue() + "    update=" + column.getUpdated());
            }
        } else if (eventType == CanalEntry.EventType.INSERT) {
            System.out.println("-----------------新增的数据------------------");
            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
            for (CanalEntry.Column column : afterColumnsList) {
                System.out.println("列->" + column.getName() + " 值->" + column.getValue() + "    update=" + column.getUpdated());
            }
        }
    }
}
