package jiahan.chen.service;

import jiahan.chen.entity.Category;
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
public interface ICategoryService extends IService<Category> {
    List<Category> getAllCategory();
}
