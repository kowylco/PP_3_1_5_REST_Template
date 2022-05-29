package kata.kowylco.rest.template;

import kata.kowylco.rest.template.config.MyConfig;
import kata.kowylco.rest.template.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

        System.out.println(communication.getAllUsers());


        User userToAdd = new User(3L, "James", "Brown", (byte) 23);
        String responseFromAdd = communication.saveUser(userToAdd);
        System.out.println(responseFromAdd);

        User userToEdit = new User(3L, "Thomas", "Shelby", (byte) 23);
        String responseFromEdit = communication.editUser(userToEdit);
        System.out.println(responseFromEdit);

        String responseFromDelete = communication.deleteUser(userToEdit);
        System.out.println(responseFromDelete);

        System.out.println(responseFromAdd + responseFromEdit + responseFromDelete);
    }
}
