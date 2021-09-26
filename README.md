# MIDTERM PROJECT

## INTRODUCTION

The purpose of the banking system application is to enable the customer to access their accounts, carry out transactions: deposits and withdrawals. This service also applies to an third party user - payment and collection of funds from the account holder.
The administrator of the application is able to create a new account, charge interest and penalty fees, and track abuses made with bank accounts.

In accordance with the requirements, the system identifies the user (Admin, Account Holder, Third Party) and, after verifying his authorization data, enables the performance of specific operations.

## HTTP REQUEST METHODS
### Methods available only to the Administrator

```
GET   "/admin/account"            returns a list of all accounts
GET   "/admin/account-holder"     returns a list of all account holders
GET   "/admin/third-party"        returns a list of all third party
GET   "/admin/address"            returns a list of all addresses
GET   "/admin/checking"           returns a list of all checking accounts
GET   "/admin/creditcard"         returns a list of all credit card accounts
GET   "/admin/savings"            returns a list of all savings accounts
GET   "/admin/student"            returns a list of all student checking accounts
GET   "/admin/transaction"        returns a list of all transaction
POST  "/admin/add/account-holder" creates a new account holder
POST  "/admin/add/third-party"    creates a new third party
POST  "/admin/add/address"        creates a new address
POST  "/admin/checking"           creates a new checking account
POST  "/admin/creditcard"         creates a new credit card account
POST  "/admin/savings"            creates a new savings account
PATCH "/admin/checking/{id}"      modifies the balance on checking account, {id} is the numer of account
PATCH "/admin/credit-card/{id}"   modifies the balance on credit card account, {id} is the numer of account
PATCH "/admin/savings/{id}"       modifies the balance on savings account, {id} is the numer of account
PATCH "/admin/student/{id}"       modifies the balance on student checking account, {id} is the numer of account
PUT   "/admin/penaltyFee/{id}"    deducts a penalty fee if the balance drops below the minimum balance, {id} is the numer of account
PUT   "/admin/interest/{id}"      adds annual or monthly interest on account,  {id} is the numer of account
PUT   "/admin/fraud-detection-v1" recognizes patterns that indicate fraud and freeze the account (patterns: transaction made in 24 hours total to more than 150% of the customers highest daily total transactions in any other 24 hour period)
PUT   "/admin/fraud-detection-v2" recognizes patterns that indicate fraud and freeze the account (patterns: more then 2 transactions occuring on a single account within a 1 second period)
PUT   "/admin/unfreeze/{id}"      unfreezes the account, , where {id} is the numer of account
```

### Methods available only to the Account Holder
```
GET  "/account-holder/{name}"                                       lets account holders access to their accounts by name of account holder
PUT  "/account-holder/{fromAcc}/transfer/{toAcc}/{owner}/{amount}"  lets account holders transfer money from any of their accounts:
                                                                         {fromAcc} – account id from which the transfer is to be made
                                                                         {toAcc} – account id to receive the transfer
                                                                         {owner} – primary or secondary owner name
                                                                         {amount} – transfer amount
```
### Methods available only to the Third Party
```
PUT "/third-party/{hashedKey}/{amount}/{accId}/{secretKey}/{operation}"   lets third party transfer money from any of their accounts:
                                                                               {fromAcc} – account id from which the transfer is to be made
                                                                               {toAcc} – account id to receive the transfer
                                                                               {owner} – primary or secondary owner name
                                                                               {amount} – transfer amount
                                                                               {operation} – DEPOSIT or WITHDRAWAL

