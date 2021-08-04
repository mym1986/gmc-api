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
    private float todayAmount;//오늘 채굴량
    private float recommendAmount;//추천 채굴량

    @Builder
    public MiningDTO(String email, float amount, float miningAmount,LocalDateTime miningStartDt,
                     String isComplete, float miningValue, long todayCount, float todayAmount, float recommendAmount) {
        this.email = email;
        this.amount = amount;
        this.miningAmount = miningAmount;
        this.miningStartDt = miningStartDt;
        this.isComplete = isComplete;
        this.miningValue = miningValue;
        this.todayCount = todayCount;
        this.todayAmount = todayAmount;
        this.recommendAmount = recommendAmount;
    }
}
