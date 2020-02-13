package br.com.cast.treinamento.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.treinamento.enums.EnumAcoes;
import br.com.cast.treinamento.model.Curso;
import br.com.cast.treinamento.model.LogAuditoria;
import br.com.cast.treinamento.repository.LogAuditoriaRepository;

@Service
public class BaseService {

	@Autowired
	private LogAuditoriaRepository logRepository;

	protected void inserirLogAuditoria(EnumAcoes acao, Curso curso) {
		LogAuditoria log = new LogAuditoria(acao.toString(), new Date(), "USUARIO_FIXO", curso);
		logRepository.save(log);
	}

}
