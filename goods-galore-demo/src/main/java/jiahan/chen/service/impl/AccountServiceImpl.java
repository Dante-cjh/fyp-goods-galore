package jiahan.chen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jiahan.chen.constant.GoodsConstants;
import jiahan.chen.dto.req.ProfileReqDTO;
import jiahan.chen.entity.Account;
import jiahan.chen.mapper.AccountMapper;
import jiahan.chen.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jiahan.chen.utils.MD5Utils;
import jiahan.chen.utils.SaltUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jiahan Chen
 * @since 2024-02-05
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> getAllAccount() {
        QueryWrapper<Account> objectQueryWrapper = new QueryWrapper<>();
        // 添加筛选条件，只选取is_available为true的记录
        objectQueryWrapper.eq("is_avalible", 1);
        List<Account> accountList = accountMapper.selectList(objectQueryWrapper);
        return accountList;
    }

    @Override
    public Account getAccountByUsername(String username) {
//        QueryWrapper<Account> accountQueryWrapper = new QueryWrapper<>();
//        accountQueryWrapper.eq("username", username);

        LambdaQueryWrapper<Account> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Account::getUsername, username)
                .eq(Account::getIsAvalible, 1);
        Account account = accountMapper.selectOne(lambdaQueryWrapper);
        return account;
    }

    @Override
    public Account getByAccountId(Integer accountId) {
        return accountMapper.selectById(accountId);
    }

    @Override
    @Transactional
    public boolean updateAccountProfile(Integer accountId, ProfileReqDTO profileReqDTO) {
        Account account = accountMapper.selectById(accountId);
        // 修改用户名
        account.setUsername(profileReqDTO.getUsername());
        // 修改密码
        String password = profileReqDTO.getPassword();
        String salt = SaltUtils.getSalt();
        String newPwd = MD5Utils.md5(password + salt);
        account.setAccountSalt(salt);
        account.setPassword(newPwd);
        // 修改昵称
        account.setShowName(profileReqDTO.getShowName());
        // 处理头像文件保存
        String picImgPath = savePicImg(profileReqDTO.getPicImg());
        account.setPicImg(picImgPath);
        return accountMapper.updateById(account) > GoodsConstants.DB_UPDATE_RESULT_BIGZERO;
    }

    @Override
    @Transactional
    public boolean deleteAccount(Integer accountId) {
        Account account = accountMapper.selectById(accountId);
        account.setIsAvalible(false);
        return accountMapper.updateById(account) > GoodsConstants.DB_UPDATE_RESULT_BIGZERO;
    }

    private String savePicImg(MultipartFile file) {
        if (file == null) {
            return null;
        }
        log.info("[上传文件开始]");
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        String name = file.getName();
        log.info("[文件名为：{}; 类型为：{}]", originalFilename, contentType);
        Resource resource = file.getResource();
        //重新定义文件名
        long l = System.currentTimeMillis();
        //重新修正文件名
        String fileNames = l + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //定义保存路径
        String uploadDesk = "D:\\data\\pic_img\\";
        File file1 = new File(uploadDesk + fileNames);
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        try {
            file.transferTo(file1);
            log.info("[文件上传成功]");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("[文件上传失败]");
        }
        return fileNames;
    }
}
