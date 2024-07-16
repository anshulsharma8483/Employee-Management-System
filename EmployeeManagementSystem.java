import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem {
    private List<Employee> employees;
    private Scanner scanner;

    public EmployeeManagementSystem() {
        employees = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    removeEmployee();
                    break;
                case 4:
                    listEmployees();
                    break;
                case 5:
                    calculatePayroll();
                    break;
                case 6:
                    markAttendance();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n===== Employee Management System Menu =====");
        System.out.println("1. Add new employee");
        System.out.println("2. Update employee details");
        System.out.println("3. Remove employee");
        System.out.println("4. List all employees");
        System.out.println("5. Calculate payroll");
        System.out.println("6. Mark attendance");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addEmployee() {
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter employee department: ");
        String department = scanner.nextLine();

        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        Employee newEmployee = new Employee(id, name, department, salary);
        employees.add(newEmployee);
        System.out.println("Employee added successfully.");
    }

    private void updateEmployee() {
        listEmployees();
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Employee foundEmployee = findEmployeeById(id);
        if (foundEmployee != null) {
            System.out.print("Enter new name (leave blank to keep current): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                foundEmployee.setName(newName);
            }

            System.out.print("Enter new department (leave blank to keep current): ");
            String newDepartment = scanner.nextLine();
            if (!newDepartment.isEmpty()) {
                foundEmployee.setDepartment(newDepartment);
            }

            System.out.print("Enter new salary (leave blank to keep current): ");
            String newSalaryStr = scanner.nextLine();
            if (!newSalaryStr.isEmpty()) {
                try {
                    double newSalary = Double.parseDouble(newSalaryStr);
                    foundEmployee.setSalary(newSalary);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid salary format. Salary not updated.");
                }
            }

            System.out.println("Employee details updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void removeEmployee() {
        listEmployees();
        System.out.print("Enter employee ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Employee foundEmployee = findEmployeeById(id);
        if (foundEmployee != null) {
            employees.remove(foundEmployee);
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void listEmployees() {
        System.out.println("\n===== Employees List =====");
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    private void calculatePayroll() {
        double totalPayroll = 0;
        for (Employee employee : employees) {
            totalPayroll += employee.getSalary();
        }
        System.out.println("\nTotal Payroll: $" + totalPayroll);
    }

    private void markAttendance() {
        listEmployees();
        System.out.print("Enter employee ID to mark attendance: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Employee foundEmployee = findEmployeeById(id);
        if (foundEmployee != null) {
            System.out.println("Attendance marked for employee: " + foundEmployee.getName());
            // Here you can implement attendance marking logic as per your requirements
        } else {
            System.out.println("Employee not found.");
        }
    }

    private Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        ems.start();
    }
}
