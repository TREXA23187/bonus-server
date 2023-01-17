package cxy.project.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserBonusNum implements Serializable {

    private Long userId ;
    private Integer bonusNum;
}
