package com.example.demo.common.security.component;

import com.example.demo.common.security.constant.CommonConstants;
import com.example.demo.common.security.exception.AirAuth2Exception;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @Author oyg
 * @Date 2019/5/3/22:53
 */
public class AirAuth2ExceptionSerializer extends StdSerializer<AirAuth2Exception> {
    public AirAuth2ExceptionSerializer() {
        super(AirAuth2Exception.class);
    }

    @Override
    public void serialize(AirAuth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("status", CommonConstants.FAIL);
        gen.writeStringField("msg", value.getMessage());
        gen.writeStringField("data", value.getErrorCode());
        gen.writeEndObject();
    }
}
