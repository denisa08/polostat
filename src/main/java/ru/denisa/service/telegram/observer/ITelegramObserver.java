package ru.denisa.service.telegram.observer;

import ru.denisa.bittrex.model.Pair;

/**
 * Created by d.aleksandrov on 01.11.2017.
 */
public interface ITelegramObserver {


      void handle5minChangePrice();
      void handle5minChangeVolume();



    void handleVolumeChange(Pair pair);

    void handlePriceChange(Pair pair);
}
