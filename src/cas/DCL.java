package cas;

public class DCL {
    private static /*volatile*/ DCL INSTANCE;

    private DCL() {}

    public static DCL getInstance() {
        if (INSTANCE == null) {
            Thread.yield();
            synchronized (DCL.class) {
                if (INSTANCE == null) {
                    System.out.println("执行");
                    INSTANCE = new DCL();
                }
            }
        }

        return INSTANCE;
    }
}
