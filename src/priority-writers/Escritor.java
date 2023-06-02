import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

public class Escritor implements Runnable {
    private final Lock lock;
    private final Condition leitoresCond;
    private final Condition escritoresCond;
    private final Semaphore leitoresSem;
    private final Semaphore recursoSem;
    private static int contadorLeitores;
    private static boolean escritorAtivo;

    public Escritor(Lock lock, Condition leitoresCond, Condition escritoresCond, Semaphore leitoresSem,
            Semaphore recursoSem) {
        this.lock = lock;
        this.leitoresCond = leitoresCond;
        this.escritoresCond = escritoresCond;
        this.leitoresSem = leitoresSem;
        this.recursoSem = recursoSem;
    }

    public void run() {
        try {
            recursoSem.acquire();

            lock.lock();

            // Aguarda leitores terminarem
            while (contadorLeitores > 0) {
                escritoresCond.await();
            }

            escritorAtivo = true;

            lock.unlock();

            // Realiza a escrita no recurso compartilhado
            System.out.println("Escritor escrevendo...");

            lock.lock();

            escritorAtivo = false;

            // Libera leitores bloqueados
            leitoresCond.signalAll();

            lock.unlock();

            recursoSem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
