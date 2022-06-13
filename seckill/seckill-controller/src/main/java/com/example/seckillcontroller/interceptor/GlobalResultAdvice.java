package com.example.seckillcontroller.interceptor;

import com.example.common.utils.JsonUtils;
import com.example.common.utils.resultbean.ResultMAX;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResultAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        System.out.println("returnType.getParameterType()  "  +  returnType.getParameterType());
        return !returnType.getParameterType().isAssignableFrom(ResultMAX.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("输出body内容     =  " + body.toString() );

        if (returnType.getGenericParameterType().equals(String.class)) {
            System.out.println("输出body内容     =  " + body.toString() );
            ObjectMapper objectMapper = new ObjectMapper();
            ResultMAX<String> resultMAX = ResultMAX.build();
            resultMAX.setData(body.toString());
            try {
                return objectMapper.writeValueAsString(resultMAX);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        ResultMAX resultMAX = ResultMAX.build();
        resultMAX.setData(body);
        return JsonUtils.objectToJson(resultMAX);

    }
}
