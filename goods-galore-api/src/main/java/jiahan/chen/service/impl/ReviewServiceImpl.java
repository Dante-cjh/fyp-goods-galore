package jiahan.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jiahan.chen.constant.GoodsConstants;
import jiahan.chen.dto.req.ReviewReqDTO;
import jiahan.chen.dto.resp.ReviewSupermarketRespDTO;
import jiahan.chen.dto.resp.ReviewSupplierRespDTO;
import jiahan.chen.entity.Review;
import jiahan.chen.entity.Supermarket;
import jiahan.chen.entity.Supplier;
import jiahan.chen.mapper.ReviewMapper;
import jiahan.chen.service.IReviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jiahan.chen.service.ISupermarketService;
import jiahan.chen.service.ISupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
@Service
@Slf4j
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements IReviewService {
    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private ISupermarketService supermarketService;

    @Override
    @Transactional
    public boolean addReview(ReviewReqDTO reviewReqDTO, Integer supermarketId) {
        // 检查参数
        if (reviewReqDTO.getRating() == null || reviewReqDTO.getComment() == null || reviewReqDTO.getSupplierId() == null) {
            log.error("评分,评论内容或供应商ID为空");
            return false;
        }

        Integer rating = reviewReqDTO.getRating();
        String comment = reviewReqDTO.getComment();
        Integer supplierId = reviewReqDTO.getSupplierId();

        if (rating < 0 || rating > 5) {
            log.error("评分不在范围内");
            return false;
        }

        if (supplierService.getSupplierBySupplierId(supplierId) == null) {
            log.error("供应商不存在");
            return false;
        }

        Review review = new Review();
        review.setRating(rating);
        review.setComment(comment);
        review.setSupplierId(supplierId);
        review.setSupermarketId(supermarketId);

        return reviewMapper.insert(review) > GoodsConstants.DB_UPDATE_RESULT_BIGZERO;
    }

    @Override
    public List<ReviewSupplierRespDTO> getReviewsBySupplierId(Integer supplierId) {
        // 验证参数
        Supplier supplier = supplierService.getSupplierBySupplierId(supplierId);
        if (supplier == null) {
            log.error("供应商不存在");
            return null;
        }
        List<Review> reviews = reviewMapper.selectList(new QueryWrapper<Review>().eq("supplier_ID", supplierId));
        List<ReviewSupplierRespDTO> reviewRespDTOs = new ArrayList<>();

        for (Review review : reviews) {
            ReviewSupplierRespDTO dto = new ReviewSupplierRespDTO();
            BeanUtils.copyProperties(review, dto);

            // 假设你有方法根据ID获取超市名称
            Supermarket supermarket = supermarketService.getSupermarketBySupermarketId(review.getSupermarketId());
            dto.setSupermarketName(supermarket.getName());

            reviewRespDTOs.add(dto);
        }
        return reviewRespDTOs;
    }

    @Override
    public List<ReviewSupermarketRespDTO> getReviewsBySupermarketId(Integer supermarketId) {
        // 验证参数
        Supermarket supermarket = supermarketService.getSupermarketBySupermarketId(supermarketId);
        if (supermarket == null) {
            log.error("超市不存在");
            return null;
        }
        List<Review> reviews = reviewMapper.selectList(new QueryWrapper<Review>().eq("supermarket_ID", supermarketId));
        List<ReviewSupermarketRespDTO> reviewRespDTOs = new ArrayList<>();

        for (Review review : reviews) {
            ReviewSupermarketRespDTO dto = new ReviewSupermarketRespDTO();
            BeanUtils.copyProperties(review, dto);

            // 假设你有方法根据ID获取供应商名称
            Supplier supplier = supplierService.getSupplierBySupplierId(review.getSupplierId());
            dto.setSupplierName(supplier.getName());

            reviewRespDTOs.add(dto);
        }
        return reviewRespDTOs;
    }

    @Override
    @Transactional
    public boolean deleteReview(Integer reviewId) {
        // 验证参数
        Review review = reviewMapper.selectById(reviewId);
        if (review == null) {
            log.error("评价不存在");
            return false;
        }

        return reviewMapper.deleteById(reviewId) > GoodsConstants.DB_DELETE_RESULT_BIGZERO;
    }
}
