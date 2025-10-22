/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import dal.AccountDAO;
import dto.Response;
import model.Account;
import constant.MessageConstant;

/**
 *
 * @author Guang Trump
 */ 
//Service như là trung gian, lấy data của controller để giao cho DAO thực hiện vì DAO kết nối với DB
public class LoginService {
    
    AccountDAO accountDAO;
    
    public LoginService(){
        accountDAO = new AccountDAO();
    }
    
//Account sẽ được ném vào generics, thay T bằng account
    public Response<Account> checkLogin(String username, String password){
        Account account =  accountDAO.checkLogin(username, password);
        if(account != null){
            return new Response<>(account, true, MessageConstant.LOGIN_SUCCESS);
        } else {
            return new Response<>(null, false, MessageConstant.LOGIN_FAIL);
        }
    }
}
