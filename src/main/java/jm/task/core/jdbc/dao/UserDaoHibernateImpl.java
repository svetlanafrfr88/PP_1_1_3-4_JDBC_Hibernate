package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collections;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users(" +
                    "id SERIAL PRIMARY KEY ," +
                    " name VARCHAR(50)," +
                    " lastName VARCHAR(50)," +
                    " age INT)").executeUpdate();
            session.getTransaction().commit();
        }
        System.out.println("База данных создана");

    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("База данных удалена");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void removeUserById(long id) {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM users WHERE id = ?");
            session.getTransaction().commit();
            System.out.println("User с id - " + id + "удален из базы данных");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List <User> userList = Collections.emptyList();
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            userList = session.createQuery("from User").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
