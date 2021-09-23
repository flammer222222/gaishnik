package com.springboot.gaishnik;

import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;
import java.lang.Math;

public class NumberGenerator {
	private String number = "";
	/* 
	 	Инициализируем с -1, чтобы при первом получение с метода nextNumber()
	 	вернулось 0 значение
	*/
	private int[] currentNumber = {0, -1, 0, 0};
	private final String REGION = " 116 RUS";
	
	/*	
	 	В номерах используются только 12 букв, потому что для остальных
	 	нет аналогов
	*/
	private String alphabet = "АВЕКМНОРСТУХ";
	
	// множество для хранения выданных номеров
	private HashSet<String> numbersUsed = new HashSet<>();
	private final Logger LOGGER = Logger.getLogger(NumberGenerator.class.getName());
	
	// для установки текущего номера
	public boolean setCurrentNumber(String number) {
		//удалим регион и переведем в массив символов
		Pattern p = Pattern.compile("[АВЕКМНОРСТУХ][0-9][0-9][0-9][АВЕКМНОРСТУХ][АВЕКМНОРСТУХ] 116 RUS");  
	    Matcher m= p.matcher(number);
		if(m.matches()) {
			currentNumber[0] = alphabet.indexOf(number.charAt(0));
			currentNumber[1] = Integer.parseInt(number.substring(1, 4));
			currentNumber[2] = alphabet.indexOf(number.charAt(4));
			currentNumber[3] = alphabet.indexOf(number.charAt(5));	
			return true;
		}
		else {
			return false;
			// логирование ошибки
		}
	}
	
	private void convertNumberToString() {
		// сопоставляем числам из массива буквы из алфавита
		number = alphabet.charAt(currentNumber[0]) + 
				 String.format("%03d", (currentNumber[1])) + 
				 alphabet.charAt(currentNumber[2]) + 
				 alphabet.charAt(currentNumber[3]) + 
				 REGION;
	}
	private boolean increaseNumber() {
		currentNumber[1] += 1;
		if(currentNumber[1] == 1000) {
			currentNumber[1] = 0;
			currentNumber[3] +=1;
			if(currentNumber[3] == 12) {
				currentNumber[3] = 0;
				currentNumber[2] += 1;
				if(currentNumber[2] == 12) {
					currentNumber[2] = 0;
					currentNumber[0] += 1;
					if(currentNumber[0] == 12) {
						// возвращаем false, если дошли до конца
						return false;
					}
				}
			}
		}
		// возвращаем true, если удалось получить следующий номер
		return true;
	}
	
	public String GetNextNumber() {
		for(int i = 0; i < 12*12*12*1000; i++) {
			if(increaseNumber()) {
				convertNumberToString();
				if(numbersUsed.add(number))
					return number;
			}
		}
		return "Не удалось получить следующий номер";
	}
	
	public String GetRandomNumber() {
		/* 
			При количество попыток равном количеству возможных комбинаций, 
			возможен ложный ответ, что все номера заняты, 
			но при такой конфигурации не будет такого, 
			что останется несколько свободых номеров и 
			они будут долго рандомно генерироваться
		*/
		for(int i = 0; i < 12*12*12*1000; i++) {
			currentNumber[0] = (int)(Math.random() * alphabet.length());
			currentNumber[1] = (int)(Math.random() * 1000);
			currentNumber[2] = (int)(Math.random() * alphabet.length());
			currentNumber[3] = (int)(Math.random() * alphabet.length());
			convertNumberToString();
			if(numbersUsed.add(number))
				return number;
		}
		return "Все номера заняты";
	}
}
