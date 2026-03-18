package com.api.utils;

import java.util.Iterator;

import com.api.request.model.CreateJobPayload;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class Demo {

	public static void main(String[] args) {
		Iterator<UserBean> userBean = CSVReaderUtil.loadCSV("testData/LoginCreds.csv", UserBean.class);
		
		while(userBean.hasNext()) {
		    System.out.println(userBean.next());
		}
		
		Iterator<CreateJobBean> createJobIterator = CSVReaderUtil.loadCSV("testData/CreateJobData.csv", CreateJobBean.class);
		while(createJobIterator.hasNext()) {
			CreateJobBean tempBean = createJobIterator.next();
			CreateJobPayload tempPayload =  CreateJobBeanMapper.mapper(tempBean);
			System.out.println(tempPayload);
		}
		
		
	}

}
