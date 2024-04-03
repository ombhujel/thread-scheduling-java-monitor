public class Teacher extends Thread {
    private Monitor md;
    public Teacher( Monitor md){
        this.md = md;
    }
    public synchronized void run(){
        System.out.println("Teacher waiting for all student to arrive..");
        md.teacherlecture();
        md.candies_distribution();
        System.out.println("Teacher finished its job and terminates.....");
    }
}
