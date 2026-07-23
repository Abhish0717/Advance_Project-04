package com.sunilos.p4.ctl;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sunilos.p4.bean.BaseBean;
import com.sunilos.p4.model.BaseModel;
import com.sunilos.p4.util.DataUtility;
import com.sunilos.p4.util.DataValidator;
import com.sunilos.p4.util.MessageSource;
import com.sunilos.p4.util.PropertyReader;
import com.sunilos.p4.util.ServletUtility;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Base list controller class is extended by all the list pages in the
 * application. It contains search and pagination operation of a list page.
 * 
 * 
 * @author Abhishish Bhawsar
 * @version 1.0
 * @Copyright (c) Rays EdTech
 */
public abstract class BaseListCtl<B extends BaseBean, M extends BaseModel> extends BaseCtl<B, M> {

	private static Logger log = Logger.getLogger(BaseListCtl.class);

	/**
	 * It does call doPost method
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * It contains search and pagination operations os a list page.
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("List toPost started");
		MessageSource ms = getMessageSource(request);
		// Get the operation
		String op = DataUtility.getString(request.getParameter("operation"));

		// Create model
		M model = getModel();

		// Delete selected records
		if (OP_DELETE.equals(op)) {
			String[] ids = request.getParameterValues("ids");
			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					model.delete(DataUtility.getInt(id));
					ServletUtility.setSuccessMessage(ms.get("business.deleted"), request);
				}
			} else {
				ServletUtility.setErrorMessage(ms.get("business.select.alor"), request);
			}
		}

		// Get page number from request
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));

		// Get pagesize from request
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		// If page number is 0 then make it first page
		pageNo = (pageNo == 0) ? 1 : pageNo;

		// if page size is zero then get it from properties file
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		// Get the data from request object and set into bean object
		BaseBean bean = populateBean(request);

		// Calculate page number
		if (DataValidator.isNotNull(op)) {

			switch (op) {
			case OP_SEARCH:
				pageNo = 1;
				break;
			case OP_NEXT:
				pageNo++;
				break;
			case OP_PREVIOUS:
				if (pageNo > 1)
					pageNo--;
				break;
			}
		}

		// Search and get the list from model object
		List<B> list = model.search(bean, pageNo, pageSize);

		// Get next page list to check if next page exists
		List<B> nextList = model.search(bean, pageNo + 1, pageSize);

		// Store list into request object
		ServletUtility.setList(list, request);

		// Store next list into request object for pagination control
		request.setAttribute("nextList", nextList);

		// If list is empty then set error message
		if (list == null || list.size() == 0) {
			ServletUtility.setErrorMessage(ms.get("no.record"), request);
		}

		// Set page number and page size in the request object
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);

		// Forward to view page
		ServletUtility.forwardPage(getView(op), request, response);

		log.debug("List do post finish");

	}

}
