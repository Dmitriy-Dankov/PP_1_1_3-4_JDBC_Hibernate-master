package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;
import org.hibernate.Session;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {
        final String query = "CREATE TABLE IF NOT EXISTS users "
                .concat("(id INTEGER UNSIGNED not NULL AUTO_INCREMENT, ")
                .concat("name VARCHAR(16) not NULL, ")
                .concat("last_name VARCHAR (16) not NULL, ")
                .concat("age INTEGER not NULL, ")
                .concat("PRIMARY KEY (id))");
        
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery(query, User.class).executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users", User.class).executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()){            
            session.beginTransaction();            
            session.persist(new User(name, lastName, age));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
                session.flush();
            }                      
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createMutationQuery("DELETE User").executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
