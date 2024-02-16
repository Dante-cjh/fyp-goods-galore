package jiahan.chen.service;

import jiahan.chen.dto.req.AccountReqDTO;

/**
 * @author Jiahan Chen
 * @ClassName IRegisterService
 */
public interface IRegisterService {

    boolean register(AccountReqDTO accountReqDTO);
}
