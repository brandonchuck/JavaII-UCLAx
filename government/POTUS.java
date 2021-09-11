package government;

public class POTUS {
    private static POTUS potus_instance;

    private POTUS(){
        POTUS_count++;
        System.out.println("POTUS Count: " + POTUS_count);
    }

    private static int POTUS_count = 0; // this will be a maximum of 1

    public static POTUS getInstance(){
        if(potus_instance == null){
            potus_instance = new POTUS();
        }
        return potus_instance;
    }

    public static void impeach(){
        potus_instance = null;
        POTUS_count--;
        System.out.println("POTUS Count: " + POTUS_count);
    }

}