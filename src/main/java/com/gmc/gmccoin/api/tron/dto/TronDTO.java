package com.gmc.gmccoin.api.tron.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class TronDTO {
    private String email;
    private String privateKey;
    private String address;
    private String hexAddress;

    @Builder
    public TronDTO(String email, String privateKey, String address, String hexAddress) {
        this.email = email;
        this.privateKey = privateKey;
        this.address = address;
        this.hexAddress = hexAddress;
    }
}
