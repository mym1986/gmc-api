package com.gmc.gmccoin.api.mining.service;

import com.gmc.gmccoin.api.mining.dto.MiningDTO;
import com.gmc.gmccoin.common.model.mining.MiningHistory;

import java.util.List;

public interface MiningService {
    MiningDTO updateMiningStatus(MiningDTO miningDTO);
    MiningDTO getMiningStatus(MiningDTO miningDTO);
    List<MiningDTO> getMiningTop100();
    List<MiningHistory> getMiningHistory(MiningDTO miningDTO);
}
