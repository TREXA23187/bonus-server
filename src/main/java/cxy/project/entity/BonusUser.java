package cxy.project.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BonusUser implements Serializable {
    private Long bonusId ;
    private Long userId;
}
