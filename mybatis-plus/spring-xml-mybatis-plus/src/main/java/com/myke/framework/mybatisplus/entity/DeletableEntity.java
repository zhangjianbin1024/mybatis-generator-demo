package com.myke.framework.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * extends BaseEntity 扩展 delete 操作
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class DeletableEntity extends BaseEntity {
    /**
     * 是否删除
     */
    @TableLogic
    private Integer deleted;

}
