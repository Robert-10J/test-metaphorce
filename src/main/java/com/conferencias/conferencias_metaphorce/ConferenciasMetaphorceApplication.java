package com.conferencias.conferencias_metaphorce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.conferencias.conferencias_metaphorce.models.Participante;
import com.conferencias.conferencias_metaphorce.repositories.ParticipanteRepository;

@SpringBootApplication
public class ConferenciasMetaphorceApplication implements CommandLineRunner {

	@Autowired
	private ParticipanteRepository participanteRepository;

	public static void main(String[] args) {
		SpringApplication.run(ConferenciasMetaphorceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Participante> participantes = (List<Participante>) participanteRepository.findAll();
		participantes.stream().forEach(participante -> System.out.println(participante));
	}
}
