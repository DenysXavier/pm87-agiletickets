package br.com.caelum.agiletickets.models;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Weeks;

public enum Periodicidade {
	
	DIARIA {
		@Override
		public int calculaQuantidadeSessoes(LocalDate inicio, LocalDate fim) {			
			return Days.daysBetween(inicio, fim).getDays() + 1;
		}
	}, SEMANAL {
		@Override
		public int calculaQuantidadeSessoes(LocalDate inicio, LocalDate fim) {
			return Weeks.weeksBetween(inicio, fim).getWeeks()+1;
		}
	};
	
	public abstract int calculaQuantidadeSessoes(LocalDate inicio, LocalDate fim);
	
}
