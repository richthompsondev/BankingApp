package se.emanuel.bank;

import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransRepo extends JpaRepository <Transaction,Integer> {

    List<Transaction> findByPersonid(int id);
    List<Transaction> findTransactionByPersonid(int id);

}
