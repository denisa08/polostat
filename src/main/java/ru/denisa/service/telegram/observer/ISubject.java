package ru.denisa.service.telegram.observer;

import ru.denisa.bittrex.model.Pair;

/**
 * Created by d.aleksandrov on 02.11.2017.
 */
public interface ISubject {
    public void register (ITelegramObserver observer);
    public void unregister (ITelegramObserver observer);
    public void notifyObserver(Pair pair);

}
