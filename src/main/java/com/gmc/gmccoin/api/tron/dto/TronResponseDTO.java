package com.gmc.gmccoin.api.tron.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class TronResponseDTO {
    private String email;
    private String address;
    private String tronBalance;
    private String gmcBalance;
    private String tronDollar;
    private String gmcDollar;
    private String tronKrw;
    private String gmcKrw;

    @Builder
    public TronResponseDTO(String email, String address, String tronBalance, String gmcBalance, String tronDollar, String gmcDollar, String tronKrw, String gmcKrw) {
        this.email = email;
        this.address = address;
        this.tronBalance = tronBalance;
        this.gmcBalance = gmcBalance;
        this.tronDollar = tronDollar;
        this.gmcDollar = gmcDollar;
        this.tronKrw = tronKrw;
        this.gmcKrw = gmcKrw;
    }
}
