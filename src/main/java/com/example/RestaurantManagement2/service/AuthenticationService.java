package com.example.RestaurantManagement2.service;


import com.example.RestaurantManagement2.model.AuthenticationToken;
import com.example.RestaurantManagement2.repository.IAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    @Autowired
    IAuthTokenRepo iAuthTokenRepo;
    public boolean authenticate(String email,String authTokenValue){

        AuthenticationToken authToken=iAuthTokenRepo.findFirstByTokenValue(authTokenValue);

        if(authToken==null){
            return false;
        }

        String tokenConnectedEmail= authToken.getUser().getUserEmail();

        return tokenConnectedEmail.equals(email);
    }

}
