package ru.javarush.winter2014.zkoss;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

public class IndexVM {

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {

		Executions.sendRedirect("main.zul");
	}

}
