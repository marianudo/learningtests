package me.marianonavas.learningtests.mrunit;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.bson.BSONObject;
import org.bson.types.ObjectId;
import org.junit.Test;

import com.mongodb.BasicDBObject;

public class MrUnitBasicTests {

    @Test
    public void testVeryBasicOneAttributeDocument() throws Exception {
        Mapper<ObjectId, BSONObject, BSONObject, BSONObject> mapper = new Mapper<ObjectId, BSONObject, BSONObject, BSONObject>(){
            @SuppressWarnings("unchecked")
            @Override
            protected void map(ObjectId key, BSONObject value, org.apache.hadoop.mapreduce.Mapper.Context context)
                    throws IOException, InterruptedException {
                Object writeKey = createOutputKey();
                Object writeValue = createOutputValue();
                context.write(writeKey, writeValue);
            }
        };
        BSONObject input = new BasicDBObject("key", "value");
//        ParseMetadataAsTextIntoAvroMapper mapper = new ParseMetadataAsTextIntoAvroMapper();
        MapDriver<ObjectId, BSONObject, BSONObject, BSONObject> mapDriver = MapDriver.newMapDriver(mapper);
        mapDriver.withInput(new ObjectId(), input);
        mapDriver.withOutput(createOutputKey(), createOutputValue());
        mapDriver.runTest();
    }

    private BasicDBObject createOutputKey() {
        return new BasicDBObject("zonid", new ObjectId("5179577adb2da69ad0ee98e9"));
    }

    private BasicDBObject createOutputValue() {
        return new BasicDBObject("key", "value");
    }
}
