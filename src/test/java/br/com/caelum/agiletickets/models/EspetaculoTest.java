package br.com.caelum.agiletickets.models;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveCriarUmaSessaoSeADataInicialForIgualAFinal(){
		Espetaculo espetaculo = new Espetaculo();
		LocalDate data = new LocalDate();
		LocalTime horario = new LocalTime();
		List<Sessao> sessoes = espetaculo.criaSessoes(data, data, horario, Periodicidade.DIARIA);
		Assert.assertEquals(1, sessoes.size());
	}

	@Test
	public void deveCriarCincoSessoesDiariasEmUmIntervaloDe5Dias() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate(2016, 5, 19);
		LocalDate fim = new LocalDate(2016, 5, 23);
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, new LocalTime(8,0), Periodicidade.DIARIA);
		
		Assert.assertEquals(5, sessoes.size());
	}
	
	@Test
	public void deveCriar3SessoesDiariasNasDatasCorretas() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate(2016, 5, 19);
		LocalDate fim = new LocalDate(2016, 5, 21);
		LocalTime horario = new LocalTime(8,0);
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
		
		DateTime primeiraSessao = new DateTime(2016,5,19,8,0);
		Assert.assertEquals(primeiraSessao, sessoes.get(0).getInicio());
		
		DateTime segundaSessao = new DateTime(2016,5,20,8,0);
		Assert.assertEquals(segundaSessao, sessoes.get(1).getInicio());
		
		DateTime terceiraSessao = new DateTime(2016,5,21,8,0);
		Assert.assertEquals(terceiraSessao, sessoes.get(2).getInicio());
		
	}
	
	@Test
	public void deveCriar2SessoesSemanaisNasDatasCorretas() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate(2016, 5, 19);
		LocalDate fim = new LocalDate(2016, 5, 27);
		LocalTime horario = new LocalTime(8,0);
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.SEMANAL);
		
		DateTime primeiraSessao = new DateTime(2016,5,19,8,0);
		Assert.assertEquals(primeiraSessao, sessoes.get(0).getInicio());
		
		DateTime segundaSessao = new DateTime(2016,5,26,8,0);
		Assert.assertEquals(segundaSessao, sessoes.get(1).getInicio());
		
	}
	
	@Test
	public void deveCriar2SessoesSemanaisEmUmIntervaloDe9Dias() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate(2016, 5, 19);
		LocalDate fim = new LocalDate(2016, 5, 27);
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, new LocalTime(8,0), Periodicidade.SEMANAL);
		
		Assert.assertEquals(2, sessoes.size());
	}
	
	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
