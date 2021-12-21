package grailstestapp.converter

import grailstestapp.Transaction
import grailstestapp.dto.transaction.TransactionAdminModel
import grailstestapp.dto.transaction.TransactionUserRequestModel
import grailstestapp.dto.transaction.TransactionUserResponseModel

class TransactionConverter {
    static List<TransactionAdminModel> transactionsToAdminModels(List<Transaction> transactions){
        List<TransactionAdminModel> adminModels = new ArrayList<>();
        for(Transaction transaction:transactions){
            adminModels.add(transactionToAdminModel(transaction));
        }
        return adminModels;
    }
    static TransactionAdminModel transactionToAdminModel(Transaction transaction){
        TransactionAdminModel adminModel = new TransactionAdminModel();
        adminModel.setId(transaction.getId());
        adminModel.setType(transaction.getType());
        adminModel.setAmount(transaction.getAmount());
        adminModel.setToAmount(transaction.getToAmount());
        adminModel.setFrom(AccountConverter.accountToAdminModel(transaction.getFrom()));
        adminModel.setTo(AccountConverter.accountToAdminModel(transaction.getTo()));
        adminModel.setDateCreated(transaction.getDateCreated());
        adminModel.setLastUpdated(transaction.getLastUpdated());
        adminModel.setStatus(transaction.getStatus());
        adminModel.setIsActive(transaction.isActive);
        return adminModel;
    }
    static TransactionUserResponseModel transactionToResponse(Transaction transaction){
        TransactionUserResponseModel response = new TransactionUserResponseModel();
        response.setId(transaction.getId());
        response.setType(transaction.getType());
        response.setAmount(transaction.getAmount());
        response.setFrom(AccountConverter.accountToResponse(transaction.getFrom()));
        response.setTo(AccountConverter.accountToResponse(transaction.getTo()));
        response.setDateCreated(transaction.getDateCreated());
        response.setLastUpdated(transaction.getLastUpdated());
        response.setStatus(transaction.getStatus());
        response.setIsActive(transaction.isActive);
        return response;
    }
    static List<TransactionUserResponseModel> transactionsToResponses(List<Transaction> transactions){
        List<TransactionUserResponseModel> responses = new ArrayList<>();
        for(Transaction transaction:transactions){
            responses.add(transactionToResponse(transaction));
        }
        return responses;
    }
    static Transaction requestToTransaction(TransactionUserRequestModel request){
        Transaction transaction = new Transaction();
        transaction.setType(request.getType());
        transaction.setAmount(request.getAmount());
        return transaction;
    }
}
