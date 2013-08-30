package me.marianonavas.learningtests.fastjson;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class FastJsonParsingTest {
	private Bean bean;
	
	public void setUp() {
		bean = new Bean();
		bean.setStr("Str");
		bean.setBooleanObject(Boolean.TRUE);
		bean.setBooleanPrimitive(false);
		bean.setDate(new Date());
		bean.setIntegerObject(100);
		bean.setIntegerPrimitive(200);
	}
	
	@Test
	public void testParse() {
		String jsonString = JSON.toJSONString(bean);
		Bean actual = JSON.parseObject(jsonString, Bean.class);
		assertEquals(bean, actual);
	}
}
