package emailservice;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class EmailService {

    private Set<User> users = new LinkedHashSet<>();

    public void registerUser(String emailAddress) {
        isEmailTaken(emailAddress);
        isUpperCase(emailAddress);
        if (!emailAddress.contains("@") || !emailAddress.contains(".")) {
            throw new IllegalArgumentException("Email address is not valid: " + emailAddress);
        } else {
            String[] emailSplit = emailAddress.split("@");
            if (emailSplit[0].isBlank() || emailSplit[1].startsWith(".")) {
                throw new IllegalArgumentException("Email address is not valid: " + emailAddress);
            } else {
                users.add(new User(emailAddress));
            }
        }
    }

    private void isUpperCase(String emailAddress) {
        for (int i = 0; i < emailAddress.length(); i++) {
            if (Character.isUpperCase(emailAddress.charAt(i))) {
                throw new IllegalArgumentException("Email address is not valid: " + emailAddress);
            }
        }
    }

    private void isEmailTaken(String emailAddress) {
        for (User u: users) {
            if (u.getEmailAddress().equals(emailAddress)) {
                throw new IllegalArgumentException("Email address is already taken!");
            }
        }
    }

    public void sendEmail(String from, String to, String subject, String content) {
        User fromUser = null;
        User toUser = null;
        for (User u: users) {
            if(u.getEmailAddress().equals(from)) {
                fromUser = u;
            }
            if(u.getEmailAddress().equals(to)) {
                toUser = u;
            }
        }
        fromUser.sendEmail(subject, content, toUser);
    }

    public void sendSpam(String subject, String content) {
        for (User u: users) {
            u.sendEmail(subject, content, u);
        }
    }

    public Set<User> getUsers() {
        return users;
    }
}
