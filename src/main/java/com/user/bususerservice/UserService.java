package com.user.bususerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;
    public User createUser(User user){
        String userId ="USR"+(int)(Math.random()*100000);
        user.setUserid(userId);
        userRepository.save(user);

        return user;
    }

    public User deleteUser(String userid){
        User user =userRepository.findByUserid(userid);
        userRepository.delete(user);
        return user;
    }

    public User updateBalance(WalletTransaction walletTransaction){
        User user =userRepository.findByUserid(walletTransaction.getUserid());
        Double userWalletAmount = user.getWalletamount();
        user.setWalletamount(userWalletAmount-walletTransaction.getAmount());

        User admin =userRepository.findByUserid(walletTransaction.getAdminid());
        Double adminwalletAmount = admin.getWalletamount();
        user.setWalletamount(adminwalletAmount+walletTransaction.getAmount());

        userRepository.save(user);
        userRepository.save(admin);


        return user;
    }
}
