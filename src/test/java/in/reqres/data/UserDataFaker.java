package in.reqres.data;

import com.github.javafaker.Faker;

public class UserDataFaker {
    public UserData userDataFaker (){

        Faker fake = Faker.instance();
        return UserData.builder()
                .name(fake.name()
                        .firstName())
                .job(fake.job()
                        .field())
                .build();
    }
}
