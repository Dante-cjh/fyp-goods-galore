package jiahan.chen.service;

import jiahan.chen.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import jiahan.chen.entity.RoleMenu;
import jiahan.chen.entity.TResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
public interface IRoleService extends IService<Role> {
    /**
     * 添加角色
     */
    int create(Role role);

    /**
     * 修改角色信息
     */
    int update(Long id, Role role);

    /**
     * 批量删除角色
     */
    int delete(List<Long> ids);

    /**
     * 获取所有角色列表
     */
    List<Role> list();

    /**
     * 分页获取角色列表
     */
    List<Role> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根据管理员ID获取对应菜单
     */
    List<RoleMenu> getMenuList(Long adminId);

    /**
     * 获取角色相关菜单
     */
    List<RoleMenu> listMenu(Long roleId);

    /**
     * 获取角色相关资源
     */
    List<TResource> listResource(Long roleId);

    /**
     * 给角色分配菜单
     */
    @Transactional
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);
}
