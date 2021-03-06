package com.ironhack.MidtermProject.service.impl;

import com.ironhack.MidtermProject.dao.users.ThirdParty;
import com.ironhack.MidtermProject.dao.additional.Money;
import com.ironhack.MidtermProject.dao.additional.Transaction;
import com.ironhack.MidtermProject.dao.accounts.*;
import com.ironhack.MidtermProject.dao.users.AccountHolder;
import com.ironhack.MidtermProject.enums.Operations;
import com.ironhack.MidtermProject.enums.Status;
import com.ironhack.MidtermProject.repository.users.ThirdPartyRepository;
import com.ironhack.MidtermProject.repository.additional.TransactionRepository;
import com.ironhack.MidtermProject.repository.accounts.*;
import com.ironhack.MidtermProject.repository.users.AccountHolderRepository;
import com.ironhack.MidtermProject.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ThirdPartyRepository thirdPartyRepository;


    public void transaction(Long fromAcc, BigDecimal amount, Long toAcc) {
        Optional<Account> accDeposit = accountRepository.findById(toAcc);
        Optional<Account> accWithdraw = accountRepository.findById(fromAcc);

        if (accWithdraw.isPresent()) {
            if (accWithdraw.get().getStatus().equals(Status.ACTIVE)) {
                Money initBal = accWithdraw.get().getBalance();
                Money finalBal = new Money(initBal.decreaseAmount(amount));
                if (finalBal.getAmount().compareTo(BigDecimal.ZERO) >= 0) {
                    accWithdraw.get().setBalance(finalBal);
                    accountRepository.save(accWithdraw.get());
                    Transaction newTransaction = new Transaction(Operations.WITHDRAWAL, accWithdraw.get().getId(), amount.multiply(new BigDecimal("-1")), accWithdraw.get().getAccountHolder().getAccHolderId(),
                            toAcc.toString(), accDeposit.get().getAccountHolder().getAccHolderId().toString(), true);
                    transactionRepository.save(newTransaction);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is not enough resources");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account is FROZEN.");
            }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id does not exist in the system. A withdrawal cannot be made.");
            }

            if (accDeposit.isPresent()) {
                Money initBal = accDeposit.get().getBalance();
                Money finalBal = new Money(initBal.increaseAmount(amount));
                accDeposit.get().setBalance(finalBal);
                accountRepository.save(accDeposit.get());
                Transaction newTransaction = new Transaction(Operations.DEPOSIT, accDeposit.get().getId(), amount, accDeposit.get().getAccountHolder().getAccHolderId(),
                        fromAcc.toString(), accWithdraw.get().getAccountHolder().getAccHolderId().toString(), true);
                transactionRepository.save(newTransaction);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id does not exist in the system. A deposit cannot be made.");
            }


        }


        public void penaltyFee (Long id){
            Optional<Savings> s = savingsRepository.findById(id);
            Optional<Checking> c = checkingRepository.findById(id);
            if (s.isPresent()) {
                if (s.get().getBalance().getAmount().compareTo(s.get().getMinimumBalance()) == -1) {
                    BigDecimal newBalanceBD = s.get().getBalance().getAmount().subtract(s.get().getPENALTY_FEE());
                    Money newBalance = new Money(newBalanceBD);
                    s.get().setBalance(newBalance);
                    savingsRepository.save(s.get());
                    Transaction newTransaction = new Transaction(Operations.PENALTY_FEE, s.get().getId(), s.get().getPENALTY_FEE().multiply(new BigDecimal("-1")), s.get().getAccountHolder().getAccHolderId(),
                            s.get().getId().toString(), s.get().getAccountHolder().getAccHolderId().toString(), true);
                    transactionRepository.save(newTransaction);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no need to charge a penalty fee.");
                }
            } else if (c.isPresent()) {
                if (c.get().getBalance().getAmount().compareTo(c.get().getMinimumBalance()) == -1) {
                    BigDecimal newBalanceBD = c.get().getBalance().getAmount().subtract(c.get().getPENALTY_FEE());
                    Money newBalance = new Money(newBalanceBD);
                    c.get().setBalance(newBalance);
                    checkingRepository.save(c.get());
                    Transaction newTransaction = new Transaction(Operations.PENALTY_FEE, c.get().getId(), c.get().getPENALTY_FEE().multiply(new BigDecimal("-1")), c.get().getAccountHolder().getAccHolderId(),
                            c.get().getId().toString(), c.get().getAccountHolder().getAccHolderId().toString(), true);
                    transactionRepository.save(newTransaction);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no need to charge a penalty fee.");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id does not exist in the system.");
            }
        }

        public void interestRate (Long id){
            Optional<Savings> s = savingsRepository.findById(id);
            Optional<CreditCard> cc = creditCardRepository.findById(id);

            if (s.isPresent()) {
                if (transactionRepository.dateOfLastInterest(id) == null) {
                    int timeFromCreate = Period.between(s.get().getCreationDate(), LocalDate.now()).getYears(); //liczba lat od otwarcia konta
                    BigDecimal newBalanceBD = s.get().getBalance().getAmount().multiply((s.get().getInterestRate().add(new BigDecimal("1"))).pow(timeFromCreate));
                    //Money newBalance = new Money(s.get().getBalance().getAmount().multiply(interest));
                    BigDecimal interest = newBalanceBD.subtract(s.get().getBalance().getAmount());
                    s.get().setBalance(new Money(newBalanceBD));
                    savingsRepository.save(s.get());
                    Transaction newTransaction = new Transaction(Operations.INTEREST, s.get().getId(), interest, s.get().getAccountHolder().getAccHolderId(),
                            s.get().getId().toString(), s.get().getAccountHolder().getAccHolderId().toString(), true);
                    transactionRepository.save(newTransaction);
                } else {
                    int timePast = Period.between(transactionRepository.dateOfLastInterest(id).toLocalDate(), LocalDate.now()).getYears();
                    if (timePast == 1) {
                        BigDecimal interest = s.get().getBalance().getAmount().multiply(s.get().getInterestRate());
                        Money newBalance = new Money(interest.add(interest));
                        s.get().setBalance(newBalance);
                        savingsRepository.save(s.get());
                        Transaction newTransaction = new Transaction(Operations.INTEREST, s.get().getId(), interest, s.get().getAccountHolder().getAccHolderId(),
                                s.get().getId().toString(), s.get().getAccountHolder().getAccHolderId().toString(), true);
                        transactionRepository.save(newTransaction);
                    } else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "It is too early to add interest");
                        // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The interest was already added to this account");
                    }
                }

            } else if (cc.isPresent()) {
                if (transactionRepository.dateOfLastInterest(id) == null) {
                    int timeFromCreate = Period.between(cc.get().getCreationDate(), LocalDate.now()).getYears() * 12 + Period.between(cc.get().getCreationDate(), LocalDate.now()).getMonths();
                    BigDecimal newBalanceBD = cc.get().getBalance().getAmount().multiply(((cc.get().getInterestRate().divide(new BigDecimal("12"), 10, RoundingMode.HALF_UP)).add(new BigDecimal("1"))).pow(timeFromCreate));
                    //Money newBalance = new Money(s.get().getBalance().getAmount().multiply(interest));
                    BigDecimal interest = newBalanceBD.subtract(cc.get().getBalance().getAmount());
                    cc.get().setBalance(new Money(newBalanceBD));
                    creditCardRepository.save(cc.get());
                    Transaction newTransaction = new Transaction(Operations.INTEREST, cc.get().getId(), interest, cc.get().getAccountHolder().getAccHolderId(),
                            cc.get().getId().toString(), cc.get().getAccountHolder().getAccHolderId().toString(), true);
                    transactionRepository.save(newTransaction);
                } else {
                    int timePast = Period.between(transactionRepository.dateOfLastInterest(id).toLocalDate(), LocalDate.now()).getMonths();
                    if (timePast > 1) {
                        BigDecimal interest = cc.get().getBalance().getAmount().multiply(s.get().getInterestRate().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
                        Money newBalance = new Money(interest.add(interest));
                        cc.get().setBalance(newBalance);
                        creditCardRepository.save(cc.get());
                        Transaction newTransaction = new Transaction(Operations.INTEREST, cc.get().getId(), interest, cc.get().getAccountHolder().getAccHolderId(),
                                cc.get().getId().toString(), cc.get().getAccountHolder().getAccHolderId().toString(), true);
                        transactionRepository.save(newTransaction);
                    } else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "It is too early to add interest");
                    }
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id does not exist in the system.");
            }

        }

        public void transactionThirdParty (Long accId, String secretKey, BigDecimal amount, String hashedKey, Operations
        operations){
            Optional<Account> acc = accountRepository.findById(accId);
            Optional<ThirdParty> tp = thirdPartyRepository.findByHashedKey(hashedKey);
            if (acc.isPresent() && tp.isPresent()) {
                if (secretKey.equals(acc.get().getSecretKey())) {
                    if (operations.equals(Operations.WITHDRAWAL)) {
                        Money initBal = acc.get().getBalance();
                        Money finalBal = new Money(initBal.decreaseAmount(amount));
                        if (finalBal.getAmount().compareTo(BigDecimal.ZERO) >= 0) {
                            acc.get().setBalance(finalBal);
                            accountRepository.save(acc.get());
                            Transaction newTransaction = new Transaction(Operations.WITHDRAWAL, acc.get().getId(), amount.multiply(new BigDecimal("-1")), acc.get().getAccountHolder().getAccHolderId(),
                                    null, hashedKey, false);
                            transactionRepository.save(newTransaction);
                        } else {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is not enough resources");
                        }
                    } else if (operations.equals(Operations.DEPOSIT)) {
                        Money initBal = acc.get().getBalance();
                        Money finalBal = new Money(initBal.increaseAmount(amount));
                        acc.get().setBalance(finalBal);
                        accountRepository.save(acc.get());
                        Transaction newTransaction = new Transaction(Operations.DEPOSIT, acc.get().getId(), amount, acc.get().getAccountHolder().getAccHolderId(),
                                null, hashedKey, false);
                        transactionRepository.save(newTransaction);
                    } else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This operation is not allowed.");
                    }
                }
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account id and/or hash key do not exist in the system.");
            }
        }

        public void fraudDetectionV2 () {
            List<Optional<Long>> accIdFraud = transactionRepository.countTransaction();
            int size = accIdFraud.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    if (accIdFraud.get(i).isPresent()) {
                        Account accFreeze = accountRepository.getById(accIdFraud.get(i).get());
                        accFreeze.setStatus(Status.FROZEN);
                        accountRepository.save(accFreeze);
                    }
                }
            } else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All accounts are fine.");
        }

    public void fraudDetectionV1 () {
        List<Optional<Long>> accIdFraud = transactionRepository.listOfAccId();
        int size = accIdFraud.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (accIdFraud.get(i).isPresent()) {
                    Account accFreeze = accountRepository.getById(accIdFraud.get(i).get());
                    accFreeze.setStatus(Status.FROZEN);
                    accountRepository.save(accFreeze);
                }
            }
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All accounts are fine.");
    }

        public void unfreeze (Long id){
            Optional<Account> acc = accountRepository.findById(id);
            if (acc.get().getStatus().equals(Status.FROZEN)) {
                acc.get().setStatus(Status.ACTIVE);
                accountRepository.save(acc.get());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account " + id + " is ACTIVE.");
            }
        }
    }
