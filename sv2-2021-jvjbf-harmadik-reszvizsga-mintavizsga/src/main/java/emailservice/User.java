package emailservice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User {

    private String emailAddress;
    private List<Email> incoming = new LinkedList<>();
    private List<Email> sent = new ArrayList<>();
    private boolean spamFilter;

    public User(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void getEmail(Email email) {
        if (this.spamFilter == true || this.emailAddress.contains("spam")) {
            throw new IllegalStateException("Be careful, tis is a spam!");
        }
        if (email.isImportant()) {
            incoming.add(0, email);
        } else {
            incoming.add(email);
        }
    }


    public void sendEmail(String subject, String content, User to) {
        Normal n = new Normal(this, to, subject, content);
        sent.add(n);
        to.getEmail(n);
    }

    public void spamFilterChange() {
        if (this.spamFilter == true) {
            this.spamFilter = false;
        } else {
            this.spamFilter = true;
        }
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public List<Email> getIncoming() {
        return incoming;
    }

    public List<Email> getSent() {
        return sent;
    }

    public boolean hasSpamFilter() {
        return spamFilter;
    }
}
