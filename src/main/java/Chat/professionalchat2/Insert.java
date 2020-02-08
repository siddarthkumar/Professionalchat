
package Chat.professionalchat2;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Insert extends HttpServlet {
	String emailid="sid.kumarroyal@gmail.com";
	String uid="shilpa@123";
	String fullname="Siddarth Kumar";
	String username="Engg";
	String profession="Engineer";
	boolean studying=true;
	String course="b.tech";
	String institute="AKTU";
	int currentyear=4;
	String org=null;;
	double experience=0.0;
	String designition=null;
	String currentlocation="Uttar Pradesh";
	String aboutus="Hello thids is an engineer";
public void init()
{
	FileInputStream serviceAccount = null;
	try {
		serviceAccount = new FileInputStream("service.json");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

			FirebaseOptions options = null;
			try {
				options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl("https://professional-c8f42.firebaseio.com")
				  .build();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			FirebaseApp.initializeApp(options);	
}
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("Professional chat");
		DatabaseReference usersRef = ref.child("users");	
		User usd = new User();
		String jos=req.getParameter("qualification").toString();
		usd.setUid(UUID.randomUUID().toString());
		if(jos.equalsIgnoreCase("study")==true) {

usd.setEmailid(req.getParameter("email").toString());
usd.setFullname(req.getParameter("fullname").toString());
usd.setUsername(req.getParameter("username").toString());
usd.setProfession(req.getParameter("profession").toString());
usd.setStudying(true);
usd.setGender(req.getParameter("gender").toString());
usd.setPassword(req.getParameter("password"));
usd.setPhone(Long.parseLong(req.getParameter("phone")));
usd.setInstitute(req.getParameter("college"));
usd.setCourse(req.getParameter("course").toString());
usd.setCurrentyear(Integer.parseInt(req.getParameter("year").toString()));
usd.setCurrentlocation(req.getParameter("location").toString());
usd.setAboutus(req.getParameter("about").toString());
		}
		else {
			usd.setEmailid(req.getParameter("email").toString());
			usd.setFullname(req.getParameter("fullname").toString());
			usd.setUsername(req.getParameter("username").toString());
			usd.setProfession(req.getParameter("profession").toString());
			usd.setStudying(false);
			usd.setGender(req.getParameter("gender").toString());
			usd.setPassword(req.getParameter("password"));
			usd.setPhone(Long.parseLong(req.getParameter("phone")));
			usd.setOrg(req.getParameter("organization"));
			usd.setDesignition(req.getParameter("post").toString());
			usd.setExperience(Double.parseDouble(req.getParameter("experience").toString()));
			//usd.setCurrentyear(Integer.parseInt(req.getParameter("year").toString()));
			usd.setCurrentlocation(req.getParameter("location").toString());
			usd.setAboutus(req.getParameter("about").toString());	
			
		}
usersRef.child(String.valueOf(usd.getUsername())).child("Userinfo").setValue(usd, null);

res.setStatus(HttpServletResponse.SC_NO_CONTENT);
	System.out.println("person is studying");
	
	
	}
	public void destroy()
	{
		System.out.println("service is over servlet is destroyed!");
	}

}
