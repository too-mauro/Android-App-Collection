public class Employee {
    String name;
    String ID;
    double salary;
    String office;
    String extension;
    int yearsOfService;

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getID() {
        return ID;
    }

    public void setID(String i) {
        ID = i;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double s) {
        salary = s;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String o) {
        office = o;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String e) {
        extension = e;
    }

    public int getYearsOfService() {
        return yearsOfService;
    }

    public void setYearsOfService(int y) {
        yearsOfService = y;
    }
}