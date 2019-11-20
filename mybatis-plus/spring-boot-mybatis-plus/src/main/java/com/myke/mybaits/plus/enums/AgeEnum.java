package com.myke.mybaits.plus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum AgeEnum  {
    ONE(1, "一岁"),
    TWO(2, "二岁"),
    THREE(3, "三岁");

    AgeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @EnumValue//标记数据库存的值是value
    private int value;
    private String desc;

}