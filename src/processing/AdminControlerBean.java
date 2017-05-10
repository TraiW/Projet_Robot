package processing;

import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.fabric.DaoFabric;
import dao.instance.AdminDao;
import model.LoginBean;
import model.AdminModelBean;

@ManagedBean(name = "AdminControler")
@ApplicationScoped // Utilisation de application scope afin d'offrir un point d'entrée unique à l'ensemble des clients
public class AdminControlerBean {
	private AdminDao AdminDao;
	public AdminControlerBean() {
		this.AdminDao=DaoFabric.getInstance().createAdminDao();
	}
	
	public String checkUser(LoginBean loginBean){
		AdminModelBean admin = this.AdminDao.checkAdmin(loginBean.getLogin(),loginBean.getPwd());
		if(admin!=null){
			//récupère l'espace de mémoire de JSF
			ExternalContext externalContext =FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			//place l'utilisateur dans l'espace de mémoire de JSF
			sessionMap.put("loggedAdmin", admin);
			//redirect the current page
			return "admin.xhtml";
		}else{
			//redirect the current page
			return "adminLogin.xhtml";
		}
	}
}