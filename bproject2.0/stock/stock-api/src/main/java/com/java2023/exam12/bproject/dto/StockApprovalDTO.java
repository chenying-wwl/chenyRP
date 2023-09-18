package com.java2023.exam12.bproject.dto;

import com.java2023.exam12.bproject.entity.TbApprovalFlow;
import com.java2023.exam12.bproject.entity.TbStockOutIn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockApprovalDTO extends TbApprovalFlow {
    TbStockOutIn tbStockOutIn;
}
