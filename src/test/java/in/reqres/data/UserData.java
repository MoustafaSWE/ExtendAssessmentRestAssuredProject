package in.reqres.data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserData {

    private String name;
    private String job;

    public UserData(final String name, final String job) {
        this.name = name;
        this.job = job;

    }
}
