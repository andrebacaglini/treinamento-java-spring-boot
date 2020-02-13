package br.com.cast.treinamento.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Tarefa {

	@Scheduled(cron = "0 0/1 * * * *")
	public void inserirCategoria() {
		System.out.println("Esse código foi executado de forma agendada.");
	}

}
