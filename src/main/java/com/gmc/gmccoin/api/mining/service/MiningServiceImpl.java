package com.gmc.gmccoin.api.mining.service;


import com.gmc.gmccoin.api.mining.dto.MiningDTO;
import com.gmc.gmccoin.api.mining.repository.MiningRepository;
import com.gmc.gmccoin.api.mining.repository.MininghistoryRepository;
import com.gmc.gmccoin.common.model.mining.Mining;
import com.gmc.gmccoin.common.model.mining.MiningHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MiningServiceImpl
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MiningServiceImpl implements MiningService{
    private final MiningRepository miningRepository;
    private final MininghistoryRepository mininghistoryRepository;
    private final ModelMapper modelMapper;
    @Override
    public MiningDTO updateMiningStatus(MiningDTO miningDTO) {
        MiningHistory miningHistory = this.mininghistoryRepository.findTop1ByEmailAndIsCompleteOrderByIdDesc(miningDTO.getEmail(), "N");
        Mining mining = this.miningRepository.findByEmail(miningDTO.getEmail());
        long todayCount = this.mininghistoryRepository.selectCountByEmailAndIsComplete(miningDTO.getEmail(), "Y");
        if(todayCount >= 10) {
            miningDTO.setAmount(mining.getAmount());
            miningDTO.setTodayCount(todayCount);
            return miningDTO;
        }
        if(miningHistory == null) {
            miningDTO.setIsComplete("N");
            miningDTO.setMiningStartDt(LocalDateTime.now());
            miningDTO = this.modelMapper.map(this.mininghistoryRepository.save(this.modelMapper.map(miningDTO, MiningHistory.class)), MiningDTO.class);
            miningDTO.setAmount(mining.getAmount());
            miningDTO.setTodayCount(todayCount);
            return miningDTO;
        } else {
            Long time = ChronoUnit.SECONDS.between(miningHistory.getMiningStartDt(), LocalDateTime.now());
            if(time >= 600) {
                miningHistory.setIsComplete("Y");
                this.mininghistoryRepository.save(miningHistory);
                this.mininghistoryRepository.delete(miningHistory.getId());
                mining.setAmount(new BigDecimal(mining.getAmount()).add(new BigDecimal(miningHistory.getMiningAmount())).floatValue());
                this.miningRepository.save(mining);
                miningDTO.setIsComplete("N");
                miningDTO.setMiningStartDt(LocalDateTime.now());
                miningDTO.setAmount(mining.getAmount());
                miningDTO = this.modelMapper.map(this.mininghistoryRepository.save(this.modelMapper.map(miningDTO, MiningHistory.class)), MiningDTO.class);
                miningDTO.setTodayCount(todayCount + 1);
                return miningDTO;
            } else {
                MiningDTO res = this.modelMapper.map(miningHistory, MiningDTO.class);
                float miningValue = new BigDecimal(time).divide(new BigDecimal(600), 2, BigDecimal.ROUND_HALF_UP).floatValue();
                res.setMiningValue(miningValue);
                res.setAmount(mining.getAmount());
                res.setTodayCount(todayCount);
                return res;
            }
        }
    }

    @Override
    public MiningDTO getMiningStatus(MiningDTO miningDTO) {
        MiningHistory miningHistory = this.mininghistoryRepository.findTop1ByEmailAndIsCompleteOrderByIdDesc(miningDTO.getEmail(), "N");
        Mining mining = this.miningRepository.findByEmail(miningDTO.getEmail());
        Long todayCount = this.mininghistoryRepository.selectCountByEmailAndIsComplete(miningDTO.getEmail(), "Y");
        if(todayCount >= 10) {
            miningDTO.setAmount(mining.getAmount());
            miningDTO.setTodayCount(todayCount);
            return miningDTO;
        }
        if(miningHistory == null) {
            miningDTO.setAmount(mining.getAmount());
            miningDTO.setTodayCount(todayCount);
            return miningDTO;
        } else {
            Long time = ChronoUnit.SECONDS.between(miningHistory.getMiningStartDt(), LocalDateTime.now());
            if(time >= 600) {
                miningHistory.setIsComplete("Y");
                this.mininghistoryRepository.save(miningHistory);
                this.mininghistoryRepository.delete(miningHistory.getId());
                mining.setAmount(new BigDecimal(mining.getAmount()).add(new BigDecimal(miningHistory.getMiningAmount())).floatValue());
                miningDTO.setAmount(mining.getAmount());
                this.miningRepository.save(mining);
                miningDTO.setTodayCount(todayCount + 1);
                return miningDTO;
            } else {
                MiningDTO res = this.modelMapper.map(miningHistory, MiningDTO.class);
                float miningValue = new BigDecimal(time).divide(new BigDecimal(600), 2, BigDecimal.ROUND_HALF_UP).floatValue();
                res.setMiningValue(miningValue);
                res.setAmount(mining.getAmount());
                res.setTodayCount(todayCount);
                return res;
            }
        }
    }

    @Override
    public List<MiningDTO> getMiningTop100() {
        List<Mining> miningList = this.miningRepository.findTop100ByOrderByAmountDesc();
        return miningList.stream().map(mining -> this.modelMapper.map(mining, MiningDTO.class)).collect(Collectors.toList());
    }


}
