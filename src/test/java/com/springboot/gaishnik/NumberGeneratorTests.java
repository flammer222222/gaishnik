package com.springboot.gaishnik;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberGeneratorTests {
	private NumberGenerator NumberGenerator = new NumberGenerator();
	private String currentNumber = "A999РХ 116 RUS"; 
	
	@Test
	public void setCurrentNumberTest(){
		// устанавлимваем текущий номер
		assertTrue(NumberGenerator.setCurrentNumber(currentNumber), "must return true");
	}
	
	@Test
	public void GetNextNumberTest(){
		// проверяем, что вернулся следующий номер
		String result = "A000СА 116 RUS";
		assertEquals(result, NumberGenerator.GetNextNumber(), "must return A000СА 116 RUS");
	}
	
	@Test
	public void GetRandomNumberTest(){
		// проверяем соответсвие случайного номера регулярному выражению
		String number = NumberGenerator.GetRandomNumber();
		Pattern p = Pattern.compile("[АВЕКМНОРСТУХ][000-999][АВЕКМНОРСТУХ][АВЕКМНОРСТУХ] 116 RUS");  
	    Matcher m= p.matcher(number);
	    assertTrue(m.matches(), "must return true");
	}
}
