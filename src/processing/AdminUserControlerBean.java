package processing;

import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.fabric.DaoFabric;
import dao.instance.AdminDao;
import dao.instance.UserDao;
import model.AdminUserModelBean;
import model.LoginBean;

@ManagedBean(name = "AdminUserControler")
@ApplicationScoped // Utilisation de application scope afin d'offrir un point d'entrée unique à l'ensemble des clients
public class AdminUserControlerBean {
	private UserDao UserDao;
	private AdminDao AdminDao;
	
	public AdminUserControlerBean() {
		this.AdminDao=DaoFabric.getInstance().createAdminDao();
		this.UserDao=DaoFabric.getInstance().createUserDao();

	}
	
	public String checkUser(LoginBean loginBean){
		if(loginBean.getLogin().equals("Admin")||loginBean.getLogin().equals("admin"))//si on est un Admin
		{
			AdminUserModelBean admin = this.AdminDao.checkAdmin(loginBean.getLogin(),loginBean.getPwd());
			if(admin!=null){
				//récupère l'espace de mémoire de JSF
				ExternalContext externalContext =FacesContext.getCurrentInstance().getExternalContext();
				Map<String, Object> sessionMap = externalContext.getSessionMap();
				//place l'utilisateur dans l'espace de mémoire de JSF
				sessionMap.put("loggedAdmin", admin);
				//redirect the current page
				return "admin.xhtml";
			}
			else{
				//redirect the current page
				return "login.xhtml";
			}
		}
		else{//si on est un user
			System.out.println(loginBean.getLogin()+" "+loginBean.getPwd());
			AdminUserModelBean user = this.UserDao.checkUser(loginBean.getLogin(),loginBean.getPwd());
			if(user!=null){
				//récupère l'espace de mémoire de JSF
				ExternalContext externalContext =FacesContext.getCurrentInstance().getExternalContext();
				Map<String, Object> sessionMap = externalContext.getSessionMap();
				//place l'utilisateur dans l'espace de mémoire de JSF
				sessionMap.put("loggedUser", user);
				//redirect the current page
				return "cmd.xhtml";
			}
			else{
				//redirect the current page
				return "login.xhtml";
			}
			
		}

			
	}
	
	public String goStart()
		{
			return "cmd.xhtml";
		}
	public String goStop()
		{
			return "login.xhtml";
		}
}