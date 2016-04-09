package OnlineMealOrder;

import java.io.*;
import java.util.*;

import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.persistence.*;

public class UserDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("SpoonFork");
	EntityManager em = null;

	public User findUserbyPassword(String username, String password) {

		em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("findUserbyPassword");
		query.setParameter("username", username);
		User user = (User)query.getSingleResult();
		em.getTransaction().commit();
		em.close();
		
		if (user == null || !user.getPassword().equals(password))
			return null;
		else
			return user;
	}
}
