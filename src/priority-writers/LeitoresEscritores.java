import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

public class LeitoresEscritores {
    public static void main(String[] args) {
        int numLeitores = 5;
        int numEscritores = 3;

        Lock lock = new ReentrantLock();
        Condition leitoresCond = lock.newCondition();
        Condition escritoresCond = lock.newCondition();
        Semaphore leitoresSem = new Semaphore(1);
        Semaphore recursoSem = new Semaphore(1);

        Leitor[] leitores = new Leitor[numLeitores];
        Escritor[] escritores = new Escritor[numEscritores];

        for (int i = 0; i < numLeitores; i++) {
            leitores[i] = new Leitor(lock, leitoresCond, escritoresCond, leitoresSem);
            Thread threadLeitor = new Thread(leitores[i]);
            threadLeitor.start();
        }

        for (int i = 0; i < numEscritores; i++) {
            escritores[i] = new Escritor(lock, leitoresCond, escritoresCond, leitoresSem, recursoSem);
            Thread threadEscritor = new Thread(escritores[i]);
            threadEscritor.start();
        }
    }
}
