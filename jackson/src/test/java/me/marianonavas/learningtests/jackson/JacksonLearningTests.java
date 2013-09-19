package me.marianonavas.learningtests.jackson;

import static org.junit.Assert.*;

import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class JacksonLearningTests {

    @Test
    public void testObjectToJSON() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Bean bean = instanceRegularBean();
        String valueAsString = mapper.writeValueAsString(bean);
        boolean assertCondition = valueAsString.startsWith("{") &&  valueAsString.endsWith("}");
        assertTrue(assertCondition);
    }

    private Bean instanceRegularBean() {
        Bean bean = new Bean();
        bean.setBool(true);
        bean.setDate(new Date());
        bean.setInteger(100);
        bean.setString("string");
        return bean;
    }

    public static class Bean {
        private String string;
        private int integer;
        private Date date;
        private boolean bool;
        public String getString() {
            return string;
        }
        public void setString(String string) {
            this.string = string;
        }
        public int getInteger() {
            return integer;
        }
        public void setInteger(int integer) {
            this.integer = integer;
        }
        public Date getDate() {
            return date;
        }
        public void setDate(Date date) {
            this.date = date;
        }
        public boolean isBool() {
            return bool;
        }
        public void setBool(boolean bool) {
            this.bool = bool;
        }
    }
}
