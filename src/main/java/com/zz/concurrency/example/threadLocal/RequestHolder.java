package com.zz.concurrency.example.threadLocal;

/**
 * ThreadLocal
 *
 */
public class RequestHolder  {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    //在请求进入后端服务器还没处理的时候 add，可以使用filter
    public static  void  add(Long id){
        //key是当前线程的id
        requestHolder.set(id);
    }

    public static Long getId(){
        //传入当前线程的id，去取出之前存的Long
        return requestHolder.get();
    }


    //️在接口处理完之后处理，用 interceptor
    public static void remove(){
        //remove当前线程
        requestHolder.remove();
    }
}
