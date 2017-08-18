package com.trumpia.account.data;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.trumpia.data.UserRepository;
import com.trumpia.model.UserEntity;
import com.trumpia.trumpia.data.TrumpiaAccountRepository;
import com.trumpia.trumpia.data.TrumpiaRepositoryConfig;
import com.trumpia.trumpia.model.TrumpiaAccountEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TrumpiaRepositoryConfig.class})
@ActiveProfiles("dev")
public class TestTrumpiaAccountRepository {
	
	@Autowired 
	private UserRepository userRepo;
	
	@Autowired 
	private TrumpiaAccountRepository trumRepo;
	
	@Autowired
	public void setUserRepository(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Autowired
	public void setTrumpiaAccountRepository(TrumpiaAccountRepository trumRepo) {
		this.trumRepo = trumRepo;
	}

	private UserEntity userEntity;

	@Before
	public void setUp() {

	}

	@Test
	public void test() {
		//create
		userEntity = new UserEntity();
		userEntity.setEmail("test@mytrum.com");
		userEntity.setUsername("test");
		userEntity.setUniqueId("test");
		userEntity.setPassword("test");
		userEntity.setUpdatedDate(new Date());
		userRepo.save(userEntity);

		TrumpiaAccountEntity trumEntity = new TrumpiaAccountEntity();
		trumEntity.setUniqueId("trumtest");
		trumEntity.setAPIkey("trumtest");
		trumEntity.setUserEntity(userEntity);
		
		
		//Save
		
		assertNull(trumEntity.getId());
		trumRepo.save(trumEntity);
		assertNotNull(trumEntity.getId());

		//Fetch
		TrumpiaAccountEntity fetchedEntity = trumRepo.findOne(trumEntity.getId());
		assertEquals(trumEntity.getId(), fetchedEntity.getId());

		//Update
		fetchedEntity.setAPIkey("updated");
		trumRepo.save(fetchedEntity);
		TrumpiaAccountEntity fetchedAndUpdatedEntity = trumRepo.findOne(fetchedEntity.getId());
		assertEquals(fetchedEntity.getAPIkey(), fetchedAndUpdatedEntity.getAPIkey());

		//Delete
		trumRepo.delete(fetchedEntity);
		assertNull(trumRepo.findOne(fetchedEntity.getId()));
	}
}

