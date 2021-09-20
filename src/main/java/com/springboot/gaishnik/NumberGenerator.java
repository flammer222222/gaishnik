package com.springboot.gaishnik;
import java.util.logging.*;
import java.util.HashSet;
import java.lang.Math;

public class NumberGenerator {
	private String number = "";
	// инициализируем с -1, чтобы при первом получение с метода nextNumber()
	// вернулось 0 значение
	private int[] currentNumber = {0, -1, 0, 0};
	private final String REGION = " 116 RUS";
	
	//	В номерах используются только 12 букв, потому что для остальных
	//	нет аналогов
	private String alphabet = "АВЕКМНОРСТУХ";
	
	// множество для хранения выданных номеров
	private HashSet<String> numbersUsed = new HashSet<>();
	private final Logger LOGGER = Logger.getLogger(NumberGenerator.class.getName());
	
	private void numberToString() {
		
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
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public String GetNextNumber() {
		for(int i = 0; i < 12*12*12*1000; i++) {
			if(increaseNumber() && numbersUsed.add(number))
				return number;
		}
		return "Все номера заняты";
	}
	
	public String GetRandomNumber() {
		
		for(int i = 0; i < 12*12*12*1000; i++) {
			currentNumber[0] = (int)(Math.random() * alphabet.length());
			currentNumber[1] = (int)(Math.random() * 1000);
			currentNumber[2] = (int)(Math.random() * alphabet.length());
			currentNumber[3] = (int)(Math.random() * alphabet.length());
			number = alphabet.charAt(currentNumber[0]) + 
					 String.format("%03d", (currentNumber[1])) + 
					 alphabet.charAt(currentNumber[2]) + 
					 alphabet.charAt(currentNumber[3]) + 
					 REGION;
			if(numbersUsed.add(number))
				return number;
		}
		return "Все номера заняты";
	}
}
