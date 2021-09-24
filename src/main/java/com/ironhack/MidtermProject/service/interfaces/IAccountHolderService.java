package com.ironhack.MidtermProject.service.interfaces;

import com.ironhack.MidtermProject.dao.users.AccountHolder;

public interface IAccountHolderService {

   AccountHolder createAccountHolder(AccountHolder accountHolder);
   void modifyAccountHolder(Long id, AccountHolder accountHolder);
}
