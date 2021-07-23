package com.gmc.gmccoin.api.tron.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class SendDTO {
    private String email;
    private String toAddress;
    private float amount;
    private boolean isSend;

    @Builder
    public SendDTO(String email, String toAddress, float amount, boolean isSend) {
        this.email = email;
        this.toAddress = toAddress;
        this.amount = amount;
        this.isSend = isSend;
    }
}
