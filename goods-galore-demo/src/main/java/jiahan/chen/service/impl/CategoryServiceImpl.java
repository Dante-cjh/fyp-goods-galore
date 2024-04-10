package jiahan.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jiahan.chen.entity.Category;
import jiahan.chen.mapper.CategoryMapper;
import jiahan.chen.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategory() {
        QueryWrapper<Category> catagoryWrapper = new QueryWrapper<>();
        List<Category> categoryList = categoryMapper.selectList(catagoryWrapper);
        return categoryList;
    }
}
