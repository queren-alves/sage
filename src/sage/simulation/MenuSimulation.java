package sage.simulation;

import sage.controller.AmbienteController;
import sage.model.Ambiente;

import java.util.List;

public class MenuSimulation implements Runnable {
    private volatile boolean ativo = true;

    public void parar() {
        ativo = false;
    }

    @Override
    public void run() {
        while (ativo && !Thread.currentThread().isInterrupted()) {
            List<Ambiente> ambientes = AmbienteController.findAll();

            if (!ambientes.isEmpty()) {
                Ambiente ambiente = ambientes.stream()
                        .filter(a -> a.getDispositivos().stream().allMatch(d -> !d.isLigado()))
                        .findAny()
                        .orElse(null);

                if (ambiente != null) {
                    ambiente.acionamento();
                }
            }

            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
   

