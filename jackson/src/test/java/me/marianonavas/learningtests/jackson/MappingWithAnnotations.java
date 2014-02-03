package me.marianonavas.learningtests.jackson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import me.marianonavas.learningtests.jackson.beans.OneStringBean;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class MappingWithAnnotations {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void objectToJSON() throws Exception {
        String value = "Hola";
        Writer w = createWriterWithValue(value);
        assertTrue(w.toString().contains(value));
        System.out.println(w);
    }

    private Writer createWriterWithValue(String value) throws IOException, JsonGenerationException,
            JsonMappingException {
        OneStringBean bean = new OneStringBean(value);
        Writer w = new StringWriter();
        mapper.writeValue(w, bean);
        return w;
    }

    @Test
    public void jsonToObject() throws Exception {
        String value = "Pedo";
        String json = createWriterWithValue(value).toString();
        OneStringBean readValue = mapper.readValue(json, OneStringBean.class);
        assertEquals(value, readValue.getString());
    }
}
