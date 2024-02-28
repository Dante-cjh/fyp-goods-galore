package jiahan.chen.service;

import io.swagger.models.auth.In;
import jiahan.chen.dto.req.ReviewReqDTO;
import jiahan.chen.dto.resp.ReviewSupermarketRespDTO;
import jiahan.chen.dto.resp.ReviewSupplierRespDTO;
import jiahan.chen.entity.Review;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
public interface IReviewService extends IService<Review> {
    boolean addReview(ReviewReqDTO reviewReqDTO, Integer supermarketId);

    List<ReviewSupplierRespDTO> getReviewsBySupplierId(Integer supplierId);

    List<ReviewSupermarketRespDTO> getReviewsBySupermarketId(Integer supermarketId);
}
