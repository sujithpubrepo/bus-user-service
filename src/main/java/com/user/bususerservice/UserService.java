package com.user.bususerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Math.abs;

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

    public PaymentStatus updateBalance(WalletTransaction walletTransaction){

        User user =userRepository.findByUserid(walletTransaction.getUserid());
        Double userWalletAmount = user.getWalletamount();

        User admin =userRepository.findByUserid(walletTransaction.getAdminid());
        Double adminwalletAmount = admin.getWalletamount();


        PaymentStatus paymentStatus = new PaymentStatus();

        if(walletTransaction.getAmount() > 0){
            if(userWalletAmount >= walletTransaction.getAmount()){
                user.setWalletamount(userWalletAmount-walletTransaction.getAmount());
                admin.setWalletamount(adminwalletAmount+walletTransaction.getAmount());
                userRepository.save(user);
                userRepository.save(admin);
                setPaymentStatus(paymentStatus,"PAYMENT_SUCCESS","");
            } else {
                setPaymentStatus(paymentStatus,"PAYMENT_FAILURE","Not enough balance");

            }

        }else if (walletTransaction.getAmount() < 0){
            if(adminwalletAmount >= abs(walletTransaction.getAmount())){
                admin.setWalletamount(adminwalletAmount-walletTransaction.getAmount());
                user.setWalletamount(userWalletAmount+walletTransaction.getAmount());
                setPaymentStatus(paymentStatus,"PAYMENT_SUCCESS","");
            }else{
                setPaymentStatus(paymentStatus,"PAYMENT_FAILURE","Not enough balance");
            }

        }else{
            setPaymentStatus(paymentStatus,"PAYMENT_FAILURE","Amount is invalid");
        }

        return paymentStatus;
    }

    private void setPaymentStatus(PaymentStatus paymentStatus, String status, String reason){
        paymentStatus.setStatus(status);
        paymentStatus.setReason(reason);

    }
}
