package Chat.professionalchat2;

import java.util.Collections;
import java.util.List;

import com.google.api.client.googleapis.json.GoogleJsonError.ErrorInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ImportUserRecord;
import com.google.firebase.auth.UserImportResult;
import com.google.firebase.auth.UserProvider;

public class Importdat {

	public static void main(String[] args) {
		try {
			  List<ImportUserRecord> users = Collections.singletonList(ImportUserRecord.builder()
			      .setUid("some-uid")
			      .setDisplayName("John Doe")
			      .setEmail("johndoe@gmail.com")
			      .setPhotoUrl("http://www.example.com/12345678/photo.png")
			      .setEmailVerified(true)
			      .setPhoneNumber("+11234567890")
			      .putCustomClaim("admin", true) // set this user as admin
			      .addUserProvider(UserProvider.builder() // user with Google provider
			          .setUid("google-uid")
			          .setEmail("johndoe@gmail.com")
			          .setDisplayName("John Doe")
			          .setPhotoUrl("http://www.example.com/12345678/photo.png")
			          .setProviderId("google.com")
			          .build())
			      .build());
			  UserImportResult result = FirebaseAuth.getInstance().importUsers(users);
			  for (com.google.firebase.auth.ErrorInfo indexedError : result.getErrors()) {
			    System.out.println("Failed to import user: " + indexedError.getReason());
			  }
			} catch (FirebaseAuthException e) {
			  System.out.println("Error importing users: " + e.getMessage());
			}
		

	}

}
