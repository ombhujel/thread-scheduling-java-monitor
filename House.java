public class House extends Thread {
    private Monitor md;
    private int num;
    public House( Monitor md, int num){
        this.md = md;
        this.num = num;
    }
    public void run(){
        md.firstround(num);
        //md.secondround();
        //md.thirdround();
        //md.fourthround();
    }
    
}

