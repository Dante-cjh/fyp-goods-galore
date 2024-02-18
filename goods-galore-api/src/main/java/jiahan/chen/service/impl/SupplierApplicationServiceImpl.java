package jiahan.chen.service.impl;

import jiahan.chen.constant.GoodsConstants;
import jiahan.chen.dto.req.SupplierApplyReqDTO;
import jiahan.chen.entity.Account;
import jiahan.chen.entity.Supplier;
import jiahan.chen.entity.SupplierApplication;
import jiahan.chen.mapper.AccountMapper;
import jiahan.chen.mapper.SupplierApplicationMapper;
import jiahan.chen.mapper.SupplierMapper;
import jiahan.chen.service.ISupplierApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jiahan.chen.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * <p>
 * 申请成为供应商 服务实现类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-17
 */
@Service
@Slf4j
public class SupplierApplicationServiceImpl extends ServiceImpl<SupplierApplicationMapper, SupplierApplication> implements ISupplierApplicationService {
    @Autowired
    private SupplierApplicationMapper supplierApplicationMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @Transactional
    public boolean submitApplication(Integer userId, SupplierApplyReqDTO supplierApplyReqDTO) {
        String proofPath = null;

        // 获得参数
        String name = supplierApplyReqDTO.getName();
        String address = supplierApplyReqDTO.getAddress();
        String phoneNumber = supplierApplyReqDTO.getPhoneNumber();
        String email = supplierApplyReqDTO.getEmail();
        String description = supplierApplyReqDTO.getDescription();
        MultipartFile file = supplierApplyReqDTO.getDocumentaryProof();
        if (file != null) {
            proofPath = FileUtils.upload(file);
        }

        // 提交申请
        SupplierApplication supplierApplication = new SupplierApplication();
        supplierApplication.setAccountId(userId);
        supplierApplication.setAddress(address);
        supplierApplication.setName(name);
        supplierApplication.setSubmissionDate(LocalDateTime.now());
        supplierApplication.setApplicationStatus(1); // 设置为pending
        supplierApplication.setPhoneNumber(phoneNumber);
        supplierApplication.setEmail(email);
        supplierApplication.setDescription(description);
        supplierApplication.setDocumentaryProof(proofPath);

        // 4. 添加数据
        return supplierApplicationMapper.insert(supplierApplication) > GoodsConstants.DB_INSERT_RESULT_BIGZERO;
    }

    @Override
    @Transactional
    public boolean reviewApplication(Integer applicationId, Integer status) {
        SupplierApplication application = getByApplicationId(applicationId);
        if(application == null){
            log.error("申请不存在");
            return false;
        }

        // 获得账户信息
        Account account = accountMapper.selectById(application.getAccountId());
        if(account == null){
            log.error("账户不存在");
            return false;
        }

        // 修改申请状态 (1:pending 2:accepted 3:rejected)
        application.setApplicationStatus(status);
        if(supplierApplicationMapper.updateById(application) < GoodsConstants.DB_UPDATE_RESULT_BIGZERO){
            log.error("修改申请状态失败");
            return false;
        };

        // 修改供应商状态
        if(status == 2){
            // 更改对应Account的role Role 1:超市 2:供应商 3:管理员 4:普通用户
            account.setRole(2);
            if(accountMapper.updateById(account) < GoodsConstants.DB_UPDATE_RESULT_BIGZERO){
                log.error("修改账号角色失败");
                return false;
            }

            // 添加到供应商数据库
            Supplier supplier = new Supplier();
            supplier.setName(application.getName());
            supplier.setAddress(application.getAddress());
            supplier.setPhoneNumber(application.getPhoneNumber());
            supplier.setEmail(application.getEmail());
            supplier.setDescription(application.getDescription());
            supplier.setAccountId(account.getAccountId());
            if(supplierMapper.insert(supplier) < GoodsConstants.DB_INSERT_RESULT_BIGZERO){
                log.error("添加供应商失败");
                return false;
            }
        }
        return true;
    }

    @Override
    public SupplierApplication getByApplicationId(Integer applicationId) {
        return supplierApplicationMapper.selectById(applicationId);
    }
}
