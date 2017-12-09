package ru.denisa.service.util.observer;

import ru.denisa.model.Pair;
import ru.denisa.telegram.observer.ITelegramObserver;

/**
 * Created by d.aleksandrov on 02.11.2017.
 */
public interface ISubject {
    public void register (ITelegramObserver observer);
    public void unregister (ITelegramObserver observer);
    public void notifyObserver(Pair pair);

}
