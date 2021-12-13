package edu.bu.homeworkteam.bankatm.repositories;

import edu.bu.homeworkteam.bankatm.entities.Collateral;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CollateralRepository extends CrudRepository<Collateral, Integer> {
    default Collateral create() {
        Collateral collateral = new Collateral();
        save(collateral);
        return collateral;
    }

    default List<Collateral> getByCustomerId(Integer customerId) {
        List<Collateral> resultList = new ArrayList<>();
        Iterable<Collateral> collateralIterable = findAll();
        for(Collateral collateral: collateralIterable) {
            if(collateral.getCustomer().getId() == customerId) {
                resultList.add(collateral);
            }
        }
        return resultList;
    }
}
