package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        //Юзеры
        User user1 = new User("UserName1", "UserLastName1",
                "UserName1Mail@gmail.com");
        User user2 = new User("UserName2", "UserLastName2",
                "UserName2Mail@gmail.com");
        User user3 = new User("UserName3", "UserLastName3",
                "UserName3Mail@gmail.com");

        User user4 = new User("UserName4", "UserLastName4",
                "UserName4Mail@gmail.com");

        //добавляем в бд
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        //получаем листЮзер из бд и смотрим

        List<User> getListUsers = userService.listUsers();
        System.out.println("----------------------------------------------");
        getListUsers.forEach(System.out::println);
        System.out.println("----------------------------------------------");

        //Кар
        Car car1 = new Car("Car1", 1000);
        Car car2 = new Car("Car2", 2000);
        Car car3 = new Car("Car3", 3000);
        Car car4 = new Car("Car4", 4000);

        //добавляем в бд
        userService.addCar(car1);
        userService.addCar(car2);
        userService.addCar(car3);
        userService.addCar(car4);

        //получаем листКар из бд и смотрим
        List<Car> getListCars = userService.listCars();
        System.out.println("----------------------------------------------");
        getListCars.forEach(System.out::println);
        System.out.println("----------------------------------------------");

        //даем юзеру по кар и обновляем -> car.setUser(this); прописан у юзера
        user1.setCar(car1);
        userService.updateUser(user1);
        user2.setCar(car2);
        userService.updateUser(user2);
        user3.setCar(car3);
        userService.updateUser(user3);
        user4.setCar(car4);
        userService.updateUser(user4);

        //получаем списки юзеров и кар и смотрим
        List<User> getListUsers2 = userService.listUsers();
        List<Car> getListCars2 = userService.listCars();

        System.out.println("----------------------------------------------");
        getListUsers2.forEach(System.out::println);
        getListCars2.forEach(System.out::println);
        System.out.println("----------------------------------------------");


        //удаляем юзера и его кар, удаляем кар и смотрим списки
        userService.deleteUser(user1);
        userService.deleteCar(car2);

        getListUsers2 = userService.listUsers();
        getListCars2 = userService.listCars();
        System.out.println("//////////////////////////////////////////");
        getListUsers2.forEach(System.out::println);
        getListCars2.forEach(System.out::println);
        System.out.println("//////////////////////////////////////////");

        context.close();
    }
}
