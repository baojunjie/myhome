

package com.myhome.dao;

import java.util.Map;

/**
 * gwb
 * ClassName:IVersionDao <br/>
 * Date:     2015年9月21日 下午7:18:09 <br/>
 * @see 	 
 */
public interface IVersionDao extends IDAO{

	public	Map<String,String>  getVersionMobile() throws Exception;

}

