package ru.denisa.cache;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.LRUMap;
import org.slf4j.LoggerFactory;
import ru.denisa.dao.PairDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by D.Aleksandrov on 10.10.2017.
 */
public class CrunchifyInMemoryCache<K, T> {

    private long timeToLive;
    private LRUMap crunchifyCacheMap;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CrunchifyInMemoryCache.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static int onemin=60000;
    public static int twoMinutes=120000;
    public static int threeMinutes=180000;
    public static int fourMinutes=240000;





    public static int fiveMinutes=300000;
    public static int tenMinutes=600000;//
    public static int thmin=1800000; //30 min
    public static int hour=3600000;
    public static int threehour=10800000;
    public static int sixhour=21600000;
    public static int elevenhours=43200000;
    public static int twofourhours=86400000;







    protected class CrunchifyCacheObject {
        public long lastAccessed = System.currentTimeMillis();
        public T value;

        protected CrunchifyCacheObject(T value) {
            this.value = value;
        }
    }

    public CrunchifyInMemoryCache(final long crunchifyTimerInterval, int maxItems) {

        crunchifyCacheMap = new LRUMap(maxItems);

        if (crunchifyTimerInterval > 0) {

            Thread t = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(crunchifyTimerInterval );
                        } catch (InterruptedException ex) {
                            log.error("CrunchifyInMemoryCache error: "+ ex.getMessage());
                        }
                        cleanup();
                    }
                }
            });

            t.setDaemon(true);
            t.start();
        }
    }

    public void put(K key, T value) {
        synchronized (crunchifyCacheMap) {
            crunchifyCacheMap.put(key, new CrunchifyCacheObject(value));
        }
    }

    @SuppressWarnings("unchecked")
    public T get(K key) {
        synchronized (crunchifyCacheMap) {
            CrunchifyCacheObject c = (CrunchifyCacheObject) crunchifyCacheMap.get(key);

            if (c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return c.value;
            }
        }
    }

    public void remove(K key) {
        synchronized (crunchifyCacheMap) {
            crunchifyCacheMap.remove(key);
        }
    }

    public int size() {
        synchronized (crunchifyCacheMap) {
            return crunchifyCacheMap.size();
        }
    }

    @SuppressWarnings("unchecked")
    public void cleanup() {

        long now = System.currentTimeMillis();
        ArrayList<K> deleteKey = null;

        synchronized (crunchifyCacheMap) {
            MapIterator itr = crunchifyCacheMap.mapIterator();

            deleteKey = new ArrayList<K>((crunchifyCacheMap.size() / 2) + 1);
            K key = null;
            CrunchifyCacheObject c = null;

            while (itr.hasNext()) {
                key = (K) itr.next();
                c = (CrunchifyCacheObject) itr.getValue();

                if (c != null && (now > (timeToLive + c.lastAccessed))) {
                    deleteKey.add(key);
                }
            }
        }

        for (K key : deleteKey) {
            synchronized (crunchifyCacheMap) {
                crunchifyCacheMap.remove(key);
            }

            Thread.yield();
        }
    }
}
