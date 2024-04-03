//import java.util.Vector;
public class Student extends Thread {
    private int number;
    private Monitor md;
    public Student(int number, Monitor md){
        this.number= number;
        this.md = md;
    }
    //Monitor md = new Monitor();
    public void run(){
        //Object obj = new Object();
        md.studentarrrival();
        System.out.println("Student " + number + " arrived to school " );
        md.waitforlecture();
        System.out.println("Student " + number + " lineup for candies. " );
        md.waitforcandies();
        md.choosegroup(number);


    }
    
}
