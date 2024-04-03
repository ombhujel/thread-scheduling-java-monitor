import java.util.Random;
import java.util.Vector;
class Monitor{
   int t1 = 0;
   int t2 = 0;
   int t3 = 0;
   int t4 = 0;
   int t5 = 0;
    int fr = 20;
    int counter; int counter3;int groups;int groupss;
    int count1;int count2;int count3;int count4;int count5;
    boolean lecture=true;boolean g = true;
    boolean firstround=true;
    boolean fr1 = true;
    boolean fr2 = true;
    boolean fr3 = true;
    boolean fr4 = true;
    boolean fr5 = true;
    boolean diffround1 = true;
    boolean diffround2 = true;
    boolean diffround3 = true;
    boolean diffround4 = true;
    boolean diffround5 = true;
    Vector<Object> groupforcandies = new Vector<Object>();
    Vector<Object> group1 = new Vector<Object>();Vector<Object> group2 = new Vector<Object>();
    Vector<Object> group3 = new Vector<Object>();Vector<Object> group4 = new Vector<Object>();
    Vector<Object> group5 = new Vector<Object>();
    Object g1 = new Object();Object g2 = new Object();Object g3 = new Object();
    Object g4 = new Object();Object g5 = new Object();
    Object first_round = new Object();
    //Count the total number of students arrival
    public synchronized void studentarrrival(){this.counter++;}
    //Method for teacher to wait for student arrival
    public synchronized void waitforlecture(){if(lecture == false){try {this.wait();} catch (InterruptedException e) {};}}
    //Method for teacher to deliver lecture
    public synchronized void teacherlecture(){
        lecture = false;
        while(counter != 20){
            try {this.wait(1000);} catch (InterruptedException e){};}
            System.out.println("No. of students arrrived is " + counter);
            System.out.println("Teacher begin lecture.");
            System.out.println("Lecture finished..");
            lecture = true;
            this.notifyAll();
        
    }
    //Students waits on object convey for teacher to give them bag of candies
    public void waitforcandies() {
        Object convey = new Object();
        synchronized (convey) {if (waitingforcandies(convey))while (true)try { convey.wait(); break; }
                 catch (InterruptedException e) { continue; }}}
  
