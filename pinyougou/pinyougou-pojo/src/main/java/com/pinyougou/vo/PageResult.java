package com.pinyougou.vo;

import javax.swing.*;
import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {
    private Long total; //总记录数
    private List<?> rows; //记录列表

    public PageResult(Long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}

