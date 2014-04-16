import org.junit.Test;

public class ReaderWriter {
   /*
    * On obejct is shared among many threads, each beloing to one of two classes:
    * Reader: read data, never modify it
    * Writer: read data, and modify it
    * [1] Many readers are allowed to read at the same time
    * [2] Only one writer is allowed to write at one time
    * [3] When one thread is writing, reading is not allowed
    * [4] When one thread is reading, writing is not allowed
    */

   class Reader extends Thread {
      protected ReaderWriterController rwl;

      public Reader(ReaderWriterController rwl) {
         this.rwl = rwl;
      }

      public void run() {
         while (true) {
            rwl.readStart();
            System.out.println(getName() + " is reading");
            rwl.readEnd();
         }
      }
   }

   class Writer extends Thread {
      protected ReaderWriterController rwl;

      public Writer(ReaderWriterController rwl) {
         this.rwl = rwl;
      }

      public void run() {
         while (true) {
            rwl.writeStart();
            System.out.println(getName() + " is writing");
            rwl.writeEnd();
         }
      }
   }

   class ReaderWriterController {
      private int numReaders = 0;
      private int numWriters = 0;

      private synchronized void readStart() {
         while (numWriters > 0) {
            try {
               wait();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
         numReaders++;
      }

      private synchronized void readEnd() {
         numReaders--;
         if (numReaders == 0)
            notify();
      }

      private synchronized void writeStart() {
         // This solution favors Writer, once there's a writer requesting access,
         // all other readers requesting access will be blocked. If we move 'numWriters++'
         // below the while loop, then it'll favor readers
         numWriters++;
         while (numReaders > 0) {
            try {
               wait();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
      }

      private synchronized void writeEnd() {
         numWriters--;
         if (numWriters == 0)
            notify();
      }
   }
   
   static ReaderWriterController rwl;
   @Test
   public void test() {
      rwl = new ReaderWriterController();
      new Reader(rwl).start();
     // new Reader(rwl).start();
      new Writer(rwl).start();
   }

}
