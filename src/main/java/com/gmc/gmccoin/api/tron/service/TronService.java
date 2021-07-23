package com.gmc.gmccoin.api.tron.service;

import com.gmc.gmccoin.api.tron.dto.SendDTO;
import com.gmc.gmccoin.api.tron.dto.TronDTO;
import com.gmc.gmccoin.api.tron.dto.TronResponseDTO;

public interface TronService {

    TronResponseDTO getTronAddress(TronDTO tronDTO);

    SendDTO sendToken(SendDTO sendDTO);

    TronResponseDTO getBalance(TronDTO tronDTO);

    SendDTO sendTransaction(SendDTO sendDTO);
}
