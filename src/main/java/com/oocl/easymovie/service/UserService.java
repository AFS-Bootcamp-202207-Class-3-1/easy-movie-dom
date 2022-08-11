package com.oocl.easymovie.service;

import cn.hutool.crypto.digest.DigestUtil;
import com.oocl.easymovie.entity.PurchasePoint;
import com.oocl.easymovie.entity.User;
import com.oocl.easymovie.entity.VIP;
import com.oocl.easymovie.exception.UserAlreadyExistsException;
import com.oocl.easymovie.exception.UserAuthenticationFailedException;
import com.oocl.easymovie.exception.UserNotFoundException;
import com.oocl.easymovie.repository.PurchasePointRepository;
import com.oocl.easymovie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author edward
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PurchasePointRepository purchasePointRepository;

    public User save(User user) {
        if (user == null) {
            return null;
        }
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public long login(User user) {
        User data = userRepository.findOneByUsername(user.getUsername());
        if (data != null && DigestUtil.bcryptCheck(user.getPassword(), data.getPassword())) {
            return data.getId();
        }
        throw new UserAuthenticationFailedException();
    }


    public String saveUser(User user) {
        User data = userRepository.findOneByUsername(user.getUsername());
        if(data!=null){
            throw new UserAlreadyExistsException();
        }
        user.setPassword(DigestUtil.bcrypt(user.getPassword()));
        this.save(user);
        return "register successfully!";
    }

    public User update(Long id, User userRequest) {
        User user = findById(id);
        if (userRequest.getBirthday() != null) {
            user.setBirthday(userRequest.getBirthday());
        }
        if (userRequest.getGender() != null) {
            user.setGender(userRequest.getGender());
        }
        userRequest.setId(id);

        return userRepository.save(user);
    }

    public VIP findVIPLevelAndDiscountById(Long id) {
        VIP vip = new VIP();
        PurchasePoint purchasePoint = purchasePointRepository.findByUserId(id);
        Double historyTotal = purchasePoint.getHistoryTotal();
        if(historyTotal==null||(historyTotal>=0&&historyTotal<200)){
            vip.setLevel(0);
            vip.setDiscount(1);
        }else if(historyTotal<500){
            vip.setLevel(1);
            vip.setDiscount(0.95);
        }else if(historyTotal<800){
            vip.setLevel(2);
            vip.setDiscount(0.9);
        }else{
            vip.setLevel(3);
            vip.setDiscount(0.85);
        }
        return vip;
    }
}
