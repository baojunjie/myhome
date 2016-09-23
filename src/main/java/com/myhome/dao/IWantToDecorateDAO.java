package com.myhome.dao;

import com.myhome.entity.WantToDecorate;

public interface IWantToDecorateDAO extends IDAO{
    public WantToDecorate get(Long id);

	public void addMobileWantToDecorate(WantToDecorate wantToDecorate) throws Exception;

    public boolean getWantToDecorateByUser(Long id) throws Exception;
}
