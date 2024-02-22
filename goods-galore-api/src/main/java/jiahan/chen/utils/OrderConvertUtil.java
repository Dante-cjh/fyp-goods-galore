package jiahan.chen.utils;

import jiahan.chen.dto.resp.OrderProductRespDTO;
import jiahan.chen.dto.resp.OrderRespDTO;
import jiahan.chen.entity.TOrder;
import jiahan.chen.entity.OrderProduct;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderConvertUtil {

    public static OrderRespDTO convertToOrderRespDTO(TOrder TOrder, List<OrderProduct> orderProducts) {
        OrderRespDTO dto = new OrderRespDTO();
        BeanUtils.copyProperties(TOrder, dto);

        List<OrderProductRespDTO> productDTOs = orderProducts.stream().map(product -> {
            OrderProductRespDTO productDTO = new OrderProductRespDTO();
            BeanUtils.copyProperties(product, productDTO);
            return productDTO;
        }).collect(Collectors.toList());

        dto.setProducts(productDTOs);
        return dto;
    }
}
