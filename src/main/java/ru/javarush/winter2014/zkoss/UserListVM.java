package ru.javarush.winter2014.zkoss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Center;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import ru.javarush.winter2014.domain.UserProfile;
import ru.javarush.winter2014.service.CRUDService;
import ru.javarush.winter2014.utilities.ConfirmResponse;
import ru.javarush.winter2014.utilities.MyLib;

public class UserListVM implements ConfirmResponse {

	private Center centerArea;
	private DataFilter dataFilter = new DataFilter();

	@Wire("#filterName")
	private Textbox filterName;
	@Wire("#filterAdmin")
	private Checkbox filterAdmin;
	@Wire("#filterAge")
	private Intbox filterAge;
	@Wire("#filterStartDate")
	private Datebox filterStartDate;
	@Wire("#filterEndDate")
	private Datebox filterEndDate;

	@WireVariable
	private CRUDService CRUDService;

	private UserProfile selectedItem;
	private List<UserProfile> allReordsInDB = null;
	private List<UserProfile> userList = null;

	public UserProfile getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(UserProfile selectedItem) {
		this.selectedItem = selectedItem;
	}

	public DataFilter getDataFilter() {
		return dataFilter;
	}

	public void setDataFilter(DataFilter dataFilter) {
		this.dataFilter = dataFilter;
	}

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("centerArea") Center centerArea) {
		Selectors.wireComponents(view, this, false);
		this.centerArea = centerArea;
		CRUDService = (CRUDService) SpringUtil.getBean("CRUDService");
		allReordsInDB = CRUDService.getAll(UserProfile.class);
		userList = new ArrayList<UserProfile>((allReordsInDB));

	}

	public List<UserProfile> getDataSet() {
		return allReordsInDB;
	}

	@Command
	public void onAddNew() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectedRecord", null);
		map.put("recordMode", "NEW");
		map.put("centerArea", centerArea);
		centerArea.getChildren().clear();
		Executions.createComponents("UserCRUD.zul", centerArea, map);
	}

	@Command
	public void onEdit(@BindingParam("userRecord") UserProfile userProfile) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectedRecord", userProfile);
		map.put("recordMode", "EDIT");
		map.put("centerArea", centerArea);
		centerArea.getChildren().clear();
		Executions.createComponents("UserCRUD.zul", centerArea, map);
	}

	@Command
	public void openAsReadOnly(
			@BindingParam("userRecord") UserProfile userProfile) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectedRecord", userProfile);
		map.put("recordMode", "READ");
		map.put("centerArea", centerArea);
		centerArea.getChildren().clear();
		Executions.createComponents("UserCRUD.zul", centerArea, map);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	public void onDelete(@BindingParam("userRecord") UserProfile userProfile) {
		MyLib.confirm("deleteFirstConfirm", "The Selected practice  \""
				+ selectedItem.getName() + "\" will be deleted.?",
				"Confirmation", this);
	}

	@NotifyChange("dataSet")
	@Command
	public void doFilter() {
		allReordsInDB = new ArrayList<UserProfile>();
		for (UserProfile tmp : userList) {
			if ((dataFilter.getName() == null || tmp.getName().toLowerCase()
					.indexOf(dataFilter.getName().toLowerCase()) == 0)
					&& (dataFilter.getAge() == null || tmp.getAge().equals(
							dataFilter.getAge()))
					&& (dataFilter.getAdmin() == null || tmp.getAdmin().equals(
							dataFilter.getAdmin()))
					&& ((dataFilter.getStartDate() == null || tmp
							.getCreateDate().getTime() >= dataFilter
							.getStartDate().getTime()) && (dataFilter
							.getEndDate() == null || tmp.getCreateDate()
							.getTime() <= dataFilter.getEndDate().getTime()))) {
				allReordsInDB.add(tmp);
			}
		}
	}

	@NotifyChange("dataSet")
	@Command
	public void doClearFilter() {
		this.dataFilter.clear();
		filterName.setValue(null);
		filterAdmin.setChecked(false);
		filterAge.setValue(null);
		filterStartDate.setValue(null);
		filterEndDate.setValue(null);
		doFilter();
	}

	@Command
	public void Logout() {
		Executions.sendRedirect("/j_spring_security_logout");
	}

	@Override
	public void onConfirmClick(String code, int button) {
		if (code.equals("deleteFirstConfirm") && button == Messagebox.YES) {
			MyLib.confirm(
					"deleteSecondConfirm",
					"The Selected practice  \""
							+ selectedItem.getName()
							+ "\" will be permanently deleted and the action cannot be undone..?",
					"Confirmation", this);
		}
		if (code.equals("deleteSecondConfirm") && button == Messagebox.YES) {

			CRUDService.delete(selectedItem);

			allReordsInDB.remove(allReordsInDB.indexOf(selectedItem));
			userList.remove(userList.indexOf(selectedItem));
			BindUtils.postNotifyChange(null, null, UserListVM.this, "dataSet");

		}
	}

}