package com.gmc.gmccoin.api.tron.repository;

import com.gmc.gmccoin.common.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TronRepo extends JpaRepository<Address, Long> {

    Address findByEmail(@Param("email")String email);
    Address findByAddress(@Param("address")String address);
}
