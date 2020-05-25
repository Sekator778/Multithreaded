package executeservice;

import org.junit.Test;

public class EmailNotificationTest {
    @Test
    public void whenTest() {
        User user = new User("Bob", "Important letter");
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(user);
        emailNotification.close();
    }
}