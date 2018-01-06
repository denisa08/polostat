package ru.denisa.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import ru.denisa.model.AppUser;
import ru.denisa.model.BittrexPair;
import ru.denisa.model.Pair;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by d.aleksandrov on 31.10.2017.
 */
@Component
public class DBSelect {

@Autowired
private MongoOperations mongoOperations;





    public void save(Pair pair) {
        mongoOperations.save(pair);
    }


    public void save(BittrexPair pair) {

        mongoOperations.save(pair);
    }





    public List<BittrexPair> findAllBtxPairs(){
        return mongoOperations.findAll(BittrexPair.class);
    }



    public Pair getFirstPair6HoursDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusHours(6).toEpochSecond();
        Query beetwenQuery = new Query();
        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));

        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }


    public Pair getFirstPair1HourDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusHours(1).toEpochSecond();
        Query beetwenQuery = new Query();
        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));

        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }

    public Pair getFirstPair10MinutesDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusMinutes(10).toEpochSecond();
        Query beetwenQuery = new Query();
        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));

        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }



    public Pair getFirstPair30MinutesDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusMinutes(30).toEpochSecond();
        Query beetwenQuery = new Query();
        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));

        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }

    public Pair getFirstPair12HoursDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusHours(12).toEpochSecond();
        Query beetwenQuery = new Query();

        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));
        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }



    public Pair getFirstPair24HoursDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusHours(24).toEpochSecond();
        Query beetwenQuery = new Query();

        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));
        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }


    public Pair getFirstPair3HoursDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusHours(3).toEpochSecond();
        Query beetwenQuery = new Query();
        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));

        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }




    public Pair getFirstPair5MinutesDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusMinutes(5).toEpochSecond();
        Query beetwenQuery = new Query();
        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));

        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }


    public Pair getFirstPairAllTimeDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusDays(7).toEpochSecond();
        Query beetwenQuery = new Query();
        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));

        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }



    public boolean checkBitrexPairs() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "id"));
        query.limit(1);
        BittrexPair pair = mongoOperations.findOne(query, BittrexPair.class);
        return (pair != null);

    }


    public Pair getLastPairDB(String pairName) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "id"));
        query.addCriteria(Criteria.where("name").is(pairName));
        query.limit(1);
        return mongoOperations.findOne(query, Pair.class);
    }


    public Pair getFirstPair4MinutesDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusMinutes(4).toEpochSecond();
        Query beetwenQuery = new Query();
        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));

        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }

    public Pair getFirstPair3MinutesDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusMinutes(3).toEpochSecond();
        Query beetwenQuery = new Query();
        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));

        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }

    public Pair getFirstPair2MinutesDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusMinutes(2).toEpochSecond();
        Query beetwenQuery = new Query();
        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));

        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }

    public Pair getFirstPair3DayDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusDays(3).toEpochSecond();
        Query beetwenQuery = new Query();
        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));

        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
     }

    public Pair getFirstPair5DayDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusDays(5).toEpochSecond();
        Query beetwenQuery = new Query();
        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));

        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }


    public Pair getFirstPair7DayDB(String pairName) {
        Long dateNow = ZonedDateTime.now().toEpochSecond();
        Long fiveLast = ZonedDateTime.now(ZoneOffset.UTC).minusDays(5).toEpochSecond();
        Query beetwenQuery = new Query();
        beetwenQuery.addCriteria(Criteria.where("name").is(pairName));

        beetwenQuery.addCriteria(Criteria.where("date").gte(fiveLast).lte(dateNow)).limit(1);
        return mongoOperations.findOne(beetwenQuery, Pair.class);
    }

}
