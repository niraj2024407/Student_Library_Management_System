package com.example.student_library_management_system.model;

import com.example.student_library_management_system.enums.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
@Table(name="transaction")
public class Transaction {
    //-> id, transactionDate, dueDate, transactionType(BORROW or RETURN).

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="transaction_date", nullable = false)
    @CreationTimestamp
    private Date transactionDate;

    @Column(name="due_date", nullable = false)
    private String dueDate;

    @Column(name="transaction_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @JoinColumn
    @ManyToOne
    private Card card;

    @JoinColumn
    @ManyToOne
    private Book book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
