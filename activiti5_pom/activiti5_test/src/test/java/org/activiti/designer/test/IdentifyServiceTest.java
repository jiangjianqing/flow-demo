package org.activiti.designer.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class IdentifyServiceTest {

	private String filename = "/home/cz_jjq/java_workspace/activiti5-demo/activiti5_pom/activiti5_test/src/main/resources/diagrams/MyProcess.bpmn";

	private IdentityService identityService;
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
	@Before
	public void setUp() throws Exception {
		identityService=activitiRule.getIdentityService();
	}
	
	@Test
	public void testUser() throws Exception {
		User user=identityService.newUser("jjq");
		user.setFirstName("蒋");
		user.setLastName("建清");
		user.setEmail("cz_jjq@qq.com");
		
		identityService.saveUser(user);
		
		User userInDb=identityService.createUserQuery().userId("jjq").singleResult();
		assertNotNull(userInDb);
		identityService.deleteUser("jjq");
		System.out.println(String.format("新增用户信息，first name=%s，last name=%s",userInDb.getFirstName(),userInDb.getLastName()));
		userInDb=identityService.createUserQuery().userId("jjq").singleResult();
		assertNull(userInDb);
		System.out.println("User测试成功");
	}

	@Test
	public void testGroup() throws Exception{
		Group group=identityService.newGroup("deptLeader");
		group.setName("部门领导");
		group.setType("assignment");//1、assignment   2、security-role
		identityService.saveGroup(group);
		List<Group> groupList=identityService.createGroupQuery().groupId("deptLeader").list();
		assertEquals(1,groupList.size());
		System.out.println(String.format("新增Group信息，Group name=%s",groupList.get(0).getName()));
		identityService.deleteGroup("deptLeader");
		groupList=identityService.createGroupQuery().groupId("deptLeader").list();
		assertEquals(0,groupList.size());
		System.out.println("Group测试成功");
	}
}
