package com.gmc.gmccoin.api.mining.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class MiningDTO implements Serializable {
    private String email;
    private float amount; //총 채굴량
    private float miningAmount; //1회 채굴량
    private LocalDateTime miningStartDt; //채굴 시작시간
    private String isComplete; //채굴완료 여부
    private float miningValue; //채굴진행값
    private long todayCount; //오늘 채굴 횟수

    @Builder
    public MiningDTO(String email, float amount, float miningAmount,LocalDateTime miningStartDt, String isComplete, float miningValue, long todayCount) {
        this.email = email;
        this.amount = amount;
        this.miningAmount = miningAmount;
        this.miningStartDt = miningStartDt;
        this.isComplete = isComplete;
        this.miningValue = miningValue;
        this.todayCount = todayCount;
    }
}
