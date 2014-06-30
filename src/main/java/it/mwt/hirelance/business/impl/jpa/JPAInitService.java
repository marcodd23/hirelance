package it.mwt.hirelance.business.impl.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.mwt.hirelance.business.BusinessException;
import it.mwt.hirelance.business.InitService;
import it.mwt.hirelance.business.model.Category;
//import it.mwt.hirelance.business.model.MainCategory;
import it.mwt.hirelance.business.model.Role;
import it.mwt.hirelance.business.model.Skill;
//import it.mwt.hirelance.business.model.SubCategory;
import it.mwt.hirelance.business.model.User;
import it.mwt.hirelance.common.util.MD5Hash;

@Service
public class JPAInitService implements InitService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void populate() throws BusinessException {
		Role admin = new Role();
		admin.setRoleID(1);
		admin.setName("admin");
		Role user = new Role();
		user.setRoleID(2);
		user.setName("user");
		em.persist(admin);
		em.persist(user);
		User uAdmin = new User();
		uAdmin.setName("admin_name");
		uAdmin.setSurname("admin_surname");
		uAdmin.setUsername("admin");
		uAdmin.setEmail("admin@admin.admin");
		uAdmin.setPassword(MD5Hash.md5("admin"));
		uAdmin.setCreationDate(new Date());
		uAdmin.setRole(admin);
		em.persist(uAdmin);
		User user1=new User("utente1",MD5Hash.md5("utente1"),"Utente 1","Utente 1","danielesimonetti@msn.com",new Date(),user);
		User user2=new User("utente2",MD5Hash.md5("utente2"),"Utente 2","Utente 2","marco.dd23@gmail.it",new Date(),user);
		em.persist(user1);em.persist(user2);
		Category mc = new Category();
		Category sc = new Category();
		Skill s = new Skill();
		
		mc.setName("IT & Programming");em.persist(mc);
		sc.setName("Web programming");sc.setParentCategory(mc);em.persist(sc);		
		sc=new Category();sc.setName("Database development");sc.setParentCategory(mc);em.persist(sc);
		sc=new Category();sc.setName("Software Application");sc.setParentCategory(mc);em.persist(sc);
		sc=new Category();sc.setName("Search engine optimization");sc.setParentCategory(mc);em.persist(sc);
		s=new Skill();s.setName("Css");s.setMainCategory(mc);em.persist(s);
		s=new Skill();s.setName("Html");s.setMainCategory(mc);em.persist(s);
		s=new Skill();s.setName("javascript");s.setMainCategory(mc);em.persist(s);
		s=new Skill();s.setName(".Net");s.setMainCategory(mc);em.persist(s);
		s=new Skill();s.setName("Php");s.setMainCategory(mc);em.persist(s);
		
		mc=new Category();mc.setName("Design & Multimedia");em.persist(mc);
		sc=new Category();sc.setName("Graphic design");sc.setParentCategory(mc);em.persist(sc);
		sc=new Category();sc.setName("Videos");sc.setParentCategory(mc);em.persist(sc);
		sc=new Category();sc.setName("Photography");sc.setParentCategory(mc);em.persist(sc);
		s=new Skill();s.setName("Adobe Photoshot");s.setMainCategory(mc);em.persist(s);
		s=new Skill();s.setName("Adobe Illustrator");s.setMainCategory(mc);em.persist(s);
		s=new Skill();s.setName("Gimp");s.setMainCategory(mc);em.persist(s);
		s=new Skill();s.setName("Adobe flash");s.setMainCategory(mc);em.persist(s);
		
		mc=new Category();mc.setName("Engineering");em.persist(mc);
		sc=new Category();sc.setName("3D Modeling");sc.setParentCategory(mc);em.persist(sc);
		sc=new Category();sc.setName("CAD");sc.setParentCategory(mc);em.persist(sc);
		sc=new Category();sc.setName("Product design");sc.setParentCategory(mc);em.persist(sc);
		s=new Skill();s.setName("Python");s.setMainCategory(mc);em.persist(s);
		s=new Skill();s.setName("MatLab");s.setMainCategory(mc);em.persist(s);
		s=new Skill();s.setName("Uml");s.setMainCategory(mc);em.persist(s);
		s=new Skill();s.setName("AutoCAD");s.setMainCategory(mc);em.persist(s);

	}

}
