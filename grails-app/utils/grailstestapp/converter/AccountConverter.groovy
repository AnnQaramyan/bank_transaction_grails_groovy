package grailstestapp.converter

import grailstestapp.Account
import grailstestapp.dto.account.AccountAdminModel
import grailstestapp.dto.account.AccountUserRequestModel
import grailstestapp.dto.account.AccountUserResponseModel

class AccountConverter {
    static AccountAdminModel accountToAdminModel(Account account){
        AccountAdminModel adminModel = new AccountAdminModel();
        adminModel.setId(account.getId());
        adminModel.setNumber(account.getNumber());
        adminModel.setCurrency(account.getCurrency());
        adminModel.setDateCreated(account.getDateCreated());
        adminModel.setLastUpdated(account.getLastUpdated());
        adminModel.setStatus(account.getStatus());
        adminModel.setIsActive(account.isActive);
        adminModel.setUserAdminModel(UserConverter.userToAdminModel(account.getUser()));
        return adminModel;
    }
    static List<AccountAdminModel> accountsToAdminModels(List<Account> accounts){
        List<AccountAdminModel> adminModels = new ArrayList<>();
        for (Account account:accounts){
            adminModels.add(accountToAdminModel(account));
        }
        return adminModels;
    }
    static AccountUserResponseModel accountToResponse(Account account){
        AccountUserResponseModel response = new AccountUserResponseModel();
        response.setId(account.getId());
        response.setNumber(account.getNumber());
        response.setCurrency(account.getCurrency());
        response.setDateCreated(account.getDateCreated());
        response.setLastUpdated(account.getLastUpdated());
        response.setStatus(account.getStatus());
        response.setIsActive(account.isActive);
        return response;
    }
    static List<AccountUserResponseModel> accountsToResponses(List<Account> accounts){
        List<AccountUserResponseModel> responses = new ArrayList<>();
        for(Account account:accounts){
            responses.add(accountToResponse(account));
        }
        return responses;
    }
    static Account requestToAccount(AccountUserRequestModel request){
        Account account = new Account();
        account.setNumber(request.getNumber());
        account.setCurrency(request.getCurrency());
        return account;
    }
}
