package ru.javarush.winter2014.zkoss;

import org.zkoss.bind.BindComposer;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.util.*;
import org.zkoss.zk.ui.ext.*;
import org.zkoss.zk.au.*;
import org.zkoss.zk.au.out.*;
import org.zkoss.zul.*;

public class MyController extends BindComposer { //GenericForwardComposer
	protected Checkbox admin;
	//protected Label response;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		//admin.setAttribute("checked", false);
	}
}
