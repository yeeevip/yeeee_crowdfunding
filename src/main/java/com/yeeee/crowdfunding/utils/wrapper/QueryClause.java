package com.yeeee.crowdfunding.utils.wrapper;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author https://www.yeee.vip丶一页 (quhailong1995@gmail.com)
 */
@Data
public class QueryClause implements Serializable {
    private static final long serialVersionUID = -6014076206210293128L;
    
    private List<WhereClause> w;
    private List<OrderClause> o;
    private PageClause p;
    
}
