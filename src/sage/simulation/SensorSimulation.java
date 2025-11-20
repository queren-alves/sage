package sage.simulation;

import java.util.Random;

public class SensorSimulation {
	private String localizacao;
	private DispositivoSimulation dispositivo;
	private long last;
	private static final long idle = 5000;
	private Random random;
	
	public SensorSimulation(String loc, DispositivoSimulation disp) {
		this.localizacao = loc;
		this.dispositivo = disp;
		this.last = System.currentTimeMillis();
		this.random = new Random();
	}
	
	public void moviments() {
		boolean moviment = random.nextBoolean();
		if(dispositivo.getTipo().equals("Iluminação")) {
			LuminosidadeSimulation luz = new LuminosidadeSimulation();
			int luminosidade = luz.lightCheck();
			System.out.println("Luminosidade atual de " + localizacao + ": " + luminosidade + "%");
			if(moviment && luminosidade < 60) {
				detection();
			} else if(moviment) {
				System.out.println("Movimento detectado em " + localizacao + ", mas luminosidade alta. Luz não acesa.");
			} else {
				System.out.println("Nenhum movimento detectado em " + localizacao + ".");
				idleCheck();
			}
		}
		
		if (dispositivo.getTipo().equals("Climatização")) {
			TemperaturaSimulation temp = new TemperaturaSimulation();
			double tempNow = temp.temperatureCheck();
			System.out.println("Temperatura atual: " + tempNow + "°C");
			if (tempNow > 26) 
				detection();
		}
	}
	
	public void detection() {
		last = System.currentTimeMillis();
			if(!dispositivo.isOn()) {
				System.out.println("Movimento detectado em " + localizacao + ".");
				dispositivo.turnOn();
			} else
				System.out.println("Movimento detectado em " + localizacao + ", mas os dispositivos já estão ligados.");
		}
	
	public void idleCheck() {
		long now = System.currentTimeMillis();
		if(dispositivo.isOn() && (now-last >= idle)) {
			System.out.println("Tempo de ociosidade excedido em " + localizacao + ".");
			dispositivo.turnOff();
		}
	}
	
	public String getLocalizacao() {
		return localizacao;
	}
	
}
