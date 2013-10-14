package me.marianonavas.learningtests.commonslang;

import static java.util.Calendar.DATE;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

public class DateTruncateByDateLearningTest {
    @Test
    public void testTruncateByDateYear() throws Exception {
        genericTest(DATE);
    }

    private void genericTest(int field) {
        Date truncatedDate = instanceTruncatedDate();
        Calendar nowCal = Calendar.getInstance();
        Calendar truncatedCal = Calendar.getInstance();
        truncatedCal.setTime(truncatedDate);
        assertEquals(nowCal.get(field), truncatedCal.get(field));
    }

    private Date instanceTruncatedDate() {
        Date nowDate = new Date();
        Date truncatedDate = DateUtils.truncate(nowDate, DATE);
        return truncatedDate;
    }

    @Test
    public void testTruncateByDateMonth() throws Exception {
        genericTest(MONTH);
    }

    @Test
    public void testTruncateByDateDate() throws Exception {
        genericTest(DATE);
    }

    @Test
    public void testTruncatedByDateHour() throws Exception {
        genericTestTrunctatedField(HOUR_OF_DAY);
    }

    private void genericTestTrunctatedField(int truncatedField) {
        Date truncatedDate = instanceTruncatedDate();
        Calendar truncatedCal = Calendar.getInstance();
        truncatedCal.setTime(truncatedDate);
        assertEquals(0, truncatedCal.get(truncatedField));
    }

    @Test
    public void testTruncatedByDateMinute() throws Exception {
        genericTestTrunctatedField(MINUTE);
    }

    @Test
    public void testTruncatedByDateSecond() throws Exception {
        genericTestTrunctatedField(SECOND);
    }

    @Test
    public void testTruncatedByDateMillisecond() throws Exception {
        genericTestTrunctatedField(MILLISECOND);
    }
}
