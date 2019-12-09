//package com.colin.playerdemo.net.rxjava;
//
//import com.colin.playerdemo.net.BaseBean;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//
//import okhttp3.Response;
//import rxhttp.wrapper.annotation.Parser;
//import rxhttp.wrapper.entity.ParameterizedTypeImpl;
//import rxhttp.wrapper.exception.ParseException;
//import rxhttp.wrapper.parse.AbstractParser;
//import rxhttp.wrapper.utils.GsonUtil;
//
//@Parser(name = "Response")
//public class ResponseParser<T> extends AbstractParser<T> {
//    protected ResponseParser() {
//        super();
//    }
//
//    public ResponseParser(Type type) {
//        super(type);
//    }
//
//    public static <T> ResponseParser<T> get(Class<T> type) {
//        return new ResponseParser<>(type);
//    }
//
//    @Override
//    public T onParse(Response response) throws IOException {
//        String content = getResult(response);
//        final Type type = ParameterizedTypeImpl.get(BaseBean.class, mType);
//        BaseBean<T> data = GsonUtil.fromJson(content, type);
////        if(data == null){
////            throw new ParseException(String.valueOf(data.getCode()),data.getInfo(),response);
////        }
//        if (data.getCode() != 0) {
//            throw new ParseException(String.valueOf(data.getCode()), data.getInfo(), response);
//        }
//        T t = data.getData();
//        if (t == null) {
//            if (mType == String.class) {
//                return (T) data.getInfo();
//            }
//            throw new ParseException(String.valueOf(data.getCode()), data.getInfo(), response);
//        }
//        return t;
//    }
//
////    @Override
////    public String getResult(Response response) throws IOException {
////        return null;
////    }
//}