     private synchronized boolean waitingforcandies(Object convey) {
        boolean status;
        if (groupforcandies.size() < 20) {groupforcandies.addElement(convey);status = true;}
        else {groupss++; status = false;}
        return status;
     }
     //Teacher uses this method to destribute candies to students in the order they arrived
     public synchronized void candies_distribution(){
        while(groupforcandies.size() != 20){try {this.wait(1000);} catch (InterruptedException e){};}
      if (groupforcandies.size() == 20) {
         for(int j =0; j<20; j++){synchronized (groupforcandies.elementAt(0)) {groupforcandies.elementAt(0).notify();}
         groupforcandies.removeElementAt(0);
         }
      }
     }
     //Uses different objects for students to block
     //counter3 is to keep track of total number of students blocked so far
     //specific counter is to keep track of size per group
     //Synchronized method used to block on the specific object
     public void choosegroup(int x){
        while (g == true){
            Random rand = new Random();
            int n = rand.nextInt((5 - 1) + 1) + 1;
            g = false;
            if(n == 1 && count1<4){counter3++;count1++; System.out.println("Student " + x + " theme matches with group 1");synchronized(g1){if(waitingfoor(g1))while(true)try {g1.wait(); break; }catch (InterruptedException e){ continue; } }
               //while(diffround1 = true){try {this.wait(1000);} catch (InterruptedException e){};}
                 }
            else if(n == 2 && count2<4){counter3++; count2++; System.out.println("Student " + x + " theme matches with group 2");synchronized(g2){if(waitingfoor2(g2))while(true)try {g2.wait(); break; }catch (InterruptedException e){ continue; } } }
            else if(n == 3 && count3<4){counter3++;count3++; System.out.println("Student " + x + " theme matches with group 3");synchronized(g3){if(waitingfoor3(g3))while(true)try {g3.wait(); break; }catch (InterruptedException e){ continue; } } }
            else if(n == 4 && count4<4){counter3++;count4++; System.out.println("Student " + x + " theme matches with group 4");synchronized(g4){if(waitingfoor4(g4))while(true)try {g4.wait(); break; }catch (InterruptedException e){ continue; } } }
            else if(n == 5 && count5<4){counter3++;count5++; System.out.println("Student " + x + " theme matches with group 5");synchronized(g5){if(waitingfoor5(g5))while(true)try {g5.wait(); break; }catch (InterruptedException e){ continue; } } }
            else g = true;
        }
        Random rand1 = new Random();
        int o = rand1.nextInt((10 - 1) + 1) + 1;
        System.out.println("Student " + x + " got "+o+" candies in first round");
        try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
     }
     private synchronized boolean waitingfoor2(Object convey) { boolean status;if (group2.size()<=4) {group2.addElement(convey);status = true;}
        else {groupss++; status = false;}return status;}
     private synchronized boolean waitingfoor3(Object convey) {boolean status;if (group3.size()<=4) {group3.addElement(convey);status = true;}
        else {groupss++; status = false;}return status;}
     private synchronized boolean waitingfoor4(Object convey) {boolean status;if (group4.size()<=4) {group4.addElement(convey);status = true;}
        else {groupss++; status = false;}return status;}
     private synchronized boolean waitingfoor5(Object convey) { boolean status;if (group5.size()<=4) {group5.addElement(convey);status = true;}
        else {groupss++; status = false;}return status;}
     private synchronized boolean waitingfoor(Object convey) {boolean status;if (group1.size()<=4) {group1.addElement(convey);status = true;}
        else {groupss++; status = false;}return status; }
     //Method for Houses to call first group and distribute candies.
     //First while loop is to hold the house threads until all the students threads are blocked in group objects.
     public synchronized void firstround(int i){
        //System.out.println("First thread check of Thread" + i);
        while(group1.size()<4 && group2.size()<4 && group3.size()<4 && group4.size()<4 && group5.size()<4){try {this.wait(1000);} catch (InterruptedException e){};}
        //System.out.println("2nd thread check of Thread" + i);
        while(fr>0 ){
            Random rand = new Random();
            int n = rand.nextInt((5 - 1) + 1) + 1;
            //System.out.println("3rd thread check of Thread" + i);
            //System.out.println(n);
            if(n==1 && fr1==true){ fr1=false; t1=n; if(group1.size()==4){for(int j = 0; j<4; j++){synchronized(group1.elementAt(0)){group1.elementAt(0).notify();}group1.removeElementAt(0);} fr=fr-4; diffround1 = false; break;}}
            else if(n==2 && fr2==true){fr2=false; t1=n; if(group2.size()==4){for(int j = 0; j<4; j++){synchronized(group2.elementAt(0)){group2.elementAt(0).notify();}group2.removeElementAt(0);} fr=fr-4; diffround2 = false; break;}}
            else if(n==3 && fr3==true){fr3=false; t1=n; if(group3.size()==4){for(int j = 0; j<4; j++){synchronized(group3.elementAt(0)){group3.elementAt(0).notify();}group3.removeElementAt(0);} fr=fr-4; diffround3 = false; break;}}
            else if(n==4 && fr4==true){fr4=false; t1=n; if(group4.size()==4){for(int j = 0; j<4; j++){synchronized(group4.elementAt(0)){group4.elementAt(0).notify();}group4.removeElementAt(0);} fr=fr-4; diffround4 = false; break;}}
            else if(n==5 && fr5==true){fr5=false; t1=n; if(group5.size()==4){for(int j = 0; j<4; j++){synchronized(group5.elementAt(0)){group5.elementAt(0).notify();}group5.removeElementAt(0);} fr=fr-4; diffround5 = false; break;}}
        }
     }
     public synchronized void secondround(int i){
        //System.out.println("First thread check of Thread" + i);
        while(group1.size()<4 && group2.size()<4 && group3.size()<4 && group4.size()<4 && group5.size()<4){try {this.wait(1000);} catch (InterruptedException e){};}
        //System.out.println("2nd thread check of Thread" + i);
        while(fr>0 ){
            Random rand = new Random();
            int n1 = rand.nextInt((5 - 1) + 1) + 1;
            //System.out.println("3rd thread check of Thread" + i);
            //System.out.println(n);
            if(n1==1 && n1!=t1 && fr1==true){ fr1=false; if(group1.size()==4){for(int j = 0; j<4; j++){synchronized(group1.elementAt(0)){group1.elementAt(0).notify();}group1.removeElementAt(0);}  diffround1 = false; break;}}
            else if(n1==2 && n1!=t1 && fr2==true){fr2=false; if(group2.size()==4){for(int j = 0; j<4; j++){synchronized(group2.elementAt(0)){group2.elementAt(0).notify();}group2.removeElementAt(0);}  diffround2 = false; break;}}
            else if(n1==3 && fr3==true){fr3=false; if(group3.size()==4){for(int j = 0; j<4; j++){synchronized(group3.elementAt(0)){group3.elementAt(0).notify();}group3.removeElementAt(0);}  diffround3 = false; break;}}
            else if(n1==4 && fr4==true){fr4=false; if(group4.size()==4){for(int j = 0; j<4; j++){synchronized(group4.elementAt(0)){group4.elementAt(0).notify();}group4.removeElementAt(0);}  diffround4 = false; break;}}
            else if(n1==5 && fr5==true){fr5=false; if(group5.size()==4){for(int j = 0; j<4; j++){synchronized(group5.elementAt(0)){group5.elementAt(0).notify();}group5.removeElementAt(0);}  diffround5 = false; break;}}
        }
     }
}