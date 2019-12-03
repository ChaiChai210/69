package com.colin.playerdemo.net;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Response;
import rxhttp.wrapper.annotation.Parser;
import rxhttp.wrapper.entity.ParameterizedTypeImpl;
import rxhttp.wrapper.exception.ParseException;
import rxhttp.wrapper.parse.AbstractParser;
import rxhttp.wrapper.utils.GsonUtil;

@Parser(name = "DataListParser")
public class DataListParser<T> extends AbstractParser<List<T>> {
    protected DataListParser(){
        super();
    }
    public DataListParser(Type type){
        super(type);
    }
    @Override
    public List<T> onParse(Response response) throws IOException {
        String content =getResult(response);
        final Type type = ParameterizedTypeImpl.get(BaseResponseBean.class,List.class,mType);
        BaseResponseBean<List<T>> data = GsonUtil.getObject(content,type);
//        if(data == null){
//            throw new ParseException(String.valueOf(data.getCode()),data.getInfo(),response);
//        }
        if(data.getCode()!=0){
            throw new ParseException(String.valueOf(data.getCode()),data.getInfo(),response);
        }
        List<T> t = data.getData();
        if(t == null)
        {
            throw new ParseException(String.valueOf(data.getCode()),data.getInfo(),response);
        }
        return t;
    }

//    @Override
//    public String getResult(Response response) throws IOException {
//        return null;
//    }
}
