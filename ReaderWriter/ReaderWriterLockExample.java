import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.crypto.Data;

public class ReaderWriterLockExample {
   /*
    * Since JDK1.5, Java provides ReentrantReadWriteLock to improve concurrency performance in some
    * collections
    */
   // http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/concurrent/locks/ReentrantReadWriteLock.java

   class RWDictionary {
      private final Map<String, Data> m = new TreeMap<String, Data>();
      private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
      private final Lock r = rwl.readLock();
      private final Lock w = rwl.writeLock();

      public Data get(String key) {
         r.lock();
         try {
            return m.get(key);
         } finally {
            r.unlock();
         }
      }

      public String[] allKeys() {
         r.lock();
         try {
            return m.keySet().toArray(new String[] {});
         } finally {
            r.unlock();
         }
      }

      public Data put(String key, Data value) {
         w.lock();
         try {
            return m.put(key, value);
         } finally {
            w.unlock();
         }
      }

      public void clear() {
         w.lock();
         try {
            m.clear();
         } finally {
            w.unlock();
         }
      }
   }
}
