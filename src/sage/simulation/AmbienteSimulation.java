package sage.simulation;

import java.util.ArrayList;
import java.util.List;
public class AmbienteSimulation {
	
		private List<SensorSimulation> sensores;
		
		public AmbienteSimulation() {
			sensores = new ArrayList<>();
		}
		
		public void addSensor(SensorSimulation sensor) {
			sensores.add(sensor);	
		}
		
		public void runCycles(int qtd) throws InterruptedException {
		    for (int i = 0; i < qtd; i++) {
		        System.out.println("\n\t--- Ciclo " + (i + 1) + " ---");
		        for (SensorSimulation s : sensores) {
		            s.moviments();
		        }
		        Thread.sleep(3000);
		        for (SensorSimulation s : sensores) {
		            s.idleCheck();
		        }
		        Thread.sleep(3000);
		    }
		}
}

