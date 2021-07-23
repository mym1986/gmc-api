package com.gmc.gmccoin.common.model.commons;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Code
 */
@Getter
@Setter
@Entity
@Table(name="tb_code")
public class Code extends Auditing{

    
}