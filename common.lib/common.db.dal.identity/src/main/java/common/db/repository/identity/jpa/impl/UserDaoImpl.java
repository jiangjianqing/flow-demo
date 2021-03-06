package common.db.repository.identity.jpa.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import common.db.base.exception.DaoException;
import common.db.base.jpa.AbstractJpaDaoImpl;
import common.db.base.jpa.internal.JpaUtil;
import common.db.model.identity.Role;
import common.db.model.identity.User;
import common.db.repository.identity.UserDAO;

public class UserDaoImpl extends AbstractJpaDaoImpl<User,Long> implements UserDAO {

	public User findByUserName(String userName) {
		;
		//String jpql=String.format("select o from %s o where o.username=?",User.class.getSimpleName());
		//List<User> userList=paginationDao.queryForList(jpql,new Object[]{userName});
		Map<String,Object> sMap = new HashMap<String, Object>();  
		sMap.put("name", userName);  
		User ret=null;
		try {
			List<User> userList=paginationDao.queryForList(baseDao.createNamedQuery("User.findByName"),sMap);
			if (userList.size()!=1){
				if(userList.size()>1){
					throw new Exception("username存在重复");

				}
			}else{
				ret=userList.get(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ret;
		
	}

	private <T> boolean isArrayEqual(List<T> list,List<T> list2){
		return isArrayEqual(list.toArray(),list2.toArray());
	}

	private boolean isArrayEqual(Object[] objArray1,Object[] objArray2){
		if(objArray1==null && objArray2!=null)
			return false;
		if(objArray2==null && objArray1!=null)
			return false;
		if(objArray1.length!=objArray2.length)
			return false;

		boolean ret=true;
		for(Object obj:objArray1){
			for(Object obj2:objArray2){
				if (!obj.equals(obj2)){
					ret=false;
					break;
				}
			}
			if(ret==false)
				break;
		}
		return ret;
	}
/*
	public User update(User user) throws NoFieldChangedException, DaoException {
		User ret=baseDao.findByKey(user.getId());
		if(ret==null){
			return null;
		}
		List<String> diffFields=null;//ret.getDifferentFields(user);
		diffFields.remove("password");
		diffFields.remove("username");
		boolean testResult=isArrayEqual(ret.getRoles().toArray(),user.getRoles().toArray());
		System.out.println("isArrayEqual="+testResult);
		if(diffFields.size()>0 || !testResult){
			if(diffFields.size()>0){
				String[] array=new String[diffFields.size()];
				diffFields.toArray(array);
				//ret.copyAttributeValue(user, array);
			}			

			if(!testResult){
				if(user.getRoles().size()>0){

					Map<String, Object> params = new HashMap<String, Object>();
					params.put("ids", user.getRoles());
					String jqhl =JpaUtil.buildSelect(Role.class, null, "o");
					jqhl+= "where id in(:ids)";
					List<Role> roles=this.paginationDao.queryForList(jqhl, params);

					ret.setRoles(roles);
				}else{
					ret.getRoles().clear();
				}
			}

			baseDao.merge(ret);
		}else{
			throw new NoFieldChangedException("User",String.format("id=%s ,no field changed",user.getId()));
		}
		return ret;
	}
*/
}

