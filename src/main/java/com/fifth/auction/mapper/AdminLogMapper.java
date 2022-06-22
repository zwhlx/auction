package com.fifth.auction.mapper;

import com.fifth.auction.emtity.AdminLog;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLogMapper {

    Integer insert(AdminLog adminLog);

}
