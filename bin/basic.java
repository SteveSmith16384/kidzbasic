import ssmith.io.EasyIn;
public class basic { 

public basic() { 
double a = 1;
double num_wanted = 5;
while (a != num_wanted) {
System.out.println("Enter the number " + num_wanted);
a = EasyIn.readDouble();
System.out.println(a);
}
System.out.println("Thank you");

}

}
