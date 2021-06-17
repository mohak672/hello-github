public class ConfigureThreadingUtil {
    public static void configureThreadPool(MyApplication app){
        int numberOfThreads = app.getNumberOfThreads();
    }
}

class MyApplication {
    public int getNumberOfThreads(){
        return 5;
    }
}


