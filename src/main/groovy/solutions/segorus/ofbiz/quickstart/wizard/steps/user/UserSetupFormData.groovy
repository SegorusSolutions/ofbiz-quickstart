package solutions.segorus.ofbiz.quickstart.wizard.steps.user

class UserSetupFormData {
    private String name;
    private String password;
    private String confirmPassword;

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    String getConfirmPassword() {
        return confirmPassword
    }

    void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword
    }
}
