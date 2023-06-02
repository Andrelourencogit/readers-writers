import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

public class Leitor implements Runnable {
    private final Lock lock;
    private final Condition leitoresCond;
    private final Condition escritoresCond;
    private final Semaphore leitoresSem;
    private static boolean escritorAtivo;
    private static int contadorLeitores;

    public Leitor(Lock lock, Condition leitoresCond, Condition escritoresCond, Semaphore leitoresSem) {
        this.lock = lock;
        this.leitoresCond = leitoresCond;
        this.escritoresCond = escritoresCond;
        this.leitoresSem = leitoresSem;

    }

    public void run() {
        try {
            leitoresSem.acquire();

            lock.lock();

            // Leitor espera caso haja um escritor ativo
            while (escritorAtivo) {
                leitoresCond.await();
            }

            contadorLeitores++;

            lock.unlock();

            leitoresSem.release();

            // Realiza a leitura do recurso compartilhado
            System.out.println("Leitor lendo...");

            lock.lock();

            contadorLeitores--;

            // Libera recurso caso não haja mais leitores
            if (contadorLeitores == 0) {
                escritoresCond.signal();
            }

            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
