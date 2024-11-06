package org.ood.adporwal.bookstore.services;

import org.ood.adporwal.bookstore.entity.InventoryItem;
import org.ood.adporwal.bookstore.entity.Transaction;
import org.ood.adporwal.bookstore.enums.TransactionType;
import org.ood.adporwal.bookstore.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl {

    @Autowired
    private GetTimestamp getTimestamp;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction recordTransaction(InventoryItem item,TransactionType transactionType){
        return transactionRepository.save(new Transaction(item, transactionType, getTimestamp.getCurrentTime() , item.getCurrentPrice()));
    }
}
