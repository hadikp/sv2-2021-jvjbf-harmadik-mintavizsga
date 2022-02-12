package emailservice;

import java.util.HashSet;
import java.util.Set;

public class EmailService {

    private Set<User> users = new HashSet<>();

    public void registerUser(String emailAddress) {
        //if (emailAddress.contains("^?@"))
        for (User u: users) {
            if (u.getEmailAddress().equals(emailAddress)) {
                throw new IllegalArgumentException("Email address is already taken!");
            }
        }
        users.add(new User(emailAddress));
    }

    public void sendEmail(String from, String to, String subject, String content) {
        for (User u: users) {
            if(u.getIncoming().contains(from)) {
                if (u.getSent().contains(to)) {
                    //u.sendEmail(subject, content, to.);
                }
            }
        }
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
