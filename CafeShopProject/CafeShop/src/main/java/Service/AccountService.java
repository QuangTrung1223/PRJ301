/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import constant.MessageConstant;
import dal.AccountDAO;
import dal.AccountRoleDAO;
import dto.RegisterRequest;
import dto.Response;
import jakarta.security.enterprise.identitystore.PasswordHash;
import model.Account;
import utils.PasswordHasher;

/**
 *
 * @author Guang Trump
 */
public class AccountService {
    
    AccountDAO accountDAO;
    AccountRoleDAO accountRoleDAO;
    
    public AccountService(){
        accountDAO = new AccountDAO();
        accountRoleDAO = new AccountRoleDAO();
    }
    
//Account sẽ được ném vào generics, thay T bằng account
    public Response<Account> checkLogin(String username, String password){
        Account account =  accountDAO.getAccountByUsername(username);
        if(account != null && PasswordHasher.checkPassword(password, account.getPassword()) ){
            return new Response<>(account, true, MessageConstant.LOGIN_SUCCESS);
        } else {
            return new Response<>(null, false, MessageConstant.LOGIN_FAIL);
        }
    }
    
    public boolean register(RegisterRequest request) {
        String hashedPassword = PasswordHasher.hashPassword(request.getPassword());
        accountRoleDAO.addAccountRole(request.getUsername());
            return accountDAO.register(
        request.getUsername(),
        hashedPassword,
          request.getEmail()
    );
}
    
    

    
    
}
