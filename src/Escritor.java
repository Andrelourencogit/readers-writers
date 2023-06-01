public class Escritor implements Runnable {
    public void run() {
        try {
            LeitoresEscritores.acquireEscritor();

            // Realiza a escrita no recurso compartilhado
            System.out.println("Escritor escrevendo...");

            LeitoresEscritores.releaseEscritor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
