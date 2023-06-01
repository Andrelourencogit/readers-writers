import java.util.concurrent.Semaphore;

public class LeitoresEscritores {
    private static Semaphore leitoresSem = new Semaphore(1);
    private static Semaphore escritoresSem = new Semaphore(1);
    private static Semaphore recursoSem = new Semaphore(1);
    private static int contadorEscritores = 0;

    public static void acquireLeitor() throws InterruptedException {
        leitoresSem.acquire();
        if (contadorEscritores > 0) {
            leitoresSem.release();
            recursoSem.acquire();
            leitoresSem.acquire();
        }
    }

    public static void releaseLeitor() {
        leitoresSem.release();
        if (leitoresSem.availablePermits() == 1) {
            recursoSem.release();
        }
    }

    public static void acquireEscritor() throws InterruptedException {
        escritoresSem.acquire();
        contadorEscritores++;
        if (contadorEscritores == 1) {
            recursoSem.acquire();
        }
        escritoresSem.release();
    }

    public static void releaseEscritor() throws InterruptedException {
        escritoresSem.acquire();
        contadorEscritores--;
        if (contadorEscritores == 0) {
            recursoSem.release();
        }
        escritoresSem.release();
    }

    public static void main(String[] args) {
        int numLeitores = 3;
        int numEscritores = 2;

        for (int i = 0; i < numLeitores; i++) {
            Thread leitorThread = new Thread(new Leitor());
            leitorThread.start();
        }

        for (int i = 0; i < numEscritores; i++) {
            Thread escritorThread = new Thread(new Escritor());
            escritorThread.start();
        }
    }
}
