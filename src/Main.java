import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path;
        double salary;

        System.out.print("Enter full file path: ");
        path = sc.nextLine();

        System.out.print("Enter salary: ");
        salary = sc.nextDouble();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            List<Employee> list = new ArrayList<>();
            String line = br.readLine();


            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            List<String> email = list.stream()
                    .filter(p -> p.getSalary() > salary)
                    .map(Employee::getEmail).sorted()
                    .toList();

            double sumSalary = list.stream()
                    .filter(x -> x.getName().charAt(0) == 'M')
                    .map(Employee::getSalary)
                    .reduce(0.0, Double::sum);

            System.out.printf("Email of people whose salary is more than %.2f:\n", salary);
            email.forEach(System.out::println);

            System.out.printf("Sum of salary of people whose name starts with 'M': %.2f\n", sumSalary);


        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}