package team.high5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class User {
    private String userId;
    private String name;
    private String password;

    private boolean isIllegal(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (isIllegal(password)) {
            throw new IllegalArgumentException("Illegal chars in password");
        }

        if (password.equals(this.password)) {
            throw new IllegalArgumentException("Duplicated value");
        }
        this.password = password;
    }
}
