package me.marianonavas.learningtests.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class TestMethodReturn {
    @Test
    public void consecutiveCallsWithDifferentResult() throws Exception {
        Task task = mock(Task.class);
        when(task.doIt()).thenReturn("a").thenReturn("b");
        String actual1 = task.doIt();
        String actual2 = task.doIt();
        assertEquals("a", actual1);
        assertEquals("b", actual2);
    }
}
