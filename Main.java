public class Main {
    
   public static void main(String[] args) {
    Monitor md = new Monitor();
    
    //Group group = new Group(md);
    //group.start();
    Teacher teacher = new Teacher(md);
    teacher.start();

    for(int i=1;i<=20;i++){
        Student abc = new Student(i, md);
        abc.start();
    }
    for(int i=1;i<=4;i++){
        House house = new House(md, i);
        house.start();
    }
   } 
}
