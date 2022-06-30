package com.fifth.auction.mapper;

import com.fifth.auction.emtity.AdminLog;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLogMapper {

    /**
     * 插入数据
     * @param adminLog 操作纪录
     * @return 影响的行数
     */
    Integer insert(AdminLog adminLog);

}
