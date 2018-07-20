
package com.buffalo.gateway.sys.model;

import com.buffalo.gateway.util.ModelUtil;


public class Menu extends ModelUtil {
    private String menu_id;
	private String name;
	private String route;
	private String type;
	private String parent_id;
	private String icon;
	private String menu_order;

    public Menu() {
        super();
        // TODO Auto-generated constructor stub
    }
	public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMenu_order() {
		return menu_order;
	}

	public void setMenu_order(String menu_order) {
		this.menu_order = menu_order;
	}

	
	
}
