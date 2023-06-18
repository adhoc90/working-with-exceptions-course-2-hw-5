import ru.skypro.exception.WrongLoginException;
import ru.skypro.exception.WrongPasswordException;

public class Main {

    private static final String VALIDATE_PATTERN = "^[a-zA-Z0-9_]+$";

    public static void main(String[] args) {
        validate("login", "pass", "pass");
        validate("loginnnnnnnnnnnnnnnnnnnnn", "pass", "pass");
        validate("login", "passsssssssssssssssssssssss", "pass");
        validate("login%&%&%&%&%&", "pass", "pass");
        validate("login", "pass2", "pass");
    }


    public static boolean validate(String login, String password, String confirmPassword) {
        boolean isValid = true;
        try {
            validateLogin(login);
            validatePassword(password, confirmPassword);

        } catch (WrongLoginException e) {
            System.out.println("Ошибка с введённым логином: " + e.getMessage());
            isValid = false;

        } catch (WrongPasswordException e) {
            System.out.println("Ошибка с введённым паролем: " + e.getMessage());
            isValid = false;
        }

        if (isValid) {
            System.out.println("Логин и пароль корректные. ");
        }
        return isValid;
    }

    public static void validateLogin(String login) throws WrongLoginException {
        if (!login.matches(VALIDATE_PATTERN)) {
            throw new WrongLoginException("Логин может содержать в себе только латинские буквы, цифры и знак подчеркивания.");
        }
        if (login.length() > 20) {
            throw new WrongLoginException("Логин не может быть длиннее двадцати символов.");
        }
    }

    public static void validatePassword(String password, String confirmPassword) throws WrongPasswordException {
        if (!password.matches(VALIDATE_PATTERN)) {
            throw new WrongPasswordException("Логин содержит в себе только латинские буквы, цифры и знак подчеркивания.");
        }
        if (password.length() > 20) {
            throw new WrongPasswordException("Пароль не может содержать более двадцати символов.");
        }

        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли не совпадают.");
        }
    }
}