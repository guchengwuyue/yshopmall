package co.yixiang.modules.shop.service.impl;

import cn.hutool.core.util.NumberUtil;
import co.yixiang.modules.shop.domain.YxUser;
import co.yixiang.modules.shop.domain.YxUserBill;
import co.yixiang.modules.shop.repository.YxUserRepository;
import co.yixiang.modules.shop.service.YxUserBillService;
import co.yixiang.modules.shop.service.YxUserService;
import co.yixiang.modules.shop.service.dto.UserMoneyDTO;
import co.yixiang.modules.shop.service.dto.YxUserDTO;
import co.yixiang.modules.shop.service.dto.YxUserQueryCriteria;
import co.yixiang.modules.shop.service.mapper.YxUserMapper;
import co.yixiang.utils.OrderUtil;
import co.yixiang.utils.PageUtil;
import co.yixiang.utils.QueryHelp;
import co.yixiang.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
* @author hupeng
* @date 2019-10-06
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YxUserServiceImpl implements YxUserService {

    private final YxUserRepository yxUserRepository;

    private final YxUserMapper yxUserMapper;

    private final YxUserBillService yxUserBillService;

    public YxUserServiceImpl(YxUserRepository yxUserRepository, YxUserMapper yxUserMapper, YxUserBillService yxUserBillService) {
        this.yxUserRepository = yxUserRepository;
        this.yxUserMapper = yxUserMapper;
        this.yxUserBillService = yxUserBillService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMoney(UserMoneyDTO param) {
        YxUserDTO userDTO = findById(param.getUid());
        Double newMoney = 0d;
        String mark = "";
        String type = "system_add";
        Integer pm = 1;
        String title = "系统增加余额";
        if(param.getPtype() == 1){
            mark = "系统增加了"+param.getMoney()+"余额";
            newMoney = NumberUtil.add(userDTO.getNowMoney(),param.getMoney()).doubleValue();
        }else{
            title = "系统减少余额";
            mark = "系统扣除了"+param.getMoney()+"余额";
            type = "system_sub";
            pm = 0;
            newMoney = NumberUtil.sub(userDTO.getNowMoney(),param.getMoney()).doubleValue();
            if(newMoney < 0) newMoney = 0d;

        }
        YxUser user = new YxUser();
        user.setUid(userDTO.getUid());
        user.setNowMoney(BigDecimal.valueOf(newMoney));
        update(user);

        YxUserBill userBill = new YxUserBill();
        userBill.setUid(userDTO.getUid());
        userBill.setLinkId("0");
        userBill.setPm(pm);
        userBill.setTitle(title);
        userBill.setCategory("now_money");
        userBill.setType(type);
        userBill.setNumber(BigDecimal.valueOf(param.getMoney()));
        userBill.setBalance(BigDecimal.valueOf(newMoney));
        userBill.setMark(mark);
        userBill.setAddTime(OrderUtil.getSecondTimestampTwo());
        userBill.setStatus(1);
        yxUserBillService.create(userBill);
    }

    @Override
    public Map<String,Object> queryAll(YxUserQueryCriteria criteria, Pageable pageable){
        Page<YxUser> page = yxUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(yxUserMapper::toDto));
    }

    @Override
    public List<YxUserDTO> queryAll(YxUserQueryCriteria criteria){
        return yxUserMapper.toDto(yxUserRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public YxUserDTO findById(Integer uid) {
        Optional<YxUser> yxUser = yxUserRepository.findById(uid);
        ValidationUtil.isNull(yxUser,"YxUser","uid",uid);
        return yxUserMapper.toDto(yxUser.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public YxUserDTO create(YxUser resources) {
        return yxUserMapper.toDto(yxUserRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(YxUser resources) {
        Optional<YxUser> optionalYxUser = yxUserRepository.findById(resources.getUid());
        ValidationUtil.isNull( optionalYxUser,"YxUser","id",resources.getUid());
        YxUser yxUser = optionalYxUser.get();
        yxUser.copy(resources);
        yxUserRepository.save(yxUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer uid) {
        yxUserRepository.deleteById(uid);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onStatus(Integer uid, Integer status) {
        if(status == 1){
            status = 0;
        }else{
            status = 1;
        }

        yxUserRepository.updateOnstatus(status,uid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void incBrokeragePrice(double price, int uid) {
        yxUserRepository.incBrokeragePrice(price,uid);
    }
}