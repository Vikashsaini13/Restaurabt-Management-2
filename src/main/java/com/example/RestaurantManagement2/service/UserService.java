package com.example.RestaurantManagement2.service;

import com.example.RestaurantManagement2.model.AuthenticationToken;
import com.example.RestaurantManagement2.model.User;
import com.example.RestaurantManagement2.model.dto.SignInInput;
import com.example.RestaurantManagement2.model.dto.SignUpOutput;
import com.example.RestaurantManagement2.repository.IAuthTokenRepo;
import com.example.RestaurantManagement2.repository.IUserRepo;
import com.example.RestaurantManagement2.service.utility.emailUtility.EmailHandler;
import com.example.RestaurantManagement2.service.utility.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    IAuthTokenRepo iAuthTokenRepo;

    public SignUpOutput signUpUser(User user) {


            boolean signUpStatus=true;
            String signUpStatusMessage=null;

            String newEmail=user.getUserEmail();
            if(newEmail==null){
                signUpStatusMessage="inValid email";
                signUpStatus=false;
                return new SignUpOutput(signUpStatus,signUpStatusMessage);
            }
            //check if this patient email is already exist or not??
            User existingUser=userRepo.findFirstByUserEmail(newEmail);


            if(existingUser!=null){
                signUpStatusMessage="Email is already exist";
                signUpStatus=false;
                return new SignUpOutput(signUpStatus,signUpStatusMessage);
            }
            try {
                //hash the password : encrypt the password
                String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());

                user.setUserPassword(encryptedPassword);
                userRepo.save(user);
                signUpStatusMessage = "user registered successfully!!!";

                return new SignUpOutput(signUpStatus, signUpStatusMessage);
            }
            catch(Exception e){
                signUpStatusMessage="Internal error occurred during signup";
                signUpStatus=false;
                return new SignUpOutput(signUpStatus,signUpStatusMessage);
            }

    }

    public String signInUser(SignInInput signInInput) {

        String signInStatusMessage=null;

        String signInEmail=signInInput.getEmail();
        if(signInInput==null){

            signInStatusMessage="inValid email";
            return signInStatusMessage;
        }

        //check if this email already exist??

        User existingUser= userRepo.findFirstByUserEmail(signInEmail);

        if(existingUser==null){

            signInStatusMessage="email not registered";
            return signInStatusMessage;
        }

        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingUser.getUserPassword().equals(encryptedPassword)){
                //session should be created since password matched and email id is valid

                AuthenticationToken authenticationToken=new AuthenticationToken(existingUser);
                iAuthTokenRepo.save(authenticationToken);

                EmailHandler.sendEmail("vs2215727@gmail.com","testing otp",authenticationToken.getTokenValue());
                return "Token sent to your email";

            }
            else{
                signInStatusMessage="invalid user name or password";
                return signInStatusMessage;
            }
        }
        catch(Exception e){
            signInStatusMessage="internal error occurred during sign in";
            return  signInStatusMessage;
        }
    }


}
