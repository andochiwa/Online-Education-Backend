package com.github.center.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.center.entity.UcenterMember;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author HAN
 * @since 2021-04-15
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    int countRegister(String date);
}
