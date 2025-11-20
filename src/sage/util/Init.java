package sage.util;

//import java.time.LocalDateTime;

import sage.controller.AmbienteController;
import sage.controller.BlocoController;
import sage.controller.DispositivoController;
import sage.controller.PainelSolarController;
import sage.controller.SensorController;
import sage.controller.SimuladorController;
import sage.controller.UsuarioController;
import sage.model.Ambiente;
import sage.model.Bloco;
import sage.model.Dispositivo;
//import sage.model.EventoDesligamento;
import sage.model.PainelSolar;
import sage.model.Sensor;
import sage.model.Usuario;
import sage.simulation.AmbienteSimulation;
import sage.simulation.DispositivoSimulation;
import sage.simulation.SensorSimulation;

public class Init {

	public static void init() {
		UsuarioController.persist(new Usuario("Quéren", "kamala", true, "123"));
		SimuladorController.iniciar();
		
		Bloco bloco = new Bloco(1, "Bloco A");
		BlocoController.persist(bloco);
		Ambiente ambiente = new Ambiente(1, "Sala 1", 1, bloco);
		AmbienteController.persist(ambiente);
		Dispositivo dispositivo = new Dispositivo(1, "AR-001", "Climatização", 14550, ambiente);
		DispositivoController.persist(dispositivo);
		Sensor sensor = new Sensor(1, "S-001", "SensoresPI", ambiente);
		SensorController.persist(sensor);
		PainelSolar painel = new PainelSolar(1, "P-001", 0.5);
		PainelSolarController.persist(painel);
		Ambiente ambiente2 = new Ambiente(2, "Sala 2", 2, bloco);
		AmbienteController.persist(ambiente2);
		Dispositivo dispositivo2 = new Dispositivo(2, "AR-002", "Climatização", 12500, ambiente2);
		DispositivoController.persist(dispositivo2);
		Sensor sensor2 = new Sensor(2, "S-002", "SensoresPI", ambiente2);
		SensorController.persist(sensor2);
		
	}
	
	public static void initSimulation(int ciclos) throws InterruptedException {
	    AmbienteSimulation ambiente = new AmbienteSimulation();
	    DispositivoSimulation luzSala1 = new DispositivoSimulation("Iluminação - Sala 1", 10, "Iluminação");
	    DispositivoSimulation arSala2 = new DispositivoSimulation("Ar condicionado - Sala 2", 750, "Climatização");
	    SensorSimulation sensorSala1 = new SensorSimulation("Sala 1", luzSala1);
	    SensorSimulation sensorSala2 = new SensorSimulation("Sala 2", arSala2);

	    ambiente.addSensor(sensorSala1);
	    ambiente.addSensor(sensorSala2);
	   
	    ambiente.runCycles(ciclos);
	}
	
}