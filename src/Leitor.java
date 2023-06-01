public class Leitor implements Runnable {
    public void run() {
        try {
            LeitoresEscritores.acquireLeitor();

            // Realiza a leitura do recurso compartilhado
            System.out.println("Leitor lendo...");

            LeitoresEscritores.releaseLeitor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
