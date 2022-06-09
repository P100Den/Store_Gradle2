package clients;

public class Clients {

    private String name;
    private String email;
    private String password;
    private double authenticationNumber;

    public Clients(String name, String email, String password, double authenticationNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.authenticationNumber = authenticationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAuthenticationNumber() {
        return authenticationNumber;
    }

    public void setAuthenticationNumber(double authenticationNumber) {
        this.authenticationNumber = authenticationNumber;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", authenticationNumber=" + authenticationNumber +
                '}';
    }
}
