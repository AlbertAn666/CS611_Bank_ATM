/**
 * newly added
 */
package edu.bu.homeworkteam.bankatm.repositories;

import edu.bu.homeworkteam.bankatm.entities.Loan;
import edu.bu.homeworkteam.bankatm.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Integer> {
    default Loan create() {
        Loan loan = new Loan();
        save(loan);
        return loan;
    }
}
