package ru.denisa.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.denisa.model.Pair;

/**
 * Created by D.Aleksandrov on 02/07/17.
 */
@Repository
public interface IPairRepo  extends MongoRepository<Pair,String>, QueryDslPredicateExecutor<Pair>  {


}
