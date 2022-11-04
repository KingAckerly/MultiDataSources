package com.example.mds.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("db")
public class DBEntity {
    private Integer id;
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "DB0Entity{" +
                "id=" + id +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
