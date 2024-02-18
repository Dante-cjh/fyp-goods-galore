package jiahan.chen.service.impl;

import jiahan.chen.constant.GoodsConstants;
import jiahan.chen.dto.req.SupermarketApplyReqDTO;
import jiahan.chen.entity.*;
import jiahan.chen.mapper.*;
import jiahan.chen.service.ISupermarketApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jiahan.chen.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * <p>
 * 申请成为超市 服务实现类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-17
 */
@Service
public class SupermarketApplicationServiceImpl extends ServiceImpl<SupermarketApplicationMapper, SupermarketApplication> implements ISupermarketApplicationService {
    @Autowired
    private SupermarketApplicationMapper supermarketApplicationMapper;

    @Autowired
    private SupermarketMapper supermarketMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @Transactional
    public boolean submitApplication(Integer userId, SupermarketApplyReqDTO supermarketApplyReqDTO) {
        String proofPath = null;

        // 获得参数
        String name = supermarketApplyReqDTO.getName();
        String address = supermarketApplyReqDTO.getAddress();
        String phoneNumber = supermarketApplyReqDTO.getPhoneNumber();
        String email = supermarketApplyReqDTO.getEmail();
        String description = supermarketApplyReqDTO.getDescription();
        MultipartFile file = supermarketApplyReqDTO.getDocumentaryProof();
        if (file != null) {
            proofPath = FileUtils.upload(file);
        }

        // 提交申请
        SupermarketApplication supermarketApplication = new SupermarketApplication();
        supermarketApplication.setAccountId(userId);
        supermarketApplication.setAddress(address);
        supermarketApplication.setName(name);
        supermarketApplication.setSubmissionDate(LocalDateTime.now());
        supermarketApplication.setApplicationStatus(1); // 设置为pending
        supermarketApplication.setPhoneNumber(phoneNumber);
        supermarketApplication.setEmail(email);
        supermarketApplication.setDescription(description);
        supermarketApplication.setDocumentaryProof(proofPath);

        // 4. 添加数据
        return supermarketApplicationMapper.insert(supermarketApplication) > GoodsConstants.DB_INSERT_RESULT_BIGZERO;
    }

    @Override
    @Transactional
    public boolean reviewApplication(Integer applicationId, Integer status) {
        SupermarketApplication application = getByApplicationId(applicationId);
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
        if(supermarketApplicationMapper.updateById(application) < GoodsConstants.DB_UPDATE_RESULT_BIGZERO){
            log.error("修改申请状态失败");
            return false;
        };

        // 修改供应商状态
        if(status == 2){
            // 更改对应Account的role Role 1:超市 2:供应商 3:管理员 4:普通用户
            account.setRole(1);
            if(accountMapper.updateById(account) < GoodsConstants.DB_UPDATE_RESULT_BIGZERO){
                log.error("修改账号角色失败");
                return false;
            }

            // 添加到供应商数据库
            Supermarket supermarket = new Supermarket();
            supermarket.setName(application.getName());
            supermarket.setAddress(application.getAddress());
            supermarket.setPhoneNumber(application.getPhoneNumber());
            supermarket.setEmail(application.getEmail());
            supermarket.setDescription(application.getDescription());
            supermarket.setAccountId(account.getAccountId());
            if(supermarketMapper.insert(supermarket) < GoodsConstants.DB_INSERT_RESULT_BIGZERO){
                log.error("添加超市失败");
                return false;
            }
        }
        return true;
    }

    @Override
    public SupermarketApplication getByApplicationId(Integer applicationId) {
        return supermarketApplicationMapper.selectById(applicationId);
    }
}
