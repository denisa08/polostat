package ru.denisa.poloniex.service;

import com.cf.client.poloniex.PoloniexExchangeService;
import com.cf.data.model.poloniex.PoloniexTicker;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.denisa.poloniex.dao.PoloniexDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by d.aleksandrov on 03.01.2018.
 */

@Component
@Slf4j
public class PoloniexRunner {
    @Autowired
    PoloniexDao poloniexDao;



    public ArrayList<PoloniexTicker> getTickers() {
        return poloniexDao.getTickers();
    }


}
