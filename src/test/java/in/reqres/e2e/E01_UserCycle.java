package in.reqres.e2e;

import in.reqres.base.BaseSetup;
import in.reqres.data.UserData;
import in.reqres.data.UserDataFaker;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class E01_UserCycle extends BaseSetup {

    private int userId;
    private final UserDataFaker userDataFake = new UserDataFaker();
    private final UserData userData = userDataFake.userDataFaker();


    @Test (priority = 1)
    public void createUsers() {

        userId = given()
                .body(userData)
                .when()
                .post("/api/users")
                .then()
                .extract()
                .response()
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("name", equalTo(userData.getName()))
                .body("job", equalTo(userData.getJob()))
                .extract()
                .jsonPath()
                .getInt("id");


    }

    @Test (priority = 2)
    public void getSingleUserById (){
        given ().when ()
                .get ("/api/users/"+ userId)
                .then ()
                .statusCode (200)
                .and ()
                .assertThat ()
                .body ("data.first_name", equalTo (userData.getName()))
                .body("data.last_name", equalTo(userData.getJob()));
    }

    @Test (priority = 3)
    public void updateCreatedUser (){
        UserData userData = userDataFake.userDataFaker();
        given().body(userData)
                .when()
                .put("/api/users/" + userId)
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("name", equalTo(userData.getName()))
                .body("job", equalTo(userData.getJob()));
    }

    @Test (priority = 4)
    public void getUsersList (){
        given ().when ()
                .get ("/api/users?page=2")
                .then ()
                .statusCode (200)
                .and ()
                .assertThat ()
                .body ("page", equalTo (2))
                .and ()
                .body ("data[0].first_name", equalTo ("Michael"))
                .body ("data[0].last_name", equalTo ("Lawson"));
    }

    @Test (priority = 5)
    public void deleteRequestTests () {
        given ().when ()
                .delete ("/api/users/" + userId)
                .then ()
                .assertThat ()
                .statusCode (204);
    }

}
