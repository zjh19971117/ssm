package factory;

public class Factory {
    public static <T> T getImpl(Class clazz){
        try {
            return (T)clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
