package ru.denisa.telegram.observer;

import ru.denisa.model.Pair;

/**
 * Created by d.aleksandrov on 01.11.2017.
 */
public interface ITelegramObserver {

    public void handle1minChange(Pair pair);
    public void handle5minChange(Pair pair);


}
