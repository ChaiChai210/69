//package com.colin.playerdemo.net;
//
//import com.colin.playerdemo.utils.GsonUtils;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.IOException;
//
//import okhttp3.Response;
//import rxhttp.wrapper.parse.Parser;
//
//public class CommonParser<T> implements Parser<BaseBean<T>> {
//
//    private TypeToken mTypeToken;
//
//    public CommonParser(TypeToken typeToken) {
//        mTypeToken = typeToken;
//    }
//
//
//    @Override
//    public BaseBean<T> onParse(Response response) throws IOException {
//        String content = getResult(response); //从Response中取出Http执行结果
//        return GsonUtils.GsonToBean(content, mTypeToken.getType());
//    }
//}
