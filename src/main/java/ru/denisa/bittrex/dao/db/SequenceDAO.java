package ru.denisa.bittrex.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ru.denisa.bittrex.model.Sequence;


/**
 * Created by root on 13/07/17.
 */
@Repository
public class SequenceDAO {

    @Autowired private MongoOperations mongoOperations;


    public Long getNextSequenceId(String key){


         if (mongoOperations.findAll(Sequence.class).size()==0){
            Sequence newSec = new Sequence();
            newSec.setId(key);
            newSec.setSequence(new Long(0));
            mongoOperations.save(newSec);
        }

        Query query = new Query(Criteria.where("id").is(key));
         //add sequence
        Update update = new Update();
        update.inc("Sequence",1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
         Sequence sequence = mongoOperations.findAndModify(query,update,options,Sequence.class);
         return sequence.getSequence();



    }









}
