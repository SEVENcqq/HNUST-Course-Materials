package com.hnust.xy.dto;

import com.hnust.xy.entity.OrderDetail;
import com.hnust.xy.entity.Orders;
import lombok.Data;
import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
